package foo;

import com.alee.extended.panel.WebButtonGroup;
import com.alee.laf.button.WebButton;
import com.alee.laf.button.WebToggleButton;
import java.awt.BorderLayout;
import java.awt.CardLayout;
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
public class TemplateWorkspaceView extends JPanel
    implements TemplateWorkspaceCallbacks {

    private static final long serialVersionUID = 1L;

    final private JPanel editorHolder;

    final private RSyntaxTextArea dataEditor;
    final private RSyntaxTextArea templateEditor;
    final private RSyntaxTextArea resultEditor;

    final private TemplateWorkspace datamodel;

    // ----- Template Views -----
    final WebToggleButton btnDataEditor;
    final WebToggleButton btnOutputEditor;
    final WebToggleButton btnTemplateEditor;

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

        this.editorHolder = new JPanel(new CardLayout());

        final JPanel pnlNorth = new JPanel();
        pnlNorth.setLayout(new FlowLayout());

        WebButtonGroup pnlTemplateSelection = new WebButtonGroup ( true );
        pnlTemplateSelection.add(velocity = createEngineButton( Engine.VELOCITY ));
        pnlTemplateSelection.add(thymeleaf = createEngineButton( Engine.THYMELEAF ));
        pnlTemplateSelection.add(freemarker = createEngineButton( Engine.FREEMARKER ));
        pnlNorth.add(pnlTemplateSelection);

        WebButtonGroup pnlSyntaxSelection = new WebButtonGroup(true);
        pnlSyntaxSelection.add(css = createSyntaxButton(Syntax.CSS));
        pnlSyntaxSelection.add(xml = createSyntaxButton(Syntax.XML));
        pnlSyntaxSelection.add(html = createSyntaxButton(Syntax.HTML));
        pnlSyntaxSelection.add(java = createSyntaxButton(Syntax.JAVA));
        pnlSyntaxSelection.add(javascript = createSyntaxButton(Syntax.JAVASCRIPT));
        pnlSyntaxSelection.add(json = createSyntaxButton(Syntax.JSON));
        pnlNorth.add(pnlSyntaxSelection);

        WebButtonGroup pnlEditorSelection = new WebButtonGroup ( true );
        pnlEditorSelection.add( btnTemplateEditor = createEditorButton( editorHolder, Editor.TEMPLATE ) );
        pnlEditorSelection.add( btnDataEditor = createEditorButton( editorHolder, Editor.DATA ) );
        pnlEditorSelection.add( btnOutputEditor = createEditorButton( editorHolder, Editor.RESULT ) );
        pnlNorth.add(pnlEditorSelection);
        btnTemplateEditor.setSelected(true);

        pnlNorth.add(this.createActionButton());

        dataEditor = new RSyntaxTextArea(20, 60);
            dataEditor.setCodeFoldingEnabled(true);
            dataEditor.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JSON);
        resultEditor = new RSyntaxTextArea(20, 60);
            resultEditor.setCodeFoldingEnabled(true);
            resultEditor.setEditable(false);
        templateEditor = new RSyntaxTextArea(20, 60);
            templateEditor.setCodeFoldingEnabled(true);
            templateEditor.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        final RTextScrollPane spDataEditor = new RTextScrollPane(dataEditor);
        final RTextScrollPane spResultEditor = new RTextScrollPane(resultEditor);
        final RTextScrollPane spTemplateEditor = new RTextScrollPane(templateEditor);

        this.editorHolder.add(spTemplateEditor, Editor.TEMPLATE.getUiMnemonic());
        this.editorHolder.add(spDataEditor, Editor.DATA.getUiMnemonic());
        this.editorHolder.add(spResultEditor, Editor.RESULT.getUiMnemonic()
        );

        // Right part content
        this.add(editorHolder, BorderLayout.CENTER);
        this.add(pnlNorth, BorderLayout.NORTH);

        try {
            Theme theme = Theme.load(getClass().getResourceAsStream(
                    "/org/jwellman/editor/themes/rsyntaxtextarea/dark.xml"));
//                    "/org/fife/ui/rsyntaxtextarea/themes/dark.xml"));
            theme.apply(templateEditor);
            theme.apply(dataEditor);
            theme.apply(resultEditor);
        } catch (IOException ioe) { // Never happens
            ioe.printStackTrace();
        }

    }

    private WebButton createActionButton( ) {
        final WebButton b = new WebButton();
        b.setAction(new ActionApplyTemplate(this));
        return b;
    }

    private WebToggleButton createEditorButton(JPanel p, Editor e) {
        final WebToggleButton b = new WebToggleButton();
        b.setAction(new ActionEditorSelector(p, e));
        return b;
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
        this.templateEditor.setSyntaxEditingStyle(syntax);
        this.resultEditor.setSyntaxEditingStyle(syntax);
    }

    /**
     * Reminder:  This is called from the Swing EDT.
     */
    @Override
    public void ApplyTemplate() {
        final TemplateEngine te = this.datamodel.getEngine();
        // te.setDataModel(null); // TODO create datamodel from editor
        te.ingestTemplate("testkey", this.templateEditor.getText());
        final String out = te.applyTemplate("testkey");
        this.resultEditor.setText(out);
    }

}
