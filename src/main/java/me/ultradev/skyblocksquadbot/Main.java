package me.ultradev.skyblocksquadbot;

import me.ultradev.skyblocksquadbot.commands.Command;
import me.ultradev.skyblocksquadbot.commands.categories.games.RpsCommand;
import me.ultradev.skyblocksquadbot.commands.categories.information.*;
import me.ultradev.skyblocksquadbot.config.Config;
import me.ultradev.skyblocksquadbot.events.GuildReactionAdd;
import me.ultradev.skyblocksquadbot.events.Message;
import me.ultradev.skyblocksquadbot.api.menu.reaction.MenuReaction;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static JDABuilder builder;

    private static final List<Command> commands = new ArrayList<>();
    private static final List<MenuReaction> menuReactions = new ArrayList<>();

    public static final String prefix = ">";

    public static final Color embedColor = new Color(0x00bfff);
    public static final String embedFooter = "SkyblockSquad Bot - Made for SkyblockSquad Guild";

    public static void main(String[] args) {

        builder = JDABuilder.createDefault(Config.getToken())
                .addEventListeners(new Message())
                .addEventListeners(new GuildReactionAdd());

        new HelpCommand();
        new HelloCommand();
        new PingCommand();
        new UptimeCommand();
        new CmdCommand();

        new RpsCommand();

        new DebugCommand();

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
