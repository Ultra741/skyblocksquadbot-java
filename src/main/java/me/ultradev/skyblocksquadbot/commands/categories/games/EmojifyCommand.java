package me.ultradev.skyblocksquadbot.commands.categories.games;

import me.ultradev.skyblocksquadbot.api.util.MessageUtil;
import me.ultradev.skyblocksquadbot.commands.Command;
import me.ultradev.skyblocksquadbot.commands.CommandCategory;
import me.ultradev.skyblocksquadbot.commands.CommandPermission;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmojifyCommand extends Command {

    public EmojifyCommand() {
        super("emojify", "Convert a text into emoji's.", "emojify [text]",
                CommandCategory.FUN_AND_GAMES, CommandPermission.NONE, null);
    }

    @Override
    public void execute(JDABuilder builder, MessageReceivedEvent event, String[] args) {

        if(args.length < 2) {
            MessageUtil.syntaxError(event.getChannel(), this);
            return;
        }

        StringBuilder sBuilder = new StringBuilder();
        String[] chars = event.getMessage().getContentRaw().split("");


        List<String> alphabet = List.of("abcdefghijklmnopqrstuvwxyz".split(""));

        Map<String, String> map = new HashMap<>();

        map.put("0", "zero"); map.put("1", "one"); map.put("2", "two"); map.put("3", "three"); map.put("4", "four");
        map.put("5", "five"); map.put("6", "six"); map.put("7", "seven"); map.put("8", "eight"); map.put("9", "nine");
        map.put("#", "hash"); map.put("*", "asterisk");

        for(int i = 0; i < chars.length; i++) {

            if(i <= 7) continue;
            String element = chars[i].toLowerCase();

            if(alphabet.contains(element)) {
                sBuilder.append(":regional_indicator_").append(element).append(":");
            } else if(map.containsKey(element)) {
                sBuilder.append(":").append(map.get(element)).append(":");
            } else if(element.equals(" ")) {
                sBuilder.append(" ");
            } else {
                event.getChannel().sendMessage("**Error:** The " + element + " character is not supported!").queue();
                return;
            }

        }

        event.getChannel().sendMessage(sBuilder).queue();

    }

}
