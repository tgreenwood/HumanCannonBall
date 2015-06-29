package com.tgreenwood.hcbhelper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	public static Texture[] textures = new Texture[16];
	public static Texture humanT;
	public static Texture cannonT;
	public static Texture baseT;
	public static Texture progressBarT;
	public static Texture pillowT;
	
	public static TextureRegion human;	
	public static TextureRegion cannon;	
	public static TextureRegion base;
	public static TextureRegion pillow;
	
	public static NinePatch empty;
	public static NinePatch full;
	public static BitmapFont font;
	
	
	public static Animation backgroundAnimation;
	

	public static void load() {
		
		
		for (int i = 0; i < textures.length; i++) {
			String pathFile = String.format("data/city%d.png", i);
			textures[i] = new Texture(Gdx.files.internal(pathFile));
		}
		
		TextureRegion[] backgrounds = new TextureRegion[textures.length];
		
		for (int i = 0; i < textures.length; i++) {
			backgrounds[i] = new TextureRegion(textures[i]);
		}
		
		backgroundAnimation = new Animation(0.14f, backgrounds);
		backgroundAnimation.setPlayMode(Animation.PlayMode.LOOP);
		
		humanT = new Texture(Gdx.files.internal("data/human.png"));
		human = new TextureRegion(humanT);
		
		cannonT = new Texture(Gdx.files.internal("data/cannon.png"));
		cannon = new TextureRegion(cannonT);
		
		baseT = new Texture(Gdx.files.internal("data/base.png"));
		base = new TextureRegion(baseT);
		
		progressBarT = new Texture(Gdx.files.internal("data/prgBar.png"));
		empty = new NinePatch(new TextureRegion(progressBarT, 0, 40, 12, 12), 4, 4, 4, 4);
		full = new NinePatch(new TextureRegion(progressBarT, 0, 52, 6, 12), 0, 0, 0, 0);
		font=new BitmapFont();
		
		pillowT = new Texture(Gdx.files.internal("data/pillow.png"));
		pillow = new TextureRegion(pillowT);
		
	}

	public static void dispose() {
		for (Texture texture : textures) {
			texture.dispose();
		}
		humanT.dispose();
		cannonT.dispose();
		baseT.dispose();
		progressBarT.dispose();
		pillowT.dispose();
		font.dispose();
		
	}
	
}
