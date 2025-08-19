package bumh3r.view;

import bumh3r.components.ButtonAccentBase;
import bumh3r.components.InputCompilatorComponent;
import bumh3r.components.SplitPanelComponent;
import bumh3r.components.TextAreaComponent;
import bumh3r.system.Form;
import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import lombok.Getter;
import net.miginfocom.swing.MigLayout;

public class FormEditor extends Form {
    private JButton button_run, button_clean, button_read;
    @Getter
    private InputCompilatorComponent textAreaCompilador;
    @Getter
    private TextAreaComponent textAreaLexico, textAreaSintactico;
    private FormEditorController controller;

    @Override
    public void installController() {
        this.controller = new FormEditorController(this);
    }

    public void installEvents(Runnable eventRun, Runnable eventClean, Runnable eventRead) {
        button_run.addActionListener(e -> eventRun.run());
        button_clean.addActionListener(e -> eventClean.run());
        button_read.addActionListener(e -> eventRead.run());
    }

    public FormEditor() {
        initComponents();
        init();
    }

    private void initComponents() {
        button_run = new ButtonAccentBase("Run", "#00B505");
        button_read = new ButtonAccentBase("Import Code", "#2f6cff");
        button_clean = new ButtonAccentBase("Clean", "#ffb71b");
        textAreaCompilador = new InputCompilatorComponent(20, 40);
        textAreaLexico = new TextAreaComponent();
        textAreaSintactico = new TextAreaComponent();
    }

    private void init() {
        setLayout(new MigLayout("wrap,fill,insets 40 n 10 n", "[fill]", "[grow 0][grow 0][fill]"));
        add(createHeader("Compilator", "Compilador Kotlin", 1), "gapx 5 5");
        add(createdControl(), "growx 0,al center");
        add(createBody(), "grow,push");
    }

    private JComponent createBody() {
        SplitPanelComponent splitPanel_analizadores = new SplitPanelComponent(JSplitPane.VERTICAL_SPLIT);
        splitPanel_analizadores.setTopComponent(createdPanelEditor("Lexico", createdCard(textAreaLexico.createInput())));
        splitPanel_analizadores.setBottomComponent(createdPanelEditor("Sintáctico", createdCard(textAreaSintactico.createInput())));
        splitPanel_analizadores.setDividerLocation(340);

        SplitPanelComponent splitPane = new SplitPanelComponent(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setLeftComponent(createdPanelEditor("Compilador", createdCard(textAreaCompilador.createdInput())));
        splitPane.setRightComponent(splitPanel_analizadores);
        splitPane.setDividerLocation(650);
        return splitPane;
    }

    private JComponent createdCard(JComponent component) {
        JPanel panel = new JPanel(new MigLayout("fillx,insets 10", "fill", "fill"));
        panel.putClientProperty(FlatClientProperties.STYLE, "arc:25;background:$Panel.background;");
        panel.add(component, "grow,push");
        return panel;
    }

    private JComponent createdPanelEditor(String title, JComponent component) {
        JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 10", "fill", "[grow 0][grow 0][fill]"));
        panel.putClientProperty(FlatClientProperties.STYLE, "arc:25;background:lighten(@background,5%);");
        JLabel label = new JLabel(title);
        label.putClientProperty(FlatClientProperties.STYLE,"font:bold;foreground:darken(@foreground,10%);");
        panel.add(label, "growx 0,al center");
        panel.add(new JSeparator(), "gapx 10 10,growx");
        panel.add(component, "grow,push");
        return panel;
    }

    private JComponent createdControl() {
        JPanel panel = new JPanel(new MigLayout("fillx,insets 8 10 8 10, w 550!", "fill"));
        panel.putClientProperty(FlatClientProperties.STYLE, "arc:25;background:lighten(@background,5%);");
        panel.add(button_run, "grow");
        panel.add(button_clean, "grow");
        panel.add(button_read, "grow");
        return panel;
    }
}