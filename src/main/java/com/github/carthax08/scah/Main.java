package com.github.carthax08.scah;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Inventory[] AH_GUI;
    private static int amountOfItems;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage("[SCAH] The plugin is initializing, please wait....");
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getServer().getConsoleSender().sendMessage("[SCAH] Config loaded....");
        if(!getConfig().isSet("ahgui")) {
            AH_GUI[0] = Bukkit.createInventory(null, 54);
            amountOfItems = 0;
            getConfig().set("ahgui", AH_GUI);
            getConfig().set("amountOfItems", amountOfItems);
            addBasicItems(AH_GUI);
        }else{
            AH_GUI = (Inventory[]) getConfig().get("ahgui");
            amountOfItems = getConfig().getInt("amountOfItems");
        }
    }

    private static void addBasicItems(Inventory[] GUI) {
        ItemStack item = new ItemStack(Material.ARROW, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Increase Page");
        item.setItemMeta(meta);

        ItemStack item2 = new ItemStack(Material.ARROW, 1);
        ItemMeta meta2 = item2.getItemMeta();
        meta2.setDisplayName("Decrease Page");
        item2.setItemMeta(meta2);

        for(Inventory i : GUI){
            i.setItem(53, item);
            i.setItem(45, item2);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static void openAuctionHouse(Player p){
        p.openInventory(AH_GUI[0]);
    }
    public static Inventory[] getAuctionHouse(){
        return AH_GUI;
    }
}
