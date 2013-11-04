package me.avery246813579.HerobrineTheGame.Timers;

import org.bukkit.World;

import me.avery246813579.HerobrineTheGame.HerobrineTheGame;
import me.avery246813579.HerobrineTheGame.Arena.ArenaItems;
import me.avery246813579.HerobrineTheGame.Game.GameManager;

public class SpawnShardTimer implements Runnable {

	private HerobrineTheGame plugin;
	private GameManager gm;
	
	public SpawnShardTimer (HerobrineTheGame plugin, GameManager gm){
		this.plugin = plugin;
		this.gm = gm;
	}
	
	@Override
	public void run() {
		if(gm.getShardSpawnTimer() != -1){
			
			int timer = gm.getShardSpawnTimer();
			
			if(gm.getShardSpawnTimer() != 0){
				timer--;
				gm.setShardSpawnTimer(timer);
			}else{
				
				World world = gm.getArena().getArenaWorld();
				ArenaItems items = new ArenaItems(plugin);
				
				if(gm.getShardRotation() == 1){
					world.dropItem(gm.getArena().getShardSpawn1(), items.shard());
					world.strikeLightningEffect(gm.getArena().getShardSpawn1());
					
					gm.setShardRotation(2);
				}
				
				if(gm.getShardRotation() == 2){
					world.dropItem(gm.getArena().getShardSpawn2(), items.shard());
					world.strikeLightningEffect(gm.getArena().getShardSpawn2());
					
					gm.setShardRotation(3);
				}
				
				if(gm.getShardRotation() == 3){
					world.dropItem(gm.getArena().getShardSpawn3(), items.shard());
					world.strikeLightningEffect(gm.getArena().getShardSpawn3());
					
					gm.setShardRotation(1);
				}
				
				gm.tellArena("A Shard has spawned!");
				
				plugin.getServer().getScheduler().cancelTask(gm.getShardSpawn());
			}
		}
	}
}
