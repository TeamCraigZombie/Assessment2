package com.craig.game.Screens;

import com.badlogic.gdx.Screen;
import com.craig.game.CraigGame;
import com.craig.game.state.State;

public class LoadingScreen extends State{

    private CraigGame parent;

    public LoadingScreen(CraigGame craigGame){
        super(craigGame);
        parent = craigGame;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        parent.switchState(CraigGame.MENU, 0);

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}

