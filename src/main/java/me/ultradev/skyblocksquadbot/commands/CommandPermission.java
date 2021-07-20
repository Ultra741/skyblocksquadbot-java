package me.ultradev.skyblocksquadbot.commands;

public enum CommandPermission {

    GUILD_MASTER("Guild Master", "683205412488478809", 5),
    ADMINISTRATOR("Administrator", "683205637001183365", 4),
    MODERATOR("Moderator", "683205888034603042", 3),
    HELPER("Helper", "683206050048114728", 2),
    NONE("None", "NONE", 1);

    private final String name;
    private final String roleID;

    private final int priority;

    CommandPermission(String name, String roleID, int priority) {
        this.name = name;
        this.roleID = roleID;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public String getRoleID() {
        return roleID;
    }

    public int getPriority() {
        return priority;
    }

}
