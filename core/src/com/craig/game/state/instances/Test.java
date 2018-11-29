package com.craig.game.state.instances;

import com.badlogic.gdx.graphics.Texture;
import com.craig.game.CraigGame;
import com.craig.game.srpite.CSprite;
import com.craig.game.state.State;

public class Test extends State {

    public CSprite img;

    public Test(CraigGame Parent)
    {
        super(Parent);
        img = new CSprite(new Texture("badlogic.jpg"), 0, 0, 256, 256);
        add(img);
    }

    @Override
    public void update(float time)
    {
        img.X += 100*time;
    }
}
