// Recibe una cadena del Cliente y se la devuelve en mayusculas
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <sys/time.h>
#include <netinet/in.h>
#include <sys/errno.h>
#include <netdb.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#define MAX_LINE 120
extern int errno;
int main()
{
    char send_num[MAX_LINE];
    struct sockaddr_in lsock, fsock, sname;
    int s, ss;
    int len, i;
    char buf[MAX_LINE];
    if ((s = socket(AF_INET, SOCK_STREAM, 0)) < 0)
    {
        perror("SOCKET: ");
        exit(0);
    }
    lsock.sin_family = AF_INET;
    lsock.sin_port = htons(5000);              /* puerto para dar el servicio */
    lsock.sin_addr.s_addr = htonl(INADDR_ANY); /* direccion IP de mi maquina servidora */
    if (bind(s, (struct sockaddr *)&lsock, sizeof(struct sockaddr_in)) < 0)
    {
        perror("BIND: ");
        exit(1);
    }
    if (listen(s, 3) < 0)
    {
        perror("LISTEN: ");
        exit(1);
    }
    while (1)
    {
        len = sizeof(struct sockaddr_in); /* &len: entra y sale el tamano del socket esperado */
        if ((ss = accept(s, (struct sockaddr *)&fsock, &len)) < 0)
        {
            perror("ACCEPT: ");
            continue;
        }
        printf("Conexion en el socket %d (antes %d)\n", ss, s);
        if (fork() == 0)
        {
            /* Aqui se ejecuta el proceso hijo */
            /* Cierra el socket incompleto */
            /* se dedica a atender la conexion con el socket completo */
            close(s);
            while (1)
            {
                if ((len = recv(ss, buf, MAX_LINE - 1, 0)) <= 0)
                {
                    perror("RECV: "); /* Si len==0 entonces el cliente cerro la conexion */
                    exit(1);
                }
                for (i = 0; i < len; i++)
                { /* Despliega y transforma a Mayusculas */
                    int num = atoi(buf) + 1;
                    sprintf(send_num, "%d", num);
                    /*putchar(buf[i]);
                    if (buf[i] >= 'a' && buf[i] <= 'z')
                        buf[i] = 'A' + (buf[i] - 'a');
                    */
                }
                putchar('\n');
                if (buf[0] == '0')
                {
                    printf("Termina el servicio por decision del Cliente\n");
                    close(ss);
                    exit(0); /* el proceso hijo se mata */
                }
                if (send(ss, send_num, len, 0) < len) /* responde al cliente */
                    perror("SEND: ");
            }          /*while */
        }              /* if fork */
        else           /* Aqui continua el proceso vigia para aceptar otra conexion */
            close(ss); /* el padre cierra el socket completo que dejo al hijo */
    }                  /*while*/
    return 0;
}