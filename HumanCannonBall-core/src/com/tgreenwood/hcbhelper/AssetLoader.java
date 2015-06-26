package com.tgreenwood.hcbhelper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation.ANONYMOUS;

public class AssetLoader {

	public static Texture[] textures = new Texture[16];
	public static Texture hTexture;
	public static Texture cTexture;
	public static Texture bTexture;
	
	public static Animation backgroundAnimation;
	
	public static TextureRegion human;	
	public static TextureRegion cannon;	
	public static TextureRegion base;

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
		
		hTexture = new Texture(Gdx.files.internal("data/human.png"));
		human = new TextureRegion(hTexture);
		
		cTexture = new Texture(Gdx.files.internal("data/cannon.png"));
		cannon = new TextureRegion(cTexture);
		
		bTexture = new Texture(Gdx.files.internal("data/base.png"));
		base = new TextureRegion(bTexture);
		
	}

	public static void dispose() {
		for (Texture texture : textures) {
			texture.dispose();
		}
		hTexture.dispose();
		cTexture.dispose();
		bTexture.dispose();
	}
	
}
