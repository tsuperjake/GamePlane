package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.mygdx.screens.screens;
import com.mygdx.planeHelper.AssetLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MyGdxGame extends Game {
	
	@Override
	public void create () {
		Gdx.app.log("Game","created");
        AssetLoader.load();
		setScreen(new screens());
	}

	
	@Override
	public void dispose () {
        AssetLoader.dispose();

	}
}
