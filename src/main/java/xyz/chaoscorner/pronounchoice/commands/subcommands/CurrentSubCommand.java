package xyz.chaoscorner.pronounchoice.commands.subcommands;

import xyz.chaoscorner.pronounchoice.util.types.SubCommand;
import xyz.chaoscorner.pronounchoice.util.types.SubCommandArg;

import java.util.Arrays;

public class CurrentSubCommand extends SubCommand {
    public CurrentSubCommand() {
        super("current", "Lists your current pronouns", Arrays.asList(
                new SubCommandArg("player", false, "pronoun.current.others")
        ), "pronoun.current.self");
    }

    @Override
    public void execute() {
        // TODO: add code here
    }
}
