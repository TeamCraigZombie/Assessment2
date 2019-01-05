package com.craig.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Projectile extends Entity {

    public Projectile(Vector2 pos, Vector2 vel, Texture tex, Vector2 size)
    {
        super(pos, vel, tex, size);
    }

    public void update() {
        sprite.X += velocity.x;
        sprite.Y += velocity.y;
    }
}
