package org.qiushawa.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Ping {
    public static void ping_command(SlashCommandInteractionEvent event){
        long time = System.currentTimeMillis();
        event.reply("ping...").setEphemeral(true) // reply or acknowledge
                .flatMap(v ->
                        event.getHook().editOriginalFormat("ping: %d ms", System.currentTimeMillis() - time) // then edit original
                ).queue();
    }
}
