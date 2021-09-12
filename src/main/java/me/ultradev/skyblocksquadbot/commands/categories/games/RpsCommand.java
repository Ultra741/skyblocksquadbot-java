package me.ultradev.skyblocksquadbot.commands.categories.games;

import me.ultradev.skyblocksquadbot.Main;
import me.ultradev.skyblocksquadbot.api.menu.reaction.MenuReaction;
import me.ultradev.skyblocksquadbot.api.menu.reaction.handlers.commands.games.rps.RpsOptionHandler;
import me.ultradev.skyblocksquadbot.api.menu.reaction.handlers.commands.games.rps.RpsPlayAgainHandler;
import me.ultradev.skyblocksquadbot.api.util.MenuUtil;
import me.ultradev.skyblocksquadbot.api.util.NumberUtil;
import me.ultradev.skyblocksquadbot.commands.Command;
import me.ultradev.skyblocksquadbot.commands.CommandCategory;
import me.ultradev.skyblocksquadbot.commands.CommandPermission;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class RpsCommand extends Command {

    public RpsCommand() {
        super("rps", "Play Rock, Paper, Scissors.", "rps",
                CommandCategory.FUN_AND_GAMES, CommandPermission.NONE, null);
    }

    @Override
    public void execute(JDABuilder builder, MessageReceivedEvent event, String[] args) {

        Consumer<MenuReaction> consumer = menu -> {
            menu.getMessage().editMessage("**Timed out.**").queue();
            menu.getMessage().editMessageEmbeds().queue();
            menu.getMessage().clearReactions().queue();
        };

        MenuUtil.removeMenus(event.getAuthor(), "rps_rock", consumer);
        MenuUtil.removeMenus(event.getAuthor(), "rps_paper");
        MenuUtil.removeMenus(event.getAuthor(), "rps_scissors");
        MenuUtil.removeMenus(event.getAuthor(), "rps_play_again", consumer);

        event.getMessage().getChannel().sendMessageEmbeds(getEmbed().build()).queue((message -> {
            new MenuReaction("rps_rock", new RpsOptionHandler(), message.getChannel(), event.getAuthor(), message.getId(), "U+1FAA8", true);
            new MenuReaction("rps_paper", new RpsOptionHandler(), message.getChannel(), event.getAuthor(), message.getId(), "U+1F5D2", true);
            new MenuReaction("rps_scissors", new RpsOptionHandler(), message.getChannel(), event.getAuthor(), message.getId(), "U+2702", true);
        }));

    }

    public static EmbedBuilder getEmbed() {

        EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle("ROCK PAPER SCISSORS")
                .setDescription("React on this message to pick an option.\n\n**:rock: -> Rock\n\n:notepad_spiral: -> Paper\n\n:scissors: -> Scissors**")
                .setColor(Main.embedColor)
                .setFooter(Main.embedFooter);

        return embed;

    }

    public static void onReact(GuildMessageReactionAddEvent event, Message message, String unicode) {

        MenuUtil.removeMenus(event.getUser(), "rps_rock");
        MenuUtil.removeMenus(event.getUser(), "rps_paper");
        MenuUtil.removeMenus(event.getUser(), "rps_scissors");
        MenuUtil.removeMenus(event.getUser(), "rps_play_again");

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
        message.clearReactions().queue((msg) -> {
            new MenuReaction("rps_play_again", new RpsPlayAgainHandler(), message.getChannel(), event.getUser(), message.getId(), "U+1F504", true);
        });


    }

    public static void playAgain(GuildMessageReactionAddEvent event, Message message) {

        MenuUtil.removeMenus(event.getUser(), "rps_play_again");

        message.clearReactions().queue();
        message.editMessageEmbeds(getEmbed().build()).queue((msg -> {
            new MenuReaction("rps_rock", new RpsOptionHandler(), msg.getChannel(), event.getUser(), msg.getId(), "U+1FAA8", true);
            new MenuReaction("rps_paper", new RpsOptionHandler(), msg.getChannel(), event.getUser(), msg.getId(), "U+1F5D2", true);
            new MenuReaction("rps_scissors", new RpsOptionHandler(), msg.getChannel(), event.getUser(), msg.getId(), "U+2702", true);
        }));

    }

}
