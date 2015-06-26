package com.tgreenwood.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Human {

	private Vector2 position, position0;
	private Vector2 velocity, velocity0;
	private Vector2 acceleration;

	private float rotation;
	private int width;
	private int height;
	
	float time;
	
	public Human(int width, int height) {
        this.width = width;
        this.height = height;
        this.position = new Vector2(0, 0);
        this.position0 = new Vector2(0, 400);
        this.velocity0 = new Vector2(200, 400);
        this.velocity = velocity0;
        this.acceleration = new Vector2(0, 10);
        this.rotation = (float) Math.atan(velocity.y / velocity.x);
        time = 0;
	}
	
    public void update(float delta) {

    	time += delta;
    	velocity.y = velocity0.y - acceleration.y * time;
    	position.x = position0.x + velocity.x * time;
    	position.y = position0.y +  velocity0.y * time - acceleration.y * time * time / 2;
    	rotation = (float) Math.atan(velocity.y / velocity.x);
    	
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
