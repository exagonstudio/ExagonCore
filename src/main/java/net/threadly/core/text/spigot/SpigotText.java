package net.threadly.core.text.spigot;

import net.md_5.bungee.api.ChatColor;
import net.threadly.core.text.Text;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SpigotText extends Text {
    private static final Pattern HEX_PATTERN = Pattern.compile("&(#\\w{6})");

    public void colorizeLines() {
        lines = lines.stream().map(line -> {
            Matcher matcher = HEX_PATTERN.matcher(ChatColor.translateAlternateColorCodes('&', line));
            StringBuffer buffer = new StringBuffer();

            while (matcher.find()) {
                matcher.appendReplacement(buffer, ChatColor.of(matcher.group(1)).toString());
            }

            return matcher.appendTail(buffer).toString();
        }).collect(Collectors.toList());
    }
}
