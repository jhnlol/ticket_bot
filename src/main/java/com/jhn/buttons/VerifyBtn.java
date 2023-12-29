package com.jhn.buttons;

import com.jhn.Main;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class VerifyBtn extends ListenerAdapter {
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getComponentId().equals("verify")) {
            Dotenv config = Main.getConfig();
            String verify_role = config.get("VERIFY_ROLE");
            System.out.println(event.getMember().getUser().getName());
            event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById(verify_role)).queue();
            event.reply("Zweryfikowano!").setEphemeral(true).queue();
        }
    }
}
