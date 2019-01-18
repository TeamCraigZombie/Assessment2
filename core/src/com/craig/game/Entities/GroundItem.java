package com.craig.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class GroundItem extends Entity {
    public GroundItem(Vector2 position, Texture tex) {
        super(position, tex, new Vector2(50, 50));
    }

    public boolean checkCollision(Player obj){return false;}
}
