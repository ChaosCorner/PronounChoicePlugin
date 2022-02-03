package xyz.chaoscorner.pronounchoice.types.exceptions;

import xyz.chaoscorner.pronounchoice.types.models.PronounGroup;

public class LuckPermsGroupPresentException extends Exception {
    public LuckPermsGroupPresentException() {
        super("LuckPerms group already exists");
    }

    public LuckPermsGroupPresentException(PronounGroup pronounGroup) {
        super(String.format("LuckPerms group already exists in given PronounGroup: %s", pronounGroup.name()));
    }
}
