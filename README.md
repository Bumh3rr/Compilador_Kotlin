# Simulador de Compilador para Kotlin - Proyect
![](images/image-1.png)
Este proyecto corresponde al proyecto final de la materia Tópicos Avanzados de Programación II, desarrollado en Java con el objetivo de construir un simulador de compilador para el lenguaje Kotlin.

El compilador implementa las fases de:
Análisis léxico: reconocimiento de tokens, identificadores, palabras reservadas, operadores y símbolos de Kotlin.
Análisis sintáctico: validación de la estructura gramatical según reglas definidas para el lenguaje.
De esta manera, el proyecto permite detectar errores léxicos y sintácticos en fragmentos de código escritos en Kotlin, simulando el proceso de compilación real.


## Tecnologías utilizadas
- IDE: IntelliJ IDEA
- Lenguaje: Java

Herramientas adicionales:
- JFlex → Generación del analizador léxico (`Tener instalador JFlex previamente`)
- CUP → Generación del analizador sintáctico
- MigLayout → Sistema de layout flexible para la interfaz gráfica
- FlatLaf → Librería para un diseño moderno de la interfaz
- RSyntaxTextArea → Editor de texto con resaltado de sintaxis

## Ejecución
* Tener instalador JFlex previamente (`IMPORTANTE`)
* Abrir en IntelliJ IDEA
* Importar el proyecto como Proyecto Maven.
* Asegurarse de que el JDK 17 (o superior) esté configurado.
* Generar analizadores
* Ejecutar la clase Generador.java para compilar los archivos JFlex y CUP.
* Ejecutar el simulador
* Iniciar la clase principal del proyecto (Demo.java o la clase definida como launcher).

## Test

```kotlin
fun main() {
    val name: String = "Alice" // Immutable variable
    var age: Int = 30         // Mutable variable
    age = 31 // Reassigning a mutable variable
    println("Name: $name, Age: $age")

    val sum = add(5, 3)
    println("Sum: $sum")

    try{
    	greet("Pancho")
    }catch(e: Exception){
    	print(e.message)
    }

}

fun add(a: Int, b: Int): Int {
    return a + b
}

fun greet(name: String) {
    println("Hello, $name!")
}


class Car {
    var brand: String = ""
    var model: String = ""
    var year: Int = 0

    fun start() {
        println("$brand $model is starting.")
    }
}

fun main() {
    val x = 10

    if (x > 5 || x < 10 ) {
        println("x is greater than 5")
    } else {
        println("x is not greater than 5")
    }

    val day = "Monday"
    when (day) {
        "Monday" -> println("Start of the week")
        "Friday" -> println("End of the week")
        else -> println("Mid-week")
    }

    for (i in 1..3) {
        println("Loop iteration: $i")
    }

    val numbers = listOf(1, 2, 3)
    for (num in numbers) {
        println("Number: $num")
    }
}
```
