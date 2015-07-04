package com.tgreenwood.gameworld;

import com.tgreenwood.gameobjects.Cannon;
import com.tgreenwood.gameobjects.Human;
import com.tgreenwood.gameobjects.Level;
import com.tgreenwood.gameobjects.Pillow;
import com.tgreenwood.gameobjects.Wind;
import com.tgreenwood.hcbhelper.InputHandler;


public class GameWorld {
	
	private Human human;
	private Cannon cannon;
	private Pillow pillow;
	private Wind wind;
	public int attempts;
	public int level;
	public boolean shouldResetAttempts;
	private InputHandler inputHandler;
	private float runTime; 
	
	public GameWorld(InputHandler inputHandler) {
		human = new Human(30, 30);
		cannon = new Cannon(25, 25);
		pillow = new Pillow(60, 60);
		wind = new Wind();
		this.inputHandler = inputHandler;
		initAttempts();
		level = 1;
		runTime = 0;
	}
	
	public void update(float delta) {
		runTime += delta;
		human.update(delta);
		cannon.update(runTime);
		checkCommand(runTime);
		updateStates();
		
	}

	private void updateStates() {
		
		if (humanOnPillow()) {
    		if (!human.stoped()) {
    			human.setJustLanded(true);
    		}
			human.setStop(true);
    		if (level == 3) {
    			shouldResetAttempts = true;
    		}
		} else if (missedPillow()) {
			if (!human.getCriedBefore()) {
				human.setCry(true);
			}
		}
		
	}

	private boolean missedPillow() {
		return (human.getPosition().x > pillow.getPosition().x + 50) ||
				(human.getPosition().y < pillow.getPosition().y - 50);
	}

	private void checkCommand(float runTime) {
		
		if (inputHandler.shouldRestart()) {
			inputHandler.reset();
			cannon.reset();
			wind.reset();
			changeAttemps();	
			if (human.stoped()) {
				if (shouldResetAttempts) {
					initAttempts();
				}
	       		changeLevel();
        		pillow.setPosition(Level.levels.get(level));
			}
			human.reset();
				
		}
		
		if (!inputHandler.isPickedInitVelocity()){
			human.setVelocity(Math.round((50 * (1 + Math.sin(runTime))) + 1) + 30 + wind.getVelocity());
		} else {
			human.setCanShot(true);
			if (human.fired()) {
				human.setJustFired(true);
			}
		}

		if (!inputHandler.isPickedAngle()) {
			human.setAngle(cannon.getAngle());
		} else {
			cannon.setFixedAngle(true);
		}
		
	}
	
	public boolean humanOnPillow() {
		return pillow.contains(human.getPosition(), 30);
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


	public Wind getWind() {
		return wind;
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
