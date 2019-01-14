package com.craig.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.craig.game.srpite.CSprite;

public class Player extends Entity {
    private int health;
    private int MAXV = 5;
    public double DELTAV = 0.2;

    private int mouseX, mouseY;

    public Player(Vector2 pos, Vector2 vel, Texture tex, Vector2 size)
    {
        super(pos, vel, tex, size);
        health = 100;
    }

    public Player(Vector2 pos, Texture tex, Vector2 size)
    {
        super(pos, tex, size);
        health = 100;
    }

    public void update(){
        sprite.X += velocity.x;
        sprite.Y += velocity.y;
        mouseX = Gdx.input.getX();
        mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
        rotate();
        if (velocity.x != 0 || velocity.y != 0) {updateVelocity();}
    }

    private void updateVelocity(){
        if (velocity.x > DELTAV) {velocity.x -= DELTAV;}
        else if (velocity.x < -DELTAV) {velocity.x += DELTAV;}
        else {velocity.x = 0;}

        if (velocity.y > DELTAV) {velocity.y -= DELTAV;}
        else if (velocity.y < -DELTAV) {velocity.y += DELTAV;}
        else {velocity.y = 0;}
    }

    public void moveUp() {if (velocity.y <= MAXV) velocity.y++;}
    public void moveDown() {if (velocity.y >= -MAXV) velocity.y--;}
    public void moveLeft() {if (velocity.x >= -MAXV) velocity.x--;}
    public void moveRight() {if (velocity.x <= MAXV) velocity.x++;}

    private void rotate(){
        float angle;
        angle = (float)Math.toDegrees(Math.atan2(mouseY - (sprite.Y + sprite.Height/2), mouseX - (sprite.X + sprite.Width/2)));
        angle = (angle%360) - 90;
        sprite.setRotation(angle);
    }

    public CSprite shoot(Array<Projectile> bullets) {
        Vector2 prjVel = new Vector2();
        Vector2 prjPos = new Vector2();
        Vector2 size = new Vector2(20, 20);

        prjPos.x = sprite.X + (sprite.Width/2) - (size.x/2);
        prjPos.y = sprite.Y + (sprite.Height/2) - (size.y/2);

        prjVel.x = mouseX - prjPos.x + (size.x/2);
        prjVel.y = mouseY - prjPos.y + (size.y/2);

        prjVel.nor();
        prjVel.x = prjVel.x * 20;
        prjVel.y = prjVel.y * 20;

        Projectile bullet = new Projectile(prjPos, prjVel, new Texture("square.png"), size);
        bullets.add(bullet);

        return bullet.sprite;
    }
}
