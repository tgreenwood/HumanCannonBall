package com.tgreenwood.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Pillow {

	private Vector2 position;
	private int height;
	private int width;
	
	public Pillow(int width, int height) {
		this.width = width;
		this.height = height;
		this.position = new Vector2(285, 292);
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}

	public boolean contains(Vector2 point, float offset) {
		Rectangle pillow = new Rectangle(position.x	, position.y, width, height / 3);
		return pillow.contains(point.x + offset, point.y + offset);
	}
	
}
