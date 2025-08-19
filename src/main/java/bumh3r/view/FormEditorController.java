package bumh3r.view;

import bumh3r.utils.Lexer;
import bumh3r.utils.Lexer_2;
import bumh3r.utils.Parser;
import bumh3r.utils.Token;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FormEditorController {
    private FormEditor view;

    public FormEditorController(FormEditor view) {
        this.view = view;
        this.view.installEvents(this::run, this::clean, this::importCode);
    }

    public void run() {
        try {
            String code = view.getTextAreaCompilador().getText().strip(); // <- Obtener el código del compilador
            if (code.isEmpty()) {
                showMessage("El código esta vació, Invalido compilar el código", JOptionPane.ERROR_MESSAGE);
                return;
            }
            StringBuilder lexico = getResponseLexico(code); // <- Obtener los tokens léxicos
            SwingUtilities.invokeLater(() -> view.getTextAreaLexico().setText(lexico.toString())); // <- Mostrar en la vista

            Parser parser = new Parser(new Lexer_2(new StringReader(code)));
            try {
                parser.parse();
                view.getTextAreaSintactico().setForeground(Color.GREEN);
                view.getTextAreaSintactico().setText("Análisis sintáctico realizado correctamente");
            } catch (Exception e) {
                String sym = parser.getError();
                view.getTextAreaSintactico().setText(sym);
                view.getTextAreaSintactico().setForeground(Color.RED);
            }

        } catch (Exception e) {
            showMessage("Error al analizar el código: " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
            log.error("Error al analizar el código: {}", e.getMessage(), e);
        }
    }

    public void clean() {
        view.getTextAreaCompilador().setText("");
        view.getTextAreaLexico().setText("");
        view.getTextAreaSintactico().setText("");
    }

    public void importCode() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("/Users/bumh3r/Documents/GitHub/Programming/Java/Automatas_II/Proyect_Final_Automatas_II"));
        int returnValue = fileChooser.showOpenDialog(view);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                String code = new String(Files.readAllBytes(fileChooser.getSelectedFile().toPath()));
                clean();
                view.getTextAreaCompilador().setText(code);
            } catch (IOException e) {
                showMessage("Error al leer el archivo:\n" + e.getMessage(), JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void showMessage(String message, int messageType) {
        JOptionPane.showMessageDialog(view, message, "Message", messageType);
    }

    private StringBuilder getResponseLexico(String code) throws IOException {
        Lexer lexer_lexico = new Lexer(new StringReader(code));
        lexer_lexico.next_token();

        LinkedHashMap<Integer, List<Token>> mapTokens = lexer_lexico.getToken();
        StringBuilder StringTokens = new StringBuilder();

        mapTokens.forEach((integer, listTokens) -> {
            StringBuilder str = new StringBuilder();
            listTokens.forEach((token1 -> str.append(token1.toString())));
            String format = String.format("Línea: %d\n%s", integer, str);
            StringTokens.append(format).append("\n");
        });
        return StringTokens;
    }

}
