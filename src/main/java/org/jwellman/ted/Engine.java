package org.jwellman.ted;

/**
 *
 * @author Rick
 */
@SuppressWarnings("rawtypes")
public enum Engine {

    VELOCITY("Velocity")
    ,FREEMARKER("Freemarker")
    ,THYMELEAF("Thymeleaf")
    ;

    Engine(String mnenomic) {
        this.uiMnemonic = mnenomic;
    }

    private final String uiMnemonic;
    
    private TemplateEngine templateEngine;

    public String getUiMnemonic() {
        return uiMnemonic;
    }

    public TemplateEngine getTemplateEngine() {
        return templateEngine;
    }

    public void setTemplateEngine(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

}
