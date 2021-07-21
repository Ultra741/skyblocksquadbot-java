package me.ultradev.skyblocksquadbot.commands.categories.information;

import me.ultradev.skyblocksquadbot.commands.Command;
import me.ultradev.skyblocksquadbot.commands.CommandCategory;
import me.ultradev.skyblocksquadbot.commands.CommandPermission;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.lang.management.ManagementFactory;

public class UptimeCommand extends Command {

    public UptimeCommand() {
        super("uptime", "Get the bot's uptime.", "uptime",
                CommandCategory.INFORMATION, CommandPermission.NONE, null);
    }

    @Override
    public void execute(JDABuilder builder, MessageReceivedEvent event, String[] args) {

        // From: https://github.com/DV8FromTheWorld/Yui/blob/master/src/main/java/net/dv8tion/discord/commands/UptimeCommand.java

        final long duration = ManagementFactory.getRuntimeMXBean().getUptime();

        final long years = duration / 31104000000L;
        final long months = duration / 2592000000L % 12;
        final long days = duration / 86400000L % 30;
        final long hours = duration / 3600000L % 24;
        final long minutes = duration / 60000L % 60;
        final long seconds = duration / 1000L % 60;

        String uptime = (years == 0 ? "" : "**" + years + " Years**, ") + (months == 0 ? "" : "**" + months + " Months**, ") + (days == 0 ? "" : "**" + days + " Days**, ") + (hours == 0 ? "" : "**" + hours + " Hours**, ")
                + (minutes == 0 ? "" : "**" + minutes + " Minutes**, ") + (seconds == 0 ? "" : "**" + seconds + " Seconds**, ");

        uptime = replaceLast(uptime, ", ", "");
        uptime = replaceLast(uptime, ",", " and");

        event.getChannel().sendMessage(":alarm_clock: Bot uptime: " + uptime).queue();

    }

    private String replaceLast(final String text, final String regex, final String replacement) {
        return text.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
    }

}
