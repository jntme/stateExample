package ch.jntme.bfh.se.model.state;

import ch.jntme.bfh.se.model.StopWatchMachine;

public class Idle extends State {

    public Idle(StopWatchMachine machine) {
        super(machine);
    }

    @Override
    public void entry() {
        super.entry();

        machine.setTime(0);
        machine.resetWatch();
    }

    @Override
    public void handlePressB1() {
        this.machine.setState(new Running(machine));
    }
}
