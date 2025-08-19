package bumh3r.system;

import bumh3r.components.ScrollPaneComponent;
import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import lombok.Getter;
import lombok.Setter;
import net.miginfocom.swing.MigLayout;

@Setter
@Getter
public class Form extends JPanel {
    private LookAndFeel oldTheme = UIManager.getLookAndFeel();
    private Runnable eventFormInit;
    private Runnable eventFormOpen;
    private Runnable eventFormRefresh;

    public void formInit() {
    }

    public void installController() {
    }

    public JComponent createHeader(String title, String description, int size) {
        JPanel panel = new JPanel(new MigLayout("fillx,wrap,insets 0 10 0 10", "[fill]","[fill]"));
        panel.add(createTitle(title, JLabel.LEFT, (4 - size)));
        panel.add(createdSubTitle(description));
        return panel;
    }

    private JComponent createdSubTitle(String str){
        JTextArea text = new JTextArea(str);
        text.putClientProperty(FlatClientProperties.STYLE,
                "background:$Panel.background;"
                        + "[light]foreground:lighten(@foreground,30%);"
                        + "[dark]foreground:darken(@foreground,30%)");
        text.setEditable(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setBorder(BorderFactory.createEmptyBorder());
        ScrollPaneComponent scrollPaneComponent = new ScrollPaneComponent(text);
        scrollPaneComponent.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "border:0,0,0,0;"
                + "thumbInsets:0,0,0,0;"
                + "width:0;"
        );
        return scrollPaneComponent;
    }

    public JLabel createTitle(String str, int horizontalAlignment, int fontSize) {
        JLabel label = new JLabel(str);
        label.setText(str);
        label.setHorizontalAlignment(horizontalAlignment);
        label.setAlignmentX(LEFT_ALIGNMENT);
        label.putClientProperty(FlatClientProperties.STYLE, ""
                    + "font:bold +" + fontSize);
        return label;
    }

}
