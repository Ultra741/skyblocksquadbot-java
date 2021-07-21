package me.ultradev.skyblocksquadbot.commands.categories.information;

import me.ultradev.skyblocksquadbot.commands.Command;
import me.ultradev.skyblocksquadbot.commands.CommandCategory;
import me.ultradev.skyblocksquadbot.commands.CommandPermission;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class HelloCommand extends Command {

    public HelloCommand() {
        super("hello", "Hello. Why not?", "hello", CommandCategory.INFORMATION, CommandPermission.NONE, null);
    }

    @Override
    public boolean execute(JDABuilder builder, MessageReceivedEvent event, String[] args) {

        event.getMessage().getChannel().sendMessage("Go away.").queue();

        return true;

    }

}
