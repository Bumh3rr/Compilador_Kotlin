package bumh3r.utils;

import java.io.File;

public class Generate {
    public static void main(String[] args) {
        final String ruta = "src/main/java/bumh3r/utils/Lexer_2.jflex";
        try {
            File archivo = new File(ruta);
            if (!archivo.exists()) throw new Exception("Archivo no encontrado");

            jflex.Main.generate(archivo);
            System.out.println("Analizador léxico generado correctamente");

            //if (true) return;
            String[] opciones = {
                    "-destdir", "src/main/java/bumh3r/utils", // Carpeta destino
                    "-parser", "Parser",          // Nombre del parser
                    "-symbols", "sym",
                    "src/main/java/bumh3r/utils/Gramatica.cup" // Archivo CUP
            };
            java_cup.Main.main(opciones); // <- Generación del parser CUP
            System.out.println("Analizador sintáctico generado correctamente");
        } catch (Exception e) {
            System.out.println("Error al generar el parser CUP: " + e.getMessage());
        }
    }
}
