package com.craig.game.srpite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CSprite extends Drawable{
    public int Width;
    public int Height;

    public int SourceX;
    public int SourceY;
    private int SourceWidth;
    private int SourceHeight;

    public Texture Tex;

    private float rotation;
    private float scaleX = 1, scaleY = 1;

    public CSprite(Texture tex, int x, int y, int w, int h, int sx, int sy)
    {
        super(x, y);
        Width = w;
        Height = h;
        SourceWidth = w;
        SourceHeight = h;

        SourceX = sx;
        SourceY = sy;

        Tex = tex;
    }

    public CSprite(Texture tex, int x, int y, int w, int h)
    {
        super(x, y);
        Width = w;
        Height = h;
        SourceWidth = w;
        SourceHeight = h;

        SourceX = 0;
        SourceY = 0;

        Tex = tex;
    }

    @Override
    public void draw(SpriteBatch target)
    {
        target.draw(Tex, X, Y, (Width/2), (Height/2), Width, Height, scaleX, scaleY, rotation, SourceX, SourceY, SourceWidth, SourceHeight, false, false);
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

    public void setRotation(float angle) {rotation = angle;}

}
