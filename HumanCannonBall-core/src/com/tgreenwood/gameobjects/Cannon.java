package com.tgreenwood.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Cannon {

	private Vector2 position;
	private float angle;
	
	private int width;
	private int height;
	private float time;
	
	public Cannon(int width, int height) {
		this.position = new Vector2(5, 350);
		this.setAngle(0);		
		this.width = width;
		this.height = height;
	}
	
    public void update(float delta) {

    	time += delta;
    	angle = (float) (75 * (Math.sin(time) + 1) / 2 + 15);
    	
    }
	
	public void onClick() {
	}

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
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


	
	
	
}
