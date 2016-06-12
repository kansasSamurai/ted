package foo;

/**
 *
 * @author Rick
 */
public enum Editor {

    TEMPLATE("Template")
    ,DATA("Data Model")
    ,RESULT("Output")
    ;

    Editor(String ui) {
        this.uiMnemonic = ui;
    }

    private final String uiMnemonic;

    public String getUiMnemonic() {
        return uiMnemonic;
    }

}
