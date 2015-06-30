package com.tgreenwood.gameworld;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tgreenwood.gameobjects.Cannon;
import com.tgreenwood.gameobjects.Human;
import com.tgreenwood.gameobjects.Level;
import com.tgreenwood.gameobjects.Pillow;
import com.tgreenwood.hcbhelper.AssetLoader;
import com.tgreenwood.hcbhelper.InputHandler;

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
	
	public GameRenderer(GameWorld world) {
		this.world = world;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, WIDTH, HEIGHT);
		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
		sprite = new Sprite(AssetLoader.human);
		
		initGameObjects();
	}

	private void initGameObjects() {
		human = world.getHuman();
		cannon = world.getCannon();
		pillow = world.getPillow();		
	}
	

	public void render(float runTime) {
		
		checkInput(runTime);
		
		batcher.begin();
		
		drawBackground(runTime);
		drawHuman(runTime);
		drawCannon(runTime);
		drawProgressBar(runTime);
		drawPillow(runTime);
		
		// attempts text
		AssetLoader.shadow.draw(batcher, "Attempts: " + Integer.toString(world.attempts), 10, 695);
		AssetLoader.font.draw(batcher, "Attempts: " + Integer.toString(world.attempts), 10, 695);

		// power text
		AssetLoader.shadow.draw(batcher, "Power: " + (Math.round(human.getAbsVelocity()) - 31) + "%", 10, 55);
		AssetLoader.font.draw(batcher, "Power: " + (Math.round(human.getAbsVelocity()) - 31) + "%", 10, 55);
		
		batcher.end();
        
	}
	

	private void checkInput(float runTime) {
		
		if (InputHandler.shouldRestart()) {
			InputHandler.reset();
			cannon.reset();
			world.changeAttemps();		
			if (human.stoped()) {
	       		world.changeLevel();
        		pillow.setPosition(Level.levels.get(world.level));
			}
			human.reset();
				
		}
		
		if (!InputHandler.isPickedInitVelocity()){
			human.setVelocity(Math.round((50 * (1 + Math.sin(runTime))) + 1) + 30);
		} else {
			human.setCanShot(true);
		}

		if (!InputHandler.isPickedAngle()) {
			human.setAngle(cannon.getAngle());
		} else {
			cannon.setFixedAngle(true);
		}
		
	}

	private void drawBackground(float runTime) {
		// render background
		batcher.draw(AssetLoader.backgroundAnimation.getKeyFrame(runTime), 0, 0, WIDTH, HEIGHT);
	}
	
	private void drawHuman(float runTime) {
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
        	}
        }
	}
	
	private void drawCannon(float runTime) {
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

	private void drawProgressBar(float runTime) {
		// render progress velocity bar
		AssetLoader.empty.draw(batcher, 10, 10, 100, 20);
		AssetLoader.full.draw(batcher, 10, 10, human.getAbsVelocity() - 30, 20);
//		AssetLoader.font.draw(batcher, Float.toString(initVelocity) + " %", 43, 25);		
	}
	
	private void drawPillow(float runTime) {
		// render pillow
		batcher.draw(AssetLoader.pillow, pillow.getPosition().x, pillow.getPosition().y);		
	}

}
