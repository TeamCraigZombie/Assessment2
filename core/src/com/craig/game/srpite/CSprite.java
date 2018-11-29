package com.craig.game.srpite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CSprite extends Drawable{
    public int Width;
    public int Height;

    public int SourceX;
    public int SourceY;

    public Texture Tex;

    public CSprite(Texture tex, int x, int y, int w, int h, int sx, int sy)
    {
        super(x, y);
        Width = w;
        Height = h;

        SourceX = sx;
        SourceY = sy;

        Tex = tex;
    }

    public CSprite(Texture tex, int x, int y, int w, int h)
    {
        super(x, y);
        Width = w;
        Height = h;

        SourceX = 0;
        SourceY = 0;

        Tex = tex;
    }

    @Override
    public void draw(SpriteBatch target)
    {
        target.draw(Tex, X, Y, SourceX, SourceY, Width, Height);
    }

    public void setPosition(int x, int y, int w, int h)
    {
        X = x;
        Y = y;
        Width = w;
        Height = h;
    }

    public void setPosition(int x, int y)
    {
        setPosition(x, y, Width, Height);
    }

}
