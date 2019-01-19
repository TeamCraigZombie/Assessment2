package com.craig.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class RapidFire extends Powerup {

    private double endTime;

    public RapidFire(Texture tex, Vector2 mapSize, TiledMapTileLayer collisionLayer) {
        super(tex, mapSize, collisionLayer);
    }

    public boolean checkCollision(Player obj, boolean rfActive) {
        if (isCollision(obj) && !rfActive) {
            endTime = System.currentTimeMillis() + 20000;
            return true;
        } else {
            return false;
        }
    }

    public boolean isTimeUp(){
        if (System.currentTimeMillis() >= endTime) {return true;}
        else {return false;}
    }

    @Override
    public boolean israpidFire() {return true;}
}
