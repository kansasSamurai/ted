package foo;

import com.alee.laf.label.WebLabel;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.splitpane.WebSplitPane;
import com.alee.laf.tabbedpane.WebTabbedPane;
import java.awt.*;
import javax.swing.*;
import static javax.swing.JSplitPane.HORIZONTAL_SPLIT;

/**
 * A simple example showing how to use RSyntaxTextArea to add Java syntax
 * highlighting to a Swing application.
 *
 * http://bobbylight.github.io/RSyntaxTextArea/
 *
 */
public class TextEditorDemo extends JPanel {

    private static final long serialVersionUID = 1L;

    public TextEditorDemo() {

        this.setLayout(new BorderLayout());

        // Left part content
        WebLabel leftLabel = new WebLabel ( "left", WebLabel.CENTER );
        leftLabel.setMargin ( 5 );
        WebPanel leftPanel = new WebPanel ( true, leftLabel );

        // Right part content
        WebTabbedPane workspaces = new WebTabbedPane();
        workspaces.addTab("(unsaved)*", new TemplateWorkspaceView());
        workspaces.addTab("(unsaved)*", new TemplateWorkspaceView());

        WebPanel rightPanel = new WebPanel ( true );
        rightPanel.add(workspaces, BorderLayout.CENTER);

        // Split
        WebSplitPane splitPane = new WebSplitPane ( HORIZONTAL_SPLIT, leftPanel, rightPanel );
        splitPane.setOneTouchExpandable ( true );
        splitPane.setPreferredSize ( new Dimension ( 250, 200 ) );
        splitPane.setDividerLocation ( 125 );
        splitPane.setContinuousLayout ( true );

        this.add(splitPane, BorderLayout.CENTER);

    }

}
