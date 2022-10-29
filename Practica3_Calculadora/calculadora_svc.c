/*
 Esta es la parte del código donde el servidor recibe los parametros enviados por el cliente
 y desempaqueta y traduce la infomración enviada por el cliente
 */

#include "calculadora.h"
#include <stdio.h>
#include <stdlib.h>
#include <rpc/pmap_clnt.h>
#include <string.h>
#include <memory.h>
#include <sys/socket.h>
#include <netinet/in.h>

#ifndef SIG_PF
#define SIG_PF void(*)(int)
#endif

static void
suma_prog_1(struct svc_req *rqstp, register SVCXPRT *transp)
{
	union {
		dupla_int suma_1_arg;
		dupla_int resta_1_arg;
		dupla_int multiplicacion_1_arg;
		dupla_int division_1_arg;
	} argument;
	char *result;
	xdrproc_t _xdr_argument, _xdr_result;
	char *(*local)(char *, struct svc_req *);

	switch (rqstp->rq_proc) {
	case NULLPROC:
		(void) svc_sendreply (transp, (xdrproc_t) xdr_void, (char *)NULL);
		return;

	case SUMA:
		_xdr_argument = (xdrproc_t) xdr_dupla_int;
		_xdr_result = (xdrproc_t) xdr_int;
		local = (char *(*)(char *, struct svc_req *)) suma_1_svc;
		break;

	case RESTA:
		_xdr_argument = (xdrproc_t) xdr_dupla_int;
		_xdr_result = (xdrproc_t) xdr_int;
		local = (char *(*)(char *, struct svc_req *)) resta_1_svc;
		break;

	case MULTIPLICACION:
		_xdr_argument = (xdrproc_t) xdr_dupla_int;
		_xdr_result = (xdrproc_t) xdr_int;
		local = (char *(*)(char *, struct svc_req *)) multiplicacion_1_svc;
		break;

	case DIVISION:
		_xdr_argument = (xdrproc_t) xdr_dupla_int;
		_xdr_result = (xdrproc_t) xdr_float;
		local = (char *(*)(char *, struct svc_req *)) division_1_svc;
		break;

	default:
		svcerr_noproc (transp);
		return;
	}
	memset ((char *)&argument, 0, sizeof (argument));
	if (!svc_getargs (transp, (xdrproc_t) _xdr_argument, (caddr_t) &argument)) {
		svcerr_decode (transp);
		return;
	}
	result = (*local)((char *)&argument, rqstp);
	if (result != NULL && !svc_sendreply(transp, (xdrproc_t) _xdr_result, result)) {
		svcerr_systemerr (transp);
	}
	if (!svc_freeargs (transp, (xdrproc_t) _xdr_argument, (caddr_t) &argument)) {
		fprintf (stderr, "%s", "unable to free arguments");
		exit (1);
	}
	return;
}

int
main (int argc, char **argv)
{
	register SVCXPRT *transp;

	pmap_unset (SUMA_PROG, SUMA_VERS);

	transp = svcudp_create(RPC_ANYSOCK);
	if (transp == NULL) {
		fprintf (stderr, "%s", "cannot create udp service.");
		exit(1);
	}
	if (!svc_register(transp, SUMA_PROG, SUMA_VERS, suma_prog_1, IPPROTO_UDP)) {
		fprintf (stderr, "%s", "unable to register (SUMA_PROG, SUMA_VERS, udp).");
		exit(1);
	}

	transp = svctcp_create(RPC_ANYSOCK, 0, 0);
	if (transp == NULL) {
		fprintf (stderr, "%s", "cannot create tcp service.");
		exit(1);
	}
	if (!svc_register(transp, SUMA_PROG, SUMA_VERS, suma_prog_1, IPPROTO_TCP)) {
		fprintf (stderr, "%s", "unable to register (SUMA_PROG, SUMA_VERS, tcp).");
		exit(1);
	}

	svc_run ();
	fprintf (stderr, "%s", "svc_run returned");
	exit (1);
	/* NOTREACHED */
}
