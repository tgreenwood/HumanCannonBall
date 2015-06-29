package com.tgreenwood.gameworld;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.tgreenwood.gameobjects.Cannon;
import com.tgreenwood.gameobjects.Human;
import com.tgreenwood.gameobjects.Pillow;
import com.tgreenwood.hcbhelper.AssetLoader;
import com.tgreenwood.hcbhelper.InputHandler;

public class GameRenderer {

	private GameWorld world;
	private OrthographicCamera cam;
	private SpriteBatch batcher;
	private ShapeRenderer shapeRenderer;
	private Sprite sprite;
	
	public GameRenderer(GameWorld world) {
		this.world = world;
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 500, 708);
		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
		
		sprite = new Sprite(AssetLoader.human);

		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
	}

	public void render(float runTime) {
		
		float x = 0;
		float y = 0;
		
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
        	if (pillow.contain(human.getPosition().x + 30, human.getPosition().y + 30)) {
        		human.setStop(true);
        	}
        	sprite.setPosition(human.getPosition().x, human.getPosition().y);
        	sprite.setRotation(human.getAngle() * 180f / (float)Math.PI - 90f);
        	sprite.draw(batcher);
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
        
        
//        shapeRenderer.begin(ShapeType.Filled);
//        shapeRenderer.setColor(87 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);
//        shapeRenderer.rect(pillow.getPosition().x, pillow.getPosition().y, pillow.getWidth(), pillow.getHeight() / 3);
//        shapeRenderer.end();

		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(255 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);
		shapeRenderer.circle(human.getPosition().x + 30, human.getPosition().y + 30, 5);
		shapeRenderer.end();
		
//		shapeRenderer.circle(human.getPosition().x + 30, human.getPosition().y + 30, 5);
	}

}
