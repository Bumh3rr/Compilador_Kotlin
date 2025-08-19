package bumh3r.components;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

public class ScrollPaneComponent extends JScrollPane {
    public ScrollPaneComponent(Component view) {
        super(view);
        putClientProperty(FlatClientProperties.STYLE, ""
                + "border:0,0,0,0;");
        getHorizontalScrollBar().setUnitIncrement(25);
        getVerticalScrollBar().setUnitIncrement(25);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setColorScroll();
    }

    public void setColorScroll() {
        getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "border:0,0,0,0;"
                + "thumbInsets:0,0,0,0;"
                + "thumb:lighten($ScrollBar.thumb,10%);"
                + "pressedThumbColor:$ScrollBar.thumb;"
                + "width:9;"
                + "[light]track:lighten($Panel.background,4%);"
                + "[dark]track:@background;"
        );
    }


    public static void applyStylesScroll(JScrollPane component){
        component.setBorder(BorderFactory.createEmptyBorder());
        component.getHorizontalScrollBar().setUnitIncrement(25);
        component.getVerticalScrollBar().setUnitIncrement(25);
        component.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "border:0,0,0,0;"
                + "thumbInsets:0,0,0,0;"
                + "thumb:lighten($ScrollBar.thumb,10%);"
                + "pressedThumbColor:$ScrollBar.thumb;"
                + "width:9;"
                + "[dark]track:@background;"
        );
        component.getHorizontalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "border:0,0,0,0;"
                + "thumbInsets:0,0,0,0;"
                + "thumb:lighten($ScrollBar.thumb,10%);"
                + "pressedThumbColor:$ScrollBar.thumb;"
                + "width:5;"
                + "[dark]track:@background;"
        );
    }
}
