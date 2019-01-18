package com.craig.game.Entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;

public class Collideable extends Entity {
    protected boolean collidable;

    public Collideable (Vector2 pos, Texture tex, Vector2 size) {
        super(pos,new Vector2(0,0),tex,size);

        collidable = true;
    }

}
