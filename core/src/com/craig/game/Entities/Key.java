package com.craig.game.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Key extends GroundItem {
    public Boolean found; //Boolean indicating if this instance of this object has been found by the player.

    //Passes given values to super class and assigns found to false.
    public Key(Vector2 pos, Texture tex){
        super(pos, tex);
        found = false;
    }

    //Returns true if there is a collision.
    //Sets found to true if collision has occurred with given player.
    @Override
    public boolean checkCollision(Player obj, boolean na) {
        if (isCollision(obj)) {
            found = true;
            return true;
        } else {
            return false;
        }
    }

    //Returns value of 'found'.
    public boolean isFound() {return found;}
}
