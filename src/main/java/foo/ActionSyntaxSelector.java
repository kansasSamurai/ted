package foo;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author Rick
 */
public class ActionSyntaxSelector extends AbstractAction {

    final private Syntax syntax;
    final private TemplateWorkspace datamodel;
    final private TemplateWorkspaceView view;

    public ActionSyntaxSelector(Syntax s, TemplateWorkspace dm, TemplateWorkspaceView v) {
        super(s.getUiMnemonic());
        this.syntax = s;
        this.datamodel = dm;
        this.view = v;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.datamodel.setSyntax(syntax);
        this.view.setTemplateSyntax(syntax.getSyntaxKey());
    }

}
