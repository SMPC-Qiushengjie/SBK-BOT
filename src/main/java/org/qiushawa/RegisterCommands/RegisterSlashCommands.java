package org.qiushawa.RegisterCommands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static org.qiushawa.commands.Avatar.avatar_command;
import static org.qiushawa.commands.Ping.ping_command;


public class RegisterSlashCommands extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event)
    {

        if (event.getName().equals("ping")) {
            ping_command(event);
        }
        if (event.getName().equals("avatar")) {
            avatar_command(event);
        }
    }
}
