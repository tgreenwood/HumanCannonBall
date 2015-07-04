package com.tgreenwood.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.tgreenwood.gameworld.GameRenderer;
import com.tgreenwood.gameworld.GameWorld;
import com.tgreenwood.hcbhelper.AssetLoader;
import com.tgreenwood.hcbhelper.InputHandler;

public class GameScreen implements Screen {
	
	private InputHandler inputHandler;
    private GameWorld world;
    private GameRenderer renderer;
    
    public GameScreen() {
    	inputHandler = new InputHandler();
        world = new GameWorld(inputHandler);
        renderer = new GameRenderer(world);
        

        AssetLoader.noiseCars.play();
        AssetLoader.noiseTrain.play();
        
        Gdx.input.setInputProcessor(inputHandler);
    }

	@Override
	public void show() {
		Gdx.app.log("GameScreen", "show called");
	}

	@Override
	public void render(float delta) {
        world.update(delta);
        renderer.render(delta);
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log("GameScreen", "resize called");
	}

	@Override
	public void pause() {
		Gdx.app.log("GameScreen", "pause called");

	}

	@Override
	public void resume() {
		Gdx.app.log("GameScreen", "resume called");
	}

	@Override
	public void hide() {
		Gdx.app.log("GameScreen", "hide called");

	}

	@Override
	public void dispose() {

	}

}
