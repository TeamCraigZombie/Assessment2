package com.craig.game.srpite;

import com.badlogic.gdx.graphics.Texture;

public class CSprite {
    public int X;
    public int Y;
    public int Width;
    public int Height;

    public int SourceX;
    public int SourceY;

    public Texture Tex;

    public CSprite(Texture tex, int x, int y, int w, int h, int sx, int sy)
    {
        X = x;
        Y = y;
        Width = w;
        Height = h;

        SourceX = sx;
        SourceY = sy;

        Tex = tex;
    }

    public CSprite(Texture tex, int x, int y, int w, int h)
    {
        X = x;
        Y = y;
        Width = w;
        Height = h;

        SourceX = 0;
        SourceY = 0;

        Tex = tex;
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
