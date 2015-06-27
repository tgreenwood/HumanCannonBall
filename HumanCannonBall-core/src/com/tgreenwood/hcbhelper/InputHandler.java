package com.tgreenwood.hcbhelper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class InputHandler {

	private static boolean pickedAngle = false;
	private static boolean pickedInitVelocity = false;
	private static boolean restart = false;
	
	public static void update() {
		
		if (Gdx.input.isKeyJustPressed(Keys.SPACE) && isPickedAngle() && isPickedInitVelocity()) {
			setRestart(true);
			return;
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.SPACE) && !isPickedAngle()) {
			setPickedAngle(true);
			return;
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.SPACE) && !isPickedInitVelocity()) {
			setPickedInitVelocity(true);
			return;
		}
		
	}

	public static boolean isPickedAngle() {
		return pickedAngle;
	}

	public static void setPickedAngle(boolean pickedAngle) {
		InputHandler.pickedAngle = pickedAngle;
	}

	public static boolean isPickedInitVelocity() {
		return pickedInitVelocity;
	}

	public static void setPickedInitVelocity(boolean pickedInitVelocity) {
		InputHandler.pickedInitVelocity = pickedInitVelocity;
	}

	public static boolean shouldRestart() {
		return restart;
	}

	public static void setRestart(boolean restart) {
		InputHandler.restart = restart;
	}
	
	public static void reset() {
		restart = false;
		pickedAngle = false;
		pickedInitVelocity = false;
	}

}
