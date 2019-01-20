package com.craig.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class Powerup extends GroundItem {

    public Powerup(Texture tex, Vector2 mapSize, TiledMapTileLayer collisionLayer) {
        //Passes texture and position of 0, 0 to the super class.
        super(new Vector2(0, 0), tex);

        boolean posFound = false;   //boolean to indicate if a suitable position for the power up has been found.

        //Position is set to a random spot on the map which is then checked to see if it is a place that is not blocked.
        //Process repeats until a suitable position is found.
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

    //Returns false. Is overridden by the RapidFire subclass.
    public boolean israpidFire() {return false;}
}
