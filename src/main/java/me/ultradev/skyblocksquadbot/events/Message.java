package me.ultradev.skyblocksquadbot.events;

import me.ultradev.skyblocksquadbot.Main;
import me.ultradev.skyblocksquadbot.commands.Command;
import me.ultradev.skyblocksquadbot.commands.CommandPermission;
import me.ultradev.skyblocksquadbot.api.util.UserUtil;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Message extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if(event.getMessage().getContentRaw().startsWith(Main.prefix)) {

            for(int i = 0; i < Main.getCommands().size(); i++) {

                Command element = Main.getCommands().get(i);

                String[] args = event.getMessage().getContentRaw().split(" ");

                if(args[0].equalsIgnoreCase(Main.prefix + element.getName()) ||
                        element.getAliases().contains(args[0].substring(1))) {

                    CommandPermission permission = element.getPermission();


                    if(permission.getRoleID().equals(UserUtil.getStaffRoleID(event.getMember())) ||
                            permission.getRoleID().equalsIgnoreCase("NONE")) {

                        element.execute(Main.getBuilder(), event, args);

                    } else {

                        event.getChannel().sendMessage("**Error:** You don't have permission to do this!").queue();

                    }

                }

            }

        }

    }

}
