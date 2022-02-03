package xyz.chaoscorner.pronounchoice.types.exceptions;

import org.bukkit.entity.Player;

public class UserNoPronounException extends Exception {
    public UserNoPronounException(Player player) {
        super(String.format("Player (name: %s | uuid: %s) is not a member of a PronounGroup", player.getName(), player.getUniqueId()));
    }
}
