package de.raidcraft.mythicmobs.mechanics;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import io.lumine.xikage.mythicmobs.utils.Events;
import io.lumine.xikage.mythicmobs.utils.Schedulers;
import io.lumine.xikage.mythicmobs.utils.terminable.Terminable;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;

public class FullPrisonMechanic extends SkillMechanic implements ITargetedEntitySkill {

    private Material material = Material.IRON_BARS;
    private Material baseMaterial = Material.IRON_BLOCK;
    private Material topMaterial = Material.BARRIER;
    private int duration = 100;
    private boolean breakable = false;
    private boolean fullbase = true;
    private boolean fulltop = false;

    public FullPrisonMechanic(MythicLineConfig config) {
        super(config.getLine(), config);

        this.setAsyncSafe(false);
        this.setTargetsCreativePlayers(false);

        this.material = Material.matchMaterial(config.getString(new String[]{"material", "m"}, material.getKey().toString()));
        this.baseMaterial = Material.matchMaterial(config.getString(new String[]{"baseMaterial", "bm"}, baseMaterial.getKey().toString()));
        this.topMaterial = Material.matchMaterial(config.getString(new String[]{"topMaterial", "tm"}, topMaterial.getKey().toString()));
        this.duration = config.getInteger(new String[] {"duration", "d"}, duration);
        this.breakable = config.getBoolean(new String[] {"breakable", "b"}, breakable);
        this.fullbase = config.getBoolean(new String[] {"fullBase", "fb"}, fullbase);
        this.fulltop = config.getBoolean(new String[] {"fullTop", "ft"}, fulltop);
    }

    @Override
    public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
        if (this.material == null) {
            return false;
        } else {
            new FullPrisonMechanic.Prison(target);
            return true;
        }
    }

    private class Prison implements Terminable {

        private final AbstractEntity prisoner;
        private final ArrayList<Block> prisonBlocks = new ArrayList<>();
        private final Terminable listener;

        public Prison(AbstractEntity prisoner) {

            this.prisoner = prisoner;
            int x = prisoner.getLocation().getBlockX();
            int y = prisoner.getLocation().getBlockY();
            int z = prisoner.getLocation().getBlockZ();
            AbstractLocation location = new AbstractLocation(prisoner.getWorld(), x < 0 ? (double) x - 0.5D : (double) x + 0.5D, (double) y + 0.5D, z < 0 ? (double) z - 0.5D : (double) z + 0.5D, prisoner.getLocation().getYaw(), prisoner.getLocation().getPitch());
            this.listener = Events.subscribe(BlockBreakEvent.class).handler((event) -> {
                if (this.prisonBlocks.contains(event.getBlock())) {
                    event.setCancelled(true);
                    if (FullPrisonMechanic.this.breakable) {
                        event.getBlock().setType(Material.AIR);
                    }
                }

            });

            prisoner.teleport(location);
            this.imprison();
            Schedulers.sync().runLater(this::terminate, FullPrisonMechanic.this.duration);
        }

        private void imprison() {
            Entity target = BukkitAdapter.adapt(this.prisoner);
            Block feet = target.getLocation().getBlock();
            Block temp = feet.getRelative(1, 0, 0);
            if (temp.getType() == Material.AIR) {
                temp.setType(FullPrisonMechanic.this.material);
                this.prisonBlocks.add(temp);
            }

            temp = feet.getRelative(1, 1, 0);
            if (temp.getType() == Material.AIR) {
                temp.setType(FullPrisonMechanic.this.material);
                this.prisonBlocks.add(temp);
            }

            temp = feet.getRelative(-1, 0, 0);
            if (temp.getType() == Material.AIR) {
                temp.setType(FullPrisonMechanic.this.material);
                this.prisonBlocks.add(temp);
            }

            temp = feet.getRelative(-1, 1, 0);
            if (temp.getType() == Material.AIR) {
                temp.setType(FullPrisonMechanic.this.material);
                this.prisonBlocks.add(temp);
            }

            temp = feet.getRelative(0, 0, 1);
            if (temp.getType() == Material.AIR) {
                temp.setType(FullPrisonMechanic.this.material);
                this.prisonBlocks.add(temp);
            }

            temp = feet.getRelative(0, 1, 1);
            if (temp.getType() == Material.AIR) {
                temp.setType(FullPrisonMechanic.this.material);
                this.prisonBlocks.add(temp);
            }

            temp = feet.getRelative(0, 0, -1);
            if (temp.getType() == Material.AIR) {
                temp.setType(FullPrisonMechanic.this.material);
                this.prisonBlocks.add(temp);
            }

            temp = feet.getRelative(0, 1, -1);
            if (temp.getType() == Material.AIR) {
                temp.setType(FullPrisonMechanic.this.material);
                this.prisonBlocks.add(temp);
            }

            // corners

            temp = feet.getRelative(1, 0, 1);
            if (temp.getType() == Material.AIR) {
                temp.setType(FullPrisonMechanic.this.material);
                this.prisonBlocks.add(temp);
            }

            temp = feet.getRelative(1, 1, 1);
            if (temp.getType() == Material.AIR) {
                temp.setType(FullPrisonMechanic.this.material);
                this.prisonBlocks.add(temp);
            }

            temp = feet.getRelative(1, 0, -1);
            if (temp.getType() == Material.AIR) {
                temp.setType(FullPrisonMechanic.this.material);
                this.prisonBlocks.add(temp);
            }

            temp = feet.getRelative(1, 1, -1);
            if (temp.getType() == Material.AIR) {
                temp.setType(FullPrisonMechanic.this.material);
                this.prisonBlocks.add(temp);
            }

            temp = feet.getRelative(-1, 0, -1);
            if (temp.getType() == Material.AIR) {
                temp.setType(FullPrisonMechanic.this.material);
                this.prisonBlocks.add(temp);
            }

            temp = feet.getRelative(-1, 1, -1);
            if (temp.getType() == Material.AIR) {
                temp.setType(FullPrisonMechanic.this.material);
                this.prisonBlocks.add(temp);
            }

            temp = feet.getRelative(-1, 0, 1);
            if (temp.getType() == Material.AIR) {
                temp.setType(FullPrisonMechanic.this.material);
                this.prisonBlocks.add(temp);
            }

            temp = feet.getRelative(-1, 1, 1);
            if (temp.getType() == Material.AIR) {
                temp.setType(FullPrisonMechanic.this.material);
                this.prisonBlocks.add(temp);
            }

            // top

            temp = feet.getRelative(0, 2, 0);
            if (temp.getType() == Material.AIR) {
                temp.setType(FullPrisonMechanic.this.topMaterial);
                this.prisonBlocks.add(temp);
            }

            if (fulltop) {
                temp = feet.getRelative(1, 2, 0);
                if (temp.getType() == Material.AIR) {
                    temp.setType(FullPrisonMechanic.this.topMaterial);
                    this.prisonBlocks.add(temp);
                }

                temp = feet.getRelative(1, 2, 1);
                if (temp.getType() == Material.AIR) {
                    temp.setType(FullPrisonMechanic.this.topMaterial);
                    this.prisonBlocks.add(temp);
                }

                temp = feet.getRelative(0, 2, 1);
                if (temp.getType() == Material.AIR) {
                    temp.setType(FullPrisonMechanic.this.topMaterial);
                    this.prisonBlocks.add(temp);
                }

                temp = feet.getRelative(0, 2, -1);
                if (temp.getType() == Material.AIR) {
                    temp.setType(FullPrisonMechanic.this.topMaterial);
                    this.prisonBlocks.add(temp);
                }

                temp = feet.getRelative(-1, 2, -1);
                if (temp.getType() == Material.AIR) {
                    temp.setType(FullPrisonMechanic.this.topMaterial);
                    this.prisonBlocks.add(temp);
                }

                temp = feet.getRelative(-1, 2, 0);
                if (temp.getType() == Material.AIR) {
                    temp.setType(FullPrisonMechanic.this.topMaterial);
                    this.prisonBlocks.add(temp);
                }

                temp = feet.getRelative(-1, 2, 1);
                if (temp.getType() == Material.AIR) {
                    temp.setType(FullPrisonMechanic.this.topMaterial);
                    this.prisonBlocks.add(temp);
                }

                temp = feet.getRelative(1, 21, -1);
                if (temp.getType() == Material.AIR) {
                    temp.setType(FullPrisonMechanic.this.topMaterial);
                    this.prisonBlocks.add(temp);
                }
            }

            // base

            temp = feet.getRelative(0, -1, 0);
            if (temp.getType() == Material.AIR) {
                temp.setType(FullPrisonMechanic.this.baseMaterial);
                this.prisonBlocks.add(temp);
            }

            if (fullbase) {
                temp = feet.getRelative(1, -1, 0);
                if (temp.getType() == Material.AIR) {
                    temp.setType(FullPrisonMechanic.this.baseMaterial);
                    this.prisonBlocks.add(temp);
                }

                temp = feet.getRelative(1, -1, 1);
                if (temp.getType() == Material.AIR) {
                    temp.setType(FullPrisonMechanic.this.baseMaterial);
                    this.prisonBlocks.add(temp);
                }

                temp = feet.getRelative(0, -1, 1);
                if (temp.getType() == Material.AIR) {
                    temp.setType(FullPrisonMechanic.this.baseMaterial);
                    this.prisonBlocks.add(temp);
                }

                temp = feet.getRelative(0, -1, -1);
                if (temp.getType() == Material.AIR) {
                    temp.setType(FullPrisonMechanic.this.baseMaterial);
                    this.prisonBlocks.add(temp);
                }

                temp = feet.getRelative(-1, -1, -1);
                if (temp.getType() == Material.AIR) {
                    temp.setType(FullPrisonMechanic.this.baseMaterial);
                    this.prisonBlocks.add(temp);
                }

                temp = feet.getRelative(-1, -1, 0);
                if (temp.getType() == Material.AIR) {
                    temp.setType(FullPrisonMechanic.this.baseMaterial);
                    this.prisonBlocks.add(temp);
                }

                temp = feet.getRelative(-1, -1, 1);
                if (temp.getType() == Material.AIR) {
                    temp.setType(FullPrisonMechanic.this.baseMaterial);
                    this.prisonBlocks.add(temp);
                }

                temp = feet.getRelative(1, -1, -1);
                if (temp.getType() == Material.AIR) {
                    temp.setType(FullPrisonMechanic.this.baseMaterial);
                    this.prisonBlocks.add(temp);
                }
            }
        }

        public void close() {

            for (Block block : this.prisonBlocks) {
                if (material == block.getType()
                        || topMaterial == block.getType()
                        || baseMaterial == block.getType()
                ) {
                    block.setType(Material.AIR);
                }
            }

            this.listener.closeAndReportException();
        }
    }
}
