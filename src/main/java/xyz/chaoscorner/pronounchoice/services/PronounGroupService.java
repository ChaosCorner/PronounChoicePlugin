package xyz.chaoscorner.pronounchoice.services;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.Group;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import xyz.chaoscorner.pronounchoice.PronounChoice;
import xyz.chaoscorner.pronounchoice.types.exceptions.LuckPermsGroupNotPresentException;
import xyz.chaoscorner.pronounchoice.types.exceptions.LuckPermsGroupPresentException;
import xyz.chaoscorner.pronounchoice.types.models.PronounGroup;
import xyz.chaoscorner.pronounchoice.types.models.PronounGroupFactory;
import xyz.chaoscorner.pronounchoice.util.Constants;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public record PronounGroupService(LuckPerms api) {
    public List<PronounGroup> getPronounGroups() {
        return api.getGroupManager().getLoadedGroups().stream()
                .filter(group -> group.getName().startsWith("meta_pronoun_"))
                .map(PronounGroupFactory::create)
                .toList();
    }

    public PronounGroup getPronounGroupByName(String name) {
        return PronounChoice.pronounGroups.stream()
                .filter(pronounGroup -> pronounGroup.name().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void createPronounGroup(String name, String description, ChatColor color) throws LuckPermsGroupPresentException {
        if (getPronounGroupByName(name) != null) {
            throw new LuckPermsGroupPresentException();
        }

        CompletableFuture<Group> groupFuture = api.getGroupManager().createAndLoadGroup(
                String.format("%s%s", Constants.LUCKPERMS_PRONOUN_COLOR_META_KEY, name)
        );

        groupFuture.whenCompleteAsync((group, throwable) -> {
            if (throwable != null) {
                Bukkit.getLogger().severe("Uh oh. Stinky! Poooop! Haha!");
                Bukkit.getLogger().severe(throwable.getMessage());
                return;
            }

            PronounGroup pronounGroup = new PronounGroup(name, description, color, group);
            PronounChoice.pronounGroups.add(pronounGroup);
        });
    }

    public void deletePronounGroup(PronounGroup pronoun) throws LuckPermsGroupNotPresentException {
        if (pronoun.luckPermsGroup() == null) {
            throw new LuckPermsGroupNotPresentException(pronoun);
        }

        CompletableFuture<Void> groupFuture = api.getGroupManager().deleteGroup(pronoun.luckPermsGroup());

        groupFuture.whenCompleteAsync((nothing, throwable) -> {
            if (throwable != null) {
                Bukkit.getLogger().severe("Uh oh. Stinky! Poooop! Haha!");
                Bukkit.getLogger().severe(throwable.getMessage());
                return;
            }

            PronounChoice.pronounGroups.remove(pronoun);
        });
    }
}
