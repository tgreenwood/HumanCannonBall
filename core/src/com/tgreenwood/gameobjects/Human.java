package com.tgreenwood.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.tgreenwood.hcbhelper.AssetLoader;

public class Human {

	private Vector2 position, position0;
	private Vector2 velocity, velocity0;
	private Vector2 acceleration;
	
	private float absVelocity;
	private float angle;
	private int width;
	private int height;
	
	private float time;
	private boolean canMove = false;
	private boolean stop = false;
	private boolean justFired = false;
	private boolean fired = true;
	private boolean justLanded = false;
	private boolean cry = false;
	private boolean criedBefore = false;
	
	public Human(int width, int height) {
        this.width = width;
        this.height = height;
        this.acceleration = new Vector2(0, 1);
        init();
	}
	
	private void init() {
		this.position0 = new Vector2(-16, 276);
		this.position = position0;
        time = 0;
        setCanShot(false);
        setStop(false);
        setJustFired(false);
        setFired(true);
        setJustLanded(false);
        setCry(false);
        setCriedBefore(false);
	}
	
    public void update(float delta) {
    	
    	if (!stoped()) {
    		if (canMove) {
    			
    			// sound shot on
    			if (justFired) {
    				AssetLoader.cry.stop();
    				AssetLoader.shot.play();
    				setJustFired(false);
    				setFired(false);
    			}
    			
    			// sound cry on
    			if (cry) {
    				AssetLoader.cry.play(0.2f);
    				setCry(false);
    				setCriedBefore(true);
    			}
    			
    			// calculate position, velocity and angle
    			time += delta / 4;
    			velocity.y = velocity0.y - acceleration.y * time;
    			position.x = position0.x + velocity0.x * time;
    			position.y = position0.y +  velocity0.y * time - acceleration.y * time * time / 2;
    			setAngle((float) Math.atan(velocity.y / (velocity.x)));
    		} else {
    			this.velocity0 = new Vector2((float)(absVelocity * Math.cos(Math.toRadians(getAngle())) / 3), 
    										 (float)(absVelocity * Math.sin(Math.toRadians(getAngle())) / 3));
    			this.velocity = velocity0;
    		}
    	} else {
    		
			// sound pillow interaction on
			if (justLanded) {
				AssetLoader.land.play();
				setJustLanded(false);
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
    	return canMove;
    }
    
    public void setCanShot(boolean value) {
    	canMove = value;
    }
    
    public void reset() {
    	init();
    }

	public float getAbsVelocity() {
		return absVelocity;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}
	
	public void setVelocity(float absVel) {
		this.absVelocity = absVel;
		this.velocity0 = new Vector2((int)(absVelocity * Math.cos(getAngle())), (int)(absVelocity * Math.sin(getAngle())));
		this.velocity = velocity0;
	}

	public boolean stoped() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public void setJustFired(boolean justShot) {
		this.justFired = justShot;
	}

	public boolean fired() {
		return fired;
	}

	public void setFired(boolean shotOn) {
		this.fired = shotOn;
	}

	public boolean justLanded() {
		return justLanded;
	}

	public void setJustLanded(boolean justLanded) {
		this.justLanded = justLanded;
	}

	public boolean shouldCry() {
		return cry;
	}

	public void setCry(boolean cry) {
		this.cry = cry;
	}

	public boolean getCriedBefore() {
		return criedBefore;
	}
	
	public void setCriedBefore(boolean crieadBefore) {
		this.criedBefore = crieadBefore;
	}
    
}
