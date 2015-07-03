package com.tgreenwood.gameobjects;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;

public class Level {

	public static Map<Integer, Vector2> levels = new HashMap<Integer, Vector2>();
	
	static {
		levels.put(1, new Vector2(285, 294));
		levels.put(2, new Vector2(325, 236));
		levels.put(3, new Vector2(440, 307));
	}
	
}
