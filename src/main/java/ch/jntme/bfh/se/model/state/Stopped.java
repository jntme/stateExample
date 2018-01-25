package ch.jntme.bfh.se.model.state;

import ch.jntme.bfh.se.model.StopWatchMachine;

public class Stopped extends State {

    public Stopped(StopWatchMachine machine) {
        super(machine);
    }

    @Override
    public void entry() {
        super.entry();

        machine.stopWatch();
    }

    @Override
    public void handlePressB2() {
        super.handlePressB2();

        machine.setState(new Idle(machine));
    }
}
