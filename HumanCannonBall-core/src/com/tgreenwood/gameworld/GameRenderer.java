package com.tgreenwood.gameworld;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
		
		batcher.begin();
        batcher.draw(AssetLoader.backgroundAnimation.getKeyFrame(runTime),
	                0, 0, 500, 708);
        batcher.end();
		
	}

}
