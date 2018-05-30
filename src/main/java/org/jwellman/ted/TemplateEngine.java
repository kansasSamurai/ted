package org.jwellman.ted;

/**
 *
 * @author Rick
 *
 * @param <E>
 * @param <M>
 */
public interface TemplateEngine<E,M> {

    public void setEngine(E engine);

    public void setDataModel(M model);

    public void ingestTemplate(String key, String resourceid);

    public String applyTemplate(String key);

}
