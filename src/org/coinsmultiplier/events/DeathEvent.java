package org.coinsmultiplier.events;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.coinsmultiplier.Main;

public class DeathEvent implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        try {
            Player p = e.getEntity();
            Player killer = p.getKiller();
            if (killer != null) {
                if (killer.hasPermission(Main.getInstance().getConfig().getString
                        ("Permissions.Multiplierx3"))) {
                    Main.getInstance().getEconomy().depositPlayer
                            (killer.getName(), Main.getInstance().getConfig().getInt("Multiplierx3"));
                    killer.sendMessage
                            (Main.color(Main.getInstance().getConfig().getString
                                    ("Messages.Coins-Received").replace
                                    ("%killed%", p.getName()).replace
                                    ("%coins%", String.valueOf
                                            (Main.getInstance().getConfig().getInt("Multiplierx3")))));
                } if (killer.hasPermission(Main.getInstance().getConfig().getString
                        ("Permissions.Multiplierx2"))) {
                    Main.getInstance().getEconomy().depositPlayer
                            (killer.getName(), Main.getInstance().getConfig().getInt("Multiplierx2"));
                    killer.sendMessage
                            (Main.color(Main.getInstance().getConfig().getString
                                    ("Messages.Coins-Received").replace
                                    ("%killed%", p.getName()).replace
                                    ("%coins%", String.valueOf
                                            (Main.getInstance().getConfig().getInt("Multiplierx2")))));
                } if (killer.hasPermission(Main.getInstance().getConfig().getString
                        ("Permissions.Normal"))) {
                    Main.getInstance().getEconomy().depositPlayer
                            (killer.getName(), Main.getInstance().getConfig().getInt("Normal"));
                    killer.sendMessage
                            (Main.color(Main.getInstance().getConfig().getString
                                    ("Messages.Coins-Received").replace
                                    ("%killed%", p.getName()).replace
                                    ("%coins%", String.valueOf
                                            (Main.getInstance().getConfig().getInt("Normal")))));
                } else {
                    return;
                }
            } else {
                return;
            }
        } catch (Exception es) {
            return;
        }
    }
}
