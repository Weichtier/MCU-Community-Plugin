package de.slowloris.community.v2.utils.inventory;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemBuilder {

    private ItemStack item;
    private ItemMeta meta;

    public ItemBuilder(Material material) {
        item = new ItemStack(material);
        meta = item.getItemMeta();
    }

    public ItemBuilder(Material material, byte id) {
        item = new ItemStack(material, id);
        meta = item.getItemMeta();
    }


    public ItemBuilder setTitle(String s){
        meta.setDisplayName(s);
        return this;
    }

    public ItemBuilder setLore(List<String> list){
        meta.setLore(list);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, Integer lvl){
        meta.addEnchant(enchantment, lvl, true);
        return this;
    }

    public ItemBuilder setAmout(Integer i){
        item.setAmount(i);
        return this;
    }


    public ItemStack build(){
        item.setItemMeta(meta);
        return item;
    }

}
