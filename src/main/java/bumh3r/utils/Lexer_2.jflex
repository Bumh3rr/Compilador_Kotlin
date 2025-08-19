package bumh3r.utils;
import java_cup.runtime.Symbol;

%%
%class Lexer_2
%unicode
%line
%column
%cup
%public
%standalone

%{
    public Symbol getSymbol(int value) {
         return new Symbol(value, yyline, yycolumn, yytext());
    }
%}

numeros = [0-9]+
numeroDecimal = [0-9]+\.[0-9]+
identificador = [a-zA-Z_][a-zA-Z0-9_]*
cadena = \"([^\"\\]|\\.)*\"
cadenaTriple = \"\"\"([^\"\\]|\\.|\"[^\"]|\"\"[^\"])*\"\"\"
tipoKotlin = "Int"|"Double"|"Float"|"Char"|"Boolean"|"String"|"Long"|"Short"|"Byte"|"Unit"|"Any"|"Nothing"
comentario_linea = "//".* | "//".*[\n\r]
comentario_bloque = "/*"([^*]|\*+[^*/])*\*+"/"
%%

// Palabras reservadas Kotlin
"package"        {  return getSymbol(sym.PACKAGE); }
"import"         {  return getSymbol(sym.IMPORT); }
"class"          {  return getSymbol(sym.CLASS); }
"object"         {  return getSymbol(sym.OBJECT); }
"interface"      {  return getSymbol(sym.INTERFACE); }
"data"           {  return getSymbol(sym.DATA); }
"sealed"         {  return getSymbol(sym.SEALED); }
"abstract"       {  return getSymbol(sym.ABSTRACT); }
"enum"           {  return getSymbol(sym.ENUM); }
"in"             { return getSymbol(sym.IN_RANGE); }

// Modificadores
"public"         {  return getSymbol(sym.PUBLIC); }
"private"        {  return getSymbol(sym.PRIVATE); }
"protected"      {  return getSymbol(sym.PROTECTED); }
"internal"       {  return getSymbol(sym.INTERNAL); }
"open"           {  return getSymbol(sym.OPEN); }
"final"          {  return getSymbol(sym.FINAL); }

// Palabras clave
"fun"            {  return getSymbol(sym.FUN); }
"val"            {  return getSymbol(sym.VAL); }
"var"            {  return getSymbol(sym.VAR); }
"const"          {  return getSymbol(sym.CONST); }
"lateinit"       {  return getSymbol(sym.LATEINIT); }

// Control de flujo
"if"             {  return getSymbol(sym.IF); }
"else"           {  return getSymbol(sym.ELSE); }
"when"           {  return getSymbol(sym.WHEN); }
"for"            {  return getSymbol(sym.FOR); }
"while"          {  return getSymbol(sym.WHILE); }
"do"             {  return getSymbol(sym.DO); }
"break"          {  return getSymbol(sym.BREAK); }
"continue"       {  return getSymbol(sym.CONTINUE); }
"return"         {  return getSymbol(sym.RETURN); }

// Manejo de excepciones
"try"            {  return getSymbol(sym.TRY); }
"catch"          {  return getSymbol(sym.CATCH); }
"finally"        {  return getSymbol(sym.FINALLY); }
"throw"          {  return getSymbol(sym.THROW); }

// Valores booleanos y null
"true"           {  return getSymbol(sym.TRUE); }
"false"          {  return getSymbol(sym.FALSE); }
"null"           {  return getSymbol(sym.NULL); }


// Tipos de datos Kotlin
{tipoKotlin}     {  return getSymbol(sym.TIPO_DATOS); }

// Identificadores
{identificador}  {  return getSymbol(sym.IDENTIFICADOR); }

// Números
{numeroDecimal}  {  return getSymbol(sym.NUMERO_DECIMAL); }
{numeros}        {  return getSymbol(sym.NUMERICO); }

// Cadenas
{cadenaTriple}   {  return getSymbol(sym.CADENA); }
{cadena}         {  return getSymbol(sym.CADENA); }

// Delimitadores
"="              {  return getSymbol(sym.ASIGNACION); }
";"              {  return getSymbol(sym.PUNTO_Y_COMA); }
":"              {  return getSymbol(sym.DOS_PUNTOS); }
"("              {  return getSymbol(sym.PARENTESIS_APERTURA); }
")"              {  return getSymbol(sym.PARENTESIS_CIERRE); }
"{"              {  return getSymbol(sym.LLAVE_APERTURA); }
"}"              {  return getSymbol(sym.LLAVE_CIERRE); }
"["              {  return getSymbol(sym.CORCHETE_APER); }
"]"              {  return getSymbol(sym.CORCHETE_CIERRE); }
","              {  return getSymbol(sym.COMA); }
"."              {  return getSymbol(sym.PUNTO); }

// Operadores aritméticos
"+"              {  return getSymbol(sym.MAS); }
"-"              {  return getSymbol(sym.MENOS); }
"*"              {  return getSymbol(sym.MULTIPLICACION); }
"/"              {  return getSymbol(sym.DIVISION); }
"%"              {  return getSymbol(sym.MODULO); }
"++"             {  return getSymbol(sym.INCREMENTO); }
"--"             {  return getSymbol(sym.DECREMENTO); }

// Operadores relacionales
"=="             {  return getSymbol(sym.IGUAL); }
"!="             {  return getSymbol(sym.DIFERENTE); }
"<"              {  return getSymbol(sym.MENOR); }
">"              {  return getSymbol(sym.MAYOR); }
"<="             {  return getSymbol(sym.MENOR_IGUAL); }
">="             {  return getSymbol(sym.MAYOR_IGUAL); }

// Operadores lógicos
"&&"             {  return getSymbol(sym.AND); }
"||"             {  return getSymbol(sym.OR); }
"!"              {  return getSymbol(sym.NOT); }

// Operadores específicos de Kotlin
"?:"             { return getSymbol(sym.ELVIS); }
"?."             { return getSymbol(sym.SAFE_CALL); }
"?"              { return getSymbol(sym.NULLABLE); }
"->"             { return getSymbol(sym.ARROW); }
".."             { return getSymbol(sym.RANGE); }
"..="            { return getSymbol(sym.RANGE_INCLUSIVE); }
"..!"            { return getSymbol(sym.RANGE_EXCLUSIVE); }


// Comentarios
{comentario_linea}     {  return getSymbol(sym.COMENTARIO); }
{comentario_bloque}    {  return getSymbol(sym.COMENTARIO); }


// Espacios en blanco (ignorar)
[ \t\n\r]+       { /* ignoro espacios en blanco */ }

// Error
.                {
    System.err.println("Error: Caracter no reconocido: " + yytext());
    return getSymbol(sym.error);
}