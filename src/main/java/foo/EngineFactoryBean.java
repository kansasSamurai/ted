package foo;

import freemarker.template.Configuration;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.FactoryBean;

/**
 * A Spring factory bean for configuring and accessing an EngineFactory.
 *
 * @author Rick
 */
public class EngineFactoryBean implements FactoryBean<EngineFactory> {

    @Override
    public EngineFactory getObject() throws Exception {
        EngineFactory ef = EngineFactory.get();
        return ef;
    }

    @Override
    public Class<?> getObjectType() {
        return EngineFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    private VelocityEngine velocityEngine;

    private Configuration freemarkerConfiguration;

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;

        final TemplateEngine te = new VelocityTemplateEngine();
        te.setEngine(velocityEngine);
        Engine.VELOCITY.setTemplateEngine(te);
    }

    public void setFreemarkerConfiguration(Configuration freemarkerConfiguration) {
        this.freemarkerConfiguration = freemarkerConfiguration;
        
        final TemplateEngine te = new FreemarkerTemplateEngine();
        te.setEngine(freemarkerConfiguration);
        Engine.FREEMARKER.setTemplateEngine(te);
    }

}
