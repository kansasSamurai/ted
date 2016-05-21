package foo;

import java.awt.*;
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
public class TextEditorDemo extends JFrame implements Runnable {

   private static final long serialVersionUID = 1L;

   public TextEditorDemo() {

      JPanel cp = new JPanel(new BorderLayout());

      RSyntaxTextArea textArea = new RSyntaxTextArea(20, 60);
      textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
      textArea.setCodeFoldingEnabled(true);
      RTextScrollPane sp = new RTextScrollPane(textArea);
      cp.add(sp);

      setContentPane(cp);
      setTitle("Text Editor Demo");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      pack();
      setLocationRelativeTo(null);

   }
   
   public void run() {
	   this.setVisible(true);
   }

   public static void main(String[] args) {
	   SwingUtilities.invokeLater(new TextEditorDemo());
	   // The line below invokes the original implementation:
	   // start(args);
   }

   public static void start(String[] args) {
      // Start all Swing applications on the EDT.
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            new TextEditorDemo().setVisible(true);
         }
      });	   
   }
   
}
