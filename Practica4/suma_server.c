/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include "suma.h"

int *suma_1_svc(dupla_int *argp, struct svc_req *rqstp)
{
	static int result;

	printf("El procedimiento ha sido invocado remotamente\n");
	printf("Parametros: %d, %d\n", argp->a, argp->b);
	result = argp->a + argp->b;
	printf("Regresando: %d\n", result);
	return &result;
}
