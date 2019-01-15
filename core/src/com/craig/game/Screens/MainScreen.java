package com.craig.game.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.craig.game.CraigGame;
import com.craig.game.state.State;



public class MainScreen extends State {

    private CraigGame parent;
    private Stage stage;

    public MainScreen(CraigGame craigGame){
        super(craigGame);
        parent = craigGame;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {


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
