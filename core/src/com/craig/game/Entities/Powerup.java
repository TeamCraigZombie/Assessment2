package com.craig.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class Powerup extends GroundItem {

    public Powerup(Texture tex, Vector2 mapSize, TiledMapTileLayer collisionLayer) {
        super(new Vector2(0, 0), tex);

        boolean posFound = false;

        while(!posFound){
            position.x = (float)(Math.random() *  mapSize.x);
            position.y = (float)(Math.random() *  mapSize.y);

            if(!isCellBlocked((int)position.x, (int)position.y, collisionLayer)){
                sprite.X = (int)position.x;
                sprite.Y = (int)position.y;
                posFound = true;
            }
        }
    }


    public boolean israpidFire() {return false;}
}
