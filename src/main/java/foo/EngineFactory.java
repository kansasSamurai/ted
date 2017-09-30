package foo;

import org.apache.velocity.app.VelocityEngine;

/**
 * This class provides a way for the Engine enumeration to obtain needed references.
 * these references can be instantiated in whatever way you like, but using
 * a Spring application context is probably the fastest and easiest.
 *
 * @author Rick
 */
public class EngineFactory {

    public final static EngineFactory get() {
        return singleton;
    }

    private final static EngineFactory singleton = new EngineFactory();

    private EngineFactory() {}

}
