package com.github.carthax08.scah;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Inventory AH_GUI;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage("[SCAH] The plugin is initializing, please wait....");
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getServer().getConsoleSender().sendMessage("[SCAH] Config loaded....");
        if(!getConfig().isSet("ahgui")) {
            AH_GUI = Bukkit.createInventory(null, 54);
            getConfig().set("ahgui", AH_GUI);
            addBasicItems(AH_GUI);
        }else{
            AH_GUI = (Inventory) getConfig().get("ahgui");
        }
    }

    private static void addBasicItems(Inventory GUI) {
        ItemStack item = new ItemStack(Material.ARROW, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Increase Page");
        item.setItemMeta(meta);

        GUI.setItem(45, item);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static void openAuctionHouse(Player p){
        p.openInventory(AH_GUI);
    }
    public static Inventory getAuctionHouse(){
        return AH_GUI;
    }
}
