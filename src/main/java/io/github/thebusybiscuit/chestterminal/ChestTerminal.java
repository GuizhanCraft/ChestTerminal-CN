package io.github.thebusybiscuit.chestterminal;

import org.bstats.bukkit.Metrics;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.chestterminal.items.AccessTerminal;
import io.github.thebusybiscuit.chestterminal.items.ExportBus;
import io.github.thebusybiscuit.chestterminal.items.ImportBus;
import io.github.thebusybiscuit.chestterminal.items.MilkyQuartz;
import io.github.thebusybiscuit.chestterminal.items.WirelessTerminal;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.GitHubBuildsUpdater;
import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.PluginUpdater;
import io.github.thebusybiscuit.slimefun4.libraries.dough.versions.PrefixedVersion;

public class ChestTerminal extends JavaPlugin implements Listener, SlimefunAddon {
	
	@Override
	public void onEnable() {
		Config cfg = new Config(this);
		
		// Setting up bStats
		new Metrics(this, 5503);

		// Setting up the Auto-Updater
		PluginUpdater<PrefixedVersion> updater = null;

		if (getDescription().getVersion().startsWith("DEV - ")) {
			// If we are using a development build, we want to switch to our custom 
			updater = new GitHubBuildsUpdater(this, getFile(), "ybw0014/ChestTerminal-CN/master");
		}

		if (updater != null && cfg.getBoolean("options.auto-update")) updater.start();
		
		SlimefunItemStack milkyQuartz = new SlimefunItemStack("MILKY_QUARTZ", Material.QUARTZ, "&f乳白色石英");
		SlimefunItemStack ctPanel = new SlimefunItemStack("CT_PANEL", "7a44ff3a5f49c69cab676bad8d98a063fa78cfa61916fdef3e267557fec18283", "&3CT发光面板", "&7制造组件");
		
		SlimefunItemStack chestTerminal = new SlimefunItemStack("CHEST_TERMINAL", "7a44ff3a5f49c69cab676bad8d98a063fa78cfa61916fdef3e267557fec18283", "&3CT访问终端", "&7当此方块连接到货运网络时，", "&7你可以与连入箱子终端频道的货运节点进行远程交互。");
		SlimefunItemStack importBus = new SlimefunItemStack("CT_IMPORT_BUS", "113db2e7e72ea4432eefbd6e58a85eaa2423f83e642ca41abc6a9317757b889", "&3CT输入总线", "&7当此方块连接到货运网络时，", "&7它将从其附着的方块中获取物品", "&7并放入箱子终端的货运频道。");
		SlimefunItemStack exportBus = new SlimefunItemStack("CT_EXPORT_BUS", "113db2e7e72ea4432eefbd6e58a85eaa2423f83e642ca41abc6a9317757b889", "&3CT输出总线", "&7当此方块连接到货运网络时，", "&7它将从箱子终端的货运频道中获取物品", "&7并放入其附着的方块中。");
		
		SlimefunItemStack wirelessTerminal16 = new SlimefunItemStack("CT_WIRELESS_ACCESS_TERMINAL_16", Material.ITEM_FRAME, "&3无线访问箱子终端 &b(16)", "&8\u21E8 &7连接到: &c未连接", "&8\u21E8 &7范围: &e16 个方块", "&c&o&8\u21E8 &e\u26A1 &70 / 10 J", "", "&7当此物品连接到一个CT访问终端时，", "&7你可以远程访问该终端。", "", "&7&e右键点击CT访问终端&7以连接", "&7&e右键点击&7打开连接的终端");
		SlimefunItemStack wirelessTerminal64 = new SlimefunItemStack("CT_WIRELESS_ACCESS_TERMINAL_64", Material.ITEM_FRAME, "&3无线访问箱子终端 &b(64)", "&8\u21E8 &7连接到: &c未连接", "&8\u21E8 &7范围: &e64 个方块", "&c&o&8\u21E8 &e\u26A1 &70 / 25 J", "", "&7当此物品连接到一个CT访问终端时，", "&7你可以远程访问该终端。", "", "&7&e右键点击CT访问终端&7以连接", "&7&e右键点击&7打开连接的终端");
		SlimefunItemStack wirelessTerminal128 = new SlimefunItemStack("CT_WIRELESS_ACCESS_TERMINAL_128", Material.ITEM_FRAME, "&3无线访问箱子终端 &b(128)", "&8\u21E8 &7连接到: &c未连接", "&8\u21E8 &7范围: &e128 个方块", "&c&o&8\u21E8 &e\u26A1 &70 / 50 J", "", "&7当此物品连接到一个CT访问终端时，", "&7你可以远程访问该终端。", "", "&7&e右键点击CT访问终端&7以连接", "&7&e右键点击&7打开连接的终端");
		SlimefunItemStack wirelessTerminalTransdimensional = new SlimefunItemStack("CT_WIRELESS_ACCESS_TERMINAL_TRANSDIMENSIONAL", Material.ITEM_FRAME, "&3无线访问箱子终端 &b(跨次元)", "&8\u21E8 &7连接到: &c未连接", "&8\u21E8 &7范围: &e无限", "&c&o&8\u21E8 &e\u26A1 &70 / 50 J", "", "&7当此物品连接到一个CT访问终端时，", "&7你可以远程访问该终端。", "", "&7&e右键点击CT访问终端&7以连接", "&7&e右键点击&7打开连接的终端");
		
		ItemGroup itemGroup = new ItemGroup(new NamespacedKey(this, "chest_terminal"), new CustomItemStack(chestTerminal, "&5箱子终端", "", "&a> 点击打开"));
		
		new SlimefunItem(itemGroup, milkyQuartz, RecipeType.GEO_MINER,
		new ItemStack[0])
		.register(this);
		
		new SlimefunItem(itemGroup, ctPanel, RecipeType.ENHANCED_CRAFTING_TABLE,
		new ItemStack[] {milkyQuartz, SlimefunItems.BLISTERING_INGOT_3, milkyQuartz, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.POWER_CRYSTAL, SlimefunItems.REDSTONE_ALLOY, milkyQuartz, SlimefunItems.BLISTERING_INGOT_3, milkyQuartz})
		.register(this);
		
		new AccessTerminal(itemGroup, chestTerminal, RecipeType.ENHANCED_CRAFTING_TABLE,
		new ItemStack[] {milkyQuartz, SlimefunItems.GPS_TRANSMITTER_3, milkyQuartz, SlimefunItems.POWER_CRYSTAL, ctPanel, SlimefunItems.POWER_CRYSTAL, SlimefunItems.PLASTIC_SHEET, SlimefunItems.ENERGY_REGULATOR, SlimefunItems.PLASTIC_SHEET})
		.register(this);
		
		new ImportBus(itemGroup, importBus, RecipeType.ENHANCED_CRAFTING_TABLE,
		new ItemStack[] {SlimefunItems.REDSTONE_ALLOY, SlimefunItems.POWER_CRYSTAL, SlimefunItems.REDSTONE_ALLOY, SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.CARGO_INPUT_NODE, SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.PLASTIC_SHEET, SlimefunItems.CARGO_MOTOR, SlimefunItems.PLASTIC_SHEET})
		.register(this);
		
		new ExportBus(itemGroup, exportBus, RecipeType.ENHANCED_CRAFTING_TABLE,
		new ItemStack[] {null, SlimefunItems.DAMASCUS_STEEL_INGOT, null, SlimefunItems.ALUMINUM_BRONZE_INGOT, importBus, SlimefunItems.ALUMINUM_BRONZE_INGOT, SlimefunItems.PLASTIC_SHEET, SlimefunItems.GOLD_10K, SlimefunItems.PLASTIC_SHEET})
		.register(this);
		
		new WirelessTerminal(itemGroup, wirelessTerminal16, RecipeType.ENHANCED_CRAFTING_TABLE,
		new ItemStack[] {milkyQuartz, SlimefunItems.GPS_TRANSMITTER, milkyQuartz, SlimefunItems.COBALT_INGOT, chestTerminal, SlimefunItems.COBALT_INGOT, SlimefunItems.BATTERY, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.BATTERY}) {

			@Override
			public int getRange() {
				return 16;
			}

            @Override
			public float getMaxItemCharge(ItemStack item) {
			    return 10;
			}
			
		}.register(this);
		
		new WirelessTerminal(itemGroup, wirelessTerminal64, RecipeType.ENHANCED_CRAFTING_TABLE,
		new ItemStack[] {milkyQuartz, SlimefunItems.GPS_TRANSMITTER, milkyQuartz, SlimefunItems.COBALT_INGOT, wirelessTerminal16, SlimefunItems.COBALT_INGOT, SlimefunItems.BATTERY, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.BATTERY}) {

			@Override
			public int getRange() {
				return 64;
			}

            @Override
            public float getMaxItemCharge(ItemStack item) {
                return 25;
            }
			
		}.register(this);
		
		new WirelessTerminal(itemGroup, wirelessTerminal128, RecipeType.ENHANCED_CRAFTING_TABLE,
		new ItemStack[] {milkyQuartz, SlimefunItems.GPS_TRANSMITTER_2, milkyQuartz, SlimefunItems.COBALT_INGOT, wirelessTerminal64, SlimefunItems.COBALT_INGOT, SlimefunItems.BATTERY, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.BATTERY}) {

			@Override
			public int getRange() {
				return 128;
			}

            @Override
            public float getMaxItemCharge(ItemStack item) {
                return 50;
            }
			
		}.register(this);
		
		new WirelessTerminal(itemGroup, wirelessTerminalTransdimensional, RecipeType.ENHANCED_CRAFTING_TABLE,
		new ItemStack[] {milkyQuartz, SlimefunItems.GPS_TRANSMITTER_4, milkyQuartz, SlimefunItems.COBALT_INGOT, wirelessTerminal128, SlimefunItems.COBALT_INGOT, SlimefunItems.BATTERY, SlimefunItems.BLISTERING_INGOT_3, SlimefunItems.BATTERY}) {

			@Override
			public int getRange() {
				return -1;
			}

            @Override
            public float getMaxItemCharge(ItemStack item) {
                return 50;
            }
			
		}.register(this);
		
		new MilkyQuartz(this, milkyQuartz).register();
	}

	@Override
	public JavaPlugin getJavaPlugin() {
		return this;
	}

	@Override
	public String getBugTrackerURL() {
		return "https://github.com/ybw0014/ChestTerminal-CN/issues";
	}
}
