package com.craig.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Key extends GroundItem {
    private Boolean found;

    public Key(Vector2 pos, Texture tex){
        super(pos, tex);
        found = false;
    }

    @Override
    public boolean checkCollision(Player obj, boolean na) {
        if (isCollision(obj)) {
            found = true;
            return true;
        } else {
            return false;
        }
    }

    public boolean isFound() {return found;}
}
