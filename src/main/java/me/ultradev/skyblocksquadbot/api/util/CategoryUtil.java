package me.ultradev.skyblocksquadbot.api.util;

import me.ultradev.skyblocksquadbot.Main;
import me.ultradev.skyblocksquadbot.commands.Command;
import me.ultradev.skyblocksquadbot.commands.CommandCategory;
import me.ultradev.skyblocksquadbot.commands.categories.information.HelpCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;

public class CategoryUtil {

    public static void handleReaction(GuildMessageReactionAddEvent event, Message message, String unicode, CommandCategory category) {

        message.removeReaction(unicode, event.getUser()).queue();

        EmbedBuilder builder = new EmbedBuilder();

        builder.setTitle("HELP (" + category.getName().toUpperCase() + ")")
                .setDescription(category.getDescription() + " " + HelpCommand.embedDescription)
                .setColor(Main.embedColor)
                .setFooter(Main.embedFooter);

        for(Command element : category.getCommands()) {
            builder.addField(Main.prefix + element.getSyntax(), element.getDescription(), false);
        }

        message.editMessageEmbeds(builder.build()).queue();

    }

}
