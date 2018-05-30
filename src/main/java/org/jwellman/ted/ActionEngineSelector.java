package org.jwellman.ted;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Rick
 */
@SuppressWarnings("serial")
public class ActionEngineSelector extends AbstractAction {

    final private Engine engine;
    final private TemplateWorkspace datamodel;

    public ActionEngineSelector(Engine eng, TemplateWorkspace dm) {
        super(eng.getUiMnemonic());
        this.datamodel = dm;
        this.engine = eng;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.datamodel.setEngine(this.engine.getTemplateEngine());
    }

}
