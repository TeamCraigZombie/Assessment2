package com.craig.game.srpite;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CText extends Drawable {
    static public BitmapFont DefualtFont;

    /**the text that is displayed by drawing this object*/
    public String Text;

    public BitmapFont Font;


    public CText (String message, int x, int y)
    {
        super(x, y);
        Text = message;
    }

    @Override
    public void draw(SpriteBatch target) {
        if(Font == null)
        {
            DefualtFont.draw(target, Text, X, Y);
        }
    }
}
