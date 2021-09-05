package me.ultradev.skyblocksquadbot.api.util;

import java.util.Random;

public class NumberUtil {

    public static final Random RANDOM = new Random();

    public static int getRandomBetween(int min, int max) {
        return RANDOM.nextInt(max - min) + min;
    }

}
