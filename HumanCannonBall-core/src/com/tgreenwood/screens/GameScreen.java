package com.tgreenwood.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.tgreenwood.gameworld.GameRenderer;
import com.tgreenwood.gameworld.GameWorld;
import com.tgreenwood.hcbhelper.InputHandler;

public class GameScreen implements Screen {
	
    private GameWorld world;
    private GameRenderer renderer;
    private float runTime = 0;
    
    public GameScreen() {
        world = new GameWorld();
        renderer = new GameRenderer(world);
    }

	@Override
	public void show() {
		Gdx.app.log("GameScreen", "show called");
	}

	@Override
	public void render(float delta) {
		runTime += delta;
        world.update(delta);
        renderer.render(runTime);
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
