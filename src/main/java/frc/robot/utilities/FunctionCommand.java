package frc.robot.utilities;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class FunctionCommand extends InstantCommand {

    private VoidFunctionWrapper function;

    public FunctionCommand(VoidFunctionWrapper function) {
        this.function = function;
    }

    @Override
    protected void initialize() {
        function.call();
    }
}