package me.ultradev.skyblocksquadbot.api.menu.reaction.handlers.commands.games.rps;

import me.ultradev.skyblocksquadbot.api.menu.reaction.MenuReactionHandler;
import me.ultradev.skyblocksquadbot.commands.categories.games.RpsCommand;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;

public class RpsScissorsHandler extends MenuReactionHandler {

    @Override
    public void onReaction(GuildMessageReactionAddEvent event, Message message, String unicode) {
        RpsCommand.onReact(message, unicode);
    }

}
