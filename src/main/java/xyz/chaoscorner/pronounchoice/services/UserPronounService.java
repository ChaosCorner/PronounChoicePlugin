package xyz.chaoscorner.pronounchoice.services;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.types.InheritanceNode;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import xyz.chaoscorner.pronounchoice.PronounChoice;
import xyz.chaoscorner.pronounchoice.types.exceptions.LuckPermsGroupNotPresentException;
import xyz.chaoscorner.pronounchoice.types.exceptions.UserNoPronounException;
import xyz.chaoscorner.pronounchoice.types.models.PronounGroup;

public record UserPronounService(LuckPerms api) {
    public PronounGroup getUserPronounGroup(Player player) {
        for (PronounGroup pronounGroup : PronounChoice.pronounGroups) {
            if (player.hasPermission(String.format("group.%s", pronounGroup.luckPermsGroup().getName()))) {
                return pronounGroup;
            }
        }
        return null;
    }

    public void setUserPronounGroup(Player player, PronounGroup pronounGroup) throws LuckPermsGroupNotPresentException {
        if (pronounGroup.luckPermsGroup() == null) {
            throw new LuckPermsGroupNotPresentException(pronounGroup);
        }

        try {
            removeUserPronounGroup(player);
        } catch (UserNoPronounException e) {
            Bukkit.getLogger().info(String.format("Player '%s' has no pronouns, skipping remove...", player.getName()));
        }

        Node groupNode = InheritanceNode.builder(pronounGroup.luckPermsGroup()).build();
        api.getUserManager().modifyUser(player.getUniqueId(), user -> {
            user.data().add(groupNode);
        });
    }

    public void removeUserPronounGroup(Player player) throws UserNoPronounException {
        PronounGroup current = getUserPronounGroup(player);

        if (current == null) {
            throw new UserNoPronounException(player);
        }

        removeUserPronounGroup(player, current);
    }

    public void removeUserPronounGroup(Player player, PronounGroup pronounGroup) {
        Node groupNode = InheritanceNode.builder(pronounGroup.luckPermsGroup()).build();
        api.getUserManager().modifyUser(player.getUniqueId(), user -> {
            user.data().remove(groupNode);
        });
    }
}
