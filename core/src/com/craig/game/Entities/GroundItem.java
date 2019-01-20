package com.craig.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public abstract class GroundItem extends Entity {
    public GroundItem(Vector2 position, Texture tex) {
        //Passes given values to super class. Gives super class a size value of 50x50.
        super(position, tex, new Vector2(50, 50));
    }

    //Returns false. This method is overridden by subclasses.
    public boolean checkCollision(Player obj, boolean na){return false;}
}
