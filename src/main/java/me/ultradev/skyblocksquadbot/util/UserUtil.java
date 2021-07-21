package me.ultradev.skyblocksquadbot.util;

import me.ultradev.skyblocksquadbot.commands.CommandPermission;
import net.dv8tion.jda.api.entities.Member;

public class UserUtil {

    public static int getStaffRank(Member member) {

        CommandPermission[] permissions = CommandPermission.values();

        for(int i = 0; i < permissions.length; i++) {

            CommandPermission element = permissions[i];

            if(!((RoleUtil.findRole(member, element.getRoleID())) == null)) {
                return element.getPriority();
            }

        }

        return -1;

    }

    public static String getStaffRoleID(Member member) {

        int staffRank = getStaffRank(member);

        CommandPermission[] permissions = CommandPermission.values();

        for(int i = 0; i < permissions.length; i++) {

            CommandPermission element = permissions[i];

            if(staffRank == element.getPriority()) {
                return element.getRoleID();
            }

        }

        return "";

    }

}
