package com.tgreenwood.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tgreenwood.gameobjects.Cannon;
import com.tgreenwood.gameobjects.Human;
import com.tgreenwood.hcbhelper.AssetLoader;

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
		
		// render human with transparency
		batcher.enableBlending();
		batcher.draw(
				AssetLoader.human, 
				human.getX(), human.getY(), 
				human.getWidth() / 2, human.getHeight() / 2,
				human.getWidth(), human.getHeight(),
				1, 1,
				(human.getRotation() * 180f / (float)Math.PI - 90f)
				);
//		Gdx.app.log("Rotation", Float.toString(human.getRotation() * 180f / (float)Math.PI - 90f));
		
		batcher.draw(AssetLoader.cannon, 
				cannon.getX(), cannon.getY(), 
				cannon.getWidth() / 10, cannon.getHeight() / 2, 
				cannon.getWidth(), cannon.getHeight(), 
				1, 1, 
				cannon.getAngle());
		
        batcher.end();
		
	}

}
