package ch.jntme.bfh.se.ui;

import javax.servlet.annotation.WebServlet;

import ch.jntme.bfh.se.model.StopWatchMachine;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        Label timeLabel = new Label("");
        StopWatchMachine machine = new StopWatchMachine(timeLabel);
        machine.setUi(UI.getCurrent());

        VerticalLayout vl = new VerticalLayout();

        Button b1 = new Button("B1");
        Button b2 = new Button("B2");

        b1.addClickListener(e -> machine.handlePressB1());
        b2.addClickListener(e -> machine.handlePressB2());

        vl.addComponents(timeLabel, b1, b2);

        setContent(vl);


// how to update the vaadin ui regularly via a thread:

//        UI current = UI.getCurrent();
//        current.setPollInterval(500);

//        new Thread(() -> {
//
//            int i = 0;
//
//            while (true) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                int finalI = i;
//                current.access(() -> timeLabel.setValue(String.valueOf(finalI)));
//                i++;
//            }
//        }).start();
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
