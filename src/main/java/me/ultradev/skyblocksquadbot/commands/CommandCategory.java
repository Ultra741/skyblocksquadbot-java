package me.ultradev.skyblocksquadbot.commands;

import me.ultradev.skyblocksquadbot.Main;

import java.util.ArrayList;
import java.util.List;

public enum CommandCategory {

    INFORMATION("Information", "Commands that give information.");

    private final String name;
    private final String description;

    CommandCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCommandAmount() {

        int result = 0;

        for(int i = 0; i < Main.getCommands().size(); i++) {

            Command element = Main.getCommands().get(i);

            if(element.getCategory().getName().equals(this.getName())) {
                result++;
            }

        }

        return result;

    }

    public List<Command> getCommands() {

        List<Command> result = new ArrayList<>();

        for(int i = 0; i < Main.getCommands().size(); i++) {

            Command element = Main.getCommands().get(i);

            if(element.getCategory().getName().equals(this.getName())) {
                result.add(element);
            }

        }

        return result;

    }

}
