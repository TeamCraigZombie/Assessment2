package com.craig.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.craig.game.Screens.EndScreen;
import com.craig.game.Screens.LoadingScreen;
import com.craig.game.Screens.MainScreen;
import com.craig.game.Screens.MenuScreen;
import com.craig.game.srpite.CText;
import com.craig.game.state.State;
import com.craig.game.state.instances.Test;

public class CraigGame extends Game {
	public SpriteBatch Batch;

	private MenuScreen menuScreen;
	private MainScreen mainScreen;
	private EndScreen endScreen;
	private LoadingScreen loadingScreen;

	public final static int MENU = 0;
	public final static int APPLICATION = 1;
	public final static int ENDGAME = 2;

	@Override
	public void create () {
		CText.DefualtFont = new BitmapFont();

		Batch = new SpriteBatch();
		//setScreen(new Test(this));

		loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		Batch.dispose();
	}

	/**
	 * changes the current state/view
	 */
	public void switchState(int screen) {

		//Allows the screens to be switched
		switch(screen){
			case MENU:
				if(menuScreen == null) menuScreen = new MenuScreen(this);
					this.setScreen(menuScreen);
				break;
			case ENDGAME:
				if (endScreen ==  null) endScreen = new EndScreen(this);
					this.setScreen(endScreen);
				break;
			case APPLICATION:
				if (mainScreen ==  null) mainScreen = new MainScreen(this);
					this.setScreen(mainScreen);
				break;
		}
	}
}
