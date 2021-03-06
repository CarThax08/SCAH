package com.github.carthax08.scah.events;

import com.github.carthax08.scah.Main;
import com.github.carthax08.simplecurrencies.SimpleCurrencies;
import com.github.carthax08.simplecurrencies.api.Config;
import com.github.carthax08.simplecurrencies.api.Currencies;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GuiClickEventHandler implements Listener {
    @EventHandler
    public void onGuiClickEvent(InventoryClickEvent e){
        if(e.getView().getTitle().equals("Auction House")){
            if(e.getWhoClicked() instanceof Player) {
                Player player = (Player) e.getWhoClicked();
                ItemStack item = e.getClickedInventory().getItem(e.getSlot());
                if (item.getItemMeta().getDisplayName().equals("Increase Page")) {
                    if (Main.getAuctionHouse().length > Integer.parseInt(e.getInventory().getItem(49).getItemMeta().getDisplayName())) {
                        Main.openAuctionHouse(player, Integer.parseInt(e.getInventory().getItem(49).getItemMeta().getDisplayName()) + 1);
                    }
                }
                if (item.getItemMeta().getDisplayName().equals("Decrease Page")) {
                    if (Main.getAuctionHouse().length != Integer.parseInt(e.getInventory().getItem(49).getItemMeta().getDisplayName()) && !e.getInventory().getItem(49).getItemMeta().getDisplayName().equals("1")) {
                        Main.openAuctionHouse(player, Integer.parseInt(e.getInventory().getItem(49).getItemMeta().getDisplayName()) - 1);
                    }
                    System.out.println("ree");
                }
                if (Config.checkCurrency(item.getItemMeta().getLore().get(2))) {
                    String currency = item.getItemMeta().getLore().get(2);
                    if (Currencies.getCurrency(currency, (Player) e.getWhoClicked()) >= Double.parseDouble(item.getItemMeta().getLore().get(1))) {
                        Currencies.removeCurrency(currency, player, Double.parseDouble(item.getItemMeta().getLore().get(1)));
                        Currencies.addCurrency(currency, Bukkit.getOfflinePlayer(item.getItemMeta().getLore().get(0)), Double.parseDouble(item.getItemMeta().getLore().get(1)));
                        ItemStack item2 = e.getCurrentItem();
                        ItemMeta meta = item2.getItemMeta();
                        List<String> lore = new ArrayList<String>();
                        meta.setLore(lore);
                        meta.setDisplayName(meta.getLocalizedName());
                        item2.setItemMeta(meta);
                        player.getInventory().addItem();
                    }
                }
            }
        }
    }
}
