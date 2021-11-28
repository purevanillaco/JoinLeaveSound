package co.purevanilla.mcplugins.JoinLeaveSound;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class Listener implements org.bukkit.event.Listener {

    Main main;

    Sound joinSoundPlayer = null;
    Double joinPitchPlayer = 1.0;
    Sound joinSoundAll = null;
    Double joinPitchAll = 1.0;

    Sound leaveSoundAll = null;
    Double leavePitchAll = 1.0;

    Listener(Main main, Sound joinPlayer, Double joinPitchPlayer, Sound joinAll, Double joinPitchAll, Sound leaveAll, Double leavePitchAll){
        this.main = main;
        this.joinSoundPlayer=joinPlayer;
        this.joinPitchPlayer=joinPitchPlayer;
        this.joinSoundAll=joinAll;
        this.joinPitchAll=joinPitchAll;
        this.leaveSoundAll=leaveAll;
        this.leavePitchAll=leavePitchAll;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        Bukkit.getScheduler().runTaskLater(this.main, new Runnable() {
            @Override
            public void run() {
                List<Player> players = (List<Player>) Bukkit.getServer().getOnlinePlayers();
                for (Player onlinePlayer:players) {
                    if(joinSoundPlayer!=null && onlinePlayer==player){
                        onlinePlayer.playSound(onlinePlayer.getLocation(),joinSoundPlayer, SoundCategory.MUSIC, 10^5,1);
                    } else if(joinSoundAll!=null && onlinePlayer!=player) {
                        onlinePlayer.playSound(onlinePlayer.getLocation(),joinSoundAll, SoundCategory.PLAYERS, 10^5,1);
                    }
                }
            }
        },10L);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        if(leaveSoundAll!=null){
            Bukkit.getScheduler().runTaskLater(this.main, new Runnable() {
                @Override
                public void run() {
                    List<Player> players = (List<Player>) Bukkit.getServer().getOnlinePlayers();
                    for (Player onlinePlayer:players) {
                        onlinePlayer.playSound(onlinePlayer.getLocation(),leaveSoundAll, SoundCategory.MUSIC, 10^5,1);
                    }
                }
            },10L);
        }
    }

}
