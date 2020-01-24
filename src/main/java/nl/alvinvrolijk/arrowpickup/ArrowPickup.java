package nl.alvinvrolijk.arrowpickup;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class ArrowPickup extends JavaPlugin {

    public Logger logger = Logger.getLogger(getDescription().getName());

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getScheduler().runTaskTimer(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.getNearbyEntities(0.2, 0.2, 0.2).stream().filter(entity -> entity instanceof Arrow).filter(entity -> ((Arrow) entity).isInBlock()).forEach(arrow -> {
                    arrow.remove();
                    player.getInventory().addItem(new ItemStack(Material.ARROW, 1));
                });
            }
        }, 20L, 2L);

        logger.info("Plugin enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        logger.info("Plugin disabled");
    }
}
