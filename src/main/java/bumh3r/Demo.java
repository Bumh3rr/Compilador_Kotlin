package bumh3r;

import bumh3r.components.FontPublicaSans;
import bumh3r.view.FormEditor;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.util.SystemInfo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class Demo extends JFrame {
    public Demo() {
        super("Editor");
        System.setProperty("flatlaf.animation", "true");

        System.setProperty("apple.awt.application.appearance", "system");
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("apple.awt.application.name", "Editor");
        System.setProperty("apple.awt.application.appearance", "system");
        getRootPane().putClientProperty("apple.awt.windowTitleVisible", true);
        getRootPane().putClientProperty("apple.awt.fullscreenable", true);

        if (SystemInfo.isMacFullWindowContentSupported) {
            getRootPane().putClientProperty("apple.awt.fullWindowContent", true);
            getRootPane().putClientProperty("apple.awt.transparentTitleBar", true);
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1400, 823));
        setForeground(Color.WHITE);
        setLocationRelativeTo(null);

        FormEditor form = new FormEditor();
        form.installController();
        form.formInit();
        setContentPane(form);
    }

    public static void main(String[] args) {
        FlatLaf.registerCustomDefaultsSource("themes");
        FlatMacDarkLaf.setup();
        UIManager.put("defaultFont", FontPublicaSans.getInstance().getFont(FontPublicaSans.FontType.MEDIUM, 13f));
        EventQueue.invokeLater(() -> new Demo().setVisible(true));

    }
}