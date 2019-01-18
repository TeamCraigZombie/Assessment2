package com.craig.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class MaxHealth extends Powerup {
    public MaxHealth(Texture tex, Vector2 mapSize, TiledMapTileLayer collisionLayer) {
        super(tex, mapSize, collisionLayer);
    }

    @Override
    public boolean checkCollision(Player obj) {
        if (isCollision(obj) && !obj.isHealthFull()) {
            obj.setHealthMax();
            return true;
        } else {
            return false;
        }
    }
}
