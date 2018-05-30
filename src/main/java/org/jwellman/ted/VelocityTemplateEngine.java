package org.jwellman.ted;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;

/**
 *
 * @author Rick
 */
class VelocityTemplateEngine implements TemplateEngine<VelocityEngine,Object> {

    private VelocityEngine engine;

    private StringResourceRepository repo;

    private Object dataModel;

    private final Map<String,Object> mapOfTemplates = new HashMap<>();

    public VelocityTemplateEngine() {
        final HashMap<String,Object> m = new HashMap<>();
        m.put("name", "World");
        this.dataModel = m;
    }

    @Override
    public void setEngine(VelocityEngine e) {
        this.engine = e;

// ===== The use of spring context and factory already initializes the engine;
// ===== therefore, the following code is pointless because the velocity
// ===== engine cannot be re-initialized by design.
//        engine.setProperty(Velocity.RESOURCE_LOADER, "string");
//        engine.addProperty("string.resource.loader.class", StringResourceLoader.class.getName());
//        // string.resource.loader.repository.class = org.apache.velocity.runtime.resource.util.StringResourceRepositoryImpl
//        // engine.addProperty("string.resource.loader.repository.class", StringResourceRepositoryImpl.class.getName());
//        // engine.addProperty("string.resource.loader.repository.static", "false");
//        this.engine.init();
//
//        final String a = StringResourceLoader.REPOSITORY_NAME_DEFAULT;
//        final String b = "org.apache.velocity.runtime.resource.util.StringResourceRepositoryImpl";
//        final String c = StringResourceRepositoryImpl.class.getName();
//        this.repo = (StringResourceRepository) engine.getApplicationAttribute(b);
        if (this.repo == null)
            this.repo = StringResourceLoader.getRepository();

    }

    @Override
    public void setDataModel(Object model) {
        this.dataModel = model;
    }

    @Override
    public void ingestTemplate(String key, String resourceid) {
        if (mapOfTemplates.containsKey(key)) {
            // do nothing for now; maybe change this behavior later
            mapOfTemplates.put(key, resourceid);
        } else {
            if (repo != null) {
                this.repo.putStringResource(key, resourceid);

                final Template t = this.engine.getTemplate(key);
                mapOfTemplates.put(key, t);
            } else {
                mapOfTemplates.put(key, resourceid);
            }
        }
    }

    @Override
    public String applyTemplate(String key) {
        try {
            final Object o = this.mapOfTemplates.get(key);
            if (o instanceof String)
                return this.applyVelocityTemplate((String)this.mapOfTemplates.get(key));

            return this.applyVelocityTemplate((Template)this.mapOfTemplates.get(key));
        } catch (Exception e) {
            Logger.getLogger(VelocityTemplateEngine.class.getName()).log(Level.SEVERE, null, e);
        }
        return "<<ERR237::Unable to process Velocity template>>";
    }

    private String applyVelocityTemplate(String s) {
        final StringWriter w = new StringWriter();

        final VelocityContext vc = new VelocityContext();
        //vc.put("w", "world!"); //dataModel); // this line works... trying next line
        vc.put("datamodel", this.dataModel);

        Velocity.evaluate( vc, w, "logger", s );

        return w.toString();
    }

    private String applyVelocityTemplate(Template t) {
        final StringWriter w = new StringWriter();
        final VelocityContext vc = new VelocityContext();
        vc.put("w", "world!"); //dataModel);

        t.merge(vc, w);
        return w.toString();
    }
    
}
