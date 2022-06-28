package org.coinsmultiplier.commands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.coinsmultiplier.Main;

public class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                if (p.hasPermission
                        (Main.getInstance().getConfig().getString("Permissions.Reload"))) {
                    Main.getInstance().reloadConfig();
                    p.sendMessage(Main.color(Main.getInstance().getConfig().getString("Messages.Reload")));
                }
                return true;
            }
        } else {
            sender.sendMessage("Only in-game command.");
            return true;
        }
        return true;
    }
}
