package com.tgreenwood.gameworld;

import com.tgreenwood.gameobjects.Cannon;
import com.tgreenwood.gameobjects.Human;
import com.tgreenwood.gameobjects.Pillow;
import com.tgreenwood.hcbhelper.InputHandler;


public class GameWorld {
	
	private Human human;
	private Cannon cannon;
	private Pillow pillow;
	public int attempts;
	public int level;
	public boolean shouldResetAttempts;
	
	public GameWorld() {
		human = new Human(60, 60);
		cannon = new Cannon(35, 35);
		pillow = new Pillow(60, 60);
		initAttempts();
		level = 1;
	}
	
	public void update(float delta) {
		human.update(delta);
		cannon.update(delta);
		InputHandler.update();
	}
	
	public Human getHuman() {
		return human;
	}
	
	public Cannon getCannon() {
		return cannon;
	}

	public Pillow getPillow() {
		return pillow;
	}

	public void changeLevel() {
		if (level < 3) {
			level += 1;
		} else {
			level = 1;
		}
	}

	public void changeAttemps() {
		attempts += 1;
	}
	
	public void initAttempts() {
		attempts = 0;
		shouldResetAttempts = false;
	}
	
	
}
