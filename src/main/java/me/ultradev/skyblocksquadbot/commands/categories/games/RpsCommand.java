package me.ultradev.skyblocksquadbot.commands.categories.games;

import me.ultradev.skyblocksquadbot.Main;
import me.ultradev.skyblocksquadbot.api.menu.reaction.MenuReaction;
import me.ultradev.skyblocksquadbot.api.menu.reaction.handlers.commands.games.rps.RpsPaperHandler;
import me.ultradev.skyblocksquadbot.api.menu.reaction.handlers.commands.games.rps.RpsRockHandler;
import me.ultradev.skyblocksquadbot.api.menu.reaction.handlers.commands.games.rps.RpsScissorsHandler;
import me.ultradev.skyblocksquadbot.api.util.NumberUtil;
import me.ultradev.skyblocksquadbot.commands.Command;
import me.ultradev.skyblocksquadbot.commands.CommandCategory;
import me.ultradev.skyblocksquadbot.commands.CommandPermission;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashMap;
import java.util.Map;

public class RpsCommand extends Command {

    public RpsCommand() {
        super("rps", "Play Rock, Paper, Scissors.", "rps",
                CommandCategory.FUN_AND_GAMES, CommandPermission.NONE, null);
    }

    @Override
    public void execute(JDABuilder builder, MessageReceivedEvent event, String[] args) {

        EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle("ROCK PAPER SCISSORS")
                .setDescription("React on this message to pick an option.\n\n**:rock: -> Rock\n\n:notepad_spiral: -> Paper\n\n:scissors: -> Scissors**")
                .setColor(Main.embedColor)
                .setFooter(Main.embedFooter);

        event.getMessage().getChannel().sendMessageEmbeds(embed.build()).queue((message -> {
            new MenuReaction(new RpsRockHandler(), message.getChannel(), event.getAuthor(), message.getId(), "U+1FAA8");
            new MenuReaction(new RpsPaperHandler(), message.getChannel(), event.getAuthor(), message.getId(), "U+1F5D2");
            new MenuReaction(new RpsScissorsHandler(), message.getChannel(), event.getAuthor(), message.getId(), "U+2702");
        }));

    }

    public static void onReact(Message message, String unicode) {

        Map<String, String> emojis = new HashMap<>();
        emojis.put("rock", ":moyai:"); emojis.put("paper", ":notepad_spiral:"); emojis.put("scissors", ":scissors:");

        String playerChoice = "";
        String botChoice = new String[] {"Rock", "Paper", "Scissors"}[NumberUtil.getRandomBetween(0, 2)];

        switch (unicode) {
            case "U+1FAA8" -> playerChoice = "Rock";
            case "U+1F5D2" -> playerChoice = "Paper";
            case "U+2702" -> playerChoice = "Scissors";
        }

        String playerEmoji = emojis.getOrDefault(playerChoice.toLowerCase(), ":question:");
        String botEmoji = emojis.getOrDefault(botChoice.toLowerCase(), ":question:");

        String gameStatus;

        switch (playerChoice) {
            case "Rock" -> gameStatus = switch (botChoice) {
                case "Rock" -> "tie";
                case "Paper" -> "bot_win";
                case "Scissors" -> "player_win";
                default -> "";
            };
            case "Paper" -> gameStatus = switch (botChoice) {
                case "Rock" -> "player_win";
                case "Paper" -> "tie";
                case "Scissors" -> "bot_win";
                default -> "";
            };
            case "Scissors" -> gameStatus = switch (botChoice) {
                case "Rock" -> "bot_win";
                case "Paper" -> "player_win";
                case "Scissors" -> "tie";
                default -> "";
            };
            default -> gameStatus = "";
        }

        if(gameStatus.equals("")) {
            message.editMessage("**Error:** Something went wrong while executing this command!").queue();
        }

        String[] tieMessages = new String[] {"GG!", "Congratulations!", "Nice game!"};
        String[] playerWinMessages = new String[] {"GG!", "Congratulations!", "Nice game!", "Damn, you're good!", "You're a RPS pro!", "Woah, you're skilled!"};
        String[] botWinMessages = new String[] {"GG!", "Congratulations!", "Nice game!", "You're good, but I'm better!", "I'm a pro!", "Poggers!"};

        String endMessage;
        StringBuilder descriptionBuilder = new StringBuilder();
        descriptionBuilder.append("You picked: ").append(playerEmoji).append("\nI picked: ").append(botEmoji);

        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Main.embedColor);

        switch (gameStatus) {
            case "tie" -> {
                endMessage = tieMessages[NumberUtil.getRandomBetween(0, tieMessages.length)];
                builder.setTitle("TIE!");
            }
            case "player_win" -> {
                endMessage = playerWinMessages[NumberUtil.getRandomBetween(0, playerWinMessages.length)];
                builder.setTitle("YOU WON!");
            }
            case "bot_win" -> {
                endMessage = botWinMessages[NumberUtil.getRandomBetween(0, botWinMessages.length)];
                builder.setTitle("I WON!");
            }
            default -> endMessage = "ERROR!";
        }

        builder.setDescription(descriptionBuilder).setFooter(endMessage);

        message.editMessageEmbeds(builder.build()).queue();
        message.clearReactions().queue();

    }

}
