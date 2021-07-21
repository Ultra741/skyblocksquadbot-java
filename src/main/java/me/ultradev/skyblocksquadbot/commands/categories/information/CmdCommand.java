package me.ultradev.skyblocksquadbot.commands.categories.information;

import me.ultradev.skyblocksquadbot.Main;
import me.ultradev.skyblocksquadbot.commands.Command;
import me.ultradev.skyblocksquadbot.commands.CommandCategory;
import me.ultradev.skyblocksquadbot.commands.CommandPermission;
import me.ultradev.skyblocksquadbot.util.MessageUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Collections;

public class CmdCommand extends Command {

    public CmdCommand() {
        super("command", "Get information about a command.", "command [(command name/command alias)]",
                CommandCategory.INFORMATION, CommandPermission.NONE, Collections.singletonList("cmd"));
    }

    @Override
    public void execute(JDABuilder builder, MessageReceivedEvent event, String[] args) {

        if(!(args.length == 2)) {
            MessageUtil.syntaxError(event.getChannel(), this);
            return;
        }

        String command = args[1];

        boolean commandFound = false;

        for(int i = 0; i < Main.getCommands().size(); i++) {

            Command element = Main.getCommands().get(i);

            if(element.getName().equalsIgnoreCase(command) || element.getAliases().contains(command)) {

                EmbedBuilder commandInformation = new EmbedBuilder();

                StringBuilder aliasesBuilder = new StringBuilder();

                for(int x = 0; x < element.getAliases().size(); x++) {
                    aliasesBuilder.append(", ").append(element.getAliases().get(x));
                }

                String aliases = aliasesBuilder.toString().replaceFirst(", ", "");

                if(element.getAliases().size() == 0) {
                    aliases = "None";
                }

                commandInformation.setTitle("COMMAND INFORMATION (" + command.toUpperCase() + ")")
                        .setColor(Main.embedColor)
                        .setFooter(Main.embedFooter)
                        .addField("Command Name", element.getName(), false)
                        .addField("Command Description", element.getDescription(), false)
                        .addField("Command Category", element.getCategory().toString(), false)
                        .addField("Command Aliases", aliases, false);

                event.getMessage().getChannel().sendMessageEmbeds(commandInformation.build()).queue();

                commandFound = true;

                break;

            }

        }

        if(!(commandFound)) {
            event.getMessage().getChannel().sendMessage("**Error:** Couldn't find a command with that name or alias!").queue();
        }

    }

}
