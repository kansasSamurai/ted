package foo;

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

    public VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;

        final TemplateEngine te = new VelocityTemplateEngine();
        te.setEngine(velocityEngine);
        Engine.VELOCITY.setTemplateEngine(te);
    }

}
