package com.craig.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.craig.game.srpite.CSprite;

public class Player extends Entity {
    public int trueV;   //Players default travel speed.
    public int MAXV;    //Players current travel speed.

    private int health; //Players health value.
    private double DELTAV;  //Value by which players speed is decreased to zero.
    public int mouseX, mouseY;  //Cursor position coordinates
    private double endTime; //End value for a timer system.

    //Constructor: Assigns different health and speed values depending on which character is chosen.
    public Player(Texture tex, int character)
    {
        //Passes given texture, size value of 50x50 and position value of 607, 2048 to super class.
        super(new Vector2(607, 2048), tex, new Vector2(50, 50));
        DELTAV = 0.2;
        if (character == 0) {
            health = 150;
            MAXV = 3;
            trueV = 3;
        }
        else {
            health = 100;
            MAXV = 6;
            trueV = 6;
        }
    }

    //Performs checks and updates player values every cycle.
    public void update(TiledMapTileLayer collisionLayer, TiledMapTileLayer mapBox, Vector3 camPos){
        //checks for collision with 'wall'layer and if map is limited then also the given 'barrier' layer.
        wallCollision(collisionLayer);
        if(!(mapBox == null)) {wallCollision(mapBox);}

        //update sprite position by the velocity
        sprite.X += velocity.x;
        sprite.Y += velocity.y;

        //get current cursor position.
        mouseX = (int)camPos.x - (Gdx.graphics.getWidth()/2) + Gdx.input.getX();
        mouseY = (int)camPos.y + (Gdx.graphics.getHeight()/2) - Gdx.input.getY();

        //rotate the player with respect to the mouse position.
        rotate();

        //reduce velocity if not already zero.
        if (velocity.x != 0 || velocity.y != 0) {updateVelocity();}

        //if timer is up then set player speed back to default.
        if (System.currentTimeMillis() >= endTime) {MAXV = trueV;}
    }

    //Reduce velocity towards zero.
    //If velocity component is less than deltav then set component to zero.
    private void updateVelocity(){
        if (velocity.x > DELTAV) {velocity.x -= DELTAV;}
        else if (velocity.x < -DELTAV) {velocity.x += DELTAV;}
        else {velocity.x = 0;}

        if (velocity.y > DELTAV) {velocity.y -= DELTAV;}
        else if (velocity.y < -DELTAV) {velocity.y += DELTAV;}
        else {velocity.y = 0;}
    }

    //All move methods increment velocity in the required direction but only if velocity is not already at maximum and the tile ahead is clear to be moved into.
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


    //Sets sprite rotation.
    //Calculates angle to rotate by using the mouse position.
    private void rotate(){
        float angle;
        angle = (float)Math.toDegrees(Math.atan2(mouseY - (sprite.Y + sprite.Height/2), mouseX - (sprite.X + sprite.Width/2)));
        angle = (angle%360) - 90;
        sprite.setRotation(angle);
    }

    //Spawns a bullet when called.
    //Calculates bullets trajectory using the mouse position.
    public CSprite shoot(Array<Projectile> bullets) {
        Vector2 prjVel = new Vector2();
        Vector2 prjPos = new Vector2();
        Vector2 size = new Vector2(10, 10); //Bullet size of 10x10.

        //Initial bullet position. Set to centre of current player location.
        prjPos.x = sprite.X + (sprite.Width/2) - (size.x/2);
        prjPos.y = sprite.Y + (sprite.Height/2) - (size.y/2);

        //Calculating bullets velocity.
        //First finding vector between player and mouse positions then normalising that vector
        //then scaling the components by 20.
        prjVel.x = mouseX - prjPos.x + (size.x/2);
        prjVel.y = mouseY - prjPos.y + (size.y/2);
        prjVel.nor();
        prjVel.x = prjVel.x * 20;
        prjVel.y = prjVel.y * 20;

        //Creating new instance of a bullet and adding it to the list of bullets.
        Projectile bullet = new Projectile(prjPos, prjVel, new Texture("square.png"), size);
        bullets.add(bullet);

        //returning the sprite so it can be added to the list of drawables.
        return bullet.sprite;
    }

    //Checks for collision with given tile map layer.
    //If the tile, in front of the direction the player is moving, is blocked then velocity component is set to zero so that player cannot move in that direction.
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

    //Starts a timer by setting the end time to 20 seconds after the current time.
    public void startClock(){
        endTime = System.currentTimeMillis() + 20000;
    }

    //Increases health by a value of ten.
    public void increaseHealth(){health += 10;}
}
