package me.ultradev.skyblocksquadbot.events;

import me.ultradev.skyblocksquadbot.Main;
import me.ultradev.skyblocksquadbot.commands.Command;
import me.ultradev.skyblocksquadbot.commands.CommandPermission;
import me.ultradev.skyblocksquadbot.util.UserUtil;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class Message extends ListenerAdapter {

    public static final String prefix = ">";

    public static final Color embedColor = new Color(0x00bfff);
    public static final String embedFooter = "SkyblockSquad Bot - Made for SkyblockSquad Guild";

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if(event.getMessage().getContentRaw().startsWith(prefix)) {

            for(int i = 0; i < Main.getCommands().size(); i++) {

                Command element = Main.getCommands().get(i);

                String[] args = event.getMessage().getContentRaw().split(" ");

                if(args[0].equalsIgnoreCase(prefix + element.getName()) ||
                        element.getAliases().contains(args[0].substring(1))) {

                    CommandPermission permission = element.getPermission();

                    if(permission.getRoleID().equals(String.valueOf(UserUtil.getStaffRank(event.getMember()))) ||
                            permission.getRoleID().equalsIgnoreCase("NONE")) {

                        element.execute(Main.getBuilder(), event, args);

                    }

                }

            }

        }

    }

}
