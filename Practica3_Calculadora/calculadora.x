struct dupla_int { int a;
int b;
};


program SUMA_PROG{ version SUMA_VERS {
int SUMA(dupla_int) = 1;
int RESTA(dupla_int) = 2;
int MULTIPLICACION(dupla_int) = 3;
float DIVISION(dupla_int) = 4;
} = 1;
} = 0x23451111;


