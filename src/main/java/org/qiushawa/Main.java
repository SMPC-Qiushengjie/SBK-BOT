package org.qiushawa;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.qiushawa.RegisterCommands.RegisterSlashCommands;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;


import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws LoginException {

        File configFile = new File("application.yaml");
        if (!configFile.exists()) {

            Map<String, Object> defaultConfig = generateDefaultConfig();
            writeConfigToFile(defaultConfig, configFile);
        }
        Map<String, Object> config = loadConfigFromFile(configFile);
        assert config != null;
        Map<String, Object> bot_info= (Map<String, Object>) config.get("BOT-INFO");
        String activity = (String) bot_info.get("Activity");
        String token = (String) bot_info.get("Token");
        bot(token, activity);
    }
    private static Map<String, Object> generateDefaultConfig() {
        return Map.of(
                "BOT-INFO",Map.of("Token", "你的機器人Token",
                        "Activity", "你的機器人狀態")

        );
    }
    private static void writeConfigToFile(Map<String, Object> config, File configFile) {
        try {
            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            Yaml yaml = new Yaml(options);
            FileWriter writer = new FileWriter(configFile);
            yaml.dump(config, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static Map<String, Object> loadConfigFromFile(File configFile) {

        try {
            Yaml yaml = new Yaml();
            InputStream inputStream = configFile.exists()
                    ? configFile.toURI().toURL().openStream()
                    : Main.class.getResourceAsStream("/application.yaml");
            return yaml.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static void bot(String token, String activity) throws LoginException {
        JDA jda = JDABuilder.createLight(token, Collections.emptyList())
                .setActivity(Activity.playing(activity))
                .addEventListeners(new RegisterSlashCommands())
                .build();

        // Sets the global command list to the provided commands (removing all others)
        jda.updateCommands().addCommands(
                Commands.slash("ping", "機器人延遲"),
                Commands.slash("avatar", "使用者頭貼")
                        .addOption(OptionType.USER, "user", "要查看的對象", false)
        ).queue();

    }


}