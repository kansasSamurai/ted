package org.jwellman.ted;

import javax.swing.JPanel;

import org.jwellman.foundation.uContext;
import org.jwellman.foundation.Foundation;
import org.jwellman.foundation.interfaces.uiCustomTheme;
import org.jwellman.foundation.swing.IWindow;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Foundation based application starter.
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

        // For now, only create Spring app context when running standalone;
        // since there may already be a context when running embedded.
        // Note, that it is not necessarily the existing context that "conflicts"
        // but rather certain beans within the same JVM such as the VelocityEngine.
        final String[] contextPaths = new String[] {"editor/app-context.xml"};
        @SuppressWarnings("unused")
		final ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(contextPaths);

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
        final uContext context = uContext.createContext();
        context.setTheme(this);
        context.setDimension(85);

        // Step 1 - Initialize Swing
        final Foundation f = Foundation.get();
        f.init(context); // context

        // Step 2 - Create your UIs in JPanel(s)
        mainui = f.registerUI("editor", new TextEditorDemo());

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

    @Override
    public void doCustomTheme() {
        // TODO implement theme if necessary
    }

}