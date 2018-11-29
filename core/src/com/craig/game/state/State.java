package com.craig.game.state;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.craig.game.CraigGame;
import com.craig.game.srpite.CSprite;

public class State implements Screen {
    final CraigGame Parent;

    /**Array of all sprites to be drawn*/
    private Array<CSprite> Members;

    public OrthographicCamera Cam;

    public State(final CraigGame parent)
    {
        Parent = parent;
        Members = new Array<CSprite>();
    }

    @Override
    public void render(float delta)
    {
        update(delta);

        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Parent.Batch.begin();
        for(int i = 0; i < Members.size; i++)
        {
            CSprite img = Members.get(i);
            Parent.Batch.draw(img.Tex, img.X, img.Y, img.SourceX, img.SourceY, img.Width, img.Height);
        }
        Parent.Batch.end();
    }

    @Override
    public void resize(int width, int height)
    {
    }

    @Override
    public void show()
    {

    }

    @Override
    public void hide()
    {
    }

    @Override
    public void pause()
    {
    }

    @Override
    public void resume()
    {
    }

    @Override
    public void dispose()
    {

    }

    /**
     * adds a given sprite to this state (sprite will be drawn)
     * @param target sprite to be added
     */
    public void add(CSprite target)
    {
        Members.add(target);
    }

    /**
     * removes the target sprite from this state (matches by reference not value)
     * @param target sprite to be removed
     * @return true if <target> was in this state (and has been removed)
     */
    public Boolean remove(CSprite target)
    {
        return Members.removeValue(target, true);
    }

    public void update(float time)
    {

    }
}
