package foo;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

/**
 * A simple example showing how to use RSyntaxTextArea to add Java syntax
 * highlighting to a Swing application.
 *
 * http://bobbylight.github.io/RSyntaxTextArea/
 *
 */
public class TextEditorDemo extends JPanel {

   private static final long serialVersionUID = 1L;

   public TextEditorDemo() {

      setLayout(new BorderLayout());

      final RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);
      textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
      textArea.setCodeFoldingEnabled(true);

      final RTextScrollPane sp = new RTextScrollPane(textArea);
      this.add(sp, BorderLayout.CENTER);

       try {
           Theme theme = Theme.load(getClass().getResourceAsStream(
                   "/org/fife/ui/rsyntaxtextarea/themes/dark.xml"));
           theme.apply(textArea);
       } catch (IOException ioe) { // Never happens
           ioe.printStackTrace();
       }

   }

}
