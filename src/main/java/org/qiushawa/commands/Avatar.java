package org.qiushawa.commands;

import net.dv8tion.jda.api.EmbedBuilder;

import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.components.buttons.Button;


import java.awt.Color;


public class Avatar {
    public static void avatar_command(SlashCommandInteractionEvent event){

        String url= event.getUser().getAvatar().getUrl(1024);
        String name =event.getUser().getName();
        OptionMapping m = event.getOption("user");
        if (m!=null){
            url= m.getAsUser().getAvatar().getUrl(1024);
            name=m.getAsUser().getName();
        }
        EmbedBuilder eb = new EmbedBuilder();
        eb.setImage(url)
                .setColor(Color.magenta)
                        .setTitle(name+"的頭像");
        event.replyEmbeds(eb.build())
                .addActionRow(Button.link(url,"\uD83D\uDDBC\uFE0F️ 查看大圖"))
                .addActionRow(Button.link("https://github.com/SMPC-Qiushengjie/skb-bot","Github")
                        .withEmoji(Emoji.fromFormatted("<:GitHub:1156111033392451628>"))
                )
                .queue();
    }
}
