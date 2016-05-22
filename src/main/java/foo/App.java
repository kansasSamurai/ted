package foo;

import javax.swing.JPanel;
import org.jwellman.foundation.Foundation;
import org.jwellman.foundation.interfaces.uiCustomTheme;
import org.jwellman.foundation.swing.IWindow;
import org.jwellman.foundation.uContext;

/**
 * Hello world!
 *
 */
public class App implements uiCustomTheme {

    /**
     * A reference to your JPanel's container - for customization
     */
    IWindow window;

    /**
     * Your user interface - JPanel(s) only!
     */
    JPanel mainui;

    /**
     * default noargs constructor
     */
    public App() {
    }

    /**
     * This is the entry point when run standalone.
     *
     * @param args
     */
    public static void main(String[] args) {
        new App().startup(true, args);
    }

    /**
     * This is the entry point when embedded.
     *
     * @param args
     */
    public void start(String[] args) {
        this.startup(false, args);
    }

    private App startup(boolean asMainFrame, String[] args) {

        // Prepare - User Interface Context
        int uiunit = 65;
        final uContext context = uContext.createContext();
        context.setTheme(this);
        context.setDimension(uiunit * 5, uiunit * 9);

        // Step 1 - Initialize Swing
        final Foundation f = Foundation.get();
        f.init(context);

        // Step 2 - Create your UIs in JPanel(s)
        mainui = f.registerUI("clock", new TextEditorDemo()); // ITunesPanel | StopWatch | MainPanel

        // Step 3 - Use Foundation to create your "window"; give it your UI.
        window = f.useWindow(mainui);
        window.setTitle("Text Editor Demo"); // Step 3a (optional) - Customize your window
        window.setResizable(true); // Step 3a (optional) - Customize your window

        // Step 4a - Create data models, controllers, and other non-UI objects
        // n/a
        // Step 4b (optional)- Associate models with views
        // n/a

        // Step 5 - Display your User Interface
        f.showGUI();

        return this;
    }

    public void doCustomTheme() {
        // TODO implement theme if necessary
    }

}