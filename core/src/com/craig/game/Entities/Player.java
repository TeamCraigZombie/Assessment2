package com.craig.game.Entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.craig.game.srpite.CSprite;

public class Player extends Entity {
    public int trueV;
    public int MAXV;

    private int health;
    private int maxHealth;
    private double DELTAV;
    public int mouseX, mouseY;
    private double endTime;

//    public Player(Vector2 pos, Vector2 vel, Texture tex, Vector2 size)
//    {
//        super(pos, vel, tex, size);
//        health = 100;
//    }

    public Player(Texture tex, int character)
    {
        super(new Vector2(607, 2048), tex, new Vector2(50, 50));
        if (character == 0) {
            health = 150;
            maxHealth = 150;
            DELTAV = 0.2;
            MAXV = 3;
            trueV = 3;
        }
        else {
            health = 100;
            maxHealth = 100;
            DELTAV = 0.2;
            MAXV = 6;
            trueV = 6;
        }
    }

    public void update(TiledMapTileLayer collisionLayer, TiledMapTileLayer mapBox, Vector3 camPos){
        wallCollision(collisionLayer);
        if(!(mapBox == null)) {wallCollision(mapBox);}
        sprite.X += velocity.x;
        sprite.Y += velocity.y;
        //System.out.println(sprite.X);
        //System.out.println(sprite.Y);
        mouseX = (int)camPos.x - (Gdx.graphics.getWidth()/2) + Gdx.input.getX();
        mouseY = (int)camPos.y + (Gdx.graphics.getHeight()/2) - Gdx.input.getY();
        rotate();
        if (velocity.x != 0 || velocity.y != 0) {updateVelocity();}
        if (System.currentTimeMillis() >= endTime) {MAXV = trueV;}
    }

    private void updateVelocity(){
        if (velocity.x > DELTAV) {velocity.x -= DELTAV;}
        else if (velocity.x < -DELTAV) {velocity.x += DELTAV;}
        else {velocity.x = 0;}

        if (velocity.y > DELTAV) {velocity.y -= DELTAV;}
        else if (velocity.y < -DELTAV) {velocity.y += DELTAV;}
        else {velocity.y = 0;}
    }

    public void moveUp(TiledMapTileLayer collisionLayer) {if (velocity.y <= MAXV &&
            !isCellBlocked(sprite.X + sprite.Width/2, sprite.Y + sprite.Height + 10, collisionLayer))
        velocity.y++;}
    public void moveDown(TiledMapTileLayer collisionLayer) {if (velocity.y >= -MAXV &&
        !isCellBlocked(sprite.X + sprite.Width/2, sprite.Y - 10, collisionLayer))
    velocity.y--;}
    public void moveLeft(TiledMapTileLayer collisionLayer) {if (velocity.x >= -MAXV &&
            !isCellBlocked(sprite.X - 10, sprite.Y + sprite.Height/2, collisionLayer))
        velocity.x--;}
    public void moveRight(TiledMapTileLayer collisionLayer) {if (velocity.x <= MAXV &&
            !isCellBlocked(sprite.X + sprite.Width + 10, sprite.Y + sprite.Height/2, collisionLayer))
        velocity.x++;}

    private void rotate(){
        float angle;
        angle = (float)Math.toDegrees(Math.atan2(mouseY - (sprite.Y + sprite.Height/2), mouseX - (sprite.X + sprite.Width/2)));
        angle = (angle%360) - 90;
        sprite.setRotation(angle);
    }

    public CSprite shoot(Array<Projectile> bullets) {
        Vector2 prjVel = new Vector2();
        Vector2 prjPos = new Vector2();
        Vector2 size = new Vector2(10, 10);

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

    private void wallCollision(TiledMapTileLayer collisionLayer){
        if (velocity.x > 0){
            if(isCellBlocked(sprite.X + sprite.Width + 10, sprite.Y + sprite.Height/2, collisionLayer)){
                velocity.x = 0;
            }
        }
        if (velocity.x < 0){
            if(isCellBlocked(sprite.X - 10, sprite.Y + sprite.Height/2, collisionLayer)){
                velocity.x = 0;
            }
        }
        if (velocity.y > 0){
            if(isCellBlocked(sprite.X + sprite.Width/2, sprite.Y + sprite.Height + 10, collisionLayer)){
                velocity.y = 0;
            }
        }
        if (velocity.y < 0){
            if(isCellBlocked(sprite.X + sprite.Width/2, sprite.Y - 10, collisionLayer)){
                velocity.y = 0;
            }
        }
    }

    public void startClock(){
        endTime = System.currentTimeMillis() + 20000;
    }

    public boolean isHealthFull() {
        if(health == maxHealth) {return true;}
        else {return false;}
    }

    public void setHealthMax(){health = maxHealth;}
}
