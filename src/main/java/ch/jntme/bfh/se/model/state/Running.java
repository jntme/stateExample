package ch.jntme.bfh.se.model.state;

import ch.jntme.bfh.se.model.StopWatchMachine;

public class Running extends State {

    public Running(StopWatchMachine machine) {
        super(machine);
    }

    @Override
    public void entry() {
        super.entry();

        machine.startTime();
    }

    @Override
    public void handlePressB1() {
        super.handlePressB1();

        machine.setState(new Intermediate(machine));
    }

    @Override
    public void handlePressB2() {
        super.handlePressB2();

        machine.setState(new Stopped(machine));
    }
}
