package com.tgreenwood.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Pillow {

	private Vector2 position;
	private int height;
	private int width;
	
	public Pillow(int width, int height) {
		this.width = width;
		this.height = height;
		this.position = new Vector2(285, 294);
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
}
