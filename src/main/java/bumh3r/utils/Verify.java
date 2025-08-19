package bumh3r.utils;

public class Verify {
    public static void main(String[] args) {
        try {
//            Parser parser = new Parser(new Lexer(new FileReader("src/main/java/bumh3r/utils/Entrada.txt")));
//            parser.parse();
            System.out.println("Analisis Completo");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
