package ch.jntme.bfh.se.model;

import ch.jntme.bfh.se.model.state.Idle;
import ch.jntme.bfh.se.model.state.Running;
import ch.jntme.bfh.se.model.state.State;
import ch.jntme.bfh.se.ui.EventHandler;
import ch.jntme.bfh.se.ui.MyUI;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

import java.util.concurrent.TimeUnit;

public class StopWatchMachine implements EventHandler {
    UI ui = null;
    State state = null;

    long startMillis = 0;
    long actualMillis = 0;

    Label timeLabel;

    Thread timerThread = null;

    public StopWatchMachine(Label timeLabel) {
        this.timeLabel = timeLabel;

        state = new Idle(this);
        state.entry();
    }

    @Override
    public void handlePressB1() {
        this.state.handlePressB1();
    }

    @Override
    public void handlePressB2() {
        this.state.handlePressB2();
    }

    public void setTime(long timer) {
        String formattedTime = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(timer),
                TimeUnit.MILLISECONDS.toMinutes(timer),
                TimeUnit.MILLISECONDS.toSeconds(timer) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timer))
        );

        this.timeLabel.setValue(formattedTime);
    }

    public void resetWatch() {
        startMillis = 0;
    }

    public void stopWatch() {
        this.actualMillis = System.currentTimeMillis();
        ui.access(() -> {
            this.setTime(actualMillis - startMillis);
        });
    }

    public void setState(State state) {
        if (state != null) this.state.exit();

        System.out.println("Switching to state " + state.getClass().toString() + "!");

        this.state = state;
        this.state.entry();
    }

    public void startTime() {

        if (startMillis == 0) {
            startMillis = System.currentTimeMillis();
        }//otherwise the stopwatch is continuing

        timerThread = new Thread(this::runTimer);
        timerThread.start();
    }

    public void setUi(UI ui) {
        this.ui = ui;
        ui.setPollInterval(200);
    }

    private void runTimer() {

        while (state.getClass() == Running.class) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.actualMillis = System.currentTimeMillis();

            ui.access(() -> {
                this.setTime(actualMillis - startMillis);
            });
        }
    }
}
