package com.craig.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class Projectile extends Entity {

    public Projectile(Vector2 pos, Vector2 vel, Texture tex, Vector2 size)
    {
        super(pos, vel, tex, size);
    }

    //Updates sprite position by incrementing it by the velocity.
    public void update() {
        sprite.X += velocity.x;
        sprite.Y += velocity.y;

    }

    //Checks to see if a collision has occurred with a cell in the given tile map layer.
    public boolean collision(TiledMapTileLayer collisionLayer) {
        if(isCellBlocked(sprite.X + sprite.Width/2, sprite.Y + sprite.Height/2, collisionLayer)){
            return true;
        }
        else {return false;}
    }
}
