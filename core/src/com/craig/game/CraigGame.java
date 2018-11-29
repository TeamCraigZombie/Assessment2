package com.craig.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.craig.game.srpite.CText;
import com.craig.game.state.State;
import com.craig.game.state.instances.Test;

public class CraigGame extends Game {
	public SpriteBatch Batch;

	@Override
	public void create () {
		CText.DefualtFont = new BitmapFont();

		Batch = new SpriteBatch();
		setScreen(new Test(this));
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
	 * changes the current state
	 * @param target state to change to
	 */
	public void switchState(State target)
	{
		setScreen(target);
	}
}
