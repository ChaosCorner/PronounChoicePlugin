package xyz.chaoscorner.pronounchoice.commands.subcommands;

import xyz.chaoscorner.pronounchoice.util.types.SubCommand;
import xyz.chaoscorner.pronounchoice.util.types.SubCommandArg;

import java.util.Arrays;

public class RemoveGroupSubCommand extends SubCommand {
    public RemoveGroupSubCommand() {
        super("removegroup", "Removes a pronoun group from LuckPerms", Arrays.asList(
                new SubCommandArg("name", true, "")
        ), "pronoun.removegroup");
    }

    @Override
    public void execute() {
        // TODO: add code here
    }
}
