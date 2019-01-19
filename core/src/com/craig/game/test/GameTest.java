package com.craig.game.test;

import com.badlogic.gdx.graphics.Texture;
import com.craig.game.CraigGame;
import com.craig.game.srpite.CSprite;
import com.craig.game.srpite.Drawable;
import com.craig.game.state.State;

public class GameTest {

    private static CraigGame CG;

    public static void test (CraigGame cg)
    {
        System.out.println("testing started");
        CG = cg;

        testState();
        testPlayer();

        System.out.println("all tests PASSED");
    }

    private static boolean testState()
    {
        //test creating a new state with no members
        State target = new State(CG);
        assert (target != null) : "state initialised to null";
        assert (target.Members.size == 0) : "state has members despite none being added";

        //test adding a member to a state
        CSprite sprite = new CSprite(new Texture("square.png"), 0, 0, 0, 0);
        target.add(sprite);
        assert (target.Members.size > 0) : "state has not gained a member despite one being added";
        assert (target.Members.size == 1) : "state has gained multiple members despite only one being added";

        //test removing a member from a scene
        CSprite spriteB = new CSprite(new Texture("square.png"), 0, 0, 0, 0);
        target.add(spriteB);
        target.remove(sprite);
        assert (target.Members.size < 2) : "state has too many members after removal";
        assert (target.Members.size == 1) : "state has too few members after removal";
        assert (!target.Members.contains(sprite, true)) : "state removed the wrong sprite";

        System.out.println("all tests for class State have PASSED");

        return true;
    }

    private static boolean testPlayer()
    {
        //test that Player in initialise properly


        return true;
    }
}