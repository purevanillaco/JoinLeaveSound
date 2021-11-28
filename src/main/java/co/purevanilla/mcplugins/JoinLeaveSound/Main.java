package co.purevanilla.mcplugins.JoinLeaveSound;

import org.bukkit.Sound;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();

        Sound playerSound = getConfig().getString("player.join.sound").equalsIgnoreCase("none") ? null : Sound.valueOf(getConfig().getString("player.join.sound"));
        Sound serverSound = getConfig().getString("server.join.sound").equalsIgnoreCase("none") ? null : Sound.valueOf(getConfig().getString("server.join.sound"));
        Sound leaveSound = getConfig().getString("server.leave.sound").equalsIgnoreCase("none") ? null : Sound.valueOf(getConfig().getString("server.leave.sound"));

        getServer().getPluginManager().registerEvents(new Listener(this, playerSound, getConfig().getDouble("player.join.pitch"), serverSound, getConfig().getDouble("server.join.pitch"), leaveSound, getConfig().getDouble("server.leave.pitch")), this);
    }
}
