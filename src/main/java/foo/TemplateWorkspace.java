package foo;

import java.util.Set;

/**
 * Data Model
 *
 * @author Rick
 */
public class TemplateWorkspace {

    private TemplateEngine engine;

    private Syntax syntax;

    private String dataModel;

    private String infilename;

    private String outfilename;

    private boolean htmlRenderedInBrowser;

    private Set<TemplateEngine> engineList;

    public TemplateEngine getEngine() {
        return engine;
    }

    public void setEngine(TemplateEngine engine) {
        this.engine = engine;
    }

    public Syntax getSyntax() {
        return syntax;
    }

    public void setSyntax(Syntax syntax) {
        this.syntax = syntax;
    }

    public String getDataModel() {
        return dataModel;
    }

    public void setDataModel(String dataModel) {
        this.dataModel = dataModel;
    }

    public String getInfilename() {
        return infilename;
    }

    public void setInfilename(String infilename) {
        this.infilename = infilename;
    }

    public String getOutfilename() {
        return outfilename;
    }

    public void setOutfilename(String outfilename) {
        this.outfilename = outfilename;
    }

    public boolean isHtmlRenderedInBrowser() {
        return htmlRenderedInBrowser;
    }

    public void setHtmlRenderedInBrowser(boolean htmlRenderedInBrowser) {
        this.htmlRenderedInBrowser = htmlRenderedInBrowser;
    }

    public void setEngineList(Set<TemplateEngine> engineList) {
        this.engineList = engineList;
    }

}
