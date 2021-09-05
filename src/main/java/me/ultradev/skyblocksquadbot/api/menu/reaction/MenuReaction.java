package me.ultradev.skyblocksquadbot.api.menu.reaction;

import me.ultradev.skyblocksquadbot.Main;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class MenuReaction {

    private final MenuReactionHandler handler;

    private final MessageChannel channel;

    private final User user;

    private final String messageID;
    private final String unicode;

    public MenuReaction(MenuReactionHandler handler, MessageChannel channel, User user, String messageID, String unicode) {

        this.handler = handler;
        this.channel = channel;
        this.user = user;
        this.messageID = messageID;
        this.unicode = unicode;

        channel.retrieveMessageById(messageID).queue((message -> {
            message.addReaction(unicode).queue();
        }));

        Main.getMenuReactions().add(this);

    }

    public MenuReactionHandler getHandler() {
        return handler;
    }

    public MessageChannel getChannel() {
        return channel;
    }

    public User getUser() {
        return user;
    }

    public String getMessageID() {
        return messageID;
    }

    public String getUnicode() {
        return unicode;
    }

}
