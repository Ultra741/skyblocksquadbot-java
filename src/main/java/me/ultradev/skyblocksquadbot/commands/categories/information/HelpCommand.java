package me.ultradev.skyblocksquadbot.commands.categories.information;

import me.ultradev.skyblocksquadbot.Main;
import me.ultradev.skyblocksquadbot.api.util.MenuUtil;
import me.ultradev.skyblocksquadbot.commands.Command;
import me.ultradev.skyblocksquadbot.commands.CommandCategory;
import me.ultradev.skyblocksquadbot.commands.CommandPermission;
import me.ultradev.skyblocksquadbot.api.menu.reaction.MenuReaction;
import me.ultradev.skyblocksquadbot.api.menu.reaction.handlers.help.GamesCategoryHandler;
import me.ultradev.skyblocksquadbot.api.menu.reaction.handlers.help.InformationCategoryHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.function.Consumer;

public class HelpCommand extends Command {

    public static String embedDescription = "\n\n[] = required / {} = optional / () = multiple possible arguments";

    public HelpCommand() {
        super("help", "Get a list of commands.", "help",
                CommandCategory.INFORMATION, CommandPermission.NONE, null);
    }

    @Override
    public void execute(JDABuilder builder, MessageReceivedEvent event, String[] args) {

        Consumer<MenuReaction> consumer = menu -> {
            menu.getMessage().editMessage("**Timed out.**").queue();
            menu.getMessage().editMessageEmbeds().queue();
            menu.getMessage().clearReactions().queue();
        };

        MenuUtil.removeMenus(event.getAuthor(), "help_information", consumer);
        MenuUtil.removeMenus(event.getAuthor(), "help_fun_and_games");

        EmbedBuilder helpEmbed = new EmbedBuilder();

        helpEmbed.setTitle("HELP")
                .setDescription("See a list of commands categories below! " + embedDescription)
                .setColor(Main.embedColor)
                .setFooter(Main.embedFooter)
                .addBlankField(false);

        for(CommandCategory category : CommandCategory.values()) {
            helpEmbed.addField(category.getEmoji() + "__" + category.getName() + "__",
                    "*" + category.getDescription() + "*\n**" + category.getCommandAmount() + " Commands**", false);
        }

        event.getMessage().getChannel().sendMessageEmbeds(helpEmbed.build()).queue((message -> {
            new MenuReaction("help_information", new InformationCategoryHandler(), message.getChannel(), event.getAuthor(), message.getId(), "U+2139", false);
            new MenuReaction("help_fun_and_games", new GamesCategoryHandler(), message.getChannel(), event.getAuthor(), message.getId(), "U+1F389", false);
        }));

    }

}
