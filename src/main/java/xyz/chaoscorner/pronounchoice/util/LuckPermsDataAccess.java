package xyz.chaoscorner.pronounchoice.util;

import net.luckperms.api.LuckPerms;
import org.bukkit.entity.Player;
import xyz.chaoscorner.pronounchoice.util.types.Pronoun;

import java.util.List;

public class LuckPermsDataAccess {
    private static final String PRONOUN_GROUP_PREFIX = "meta_pronoun_";
    private static final String COLOR_KEY = "meta.pronoun_color_code.";
    private static final String DESC_KEY = "meta.pronoun_desc.";

    private final LuckPerms luckPerms;

    public LuckPermsDataAccess(LuckPerms luckPerms) {
        this.luckPerms = luckPerms;
    }

    // gets the current list of pronouns
    public List<Pronoun> getPronounsList() {
        // TODO: add code here
    }

    // gets a user's current set of pronouns
    public Pronoun getPlayerPronouns(Player player) {
        // TODO: add code here
    }

    // sets a user's pronouns to a certain group
    public void setPlayerPronouns(Pronoun pronoun) {
        // TODO: add code here
    }

    // creates a pronoun group
    public void createPronounGroup(Pronoun pronoun) {
        // TODO: add code here
    }

    // removes a pronoun group
    public void removePronounGroup(Pronoun pronoun) {
        // TODO: add code here
    }
}
