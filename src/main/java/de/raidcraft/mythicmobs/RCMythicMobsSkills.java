package de.raidcraft.mythicmobs;

import de.raidcraft.mythicmobs.mechanics.FullPrisonMechanic;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicConditionLoadEvent;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicDropLoadEvent;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMechanicLoadEvent;
import io.lumine.xikage.mythicmobs.drops.Drop;
import io.lumine.xikage.mythicmobs.skills.SkillCondition;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
import kr.entree.spigradle.annotations.PluginMain;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

@PluginMain
public class RCMythicMobsSkills extends JavaPlugin implements Listener {

    private Logger log;

    @Override
    public void onEnable() {

        log = this.getLogger();
        Bukkit.getPluginManager().registerEvents(this, this);

        log.info("MythicMobs Raid-Craft Skills Enabled!");
    }

    /*
     * Registers all of the custom mechanics when MythicMechanicLoadEvent is called
     */
    @EventHandler
    public void onMythicMechanicLoad(MythicMechanicLoadEvent event)	{
        log.info("MythicMechanicLoadEvent called for mechanic " + event.getMechanicName());

        if(event.getMechanicName().equalsIgnoreCase("FULLPRISON"))	{
            SkillMechanic mechanic = new FullPrisonMechanic(event.getConfig());
            event.register(mechanic);
            log.info("-- Registered FULLPRISON mechanic!");
        }
    }

//    /*
//     * Registers all of the custom conditions when MythicConditionLoadEvent is called
//     */
//    @EventHandler
//    public void onMythicConditionLoad(MythicConditionLoadEvent event)	{
//        log.info("MythicConditionLoadEvent called for condition " + event.getConditionName());
//
//        if(event.getConditionName().equalsIgnoreCase("INVEHICLE"))	{
//            SkillCondition condition = new InVehicleCondition(event.getConfig());
//            event.register(condition);
//            log.info("-- Registered InVehicle dondition!");
//        }
//    }
//
//    /*
//     * Registers all of the custom drops when MythicDropLoadEvent is called
//     */
//    @EventHandler
//    public void onMythicDropLoad(MythicDropLoadEvent event)	{
//        log.info("MythicDropLoadEvent called for drop " + event.getDropName());
//
//        if(event.getDropName().equalsIgnoreCase("SPECIAL"))	{
//            Drop drop = new SpecialItem(event.getConfig());
//            event.register(drop);
//            log.info("-- Registered SPECIAL drop!");
//        }
//    }
}
