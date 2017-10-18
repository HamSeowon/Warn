package a;

import b.WarnCommand;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

public class Main extends PluginBase implements Listener{
	public static Config warning;
	public static Main instance;
	
	@Override
	public void onEnable(){
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getServer().getCommandMap().register("경고",new WarnCommand());
		this.getLogger().info("Plugin_Enable");
		this.getDataFolder().mkdir();
		warning = new Config(getDataFolder()+"WarnLists.yaml",Config.YAML);
		instance = this;
	}
	@Override
	public void onDisable(){
		warning.save();
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent ev){
		if(!Main.warning.exists(ev.getPlayer().getName().toLowerCase())){
			Main.warning.set(ev.getPlayer().getName().toLowerCase(),0);}
		warning.save();
	}
	public  void addBan(String name){
		this.getServer().getNameBans().addBan(name);
	}
	

}
