package foo;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JPanel;

/**
 *
 * @author Rick
 */
public class ActionEditorSelector extends AbstractAction {

    final private JPanel panel;
    final private Editor editor;

    public ActionEditorSelector(JPanel p, Editor e) {
        super(e.getUiMnemonic());
        this.panel = p;
        this.editor = e;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final CardLayout layout = (CardLayout) panel.getLayout();
        layout.show(panel, editor.getUiMnemonic());
    }

}
