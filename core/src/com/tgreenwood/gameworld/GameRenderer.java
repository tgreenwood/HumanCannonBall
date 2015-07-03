package com.tgreenwood.gameworld;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tgreenwood.gameobjects.Cannon;
import com.tgreenwood.gameobjects.Human;
import com.tgreenwood.gameobjects.Pillow;
import com.tgreenwood.gameobjects.Wind;
import com.tgreenwood.hcbhelper.AssetLoader;

public class GameRenderer {
	
	final private int WIDTH = 500;
	final private int HEIGHT = 708;

	private GameWorld world;
	private OrthographicCamera cam;
	private SpriteBatch batcher;
	private Sprite sprite;
	
	private Human human;
	private Cannon cannon;
	private Pillow pillow;
	private Wind wind;
	
	private int runTime;
	
	public GameRenderer(GameWorld world) {
		this.world = world;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, WIDTH, HEIGHT);
		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
		sprite = new Sprite(AssetLoader.human);
		initGameObjects();
		runTime = 0;
	}

	private void initGameObjects() {
		human = world.getHuman();
		cannon = world.getCannon();
		pillow = world.getPillow();		
		wind = world.getWind();
	}
	

	public void render(float delta) {
		
		runTime += delta;
		
		batcher.begin();
		
		drawBackground(runTime);
		drawHuman();
		drawCannon();
		drawProgressBar();
		drawPillow();
		drawWind();
		
		writeAttempts(world.attempts);
		writeLevel(world.level);
		writePower(human.getAbsVelocity());
		writeWindVelocity(wind.getVelocity());

		batcher.end();
	}

	private void drawBackground(float runTime) {
		// render background
		batcher.draw(AssetLoader.backgroundAnimation.getKeyFrame(runTime), 0, 0, WIDTH, HEIGHT);
	}
	
	private void drawHuman() {
		// render human
        if (human.canShot()) {
        	
        	if (human.getPosition().x > 10) {
        		sprite.setPosition(human.getPosition().x, human.getPosition().y);
        		sprite.setRotation(human.getAngle() * 180f / (float)Math.PI - 90f);
        		sprite.draw(batcher);
        	}
        	
        	// check for a collision
        	if (pillow.contains(human.getPosition(), 30)) {
        		human.setStop(true);
        		if (world.level == 3) {
        			world.shouldResetAttempts = true;
        		}
        		writeNextLevelProp();
        	}
        }
	}

	private void drawCannon() {
		// render cannon
		batcher.draw(AssetLoader.cannon, 
				cannon.getPosition().x, cannon.getPosition().y, 
				cannon.getWidth() / 10, cannon.getHeight() / 2, 
				cannon.getWidth(), cannon.getHeight(), 
				1, 1, 
				cannon.getAngle());
		// render cannon base
		batcher.draw(AssetLoader.base, cannon.getBasePosition().x, cannon.getBasePosition().y, 40, 40);
	}

	private void drawProgressBar() {
		// render progress velocity bar
		AssetLoader.empty.draw(batcher, 10, 10, 100, 20);
		AssetLoader.full.draw(batcher, 10, 10, human.getAbsVelocity() - 30 - wind.getVelocity(), 20);
	}
	
	private void drawPillow() {
		// render pillow
		batcher.draw(AssetLoader.pillow, pillow.getPosition().x, pillow.getPosition().y);		
	}

	private void drawWind() {
		// render wind arrow
		switch (wind.getVelocityIdx()) {
		case 0:
			batcher.draw(AssetLoader.wind0, 170, 65, 30, 30);
			break;
		case 1:
			batcher.draw(AssetLoader.wind1, 170, 65, 30, 30);
			break;
		case 2:
			batcher.draw(AssetLoader.wind2, 170, 65, 30, 30);
			break;
		case 3:
			batcher.draw(AssetLoader.wind3, 170, 65, 30, 30);
			break;
		case 4:
			batcher.draw(AssetLoader.wind4, 170, 65, 30, 30);
			break;
		case 5:
			batcher.draw(AssetLoader.wind5, 170, 65, 30, 30);
			break;
		}
	}
	
	private void writeNextLevelProp() {
		// proposal to move to the next level
		String proposal = "";
		if (world.level < 3) {
			proposal = "Press SPACEBAR to continue";
		} else {
			proposal = "Press SPACEBAR to play again";
		}
		AssetLoader.shadow.draw(batcher, proposal, 95, 360);
		AssetLoader.font.draw(batcher, proposal, 95, 360);			
	}

	private void writeLevel(int level) {
		// level text
		AssetLoader.shadow.draw(batcher, "Level: " + Integer.toString(level) +  " / 3", 10, 670);
		AssetLoader.font.draw(batcher, "Level: " + Integer.toString(level) + " / 3", 10, 670);
	}
	
	private void writePower(float absVelocity) {
		// power text
		AssetLoader.shadow.draw(batcher, "Power: " + (Math.round(absVelocity - wind.getVelocity()) - 31) + "%", 10, 55);
		AssetLoader.font.draw(batcher, "Power: " + (Math.round(absVelocity - wind.getVelocity()) - 31) + "%", 10, 55);
	}

	private void writeAttempts(int attempts) {
		// attempts text
		AssetLoader.shadow.draw(batcher, "Attempts: " + Integer.toString(attempts), 10, 695);
		AssetLoader.font.draw(batcher, "Attempts: " + Integer.toString(attempts), 10, 695);
	}
	
	private void writeWindVelocity(float velocity) {
		// wind velocity text
		AssetLoader.shadow.draw(batcher, "Wind velocity: ", 10, 85);
		AssetLoader.font.draw(batcher, "Wind velocity: ", 10, 85);
	}
	
	
}
