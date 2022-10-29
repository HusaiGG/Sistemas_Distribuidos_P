/*
Esta es la parte del rpc en donde el cliente empaqueta los parametros que va a utilizar el servidor
como su ip, protocolo y valores para la calculadora
*/

#include "calculadora.h"

void suma_prog_1(char *host, int a, int b)
{
	CLIENT *clnt;
	int *result_1;
	dupla_int suma_1_arg;
	int *result_2;
	dupla_int resta_1_arg;
	int *result_3;
	dupla_int multiplicacion_1_arg;
	float *result_4;
	dupla_int division_1_arg;

#ifndef DEBUG
	//Cambiar udp por el protocolo deseado para conectarse entre diferentes redes
	//cambiar la variable host por una variable que reciba una direccion ip diferente a local host
	clnt = clnt_create(host, SUMA_PROG, SUMA_VERS, "udp");
	if (clnt == NULL)
	{
		clnt_pcreateerror(host);
		exit(1);
	}
#endif /* DEBUG */
	// suma
	suma_1_arg.a = a;
	suma_1_arg.b = b;
	// resta
	resta_1_arg.a = a;
	resta_1_arg.b = b;

	// multiplicacion
	multiplicacion_1_arg.a = a;
	multiplicacion_1_arg.b = b;
	// division
	division_1_arg.a = a;
	division_1_arg.b = b;

	result_1 = suma_1(&suma_1_arg, clnt);
	if (result_1 == (int *)NULL)
	{
		clnt_perror(clnt, "call failed");
	}
	else
	{
		printf("Resultado suma =  %d\n", *result_1);
	}
	result_2 = resta_1(&resta_1_arg, clnt);
	if (result_2 == (int *)NULL)
	{
		clnt_perror(clnt, "call failed");
	}
	else
	{
		printf("Resultado resta =  %d\n", *result_2);
	}
	result_3 = multiplicacion_1(&multiplicacion_1_arg, clnt);
	if (result_3 == (int *)NULL)
	{
		clnt_perror(clnt, "call failed");
	}
	else
	{
		printf("Resultado multiplicacion =  %d\n", *result_3);
	}
	result_4 = division_1(&division_1_arg, clnt);
	if (result_4 == (float *)NULL)
	{
		clnt_perror(clnt, "call failed");
	}
	else
	{
		printf("Resultado division =  %f\n", *result_4);
	}
#ifndef DEBUG
	clnt_destroy(clnt);
#endif /* DEBUG */
}

int main(int argc, char *argv[])
{
	char *host;
	int a, b;

	if (argc != 4)
	{
		printf("usage: %s server_host\n", argv[0]);
		exit(1);
	}
	host = argv[1];

	if ((a = atoi(argv[2])) == 0 && *argv[2] != '0')
	{
		fprintf(stderr, "invalid value: %s\n", argv[2]);
		exit(1);
	}
	if ((b = atoi(argv[3])) == 0 && *argv[3] != '0')
	{
		fprintf(stderr, "invalid value: %s\n", argv[3]);
		exit(1);
	}
	suma_prog_1(host, a, b);
	exit(0);
}
