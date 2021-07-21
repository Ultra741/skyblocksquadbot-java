package me.ultradev.skyblocksquadbot.commands.categories.information;

import me.ultradev.skyblocksquadbot.commands.Command;
import me.ultradev.skyblocksquadbot.commands.CommandCategory;
import me.ultradev.skyblocksquadbot.commands.CommandPermission;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;

public class PingCommand extends Command {

    public PingCommand() {
        super("ping", "Get the bot's ping.", "ping", CommandCategory.INFORMATION, CommandPermission.NONE, Collections.singletonList("pong"));
    }

    @Override
    public boolean execute(JDABuilder builder, MessageReceivedEvent event, String[] args) {

        event.getMessage().getChannel().sendMessage("Ping...").queue((message -> {

            long ping = event.getMessage().getTimeCreated().until(message.getTimeCreated(), ChronoUnit.MILLIS);
            message.editMessage(":ping_pong: **Ping:** " + ping + " ms\n**Websocket:** " + event.getJDA().getGatewayPing() + " ms").queue();

        }));

        return true;

    }

}
