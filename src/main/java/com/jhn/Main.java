package com.jhn;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import com.jhn.other.Initializers;
public class Main {
    static Dotenv config;
    public static void main(String[] args) {
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");
        JDABuilder builder = JDABuilder.createDefault(token);
        Initializers.initializeEventListeners(builder);
        JDA jda = builder.build();
        Initializers.initializeCommands(jda);
    }
    public static Dotenv getConfig() {
        return config;
    }
}