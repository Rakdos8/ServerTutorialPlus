package nl.martenm.servertutorialplus.events;

import nl.martenm.servertutorialplus.MainClass;
import nl.martenm.servertutorialplus.managers.FlatFileManager;
import nl.martenm.servertutorialplus.objects.TutorialController;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Player quit event listener.
 * Save the old state of a player if he leaves while in a tutorial. This makes sure we actually restore the properties of the player on the next join.
 * @author MartenM
 */
public class OnPlayerQuitEvent implements Listener {

    private MainClass plugin;
    public OnPlayerQuitEvent(MainClass plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        if(plugin.inTutorial.containsKey(event.getPlayer().getUniqueId())){
            TutorialController tc = plugin.inTutorial.get(event.getPlayer().getUniqueId());
            tc.cancel(true);
            FlatFileManager.saveJson(plugin, tc.getOldValuesPlayer());
        }
    }
}
