package me.dulexzach.forcecommand;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class forcecommand extends JavaPlugin implements Listener{
	
	
	
	public final Logger logger = Logger.getLogger("Minecraft");
	public static forcecommand plugin;
	public boolean freeze = false;
	
	@Override
	public void onDisable(){
		
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info("[" + pdfFile.getName()  + "] "+ " Has been DISABLED!");
		
	}

	@Override
	public void onEnable() {

		getConfig().options().copyDefaults(true);
		saveConfig();
		
		getServer().getPluginManager().registerEvents(this,this);
		PluginDescriptionFile pdfFile = this.getDescription();
		
		this.logger.info("[" + pdfFile.getName()  + "] "+ " Has been ENABLED!");
		this.logger.info("[" + pdfFile.getName()  + "] "+ " Made by DulexZach - Check out my server!  ");

		
	}
	

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {	
		if(label.equalsIgnoreCase("forcecommand")){
			Player player = (Player) sender;
			player.sendMessage(ChatColor.GOLD + "You are currently using version 0.3 of ForceCommand.");
			player.sendMessage(ChatColor.GOLD + "-Made by DulexZach (http://www.youtube.com/user/DulexZach)");
		}
		
		if(label.equalsIgnoreCase("fcommand")){
			Player player = (Player) sender;
			
			String commandrun = "sudo " + player.getName() + " " + this.getConfig().getString("COMMAND");
			
			 sender.getServer().dispatchCommand(player.getServer().getConsoleSender(), commandrun);
			 if(freeze){
				 freeze = false;
			 }
			 
		}
		
		if(label.equalsIgnoreCase("fcreload")){
			reloadConfig();
			sender.sendMessage(ChatColor.GREEN + "Successfully Reloaded!");
		}
		
		return false;
	}
	


	@EventHandler
	public void onPlayerLogin(PlayerJoinEvent e){
		Player player = e.getPlayer();
		if(!player.hasPlayedBefore()){
			freeze = true;
			player.sendMessage(ChatColor.RED + this.getConfig().getString("MESSAGE"));
		}else if(player.hasPlayedBefore()){
			
		}
	}		
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
			if(freeze){
				Player player = e.getPlayer();
				player.teleport(player);
			}
		}
}
