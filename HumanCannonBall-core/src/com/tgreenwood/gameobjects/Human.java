package com.tgreenwood.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Human {

	private Vector2 position;
	private Vector2 velocity, velocity0;
	private Vector2 acceleration;

	private float rotation;
	private int width;
	private int height;
	
	float time;
	
	public Human(int width, int height, float rotation) {
        this.width = width;
        this.height = height;
		this.rotation = rotation;
        position = new Vector2(0, 0);
        velocity0 = new Vector2(100, 100);
        velocity = velocity0;
        acceleration = new Vector2(0, 10);
        time = 0;
	}
	
    public void update(float delta) {

    	time += delta;
    	position.x = velocity.x * time;
    	velocity.y = velocity0.y - acceleration.y * time;
    	position.y = velocity0.y * time - acceleration.y * time * time / 2;
    	
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

    public float getRotation() {
        return rotation;
    }
	
}
