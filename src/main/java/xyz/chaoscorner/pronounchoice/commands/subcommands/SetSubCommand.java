package xyz.chaoscorner.pronounchoice.commands.subcommands;

import xyz.chaoscorner.pronounchoice.util.types.SubCommand;
import xyz.chaoscorner.pronounchoice.util.types.SubCommandArg;

import java.util.List;

public class SetSubCommand extends SubCommand {
    public SetSubCommand() {
        super("set", "Sets your pronouns", List.of(
                new SubCommandArg("name", true, ""),
                new SubCommandArg("player", false, "pronoun.set.others")
        ), "pronoun.set.self");
    }

    @Override
    public void execute() {
        // TODO: add code here
    }
}
