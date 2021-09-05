package me.ultradev.skyblocksquadbot.api.cooldowns;

import net.dv8tion.jda.api.entities.User;

import java.util.HashMap;
import java.util.Map;

public class CooldownManager {

    private final Map<User, Long> cooldowns = new HashMap<>();

    public void setCooldown(User player, long time) {

        if(time < 1) {
            cooldowns.remove(player);
        } else {
            cooldowns.put(player, time);
        }

    }

    public long getCooldown(User player) {
        return cooldowns.getOrDefault(player, Long.parseLong("0"));
    }

    public long getCooldownTime(User player) {
        return System.currentTimeMillis() - getCooldown(player);
    }

    public String getCooldownString(long ms, User player) {
        return String.valueOf((ms - getCooldownTime(player)) / 1000d);
    }

}
