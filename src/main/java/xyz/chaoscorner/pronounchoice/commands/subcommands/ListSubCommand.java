package xyz.chaoscorner.pronounchoice.commands.subcommands;

import xyz.chaoscorner.pronounchoice.util.types.SubCommand;
import xyz.chaoscorner.pronounchoice.util.types.SubCommandArg;

import java.util.ArrayList;

public class ListSubCommand extends SubCommand {
    public ListSubCommand() {
        super("list", "Lists available pronouns", new ArrayList<SubCommandArg>(), "pronoun.list");
    }

    @Override
    public void execute() {
        // TODO: add code here
    }
}
