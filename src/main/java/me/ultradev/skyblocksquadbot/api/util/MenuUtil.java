package me.ultradev.skyblocksquadbot.api.util;

import me.ultradev.skyblocksquadbot.Main;
import me.ultradev.skyblocksquadbot.api.menu.reaction.MenuReaction;
import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MenuUtil {

    public static List<MenuReaction> removeMenus(User user, String id) {

        List<MenuReaction> res = new ArrayList<>();
        for(MenuReaction reaction : Main.getMenuReactions()) {
            if(reaction.getUser().getId().equals(user.getId()) && reaction.getID().equals(id)) {
                res.add(reaction);
            }
        }

        for(MenuReaction reaction : res) {
            Main.getMenuReactions().remove(reaction);
        }

        return res;

    }

    public static void removeMenus(User user, String id, Consumer<MenuReaction> consumer) {
        List<MenuReaction> reactions = removeMenus(user, id);
        for(MenuReaction reaction : reactions) {
            consumer.accept(reaction);
        }
    }

}
