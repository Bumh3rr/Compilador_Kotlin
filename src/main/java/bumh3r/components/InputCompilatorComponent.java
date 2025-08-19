package bumh3r.components;

import java.awt.Color;
import javax.swing.UIManager;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rtextarea.RTextScrollPane;

public class InputCompilatorComponent extends RSyntaxTextArea {

    public InputCompilatorComponent(int row, int column) {
        super(row, column);
        setBackground(UIManager.getColor("Panel.background"));
        setFont(FontPublicaSans.getInstance().getFont(FontPublicaSans.FontType.MEDIUM,15f));
        setForeground(Color.getColor("#FFFFFF"));
        setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_KOTLIN);
        setCodeFoldingEnabled(true);
        setAntiAliasingEnabled(true);
        setCloseCurlyBraces(true);
        setCurrentLineHighlightColor(UIManager.getColor("Panel.background"));
        applyStyles();
    }

    public void applyStyles() {
        SyntaxScheme scheme = getSyntaxScheme();
        scheme.getStyle(Token.RESERVED_WORD).foreground = Color.decode("#FE78C3");
        scheme.getStyle(Token.RESERVED_WORD_2).foreground = Color.decode("#FE78C3");
        scheme.getStyle(Token.DATA_TYPE).foreground = Color.decode("#FE78C3");
        scheme.getStyle(Token.COMMENT_EOL).foreground = Color.gray;
        scheme.getStyle(Token.COMMENT_MULTILINE).foreground = Color.gray;
        scheme.getStyle(Token.COMMENT_DOCUMENTATION).foreground = Color.gray;
        scheme.getStyle(Token.SEPARATOR).foreground = Color.WHITE;
        scheme.getStyle(Token.OPERATOR).foreground = Color.decode("#00CFFF");
        scheme.getStyle(Token.LITERAL_STRING_DOUBLE_QUOTE).foreground = Color.decode("#2CCF00");
        scheme.getStyle(Token.LITERAL_BOOLEAN).foreground = Color.decode("#2CCF00");
        scheme.getStyle(Token.LITERAL_NUMBER_DECIMAL_INT).foreground = Color.decode("#C997FB");
        scheme.getStyle(Token.LITERAL_NUMBER_FLOAT).foreground = Color.decode("#C997FB");
        scheme.getStyle(Token.VARIABLE).foreground = Color.decode("#9876AA");
        scheme.getStyle(Token.ERROR_CHAR).foreground = Color.RED;
        scheme.getStyle(Token.FUNCTION).foreground = Color.WHITE;
        revalidate();
        repaint();
    }

    public RTextScrollPane createdInput() {
        RTextScrollPane sp = new RTextScrollPane(this);
        ScrollPaneComponent.applyStylesScroll(sp);
        sp.setLineNumbersEnabled(true);
        sp.setFoldIndicatorEnabled(false);
        return sp;
    }
}
