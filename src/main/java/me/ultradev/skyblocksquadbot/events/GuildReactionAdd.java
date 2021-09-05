package me.ultradev.skyblocksquadbot.events;

import me.ultradev.skyblocksquadbot.Main;
import me.ultradev.skyblocksquadbot.api.menu.reaction.MenuReaction;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class GuildReactionAdd extends ListenerAdapter {

    @Override
    public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent event) {

        for(MenuReaction reaction : Main.getMenuReactions()) {

            if(event.getMessageId().equals(reaction.getMessageID())) {

                if(event.getReactionEmote().toString().toUpperCase().endsWith(reaction.getUnicode())) {

                    if(event.getUser().equals(reaction.getUser())) {

                        event.getChannel().retrieveMessageById(event.getMessageId()).queue((message -> {
                            if(reaction.isOneTime()) {
                                Main.getMenuReactions().remove(reaction);
                            }
                            reaction.getHandler().onReaction(event, message, reaction.getUnicode());
                        }));

                    } else {

                        if(!(event.getUser().isBot())) {

                            event.getChannel().retrieveMessageById(event.getMessageId()).queue((message -> {
                                message.removeReaction(reaction.getUnicode(), event.getUser()).queue();
                            }));

                        }

                    }

                }

            }

        }

    }

}
