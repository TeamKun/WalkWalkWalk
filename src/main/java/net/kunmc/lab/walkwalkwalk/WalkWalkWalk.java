package net.kunmc.lab.walkwalkwalk;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public final class WalkWalkWalk extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().stream()
                        .filter(Entity::isValid)
                        .forEach(e -> {
                            Vector look = e.getLocation()
                                    .getDirection()
                                    .normalize()
                                    .setY(0)
                                    .normalize()
                                    .multiply(e.isOnGround() ? .15 : .05);

                            Vector v = e.getVelocity();
                            /*
                            if (v.lengthSquared() < .1) {
                                Location loc = e.getLocation();
                                Vector neg = loc.getDirection().multiply(-1);
                                e.teleport(loc.setDirection(new Vector(neg.getX(), loc.getDirection().getY(), neg.getZ())));
                            }
                            */

                            e.setVelocity(v.add(look));
                        });
            }
        }.runTaskTimer(this, 0, 1);
    }

}
