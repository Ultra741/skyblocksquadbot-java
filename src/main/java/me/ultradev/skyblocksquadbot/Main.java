package me.ultradev.skyblocksquadbot;

import me.ultradev.skyblocksquadbot.commands.Command;
import me.ultradev.skyblocksquadbot.commands.categories.information.HelloCommand;
import me.ultradev.skyblocksquadbot.commands.categories.information.HelpCommand;
import me.ultradev.skyblocksquadbot.commands.categories.information.PingCommand;
import me.ultradev.skyblocksquadbot.commands.categories.information.UptimeCommand;
import me.ultradev.skyblocksquadbot.config.Config;
import me.ultradev.skyblocksquadbot.events.GuildReactionAdd;
import me.ultradev.skyblocksquadbot.events.Message;
import me.ultradev.skyblocksquadbot.util.menu.reaction.MenuReaction;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static JDABuilder builder;

    private static final List<Command> commands = new ArrayList<>();
    private static List<MenuReaction> menuReactions = new ArrayList<>();

    public static void main(String[] args) {

        builder = JDABuilder.createDefault(Config.getToken())
                .addEventListeners(new Message())
                .addEventListeners(new GuildReactionAdd());

        new HelpCommand();
        new HelloCommand();
        new PingCommand();
        new UptimeCommand();

        builder.setActivity(Activity.playing("Roblox"));

        try {
            builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }

    }

    public static List<Command> getCommands() {
        return commands;
    }

    public static List<MenuReaction> getMenuReactions() {
        return menuReactions;
    }

    public static JDABuilder getBuilder() {
        return builder;
    }

}
