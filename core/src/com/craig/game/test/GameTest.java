package com.craig.game.test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.craig.game.CraigGame;
import com.craig.game.Entities.Player;
import com.craig.game.Entities.Projectile;
import com.craig.game.Screens.MainScreen;
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
        testGameScreen();

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
        Player target = new Player(new Texture("square.png"), 0);
        assert (target != null) : "Player initialised to null";

        //test that bullets are generated properly
        Array<Projectile> bullets = new Array<Projectile>();
        target.shoot(bullets);
        assert (bullets.size == 1) : "Player has created the wrong number of bullets";

        System.out.println("all tests for class Player have PASSED");

        return true;
    }

    private static boolean testGameScreen()
    {
        //test that a new MainScreen can be created
        MainScreen target = new MainScreen(CG, 0);
        assert (target != null) : "MainScreen initialised to null";

        //test that a player is created
        assert (target.player1 != null) : "MainScreen has not created a player";

        //test that exactly nine power-ups are generated
        assert (target.powerups.size > 8) : "MainScreen has created to few power-ups";
        assert (target.powerups.size == 9) : "MainScreen has created to many power-ups";

        //test that exactly 4 keys are created
        assert (target.Keys.size > 3) : "MainScreen has created to few keys";
        assert (target.Keys.size == 4) : "MainScreen has made to many keys";

        //test that collecting all keys triggers the game completion flag
        for(int i = 0; i < 4; i++)
        {
            target.Keys.get(i).found = true;
        }
        target.update(0);
        assert (target.gameComplete) : "game remains incomplete even though all keys are marked as found";

        System.out.println("all tests for class MainScreen have PASSED");

        return true;
    }
}