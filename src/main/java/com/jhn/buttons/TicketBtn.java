package com.jhn.buttons;

import com.jhn.Main;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;
import java.util.EnumSet;

public class TicketBtn extends ListenerAdapter {
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if(event.getComponentId().equals("ticket")) {
            Dotenv config = Main.getConfig();
            String staffrole = config.get("STAFFROLE");
            String ticket_category = config.get("TICKET_CATEGORY");
            Guild guild = event.getGuild();
            Member member = event.getMember();
            EnumSet<Permission> viewChannel = EnumSet.of(Permission.VIEW_CHANNEL);
            Role staffRole = guild.getRoleById(staffrole);
            Category category = guild.getCategoryById(ticket_category);
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Ticket");
            eb.setColor(Color.green);
            eb.setDescription("Describe your problem");
            eb.setFooter("JHN TICKET SYSTEM");
            net.dv8tion.jda.api.interactions.components.buttons.Button close = net.dv8tion.jda.api.interactions.components.buttons.Button.danger("close-ticket", "Close Ticket");
            guild.createTextChannel("t-"+member.getId(), category)
                    .addPermissionOverride(member, viewChannel, null)
                    .addPermissionOverride(guild.getPublicRole(), null, viewChannel)
                    .addPermissionOverride(staffRole, viewChannel, null)
                    .complete()
                    .sendMessageEmbeds(eb.build())
                    .setActionRow(close)
                    .queue();

            event.reply("Ticket was created").setEphemeral(true).queue();
        } else if (event.getComponentId().equals("close-ticket")) {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Ticket");
            eb.setColor(Color.red);
            eb.setDescription("You definitely want to close the ticket");
            eb.setFooter("JHN TICKET SYSTEM");
            net.dv8tion.jda.api.interactions.components.buttons.Button close = Button.danger("close-ticket2", "Close Ticket");
            event.getChannel().sendMessageEmbeds(eb.build()).addActionRow(close).queue();

        } else if (event.getComponentId().equals("close-ticket2")) {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Ticket");
            eb.setColor(Color.red);
            eb.setDescription("In 5 seconds your ticket will be deleted");
            eb.setFooter("JHN TICKET SYSTEM");
            event.getChannel().sendMessageEmbeds(eb.build()).queue();
            Guild guild = event.getGuild();
            String channelId = event.getChannel().getId();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            guild.getTextChannelById(channelId).delete().queue();
        }
    }
 }
