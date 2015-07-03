package com.tgreenwood.hcbhelper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
	public static Texture windT;
	
	public static TextureRegion human;	
	public static TextureRegion cannon;	
	public static TextureRegion base;
	public static TextureRegion pillow;
	public static TextureRegion wind0, wind1, wind2, wind3, wind4, wind5;
	public static BitmapFont font, shadow;
	
	public static NinePatch empty;
	public static NinePatch full;
	
	public static Animation backgroundAnimation;
	public static Music noiseCars;
	public static Music noiseTrain; 
	public static Sound shot;

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
		
		windT = new Texture(Gdx.files.internal("data/wind.png"));
		wind0 = new TextureRegion(windT, 0, 54, 18, 10);
		wind1 = new TextureRegion(windT, 19, 54, 15, 10);
		wind2 = new TextureRegion(windT, 33, 54, 15, 10);
		wind3 = new TextureRegion(windT, 33, 42, 15, 12);
		wind4 = new TextureRegion(windT, 19, 42, 15, 12);		
		wind5 = new TextureRegion(windT, 0, 42, 15, 12);
		
		font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
		font.getData().setScale(.3f, .3f);
		shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
		shadow.getData().setScale(.3f, .3f);
		
		noiseCars = Gdx.audio.newMusic(Gdx.files.internal("data/cityNoises.mp3"));
		noiseCars.setLooping(true);
		noiseTrain = Gdx.audio.newMusic(Gdx.files.internal("data/metroNose.mp3"));
		noiseTrain.setLooping(true);
		
		shot = Gdx.audio.newSound(Gdx.files.internal("data/shot.mp3"));
		
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
		windT.dispose();
		font.dispose();
		shadow.dispose();
		noiseCars.dispose();
		noiseTrain.dispose();
		shot.dispose();
	}
	
}
