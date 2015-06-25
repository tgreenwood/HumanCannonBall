package com.tgreenwood.gameworld;

import com.tgreenwood.gameobjects.Human;


public class GameWorld {
	
	private Human human;

	public GameWorld() {
		human = new Human(17, 12, 0.75f);
	}
	
	public void update(float delta) {
		human.update(delta);
	}
	
	public Human getHuman() {
		return human;
	}

}
