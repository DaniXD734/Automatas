
%{
#include <stdio.h>
%}

%option noyywrap

%%

"int"|"float"|"char"|"if"|"else"|"while"|"return" {
    fprintf(yyout, "PALABRA RESERVADA: %s\n", yytext);
}

[0-9]+ {
    fprintf(yyout, "NUMERO: %s\n", yytext);
}

[a-zA-Z_][a-zA-Z0-9_]* {
    fprintf(yyout, "IDENTIFICADOR: %s\n", yytext);
}

[+\-*/=<>] {
    fprintf(yyout, "OPERADOR: %s\n", yytext);
}

[;(),{}] {
    fprintf(yyout, "SIMBOLO: %s\n", yytext);
}

[ \t\n]+ {
    /* Ignorar espacios */
}

. {
    fprintf(yyout, "CARACTER DESCONOCIDO: %s\n", yytext);
}

%%

int main(void) {
    yyin = fopen("programa.txt", "r");
    yyout = fopen("out.txt", "w");

    if (!yyin) {
        perror("No se pudo abrir programa.txt");
        return 1;
    }

    if (!yyout) {
        perror("No se pudo abrir out.txt");
        fclose(yyin);
        return 1;
    }

    yylex();

    fclose(yyin);
    fclose(yyout);

    return 0;
}
