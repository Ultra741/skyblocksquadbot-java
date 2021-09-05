package me.ultradev.skyblocksquadbot.commands.categories.information;

import me.ultradev.skyblocksquadbot.Main;
import me.ultradev.skyblocksquadbot.commands.Command;
import me.ultradev.skyblocksquadbot.commands.CommandCategory;
import me.ultradev.skyblocksquadbot.commands.CommandPermission;
import me.ultradev.skyblocksquadbot.api.menu.reaction.MenuReaction;
import me.ultradev.skyblocksquadbot.api.menu.reaction.handlers.help.GamesCategoryHandler;
import me.ultradev.skyblocksquadbot.api.menu.reaction.handlers.help.InformationCategoryHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class HelpCommand extends Command {

    public static String embedDescription = "\n\n[] = required / {} = optional / () = multiple possible arguments";

    public HelpCommand() {
        super("help", "Get a list of commands.", "help",
                CommandCategory.INFORMATION, CommandPermission.NONE, null);
    }

    @Override
    public void execute(JDABuilder builder, MessageReceivedEvent event, String[] args) {

        EmbedBuilder helpEmbed = new EmbedBuilder();

        helpEmbed.setTitle("HELP")
                .setDescription("See a list of commands categories below! " + embedDescription)
                .setColor(Main.embedColor)
                .setFooter(Main.embedFooter)
                .addBlankField(false)
                .addField(":information_source: __Information__",
                        "*" + CommandCategory.INFORMATION.getDescription() + "*\n**" + CommandCategory.INFORMATION.getCommandAmount() + " Commands**", true)
                .addField(":tada: __Fun & Games__",
                "*" + CommandCategory.FUN_AND_GAMES.getDescription() + "*\n**" + CommandCategory.FUN_AND_GAMES.getCommandAmount() + " Commands**", true);

        event.getMessage().getChannel().sendMessageEmbeds(helpEmbed.build()).queue((message -> {
            new MenuReaction(new InformationCategoryHandler(), message.getChannel(), event.getAuthor(), message.getId(), "U+2139");
            new MenuReaction(new GamesCategoryHandler(), message.getChannel(), event.getAuthor(), message.getId(), "U+1F389");
        }));

    }

}
