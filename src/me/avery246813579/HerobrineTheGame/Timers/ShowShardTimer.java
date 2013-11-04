package me.avery246813579.HerobrineTheGame.Timers;

import me.avery246813579.HerobrineTheGame.HerobrineTheGame;

public class ShowShardTimer implements Runnable{

	private HerobrineTheGame plugin;
	
	public ShowShardTimer (HerobrineTheGame plugin){
		this.plugin = plugin;
	}

	@Override
	public void run() {
		//TODO Add timer to spawn lighing every 30 seconds a shard is spawned.
	}
}
