package xyz.chaoscorner.pronounchoice.commands.subcommands;

import xyz.chaoscorner.pronounchoice.util.types.SubCommand;
import xyz.chaoscorner.pronounchoice.util.types.SubCommandArg;

import java.util.ArrayList;
import java.util.Arrays;

public class RemoveSubCommand extends SubCommand {
    public RemoveSubCommand() {
        super("remove", "Removes your pronouns", Arrays.asList(
                new SubCommandArg("player", false, "pronoun.remove.others")
        ), "pronoun.remove.self");
    }

    @Override
    public void execute() {
        // TODO: add code here
    }
}
