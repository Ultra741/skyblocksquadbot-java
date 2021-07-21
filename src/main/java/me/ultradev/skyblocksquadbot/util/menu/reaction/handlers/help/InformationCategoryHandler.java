package me.ultradev.skyblocksquadbot.util.menu.reaction.handlers.help;

import me.ultradev.skyblocksquadbot.Main;
import me.ultradev.skyblocksquadbot.commands.Command;
import me.ultradev.skyblocksquadbot.commands.CommandCategory;
import me.ultradev.skyblocksquadbot.commands.categories.information.HelpCommand;
import me.ultradev.skyblocksquadbot.util.menu.reaction.MenuReactionHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;

public class InformationCategoryHandler extends MenuReactionHandler {

    @Override
    public void onReaction(GuildMessageReactionAddEvent event, Message message, String unicode) {

        message.removeReaction(unicode, event.getUser()).queue();

        EmbedBuilder builder = new EmbedBuilder();

        builder.setTitle("HELP (INFORMATION)")
                .setDescription(CommandCategory.INFORMATION.getDescription() + " " + HelpCommand.embedDescription)
                .setColor(Main.embedColor)
                .setFooter(Main.embedFooter);

        for(Command element : CommandCategory.INFORMATION.getCommands()) {
            builder.addField(Main.prefix + element.getSyntax(), element.getDescription(), false);
        }

        message.editMessageEmbeds(builder.build()).queue();

    }

}
