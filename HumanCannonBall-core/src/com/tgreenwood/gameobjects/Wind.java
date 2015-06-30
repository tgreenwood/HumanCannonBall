package com.tgreenwood.gameobjects;

import java.util.Random;

public class Wind {

	private float velocity;
	private int velocityIdx;
	private float[] velocities;
	
	public Wind() {
		this.velocity = 0;
		this.velocityIdx = 3;
		this.velocities = new float[]{-30, -20, -10, 0, 10, 20};
	}

	public float getVelocity() {
		return velocity;
	}
	
	public int getVelocityIdx() {
		return velocityIdx;
	}

	private void generateNewVelocity() {
		velocityIdx = new Random().nextInt(velocities.length);
		this.velocity = velocities[velocityIdx];
	}
	
	public void reset() {
		generateNewVelocity();
	}
	
}
