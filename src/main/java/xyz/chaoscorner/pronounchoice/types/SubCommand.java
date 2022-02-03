package xyz.chaoscorner.pronounchoice.types;

import org.bukkit.command.CommandSender;
import xyz.chaoscorner.pronounchoice.types.models.SubCommandArg;

import java.util.List;

public abstract class SubCommand {
    private final String name;
    private final String description;
    private final List<SubCommandArg> subCommandArgs;
    private final String requiredPermission;

    public SubCommand(String name, String description, List<SubCommandArg> subCommandArgs, String requiredPermission) {
        this.name = name;
        this.description = description;
        this.subCommandArgs = subCommandArgs;
        this.requiredPermission = requiredPermission;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    public List<SubCommandArg> subCommandArgs() {
        return subCommandArgs;
    }

    public String requiredPermission() {
        return requiredPermission;
    }

    public abstract String isExecutable(CommandSender sender, String[] args);
    public abstract void execute(CommandSender sender, String[] args);
}
