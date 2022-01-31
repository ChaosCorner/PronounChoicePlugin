package xyz.chaoscorner.pronounchoice.commands.subcommands;

import xyz.chaoscorner.pronounchoice.util.types.SubCommand;
import xyz.chaoscorner.pronounchoice.util.types.SubCommandArg;

import java.util.ArrayList;

public class HelpSubCommand extends SubCommand {
    public HelpSubCommand() {
        super("help", "Shows the version and usage of the plugin", new ArrayList<SubCommandArg>(), "pronoun.help");
    }

    @Override
    public void execute() {
        // TODO: add code here
    }
}
