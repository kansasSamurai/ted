package org.jwellman.ted;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Rick
 */
@SuppressWarnings("serial")
public class ActionApplyTemplate extends AbstractAction {

    private final TemplateWorkspaceCallbacks callback;

    public ActionApplyTemplate(TemplateWorkspaceCallbacks c) {
        super("Merge Data/Template");
        this.callback = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.callback.ApplyTemplate();
    }
}
