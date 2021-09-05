package me.ultradev.skyblocksquadbot.api.util;

import me.ultradev.skyblocksquadbot.Main;
import me.ultradev.skyblocksquadbot.commands.Command;
import net.dv8tion.jda.api.entities.MessageChannel;

public class MessageUtil {

    public static void syntaxError(MessageChannel channel, Command command) {
        channel.sendMessage("**Error:** Invalid syntax! Please use **" + Main.prefix + command.getSyntax() + "**!").queue();
    }

}
