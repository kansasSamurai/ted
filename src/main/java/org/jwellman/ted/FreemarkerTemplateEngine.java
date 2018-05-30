package org.jwellman.ted;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

/**
 *
 * @author Rick
 */
public class FreemarkerTemplateEngine implements TemplateEngine<Configuration,Object> {

    private Configuration engine;

    private Object dataModel;

    public FreemarkerTemplateEngine() {

    }

    @Override
    public void setEngine(Configuration engine) {
        this.engine = engine;
    }

    @Override
    public void setDataModel(Object model) {
        this.dataModel = model;
    }

    @Override
    public void ingestTemplate(String key, String resourceid) {
        final StringTemplateLoader loader = (StringTemplateLoader)this.engine.getTemplateLoader();
        loader.putTemplate(key, resourceid);
    }

    @Override
    public String applyTemplate(String key) {
        try {
            final String result = FreeMarkerTemplateUtils.processTemplateIntoString(engine.getTemplate(key), dataModel);
            return result;
        } catch (IOException | TemplateException ex) {
            Logger.getLogger(FreemarkerTemplateEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "<<ERR238::Unable to process FreeMarker template>>";
    }

}
