package com.craig.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class Coffee extends Powerup {
    public Coffee(Texture tex, Vector2 mapSize, TiledMapTileLayer collisionLayer){
        super(tex, mapSize, collisionLayer);

    }

    @Override
    public boolean checkCollision(Player obj){
        if (isCollison(obj) && obj.MAXV < obj.trueV * 2) {
            obj.startClock();
            obj.MAXV = obj.trueV * 2;
            return true;
        }
        else {return false;}
    }
}
