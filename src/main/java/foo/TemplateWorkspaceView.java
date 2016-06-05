package foo;

import com.alee.extended.panel.WebButtonGroup;
import com.alee.laf.button.WebToggleButton;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.IOException;
import javax.swing.JPanel;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.RTextScrollPane;

/**
 *
 * @author Rick
 */
public class TemplateWorkspaceView extends JPanel {

    private static final long serialVersionUID = 1L;

    final private RSyntaxTextArea textArea;

    final private TemplateWorkspace datamodel;

    // ----- Template Views -----
    final WebToggleButton dataEditor;
    final WebToggleButton outputEditor;
    final WebToggleButton templateEditor;

    // ----- Template Engine -----
    final WebToggleButton velocity;
    final WebToggleButton freemarker;
    final WebToggleButton thymeleaf;

    // ----- File Syntax -----
    final WebToggleButton css;
    final WebToggleButton xml;
    final WebToggleButton html;
    final WebToggleButton java;
    final WebToggleButton javascript;
    final WebToggleButton json;

    public TemplateWorkspaceView() {

        this.setLayout(new BorderLayout());
        this.datamodel = new TemplateWorkspace();

        final JPanel pnlNorth = new JPanel();
        pnlNorth.setLayout(new FlowLayout());

        dataEditor = new WebToggleButton ( "Data Model" );
        outputEditor = new WebToggleButton ( "Output" );
        templateEditor = new WebToggleButton ( "Template" );
        WebButtonGroup grpEditorSelection = new WebButtonGroup ( true, templateEditor, dataEditor, outputEditor );
        pnlNorth.add(grpEditorSelection);

        WebButtonGroup pnlTemplateSelection = new WebButtonGroup ( true );
        pnlTemplateSelection.add(velocity = createEngineButton( Engine.VELOCITY ));
        pnlTemplateSelection.add(freemarker = createEngineButton( Engine.FREEMARKER ));
        pnlTemplateSelection.add(thymeleaf = createEngineButton( Engine.THYMELEAF ));
        pnlNorth.add(pnlTemplateSelection);

        WebButtonGroup pnlSyntaxSelection = new WebButtonGroup(true);
        pnlSyntaxSelection.add(css = createSyntaxButton(Syntax.CSS));
        pnlSyntaxSelection.add(xml = createSyntaxButton(Syntax.XML));
        pnlSyntaxSelection.add(html = createSyntaxButton(Syntax.HTML));
        pnlSyntaxSelection.add(java = createSyntaxButton(Syntax.JAVA));
        pnlSyntaxSelection.add(javascript = createSyntaxButton(Syntax.JAVASCRIPT));
        pnlSyntaxSelection.add(json = createSyntaxButton(Syntax.JSON));
        pnlNorth.add(pnlSyntaxSelection);

        textArea = new RSyntaxTextArea(20, 60);
        textArea.setCodeFoldingEnabled(true);
        //textArea.setEOLMarkersVisible(true);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        // SYNTAX_STYLE_HTML
        final RTextScrollPane sp = new RTextScrollPane(textArea);

        // Right part content
        this.add(sp, BorderLayout.CENTER);
        this.add(pnlNorth, BorderLayout.NORTH);

        try {
            Theme theme = Theme.load(getClass().getResourceAsStream(
                    "/org/fife/ui/rsyntaxtextarea/themes/dark.xml"));
            theme.apply(textArea);
        } catch (IOException ioe) { // Never happens
            ioe.printStackTrace();
        }

    }

    private WebToggleButton createSyntaxButton(Syntax syntax) {
        final WebToggleButton b = new WebToggleButton();
        b.setAction(new ActionSyntaxSelector(syntax, this.datamodel, this));
        return b;
    }

    private WebToggleButton createEngineButton(Engine eng) {
        final WebToggleButton b = new WebToggleButton();
        b.setAction(new ActionEngineSelector(eng, this.datamodel));
        return b;
    }

    public void setTemplateSyntax(String syntax) {
        this.textArea.setSyntaxEditingStyle(syntax);
    }

}
