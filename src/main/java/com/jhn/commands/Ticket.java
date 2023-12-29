package com.jhn.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;

public class Ticket extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (!event.getName().equals("ticket")) return;
        TextChannel channel = (TextChannel) event.getOption("channel").getAsChannel();
        Guild guild = event.getGuild();
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Ticket");
        eb.setColor(Color.green);
        eb.setDescription("Aby utworzyc ticket, kliknij przycisk");
        Button ticket = Button.success("ticket", "Otworz ticket");
        channel.sendMessageEmbeds(eb.build()).addActionRow(ticket).queue();
    }
}
