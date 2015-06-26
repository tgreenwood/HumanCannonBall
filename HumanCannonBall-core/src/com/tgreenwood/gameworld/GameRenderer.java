package com.tgreenwood.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tgreenwood.gameobjects.Cannon;
import com.tgreenwood.gameobjects.Human;
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
		
		batcher.begin();
		
		// render background with no transparency
		batcher.disableBlending();
		batcher.draw(AssetLoader.backgroundAnimation.getKeyFrame(runTime), 0, 0, 500, 708);

		batcher.enableBlending();
		
        // render human with transparency
        if (human.toShot()) {

        	batcher.draw(
        			AssetLoader.human, 
        			human.getX(), human.getY(), 
        			human.getWidth(), human.getHeight() / 2,
        			human.getWidth(), human.getHeight(),
        			1, 1,
        			(human.getRotation() * 180f / (float)Math.PI - 90f)
        			);
        
        }
		
		// render cannon
		batcher.draw(AssetLoader.cannon, 
				cannon.getX(), cannon.getY(), 
				cannon.getWidth() / 10, cannon.getHeight() / 2, 
				cannon.getWidth(), cannon.getHeight(), 
				1, 1, 
				cannon.getAngle());
		
		// render cannon base
//		batcher.draw(AssetLoader.base, cannon.getBasePositionX(), cannon.getBasePositionY());
		
		batcher.draw(AssetLoader.base, cannon.getBasePositionX(), cannon.getBasePositionY(), 40, 40);
		
        batcher.end();
        

		
	}

}
