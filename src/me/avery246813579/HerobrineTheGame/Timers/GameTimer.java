package me.avery246813579.HerobrineTheGame.Timers;

import me.avery246813579.HerobrineTheGame.HerobrineTheGame;
import me.avery246813579.HerobrineTheGame.Game.GameManager;

public class GameTimer implements Runnable{

	private GameManager gm;
	
	private HerobrineTheGame plugin;
	
	public GameTimer (HerobrineTheGame plugin, GameManager gm){
		this.plugin = plugin;
		this.gm = gm;
	}
	
	@Override
	public void run() {
		
	}
}
