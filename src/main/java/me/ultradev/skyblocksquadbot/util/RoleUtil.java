package me.ultradev.skyblocksquadbot.util;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import java.util.List;

public class RoleUtil {

    public static Role findRole(Member member, String id) {

        List<Role> roles = member.getRoles();

        return roles.stream()
                .filter(role -> role.getId().equals(id))
                .findFirst()
                .orElse(null);

    }

}
