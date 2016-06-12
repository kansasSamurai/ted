package foo;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 *
 * @author Rick
 */
class VelocityTemplateEngine implements TemplateEngine<VelocityEngine,Object> {

    private VelocityEngine engine;
    private Object dataModel;
    private final Map<String,Template> mapOfTemplates = new HashMap<>();

    public VelocityTemplateEngine() {
        final HashMap m = new HashMap();
        m.put("name", "World");
        this.dataModel = m;
    }

    @Override
    public void setEngine(VelocityEngine e) {
        this.engine = e;
        this.engine.init();
    }

    @Override
    public void setDataModel(Object model) {
        this.dataModel = model;
    }

    @Override
    public void ingestTemplate(String key, String resourceid) {
        if (mapOfTemplates.containsKey(key)) {
            // do nothing for now; maybe change this behavior later
        } else {
            final Template t = this.engine.getTemplate(resourceid);
            mapOfTemplates.put(key, t);
        }
    }

    @Override
    public String applyTemplate(String key) {
        final StringWriter w = new StringWriter();
            final VelocityContext vc = new VelocityContext();
            vc.put("root", dataModel);
            mapOfTemplates.get(key).merge(vc, w);
        return w.toString();
    }

}
