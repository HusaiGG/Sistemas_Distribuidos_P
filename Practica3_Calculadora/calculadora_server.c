/*
 Esta es la parte del codigo que originalmente fue una plantilla creada por el rpc para que podamos utilizar los valores enviados por
 el cliente.
 */

#include "calculadora.h"

int *suma_1_svc(dupla_int *argp, struct svc_req *rqstp)
{
	static int result;

	result = argp->a + argp->b;
	printf("Suma: %d + %d = %d\n", argp->a, argp->b, result);

	return &result;
}

int *resta_1_svc(dupla_int *argp, struct svc_req *rqstp)
{
	static int result;

	result = argp->a - argp->b;
	printf("Resta: %d - %d = %d\n", argp->a, argp->b, result);

	return &result;
}

int *multiplicacion_1_svc(dupla_int *argp, struct svc_req *rqstp)
{
	static int result;

	result = argp->a * argp->b;
	printf("Multiplicacion: %d * %d = %d\n", argp->a, argp->b, result);

	return &result;
}

float *
division_1_svc(dupla_int *argp, struct svc_req *rqstp)
{
	static float result;

	result = (float)(argp->a) / (float)(argp->b);
	printf("Division: %d / %d = %f\n", argp->a, argp->b, result);

	return &result;
}
