package me.ultradev.skyblocksquadbot.api.menu.reaction.handlers.help;

import me.ultradev.skyblocksquadbot.api.menu.reaction.MenuReactionHandler;
import me.ultradev.skyblocksquadbot.api.util.CategoryUtil;
import me.ultradev.skyblocksquadbot.commands.CommandCategory;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;

public class GamesCategoryHandler extends MenuReactionHandler {

    @Override
    public void onReaction(GuildMessageReactionAddEvent event, Message message, String unicode) {
        CategoryUtil.handleReaction(event, message, unicode, CommandCategory.FUN_AND_GAMES);
    }

}
