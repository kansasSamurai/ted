package org.jwellman.ted;

import com.alee.extended.panel.WebButtonGroup;
import com.alee.laf.button.WebButton;
import com.alee.laf.button.WebToggleButton;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonTokenId;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import org.apache.commons.lang.time.DateUtils;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.RTextScrollPane;

/**
 * This started based on the following demo; 
 * it has been HEAVILY modified (to the point the original is not recognizable)
 * 
 * http://bobbylight.github.io/RSyntaxTextArea/
 * A simple example showing how to use RSyntaxTextArea to add Java syntax
 * highlighting to a Swing application.
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
    final WebToggleButton sql;
    final WebToggleButton html;
    final WebToggleButton java;
    final WebToggleButton javascript;
    final WebToggleButton json;

    // ----- FasterXML -----
    private ObjectMapper mapper;

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
        pnlSyntaxSelection.add(sql = createSyntaxButton(Syntax.SQL));
        pnlNorth.add(pnlSyntaxSelection);

        WebButtonGroup pnlEditorSelection = new WebButtonGroup ( true );
        pnlEditorSelection.add( btnTemplateEditor = createEditorButton( editorHolder, Editor.TEMPLATE ) );
        pnlEditorSelection.add( btnDataEditor = createEditorButton( editorHolder, Editor.DATA ) );
        pnlEditorSelection.add( btnOutputEditor = createEditorButton( editorHolder, Editor.RESULT ) );
        pnlNorth.add(pnlEditorSelection);
        btnTemplateEditor.setSelected(true);

        pnlNorth.add(this.createActionButton());

        dataEditor = this.createEditor(SyntaxConstants.SYNTAX_STYLE_JSON);
        resultEditor = this.createEditor(null);
        templateEditor = this.createEditor(SyntaxConstants.SYNTAX_STYLE_JAVA);
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

    private RSyntaxTextArea createEditor(String syntax) {
    	RSyntaxTextArea editor = new RSyntaxTextArea(20, 60);
        editor.setCodeFoldingEnabled(true);
        if (syntax != null) editor.setSyntaxEditingStyle(syntax);
        return editor;
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
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void ApplyTemplate() {
        final TemplateEngine te = this.datamodel.getEngine();
        te.setDataModel(this.getDataModel());
        te.ingestTemplate("testkey", this.templateEditor.getText());
        final String out = te.applyTemplate("testkey");
        this.resultEditor.setText(out);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private Object getDataModel() {
        boolean simulate = false;
        if (simulate) {            
			final HashMap m = new HashMap();
            m.put("name", "World");
            return m;
        } else {
            String json = dataEditor.getText(); // "{\"name\":\"mkyong\", \"age\":29}";

            // convert JSON string to Map
            // reference:  https://www.leveluplunch.com/java/tutorials/033-custom-jackson-date-deserializer/
            Map<String, Object> map = null;
            try {
                //map = mapper.readValue(json, new TypeReference<Map<String, String>>(){}); // This worked with velocity and a simple JSON object                
                map = getObjectMapper().readValue(json, Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            return map;
        }
    }
    
    private ObjectMapper getObjectMapper() {

        if (mapper == null) {
            final SimpleModule simpleModule = new SimpleModule();
            simpleModule.addDeserializer(Object.class, new CustomDateDeserializer());

            mapper = new ObjectMapper();    
            mapper.registerModule(simpleModule);
        }
        
        return mapper;
    }
    
    @SuppressWarnings("deprecation")
    private class CustomDateDeserializer extends UntypedObjectDeserializer {

        private static final long serialVersionUID = 1L;

        @Override
        public Object deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {

            if (jp.getCurrentTokenId() == JsonTokenId.ID_STRING) {
                try {
                    return DateUtils.parseDate(jp.getText(), new String[] {
                            "yyyy-MM-dd", "MM/dd/yyyy", "yyyy.MM.dd G 'at' HH:mm:ss z" });
                } catch (Exception e) {
                    return super.deserialize(jp, ctxt);
                }
            } else {
                return super.deserialize(jp, ctxt);
            }
        }
    }

}
