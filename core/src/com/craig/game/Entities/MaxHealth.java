package com.craig.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class MaxHealth extends Powerup {
    //Passes given values to super class.
    public MaxHealth(Texture tex, Vector2 mapSize, TiledMapTileLayer collisionLayer) {
        super(tex, mapSize, collisionLayer);
    }

    //If collision has occurred with player, calls players increase health method and returns true.
    @Override
    public boolean checkCollision(Player obj, boolean na) {
        if (isCollision(obj)) {
            obj.increaseHealth();
            return true;
        } else {
            return false;
        }
    }
}
