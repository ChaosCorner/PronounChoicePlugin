package xyz.chaoscorner.pronounchoice.util;

import org.bukkit.ChatColor;
import xyz.chaoscorner.pronounchoice.PronounChoice;
import xyz.chaoscorner.pronounchoice.types.SubCommand;

import java.util.Arrays;

public class Helpers {
    public static boolean stringIsNullOrEmpty(String str) {
        return str == null || str.isBlank();
    }

    public static boolean anyStringIsNullOrEmpty(String ...args) {
        return Arrays.stream(args).anyMatch(Helpers::stringIsNullOrEmpty);
    }
}
