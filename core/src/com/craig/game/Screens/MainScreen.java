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

    private Player player1;
    private Array<Projectile> bullets = new Array<Projectile>();
    private boolean mouseHeld, gameComplete;

    private TiledMap tiledMap;
    private TiledMapRenderer tMapRenderer;
    private TiledMapTileLayer collisionLayer;
    private Vector2 mapSize;

    private Array<Powerup> powerups = new Array<Powerup>();
    private Array<Key> Keys = new Array<Key>();


    public MainScreen(CraigGame craigGame, int character){
        super(craigGame);
        parent = craigGame;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        this.Cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Cam.update();

        tiledMap = new TmxMapLoader().load("map.tmx");
        tMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        collisionLayer = (TiledMapTileLayer)  tiledMap.getLayers().get(1);
        //mapWidth = 5447;
        //mapHeight = 3135;
        mapSize = new Vector2(5447, 3135);

        player1 = new Player(new Texture("player.png"), character);
        add(player1.sprite);
        mouseHeld = false;
        gameComplete = false;

        for (int i=0; i < 6; i+=2) {
            powerups.add(new Coffee(new Texture("brSquare.png"), mapSize, collisionLayer) );
            add(powerups.get(i).sprite);
            powerups.add(new MaxHealth(new Texture("rSquare.png"), mapSize, collisionLayer) );
            add(powerups.get(i+1).sprite);
        }

        Keys.add(new Key(new Vector2(1254, 2198), new Texture("gldSquare.png")));
        Keys.add(new Key(new Vector2(1640, 703), new Texture("gldSquare.png")));
        Keys.add(new Key(new Vector2(4300, 2255), new Texture("gldSquare.png")));
        Keys.add(new Key(new Vector2(5319, 487), new Texture("gldSquare.png")));

        for(int i=0; i<Keys.size-1; i++){add(Keys.get(i).sprite);}
    }

    @Override
    public void update(float time)
    {
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {leftClick();}
        if(!Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {mouseHeld = false;}
        if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {keyPressed();}

        player1.update(collisionLayer, Cam.position);

        for (int i = 0; i < bullets.size; i++) {
            bullets.get(i).update();
            if (bullets.get(i).collision(collisionLayer)) {
                remove(bullets.get(i).sprite);
                bullets.removeIndex(i);}
        }

        for (int i = 0; i < powerups.size; i++){
            if (powerups.get(i).checkCollision(player1)) {
                remove(powerups.get(i).sprite);
                powerups.removeIndex(i);
            }
        }

        if(!gameComplete) {
            gameComplete = true;
            for (int i = 0; i < Keys.size - 1; i++) {
                if (Keys.get(i).checkCollision(player1)) {
                    remove(Keys.get(i).sprite);
                }
                if (!Keys.get(i).isFound()) {
                    gameComplete = false;
                }
            }
            if(gameComplete) {add(Keys.get(Keys.size-1).sprite);}
        }
        if(gameComplete && Keys.get(Keys.size-1).checkCollision(player1)){parent.switchState(CraigGame.MENU, 0);}
    }

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

    private void updateCam(){
        parent.Batch.setProjectionMatrix(Cam.combined);
        if (player1.sprite.X > Gdx.graphics.getWidth()/2 + 485 && player1.sprite.X < mapSize.x - Gdx.graphics.getWidth()/2){
            Cam.position.x = player1.sprite.X;
        }
        if (player1.sprite.Y > Gdx.graphics.getHeight()/2 + 485 && player1.sprite.Y < mapSize.y - Gdx.graphics.getHeight()/2){
            Cam.position.y = player1.sprite.Y;
        }
        Cam.update();
    }

    private void keyPressed(){
        if(Gdx.input.isKeyPressed(Input.Keys.W)){player1.moveUp(collisionLayer);}
        if(Gdx.input.isKeyPressed(Input.Keys.S)){player1.moveDown(collisionLayer);}
        if(Gdx.input.isKeyPressed(Input.Keys.A)){player1.moveLeft(collisionLayer);}
        if(Gdx.input.isKeyPressed(Input.Keys.D)){player1.moveRight(collisionLayer);}
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){parent.switchState(CraigGame.PAUSE, 0);}
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_1)){tiledMap.getLayers().get(1).setVisible(true);}
    }

    private void leftClick() {
        if (!mouseHeld) {
            add(player1.shoot(bullets));
            mouseHeld = true;
        }
    }
}
