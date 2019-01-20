package com.craig.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.craig.game.CraigGame;
import com.craig.game.Entities.*;
import com.craig.game.state.State;



public class MainScreen extends State {

    private CraigGame parent;
    private Stage stage;

    public Player player1; //Player
    private Array<Projectile> bullets = new Array<Projectile>(); //List of bullets
    public boolean mouseHeld, gameComplete,  rapidFire;

    private TiledMap tiledMap; //tile map .tmx file
    private TiledMapRenderer tMapRenderer; //renderer for tile map file
    private TiledMapTileLayer collisionLayer, csBox, rchBox;    //layers used to check for collisions.
    private Vector2 mapSize;

    private Vector2 csCamBox; //Camera limit when in computer science area.
    private int rchCamBox; //Camera limit when in ron cooke hub area.

    public Array<Powerup> powerups = new Array<Powerup>(); //List of power ups.
    public Array<Key> Keys = new Array<Key>(); //List of keys and lock.
    private RapidFire currentRF; //The current/last rapid fire power up that was picked up.


    public MainScreen(CraigGame craigGame, int character){
        super(craigGame);
        parent = craigGame;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        //Initialising Camera
        this.Cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Cam.position.x = 1445;
        Cam.position.y = 2337;
        Cam.update();

        //Initialising the map.
        tiledMap = new TmxMapLoader().load("map.tmx");
        tMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        collisionLayer = (TiledMapTileLayer)  tiledMap.getLayers().get(1);
        csBox = (TiledMapTileLayer)  tiledMap.getLayers().get(2);
        rchBox = (TiledMapTileLayer)  tiledMap.getLayers().get(3);
        mapSize = new Vector2(5447, 3135);

        //Assigning the camera limit values.
        csCamBox = new Vector2(3035, 1797);
        rchCamBox = 3740;

        //Initialising the player.
        player1 = new Player(new Texture("Character.png"), character);
        add(player1.sprite);

        //Instantiating booleans
        mouseHeld = false;
        gameComplete = false;
        rapidFire = false;

        //Initialising and adding all power ups.
        for (int i=0; i < 9; i+=3) {
            powerups.add(new Coffee(new Texture("CoffeeCup2.png"), mapSize, collisionLayer) );
            add(powerups.get(i).sprite);
            powerups.add(new MaxHealth(new Texture("HealthPack.png"), mapSize, collisionLayer) );
            add(powerups.get(i+1).sprite);
            powerups.add(new RapidFire(new Texture("RFire.png"), mapSize, collisionLayer) );
            add(powerups.get(i+2).sprite);
        }

        //Initialising and adding all keys and the lock.
        Keys.add(new Key(new Vector2(1254, 2198), new Texture("Key.png")));
        Keys.add(new Key(new Vector2(1640, 703), new Texture("Key.png")));
        Keys.add(new Key(new Vector2(4300, 2255), new Texture("Key.png")));
        Keys.add(new Key(new Vector2(5319, 487), new Texture("Lock.png")));
        for(int i=0; i<Keys.size-1; i++){add(Keys.get(i).sprite);}
    }

    @Override
    public void update(float time)
    {
        //Checking for user input.
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {leftClick();}
        if(!Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {mouseHeld = false;}
        if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {keyPressed();}

        //Calling the player update method and passing parameters depending on which keys have been found.
        if (!Keys.get(0).isFound()){ player1.update(collisionLayer, csBox, Cam.position);}
        else if (!Keys.get(1).isFound()){ player1.update(collisionLayer, rchBox, Cam.position);}
        else { player1.update(collisionLayer, null, Cam.position);}

        //Updating bullets and removing any if any collisions have occurred.
        for (int i = 0; i < bullets.size; i++) {
            bullets.get(i).update();
            if (bullets.get(i).collision(collisionLayer)) {
                remove(bullets.get(i).sprite);
                bullets.removeIndex(i);}
        }

        //Checking if any power ups have been picked up and removing them if they have been picked up.
        for (int i = 0; i < powerups.size; i++){
            if (powerups.get(i).checkCollision(player1, rapidFire)) {
                remove(powerups.get(i).sprite);
                if(powerups.get(i).israpidFire()) {
                    rapidFire = true;
                    currentRF = (RapidFire)powerups.get(i);
                }
                powerups.removeIndex(i);
            }
        }

        //If rapid fire is active then checking if the timer is up and changing the boolean accordingly.
        if(rapidFire) {
            rapidFire = !(currentRF.isTimeUp());
        }

        //Checking to see if all keys have been found. If they have then changing the boolean so that game can end.
        if(!gameComplete) {
            gameComplete = true;
            for (int i = 0; i < Keys.size - 1; i++) {
                if (Keys.get(i).checkCollision(player1, false)) {
                    remove(Keys.get(i).sprite);
                }
                if (!Keys.get(i).isFound()) {
                    gameComplete = false;
                }
            }
            if(gameComplete) {add(Keys.get(Keys.size-1).sprite);}
        }
        if(gameComplete && Keys.get(Keys.size-1).checkCollision(player1, false)){parent.switchState(CraigGame.ENDGAME, 2);}
    }

    //Rendering the map and updating the camera.
    @Override
    public void render(float delta) {
        updateCam();

        tMapRenderer.setView(Cam);
        tMapRenderer.render();

        super.render(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    //Updating the camera position so that it follows the player. Which camera limits are used depends on which keys have been found.
    private void updateCam(){
        parent.Batch.setProjectionMatrix(Cam.combined);

        if(!Keys.get(0).isFound()) {
            if (player1.sprite.X > Gdx.graphics.getWidth() / 2 + 485 && player1.sprite.X < csCamBox.x - Gdx.graphics.getWidth() / 2) {
                Cam.position.x = player1.sprite.X;
            }
            if (player1.sprite.Y > Gdx.graphics.getHeight() / 2 + csCamBox.y && player1.sprite.Y < mapSize.y - Gdx.graphics.getHeight() / 2) {
                Cam.position.y = player1.sprite.Y;
            }
        }
        else {
            if (!Keys.get(1).isFound()) {
                if (player1.sprite.X > Gdx.graphics.getWidth() / 2 + 485 && player1.sprite.X < rchCamBox - Gdx.graphics.getWidth() / 2) {
                    Cam.position.x = player1.sprite.X;
                }
            }
            else {
                if (player1.sprite.X > Gdx.graphics.getWidth()/2 + 485 && player1.sprite.X < mapSize.x - Gdx.graphics.getWidth()/2){
                    Cam.position.x = player1.sprite.X;
                }
            }
            if (player1.sprite.Y > Gdx.graphics.getHeight() / 2 + 485 && player1.sprite.Y < mapSize.y - Gdx.graphics.getHeight() / 2) {
                    Cam.position.y = player1.sprite.Y;
            }
        }
        Cam.update();
    }

    //Checks to see which key is being pressed and calls the relevant method.
    private void keyPressed(){
        if(Gdx.input.isKeyPressed(Input.Keys.W)){player1.moveUp(collisionLayer);}
        if(Gdx.input.isKeyPressed(Input.Keys.S)){player1.moveDown(collisionLayer);}
        if(Gdx.input.isKeyPressed(Input.Keys.A)){player1.moveLeft(collisionLayer);}
        if(Gdx.input.isKeyPressed(Input.Keys.D)){player1.moveRight(collisionLayer);}
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){parent.switchState(CraigGame.PAUSE, 0);}
    }

    //Calls the shoot method and adds the bullet's sprite to list of drawables only if mouse had previously been released or if rapid fire is active.
    private void leftClick() {
        if (!mouseHeld || rapidFire) {
            add(player1.shoot(bullets));
            mouseHeld = true;
        }
    }
}
