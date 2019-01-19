package com.craig.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.craig.game.CraigGame;
import com.craig.game.state.State;

public class EndScreen extends State {

    private CraigGame parent;
    private Stage stage;


    public EndScreen(CraigGame craigGame){
        super(craigGame);
        parent = craigGame;

        // creates stage and sets it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);


    }
    @Override
    public void show() {
        //Creates a table that fills the screen and sets the end screen background
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("EndScreenBackground.png"))));
        stage.addActor(table);


        //Temporary until asset Manager
        Skin skin = new Skin(Gdx.files.internal("flat-earth/skin/flat-earth-ui.json"));


        //creates button
        TextButton exit = new TextButton("EXIT TO MENU", skin);
        // adds an exit button to table
        table.add(exit).fillX().fillY().uniform().width(200).height(55).padBottom(20);

        //Methods create channel listener for button actions
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.switchState(CraigGame.MENU, 0);
            }
        });
    }

    @Override
    public void render(float delta) {
        // Empties Screen
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        // Asserts actions to stage and to draw itself
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        // Allows viewport to be changed when the size of the screen also changes
        stage.getViewport().update(width, height, true);

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
