package foo;

import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

/**
 *
 * @author Rick
 */
public enum Syntax {

    XML("xml", SyntaxConstants.SYNTAX_STYLE_XML)
    ,CSS("css", SyntaxConstants.SYNTAX_STYLE_CSS)
    ,HTML("html", SyntaxConstants.SYNTAX_STYLE_HTML)
    ,JAVA("java", SyntaxConstants.SYNTAX_STYLE_JAVA)
    ,JSON("json", SyntaxConstants.SYNTAX_STYLE_JSON)
    ,JAVASCRIPT("js", SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT)
    ;

    Syntax(String uimnemonic, String syntaxkey) {
        this.uiMnemonic = uimnemonic;
        this.syntaxKey = syntaxkey;
    }

    private final String uiMnemonic;
    private final String syntaxKey;

    public String getUiMnemonic() {
        return uiMnemonic;
    }

    public String getSyntaxKey() {
        return syntaxKey;
    }

}
