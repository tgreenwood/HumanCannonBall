package com.tgreenwood.gameworld;

import com.tgreenwood.gameobjects.Human;


public class GameWorld {
	
	private Human human;

	public GameWorld() {
		human = new Human(60, 60);
	}
	
	public void update(float delta) {
		human.update(delta);
	}
	
	public Human getHuman() {
		return human;
	}

}
