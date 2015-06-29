package com.tgreenwood.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Cannon {

	private Vector2 position;
	private Vector2 basePosition;
	private float angle;
	private boolean fixedAngle;
	private int width;
	private int height;
	private float time;
	
	
	
	public Cannon(int width, int height) {
		this.position = new Vector2(10, 293);
		this.basePosition = new Vector2(-6, 300);
		this.setAngle(0);		
		this.width = width;
		this.height = height;
	}
	
    public void update(float delta) {

    	if (!fixedAngle) {
    		angle = (float) (45 * (Math.sin(time) + 1) / 2 + 15); // degrees
    		time += delta * 2;
    	}
    	
    }
	
	public void reset() {
		setFixedAngle(false);
	}

    public Vector2 getPosition() {
        return position;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public Vector2 getBasePosition() {
		return basePosition;
	}

	public boolean isFixedAngle() {
		return fixedAngle;
	}

	public void setFixedAngle(boolean fixedAngle) {
		this.fixedAngle = fixedAngle;
	}


	
	
	
}
