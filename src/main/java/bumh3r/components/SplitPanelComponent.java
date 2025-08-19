package bumh3r.components;

import javax.swing.BorderFactory;
import javax.swing.JSplitPane;

public class SplitPanelComponent extends JSplitPane{

    public SplitPanelComponent(int orientation) {
        super(orientation);
        setResizeWeight(0.8);
        setDividerSize(10);

        setBackground(null);
        setBorder(BorderFactory.createEmptyBorder());
    }
}
