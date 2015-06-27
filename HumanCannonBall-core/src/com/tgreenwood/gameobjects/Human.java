package com.tgreenwood.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Human {

	private Vector2 position, position0;
	private Vector2 velocity, velocity0;
	private Vector2 acceleration;

	private float rotation;
	private int width;
	private int height;
	
	private float time;
	private boolean canShot = false;
	
	public Human(int width, int height) {
        this.width = width;
        this.height = height;
        init();
	}
	
	private void init() {
        this.position0 = new Vector2(0, 290);
        this.position = position0;
        this.velocity0 = new Vector2(10, 10);
        this.velocity = velocity0;
        this.acceleration = new Vector2(0, 1);
        this.rotation = (float) Math.atan(velocity.y / velocity.x);
        time = 0;
        setCanShot(false);
	}
	
    public void update(float delta) {

    	if (canShot) {
    		time += delta / 2;
    		velocity.y = velocity0.y - acceleration.y * time;
    		position.x = position0.x + velocity0.x * time;
    		position.y = position0.y +  velocity0.y * time - acceleration.y * time * time / 2;
    		rotation = (float) Math.atan(velocity.y / velocity.x);
    	}
    	
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
    
    public boolean canShot() {
    	return canShot;
    }
    
    public void setCanShot(boolean value) {
    	canShot = value;
    }
    
    public void reset() {
    	init();
    }
	
}
