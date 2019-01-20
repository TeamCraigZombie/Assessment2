package com.craig.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.craig.game.Screens.*;
import com.craig.game.srpite.CText;
import com.craig.game.test.GameTest;

public class CraigGame extends Game {
	public SpriteBatch Batch;

	private MenuScreen menuScreen;
	private MainScreen mainScreen;
	private EndScreen endScreen;
	private LoadingScreen loadingScreen;
	private PauseScreen pauseScreen;

	private boolean Test = false;

	public final static int MENU = 0;
	public final static int APPLICATION = 1;
	public final static int ENDGAME = 2;
	public final static int PAUSE = 3;

	public CraigGame()
	{
		super();
		Test = false;
	}

	public CraigGame(boolean test)
	{
		super();
		Test = test;
	}

	@Override
	public void create () {
		//debug code used to run unit tests if needed
		if(Test)
		{
			GameTest.test(this);
		}

		CText.DefualtFont = new BitmapFont();

		Batch = new SpriteBatch();

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
	public void switchState(int screen, int character) {

		//Allows the screens to be switched
		switch(screen){
			case MENU:
				if(menuScreen == null) menuScreen = new MenuScreen(this);
					this.setScreen(menuScreen);
					mainScreen = null;
					pauseScreen = null;
				break;
			case ENDGAME:
				if (endScreen ==  null) endScreen = new EndScreen(this);
					this.setScreen(endScreen);
				break;
			case APPLICATION:
				if (mainScreen ==  null) mainScreen = new MainScreen(this, character);
					this.setScreen(mainScreen);
					menuScreen = null;
				break;
			case PAUSE:
				if (pauseScreen == null) pauseScreen = new PauseScreen(this) ;
				this.setScreen(pauseScreen);
		}
	}
}
