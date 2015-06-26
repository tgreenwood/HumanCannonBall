package com.tgreenwood.humancannonball.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import com.tgreenwood.humancannonball.HCBGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Human Cannon Ball";
        config.width = 500;
        config.height = 708;
		new LwjglApplication(new HCBGame(), config);
	}
}
