package com.craig.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class Coffee extends Powerup {
    public Coffee(Texture tex, Vector2 mapSize, TiledMapTileLayer collisionLayer){
        super(tex, mapSize, collisionLayer);
    }

    //Checks to see if there has been a collision with a player. If so then the speed value for the player is increased by a factor of 2.
    @Override
    public boolean checkCollision(Player obj, boolean na){
        //Check if collision has occurred and if player speed isn't already increased.
        if (isCollision(obj) && obj.MAXV == obj.trueV) {
            //Start timer and multiply speed.
            obj.startClock();
            obj.MAXV = obj.trueV * 2;
            return true;
        }
        else {return false;}
    }
}
