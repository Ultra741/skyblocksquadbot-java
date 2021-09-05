package me.ultradev.skyblocksquadbot.api.menu.reaction;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;

public abstract class MenuReactionHandler {

    public abstract void onReaction(GuildMessageReactionAddEvent event, Message message, String unicode);

}
