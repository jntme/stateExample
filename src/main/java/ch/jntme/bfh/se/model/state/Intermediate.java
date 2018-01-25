package ch.jntme.bfh.se.model.state;

import ch.jntme.bfh.se.model.StopWatchMachine;

public class Intermediate extends State {
    public Intermediate(StopWatchMachine machine) {
        super(machine);

    }

    @Override
    public void handlePressB1() {
        super.handlePressB1();

        machine.setState(new Running(machine));
    }

    @Override
    public void handlePressB2() {
        super.handlePressB2();

        machine.setState(new Stopped(machine));
    }
}
