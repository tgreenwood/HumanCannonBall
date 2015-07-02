package com.tgreenwood.humancannonball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.tgreenwood.hcbhelper.AssetLoader;
import com.tgreenwood.screens.GameScreen;

public class HCBGame extends Game {

	@Override
	public void create() {
		Gdx.app.log("HCBGame", "created");
		AssetLoader.load();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
	
	
}
