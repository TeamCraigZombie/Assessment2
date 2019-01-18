package com.craig.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.craig.game.srpite.CSprite;

public class Entity {
    protected Vector2 position;
    protected Vector2 velocity;
    public CSprite sprite;

    public Entity(Vector2 pos, Vector2 vel, Texture tex, Vector2 size)
    {
        position = pos;
        velocity = vel;
        sprite = new CSprite(tex, (int)pos.x, (int)pos.y, (int)size.x, (int)size.y);
    }

/*    public Entity(Vector2 pos, Texture tex, Vector2 size)
    {
        position = pos;
        velocity = new Vector2(0, 0);
        sprite = new CSprite(tex, (int)pos.x, (int)pos.y, (int)size.x, (int)size.y);
    }*/

}
