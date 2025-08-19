package bumh3r.utils;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;

%%
%class Lexer
%unicode
%line
%column
%cup
%public
%standalone

%{
    public int contador; // Contador de Tokens
    public LinkedHashMap<Integer, List<Token>> arrays; // almacenar los tokens

    public void showToken(Type categoria) {
         int numToken = contador;
         int linea = (yyline + 1);
         String valueText =  yytext();
         String categoriaText = categoria.name();

         Token token = new Token(numToken,valueText,categoriaText,linea);
         add(linea,token); // <- guardar token hashmap
         System.out.print(token); // <- imprimir token

         contador++;
    }

    public LinkedHashMap<Integer, List<Token>> getToken(){
        return arrays;
    }

    public void add(int index, Token value) {
        if (arrays.containsKey(index)) {
            arrays.get(index).add(value);
        }else {
            List<Token> list = new ArrayList<>();
            list.add(value);
            arrays.put(index,list);
        }
    }

    public void showError(Type categoria) {
        System.err.printf("Token #%d Texto: \'%s\' Categoría: \"%s\" Línea: %d\n",
        contador,
        yytext(),
        categoria.name(),
        (yyline + 1));
    }

    public enum Type{
        RESERVADA,
        TIPO_DATOS,
        PUNTO_Y_COMA,
        PARENTESIS_APERTURA,
        PARENTESIS_CIERRE,
        LLAVE_APERTURA,
        LLAVE_CIERRE,
        ASIGNACION,
        COMA,
        ERROR,
        NUMERICO,
        IDENTIFICADOR,
        CADENA,
        INCREMENTO,
        MODULO,
        PUNTO,
        COMENTARIO,
        OPERADOR_ARITMETICO,
        OPERADOR_RELACIONAL,
        NUMERO_DECIMAL,
        CORCHETE_APER,
        CORCHETE_CIERRE,
        BOOLEANO,
        DOS_PUNTOS,
        OPERADOR_ELVIS,
        OPERADOR_SAFE_CALL,
        TIPO_NULLABLE,
        ARROW,
        RANGE
    }
%}

%init{
    contador = 1;
    arrays = new LinkedHashMap<>();
%init}

numeros = [0-9]+
numeroDecimal = [0-9]+\.[0-9]+
identificador = [a-zA-Z_][a-zA-Z0-9_]*
cadena = \"([^\"\\]|\\.)*\"
cadenaTriple = \"\"\"([^\"\\]|\\.|\"[^\"]|\"\"[^\"])*\"\"\"
tipoKotlin = "Int"|"Double"|"Float"|"Char"|"Boolean"|"String"|"Long"|"Short"|"Byte"|"Unit"|"Any"|"Nothing"
comentario_linea = "//".*
comentario_bloque = "/*"([^*]|\*+[^*/])*\*+"/"
%%

// Palabras reservadas Kotlin
"package"        {  showToken(Type.RESERVADA); }
"import"         {  showToken(Type.RESERVADA); }
"class"          {  showToken(Type.RESERVADA); }
"object"         {  showToken(Type.RESERVADA); }
"interface"      {  showToken(Type.RESERVADA); }
"data"           {  showToken(Type.RESERVADA); }
"sealed"         {  showToken(Type.RESERVADA); }
"abstract"       {  showToken(Type.RESERVADA); }
"enum"           {  showToken(Type.RESERVADA); }

// Modificadores
"public"         {  showToken(Type.RESERVADA); }
"private"        {  showToken(Type.RESERVADA); }
"protected"      {  showToken(Type.RESERVADA); }
"internal"       {  showToken(Type.RESERVADA); }
"open"           {  showToken(Type.RESERVADA); }
"final"          {  showToken(Type.RESERVADA); }

// Palabras clave
"fun"            {  showToken(Type.RESERVADA); }
"val"            {  showToken(Type.RESERVADA); }
"var"            {  showToken(Type.RESERVADA); }
"const"          {  showToken(Type.RESERVADA); }
"lateinit"       {  showToken(Type.RESERVADA); }

// Control de flujo
"if"             {  showToken(Type.RESERVADA); }
"else"           {  showToken(Type.RESERVADA); }
"when"           {  showToken(Type.RESERVADA); }
"for"            {  showToken(Type.RESERVADA); }
"while"          {  showToken(Type.RESERVADA); }
"do"             {  showToken(Type.RESERVADA); }
"break"          {  showToken(Type.RESERVADA); }
"continue"       {  showToken(Type.RESERVADA); }
"return"         {  showToken(Type.RESERVADA); }

// Manejo de excepciones
"try"            {  showToken(Type.RESERVADA); }
"catch"          {  showToken(Type.RESERVADA); }
"finally"        {  showToken(Type.RESERVADA); }
"throw"          {  showToken(Type.RESERVADA); }

// Valores booleanos y null
"true"           {  showToken(Type.BOOLEANO); }
"false"          {  showToken(Type.BOOLEANO); }
"null"           {  showToken(Type.RESERVADA); }


// Tipos de datos Kotlin
{tipoKotlin}     {  showToken(Type.TIPO_DATOS); }

// Identificadores
{identificador}  {  showToken(Type.IDENTIFICADOR); }

// Números
{numeroDecimal}  {  showToken(Type.NUMERO_DECIMAL); }
{numeros}        {  showToken(Type.NUMERICO); }

// Cadenas
{cadenaTriple}   {  showToken(Type.CADENA); }
{cadena}         {  showToken(Type.CADENA); }

// Delimitadores
"="              {  showToken(Type.ASIGNACION); }
";"              {  showToken(Type.PUNTO_Y_COMA); }
":"              {  showToken(Type.DOS_PUNTOS); }
"("              {  showToken(Type.PARENTESIS_APERTURA); }
")"              {  showToken(Type.PARENTESIS_CIERRE); }
"{"              {  showToken(Type.LLAVE_APERTURA); }
"}"              {  showToken(Type.LLAVE_CIERRE); }
"["              {  showToken(Type.CORCHETE_APER); }
"]"              {  showToken(Type.CORCHETE_CIERRE); }
","              {  showToken(Type.COMA); }
"."              {  showToken(Type.PUNTO); }

// Operadores aritméticos
"+"              {  showToken(Type.OPERADOR_ARITMETICO); }
"-"              {  showToken(Type.OPERADOR_ARITMETICO); }
"*"              {  showToken(Type.OPERADOR_ARITMETICO); }
"/"              {  showToken(Type.OPERADOR_ARITMETICO); }
"%"              {  showToken(Type.MODULO); }
"++"             {  showToken(Type.INCREMENTO); }
"--"             {  showToken(Type.INCREMENTO); }

// Operadores relacionales
"=="             {  showToken(Type.OPERADOR_RELACIONAL); }
"!="             {  showToken(Type.OPERADOR_RELACIONAL); }
"<"              {  showToken(Type.OPERADOR_RELACIONAL); }
">"              {  showToken(Type.OPERADOR_RELACIONAL); }
"<="             {  showToken(Type.OPERADOR_RELACIONAL); }
">="             {  showToken(Type.OPERADOR_RELACIONAL); }

// Operadores específicos de Kotlin
"?:"             { showToken(Type.OPERADOR_ELVIS); }
"?."             { showToken(Type.OPERADOR_SAFE_CALL); }
"?"              { showToken(Type.TIPO_NULLABLE); }
"->"             { showToken(Type.ARROW); }
".."             { showToken(Type.RANGE); }
"..="            { showToken(Type.RANGE); }
"..!"            { showToken(Type.RANGE); }
"in"             { showToken(Type.RANGE); }

// Operadores lógicos
"&&"             {  showToken(Type.OPERADOR_RELACIONAL); }
"||"             {  showToken(Type.OPERADOR_RELACIONAL); }
"!"              {  showToken(Type.OPERADOR_RELACIONAL); }

// Comentarios
{comentario_linea}     {  showToken(Type.COMENTARIO); }
{comentario_bloque}    {  showToken(Type.COMENTARIO); }


// Espacios en blanco (ignorar)
[ \t\n\r]+       { /* ignoro espacios en blanco */ }

// Error
.                {
    showToken(Type.ERROR);
}