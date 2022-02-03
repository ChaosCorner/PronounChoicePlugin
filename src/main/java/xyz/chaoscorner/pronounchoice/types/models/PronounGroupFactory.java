package xyz.chaoscorner.pronounchoice.types.models;

import net.luckperms.api.model.group.Group;
import net.luckperms.api.node.NodeType;
import net.luckperms.api.node.types.MetaNode;
import org.bukkit.ChatColor;
import xyz.chaoscorner.pronounchoice.util.Constants;

import java.util.Map;
import java.util.stream.Collectors;

public record PronounGroupFactory(Group group) {
    public PronounGroup createPronounGroup() {
        return create(this.group);
    }

    public static PronounGroup create(Group group) {
        Map<String, String> metaNodes = group.getNodes(NodeType.META).stream()
                .collect(Collectors.toMap(MetaNode::getMetaKey, MetaNode::getMetaValue));

        return new PronounGroup(
                group.getName().substring(Constants.LUCKPERMS_PRONOUN_GROUP_PREFIX.length() - 1),
                metaNodes.get(Constants.LUCKPERMS_PRONOUN_DESC_META_KEY),
                ChatColor.getByChar(metaNodes.get(Constants.LUCKPERMS_PRONOUN_COLOR_META_KEY).substring(1)),
                group
        );
    }
}
