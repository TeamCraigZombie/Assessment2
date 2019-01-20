package com.craig.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class RapidFire extends Powerup {

    private double endTime; //End value for timer system

    public RapidFire(Texture tex, Vector2 mapSize, TiledMapTileLayer collisionLayer) {
        super(tex, mapSize, collisionLayer);
    }

    //If collision has occurred and given boolean is false then 20 second timer is started and true is returned.
    public boolean checkCollision(Player obj, boolean rfActive) {
        if (isCollision(obj) && !rfActive) {
            endTime = System.currentTimeMillis() + 20000;
            return true;
        } else {
            return false;
        }
    }

    //If timer is up then true is returned.
    public boolean isTimeUp(){
        if (System.currentTimeMillis() >= endTime) {return true;}
        else {return false;}
    }

    //Returns true indicating that it is a rapid fire power up.
    @Override
    public boolean israpidFire() {return true;}
}
