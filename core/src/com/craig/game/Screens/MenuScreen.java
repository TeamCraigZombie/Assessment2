package com.craig.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.craig.game.CraigGame;
import com.craig.game.state.State;

public class MenuScreen extends State {

    private CraigGame parent;
    private Stage stage;

    public MenuScreen(CraigGame craigGame){
        super(craigGame);

        parent = craigGame;

        // creates stage and sets it as input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

        //Creates a table that fills the screen and sets the menu background
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("temp-background.jpg"))));
        stage.addActor(table);


        //Temporary until asset Manager
        Skin skin = new Skin(Gdx.files.internal("flat-earth/skin/flat-earth-ui.json"));


        //creates buttons
        TextButton start = new TextButton("START", skin);
        TextButton exit = new TextButton("EXIT", skin);


        //Creates select box and dialog box for character selection
        Label characterLabel = new Label("SELECT CHARACTER:", skin);
        characterLabel.setColor(Color.BLACK);
        final SelectBox<String> characters = new SelectBox<String>(skin);
        characters.setItems("Character 1","Character 2");

        //This adds the buttons, labels and dropdown box to the table

        table.add(start).fillX().fillY().uniform().width(200).height(55).padBottom(20);
        table.row();
        table.add(characterLabel);
        table.row().pad(5,0,5,0);
        table.add(characters).fillX().padBottom(35);
        table.row();
        table.add(exit).fillX().fillY().uniform().width(200). height(55);



        //Methods create channel listener for button actions
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        start.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.switchState(CraigGame.APPLICATION);
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
        // Assets disposed when no longer required
        stage.dispose();
    }
}
