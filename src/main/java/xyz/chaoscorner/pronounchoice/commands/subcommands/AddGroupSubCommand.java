package xyz.chaoscorner.pronounchoice.commands.subcommands;

import xyz.chaoscorner.pronounchoice.util.types.SubCommand;
import xyz.chaoscorner.pronounchoice.util.types.SubCommandArg;

import java.util.Arrays;

public class AddGroupSubCommand extends SubCommand {
    public AddGroupSubCommand() {
        super("addgroup", "Adds a pronoun set to LuckPerms", Arrays.asList(
                new SubCommandArg("name", true, ""),
                new SubCommandArg("description", true, ""),
                new SubCommandArg("color", false, "")
        ), "pronoun.addgroup");
    }

    @Override
    public void execute() {
        // TODO: add code here
    }
}
