package me.ultradev.skyblocksquadbot.commands.categories.information;

import me.ultradev.skyblocksquadbot.Main;
import me.ultradev.skyblocksquadbot.api.menu.reaction.MenuReaction;
import me.ultradev.skyblocksquadbot.commands.Command;
import me.ultradev.skyblocksquadbot.commands.CommandCategory;
import me.ultradev.skyblocksquadbot.commands.CommandPermission;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class DebugCommand extends Command {

    public DebugCommand() {
        super("debug", "debug", "debug",
                CommandCategory.INFORMATION, CommandPermission.NONE, null);
    }

    @Override
    public void execute(JDABuilder builder, MessageReceivedEvent event, String[] args) {
        for(MenuReaction reaction : Main.getMenuReactions()) {
            event.getChannel().sendMessage(reaction.getHandler().toString()).queue();
        }
    }

}
