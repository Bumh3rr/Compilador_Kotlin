package bumh3r.components;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.components.FlatTextArea;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JScrollPane;

public class TextAreaComponent extends FlatTextArea {

    public TextAreaComponent() {
        setEditable(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        putClientProperty(FlatClientProperties.STYLE, "background:$Panel.background;");
    }

    public JComponent createInput() {
        JScrollPane sp = new JScrollPane(this);
        ScrollPaneComponent.applyStylesScroll(sp);
        return sp;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        String text = getText();
        if (text == null || text.isEmpty()) return;

        String[] lines = text.split("\n");
        FontMetrics fm = getFontMetrics(getFont());
        int lineHeight = fm.getHeight();

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if (line.toUpperCase().contains("\"ERROR\"")) {
                int y = (i * lineHeight) + fm.getAscent();

                String upperLine = line.toUpperCase();
                int errorIndex = upperLine.indexOf("\"ERROR\"");

                while (errorIndex != -1) {
                    // Calcular posición X de la palabra ERROR
                    String beforeError = line.substring(0, errorIndex);
                    int x = fm.stringWidth(beforeError);

                        // Dibujar "ERROR" en rojo
                    g.setColor(Color.RED);
                    g.setFont(getFont().deriveFont(Font.BOLD));
                    g.drawString("ERROR", getWidth()-60, y +5);

                    // Buscar siguiente ocurrencia
                    errorIndex = upperLine.indexOf("\"ERROR\"", errorIndex + 1);
                }
            }
        }
    }
}
