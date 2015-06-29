package com.tgreenwood.gameworld;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tgreenwood.gameobjects.Cannon;
import com.tgreenwood.gameobjects.Human;
import com.tgreenwood.gameobjects.Pillow;
import com.tgreenwood.hcbhelper.AssetLoader;
import com.tgreenwood.hcbhelper.InputHandler;

public class GameRenderer {

	private GameWorld world;
	private OrthographicCamera cam;
	private SpriteBatch batcher;
	
	public GameRenderer(GameWorld world) {
		this.world = world;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 500, 708);
		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
	}

	public void render(float runTime) {
		
		Human human = world.getHuman();
		Cannon cannon = world.getCannon();
		Pillow pillow = world.getPillow();
		
		if (InputHandler.shouldRestart()) {
			InputHandler.reset();
			human.reset();
			cannon.reset();
		}
		
		if (!InputHandler.isPickedInitVelocity()){
			human.setAbsVelocity(Math.round((50 * (1 + Math.sin(runTime))) + 1));
			human.setVelocity();
		} else {
			human.setCanShot(true);
		}

		if (!InputHandler.isPickedAngle()) {
			human.setAngle(cannon.getAngle());
		} else {
			cannon.setFixedAngle(true);
		}
		
		
		batcher.begin();
		
		// render background with no transparency
		batcher.disableBlending();
		batcher.draw(AssetLoader.backgroundAnimation.getKeyFrame(runTime), 0, 0, 500, 708);

		batcher.enableBlending();
		
        // render human with transparency
        if (human.canShot()) {
        	batcher.draw(
        			AssetLoader.human, 
        			human.getX(), human.getY(), 
        			human.getWidth(), human.getHeight() / 2,
        			human.getWidth(), human.getHeight(),
        			1, 1,
        			(human.getAngle() * 180f / (float)Math.PI - 90f)
        			);
        }
		
        // render cannon
		batcher.draw(AssetLoader.cannon, 
				cannon.getPosition().x, cannon.getPosition().y, 
				cannon.getWidth() / 10, cannon.getHeight() / 2, 
				cannon.getWidth(), cannon.getHeight(), 
				1, 1, 
				cannon.getAngle());
		
		// render cannon base
		batcher.draw(AssetLoader.base, cannon.getBasePosition().x, cannon.getBasePosition().y, 40, 40);
		
		// render progress velocity bar
		AssetLoader.empty.draw(batcher, 10, 10, 100, 20);
		AssetLoader.full.draw(batcher, 10, 10, human.getAbsVelocity(), 20);
//		AssetLoader.font.draw(batcher, Float.toString(initVelocity) + " %", 43, 25);
		
		batcher.draw(AssetLoader.pillow, pillow.getPosition().x, pillow.getPosition().y);
		
        batcher.end();
        
		
	}

}
