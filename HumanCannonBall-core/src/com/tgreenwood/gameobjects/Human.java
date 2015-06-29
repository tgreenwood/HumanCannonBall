package com.tgreenwood.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Human {

	private Vector2 position, position0;
	private Vector2 velocity, velocity0;
	private Vector2 acceleration;
	
	private float absVelocity;
	private float angle;
	private int width;
	private int height;
	
	private float time;
	private boolean canShot = false;
	private boolean stop = false;
	
	public Human(int width, int height) {
        this.width = width;
        this.height = height;
        this.acceleration = new Vector2(0, 1);
        init();
	}
	
	private void init() {
        this.position0 = new Vector2(-6, 292);
        this.position = position0;
        time = 0;
        setCanShot(false);
        setStop(false);
	}
	
    public void update(float delta) {
    	
    	if (!getStop()) {
    		if (canShot) {
    			time += delta / 2;
    			velocity.y = velocity0.y - acceleration.y * time;
    			position.x = position0.x + velocity0.x * time;
    			position.y = position0.y +  velocity0.y * time - acceleration.y * time * time / 2;
    			setAngle((float) Math.atan(velocity.y / velocity.x));
    		} else {
    			this.velocity0 = new Vector2((float)(absVelocity * Math.cos(Math.toRadians(getAngle())) / 3), (float)(absVelocity * Math.sin(Math.toRadians(getAngle())) / 3));
    			this.velocity = velocity0;
    		}
    	}
    	
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

    public boolean canShot() {
    	return canShot;
    }
    
    public void setCanShot(boolean value) {
    	canShot = value;
    }
    
    public void reset() {
    	init();
    }

	public float getAbsVelocity() {
		return absVelocity;
	}

	public void setAbsVelocity(float absVelocity) {
		this.absVelocity = absVelocity;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}
	
	public void setVelocity() {
		this.velocity0 = new Vector2((int)(absVelocity * Math.cos(getAngle())), (int)(absVelocity * Math.sin(getAngle())));
		this.velocity = velocity0;
	}

	public boolean getStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
    
}
