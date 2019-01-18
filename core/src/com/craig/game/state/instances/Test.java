package com.craig.game.state.instances;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.craig.game.CraigGame;
import com.craig.game.Entities.Player;
import com.craig.game.Entities.Projectile;
import com.craig.game.state.State;
//import javafx.scene.Parent;

public class Test extends State {

    private Player player1;
    private Array<Projectile> bullets = new Array<Projectile>();
    private boolean mouseHeld;

    public Test(CraigGame Parent)
    {
        super(Parent);
        player1 = new Player(new Vector2(910, 480), new Texture("square.png"), new Vector2(100, 100));
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

    private void keyPressed(){
        if(Gdx.input.isKeyPressed(Input.Keys.W)){player1.moveUp();}
        if(Gdx.input.isKeyPressed(Input.Keys.S)){player1.moveDown();}
        if(Gdx.input.isKeyPressed(Input.Keys.A)){player1.moveLeft();}
        if(Gdx.input.isKeyPressed(Input.Keys.D)){player1.moveRight();}
    }

    private void leftClick() {
        if (!mouseHeld) {
            add(player1.shoot(bullets));
            mouseHeld = true;
        }
    }
}
