package com.craig.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.craig.game.srpite.CSprite;

public class Entity {
    protected Vector2 position; //Entity position as a 2D vector.
    protected Vector2 velocity; //Entity velocity as a 2D vector.
    public CSprite sprite;  //Entity's associated sprite.

    //Constructor assigns position, velocity and new sprite with the given texture and size.
    public Entity(Vector2 pos, Vector2 vel, Texture tex, Vector2 size)
    {
        position = pos;
        velocity = vel;
        sprite = new CSprite(tex, (int)pos.x, (int)pos.y, (int)size.x, (int)size.y);
    }

    //Overloaded constructor assigns position and new sprite with the given texture and size. Sets velocity to zero.
    public Entity(Vector2 pos, Texture tex, Vector2 size)
    {
        position = pos;
        velocity = new Vector2(0, 0);
        sprite = new CSprite(tex, (int)pos.x, (int)pos.y, (int)size.x, (int)size.y);
    }

    //Returns true if the given coordinate is within a tile that has properties 'wall = true' or 'barrier = true' for the given tile map layer.
    protected boolean isCellBlocked(int x, int y, TiledMapTileLayer collisionLayer) {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell(
                (int)(x / collisionLayer.getTileWidth()),
                (int)(y / collisionLayer.getTileHeight()));

        return cell != null && cell.getTile() != null && (cell.getTile().getProperties().get("wall").equals(true) || cell.getTile().getProperties().get("barrier").equals(true));
    }

    //Returns true if there is collision between this and the given entity.
    //Does this by checking if the bounds of the given entity overlap with the bounds of this entity.
    protected boolean isCollision(Entity obj){
        if (sprite.X >= obj.sprite.X - obj.sprite.Width && sprite.X <= obj.sprite.X + obj.sprite.Width){
            if (sprite.Y >= obj.sprite.Y - obj.sprite.Height && sprite.Y <= obj.sprite.Y + obj.sprite.Height){
                return true;
            }
            else { return false;}
        }
        else {return false;}
    }

}
