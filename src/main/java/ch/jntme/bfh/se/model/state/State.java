package ch.jntme.bfh.se.model.state;

import ch.jntme.bfh.se.model.StopWatchMachine;
import ch.jntme.bfh.se.ui.EventHandler;

public abstract class State implements EventHandler {

    protected StopWatchMachine machine;

    public void entry() {
    }

    public void execute() {
    }

    public void exit() {
    }

    public State(StopWatchMachine machine) {
        this.machine = machine;
    }

    @Override
    public void handlePressB1() {

    }

    @Override
    public void handlePressB2() {

    }
}
