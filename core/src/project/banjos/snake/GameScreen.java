package project.banjos.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {
    private Main game;
    private GameState gameState = new GameState();

    SpriteBatch batch;
    BitmapFont scoreBitmapFont;
    BitmapFont scoreDead;

    private int width = 1080;
    private int height = 1920;

    private OrthographicCamera camera = new OrthographicCamera(width, height);
    private Viewport viewport;

    public GameScreen(Main game) {
        this.game = game;
        camera.setToOrtho(false, width, height);
        viewport = new FitViewport(width, height, camera);
        viewport.apply();

        batch = new SpriteBatch();
        //scoreBitmapFont = new BitmapFont(Gdx.files.internal("test.fnt"));
        scoreBitmapFont = new BitmapFont(Gdx.files.internal("Calibri.fnt"));
        scoreDead = new BitmapFont(Gdx.files.internal("Calibri.fnt"));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        camera.update();
        viewport.apply();

        gameState.update(delta, viewport);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gameState.draw(width, height, camera);



        batch.begin();
        //Score
        scoreBitmapFont.setColor(0,1,1,1);
        scoreBitmapFont.getData().setScale(1.4f);
        scoreBitmapFont.draw(batch, "Score:  " + gameState.scoreSnakeLength, 20, 2230);


        //Deaths
        scoreDead.setColor(0,1,1,1);
        scoreDead.getData().setScale(1.4f);
        scoreDead.draw(batch, "High Score: " + gameState.scoreDead, 780, 860);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);

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
        batch.dispose();
    }
}
