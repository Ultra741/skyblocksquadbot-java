package me.ultradev.skyblocksquadbot.commands;

import me.ultradev.skyblocksquadbot.Main;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Command {

    private final String name;
    private final String description;
    private final String syntax;
    private final CommandCategory category;
    private final CommandPermission permission;

    public Command(String name, String description, String syntax, CommandCategory category, CommandPermission permission) {

        this.name = name;
        this.description = description;
        this.syntax = syntax;
        this.category = category;
        this.permission = permission;

        Main.getCommands().add(this);

    }

    public abstract boolean execute(JDABuilder builder, MessageReceivedEvent event, String[] args);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSyntax() {
        return syntax;
    }

    public CommandCategory getCategory() {
        return category;
    }

    public CommandPermission getPermission() {
        return permission;
    }

}
