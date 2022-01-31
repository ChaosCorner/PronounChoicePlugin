package xyz.chaoscorner.pronounchoice.util.types;

import java.util.List;

public abstract class SubCommand {
    private final String name;
    private final String description;
    private final List<SubCommandArg> args;
    private final String requiredPermission;

    public SubCommand(String name, String description, List<SubCommandArg> args, String requiredPermission) {
        this.name = name;
        this.description = description;
        this.args = args;
        this.requiredPermission = requiredPermission;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<SubCommandArg> getArgs() {
        return args;
    }

    public String getRequiredPermission() {
        return requiredPermission;
    }

    public abstract void execute();
}
