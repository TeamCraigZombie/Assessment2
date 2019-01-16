package com.craig.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.craig.game.CraigGame;
import com.craig.game.Entities.Player;
import com.craig.game.Entities.Projectile;
import com.craig.game.state.State;



public class MainScreen extends State {

    private CraigGame parent;
    private Stage stage;
    private Player player1;
    private Array<Projectile> bullets = new Array<Projectile>();
    private boolean mouseHeld;


    public MainScreen(CraigGame craigGame, int character){
        super(craigGame);
        parent = craigGame;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        player1 = new Player(new Vector2(910, 480), new Texture("square.png"), new Vector2(100, 100), character);
        add(player1.sprite);
        mouseHeld = false;
    }

    @Override
    public void update(float time)
    {
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {leftClick();}
        if(!Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {mouseHeld = false;}
        if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {keyPressed();}

        player1.update();

        for (int i = 0; i < bullets.size; i++) {bullets.get(i).update();}
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

    private void keyPressed(){
        if(Gdx.input.isKeyPressed(Input.Keys.W)){player1.moveUp();}
        if(Gdx.input.isKeyPressed(Input.Keys.S)){player1.moveDown();}
        if(Gdx.input.isKeyPressed(Input.Keys.A)){player1.moveLeft();}
        if(Gdx.input.isKeyPressed(Input.Keys.D)){player1.moveRight();}
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){parent.switchState(CraigGame.PAUSE, 0);}
    }

    private void leftClick() {
        if (!mouseHeld) {
            add(player1.shoot(bullets));
            mouseHeld = true;
        }
    }
}
