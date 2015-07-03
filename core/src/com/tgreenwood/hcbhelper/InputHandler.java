package com.tgreenwood.hcbhelper;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;

public class InputHandler extends InputAdapter {
	
	private boolean pickedAngle = false;
	private boolean pickedInitVelocity = false;
	private boolean restart = false;

	@Override
	public boolean keyDown(int keycode) {
		
		if (keycode == Keys.SPACE) {
			checkActions();
		}
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		if (button == Buttons.LEFT) {
			checkActions();
		}
		return true;
	}


	public boolean isPickedAngle() {
		return pickedAngle;
	}

	public void setPickedAngle(boolean pickedAngle) {
		this.pickedAngle = pickedAngle;
	}

	public boolean isPickedInitVelocity() {
		return pickedInitVelocity;
	}

	public void setPickedInitVelocity(boolean pickedInitVelocity) {
		this.pickedInitVelocity = pickedInitVelocity;
	}

	public boolean shouldRestart() {
		return restart;
	}

	public void setRestart(boolean restart) {
		this.restart = restart;
	}
	
	public void reset() {
		restart = false;
		pickedAngle = false;
		pickedInitVelocity = false;
	}
	
	public boolean checkActions() {
		
		if (isPickedAngle() && isPickedInitVelocity()) {
			setRestart(true);
		} else if (!isPickedAngle()) {
			setPickedAngle(true);
		} else if (!isPickedInitVelocity()) {
			setPickedInitVelocity(true);
		}
		
		return true;
		
	}

}
