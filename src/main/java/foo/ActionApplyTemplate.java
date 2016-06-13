package foo;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Rick
 */
public class ActionApplyTemplate extends AbstractAction {

    TemplateWorkspaceCallbacks callback;

    public ActionApplyTemplate(TemplateWorkspaceCallbacks c) {
        super("Merge Data/Template");
        this.callback = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.callback.ApplyTemplate();
    }
}
