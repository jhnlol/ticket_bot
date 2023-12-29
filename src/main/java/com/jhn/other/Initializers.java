package com.jhn.other;

import com.jhn.buttons.TicketBtn;
import com.jhn.buttons.VerifyBtn;
import com.jhn.commands.Ping;
import com.jhn.commands.Ticket;
import com.jhn.commands.Weryfikacja;
import com.jhn.events.onReady;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Initializers {
    public static void initializeEventListeners(JDABuilder builder) {
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        builder.addEventListeners(new Ping());
        builder.addEventListeners(new onReady());
        builder.addEventListeners(new Weryfikacja());
        builder.addEventListeners(new TicketBtn());
        builder.addEventListeners(new VerifyBtn());
        builder.addEventListeners(new Ticket());
    }

    public static void initializeCommands(JDA jda) {
        jda.updateCommands().addCommands(
                Commands.slash("ping", "test command").setGuildOnly(true),
                Commands.slash("ticket", "To setup ticket").setGuildOnly(true)
                        .addOption(OptionType.CHANNEL, "channel", "Podaj kanal do ticketow", true),
                Commands.slash("verify", "To setup verify").setGuildOnly(true)
                        .addOption(OptionType.CHANNEL, "channel", "Podaj kanal", true)
        ).queue();

    }

}
