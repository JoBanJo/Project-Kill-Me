package project.banjos.snake;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.viewport.Viewport;


public class GameState {

    private int boardSize = 30;
    private int yOffset = 400;

    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Controls controls =  new Controls();
    private Queue<Bodypart> mBody = new Queue<Bodypart>();
    private float mTimer = 0;
    private Food mFood = new Food(boardSize);
    private int snakeLength = 3;
    public int scoreSnakeLength = 0;

    //Colour
    private float colourCounter = 0;

    public GameState() {
        mBody.addLast(new Bodypart(15, 15, boardSize)); //Head
        mBody.addLast(new Bodypart(15, 14, boardSize));
        mBody.addLast(new Bodypart(15, 13, boardSize)); //Tail
    }




public void update(float delta, Viewport viewport) {
    mTimer += delta;
    colourCounter += delta;

    controls.update(viewport);
    if (mTimer > 0.13f) {
        mTimer = 0;
        advance();
    }
}

private void advance() {
    int headX = mBody.first().getX();
    int headY = mBody.first().getY();
    switch (controls.getDirection()) {
        case 0: //UP
            mBody.addFirst(new Bodypart(headX, headY+1, boardSize));
                break;

        case 1: //RIGHT
            mBody.addFirst(new Bodypart(headX+1, headY, boardSize));
                break;

        case 2: //DOWN
            mBody.addFirst(new Bodypart(headX, headY-1, boardSize));
                break;

        case 3: //LEFT
            mBody.addFirst(new Bodypart(headX-1, headY, boardSize));
                break;

        default: //Should never happen
            mBody.addFirst(new Bodypart(headX, headY+1, boardSize));
                break;
    }

    if (mBody.first().getX() == mFood.getX()
            && mBody.first().getY() == mFood.getY()) {
        snakeLength++;
        scoreSnakeLength = + snakeLength - 3;


        mFood.randomPos(boardSize);
    }

    for (int i = 1; i < mBody.size; i++) {
        if (mBody.get(i).getX() == mBody.first().getX()
                && mBody.get(i).getY() == mBody.first().getY()) {
            snakeLength = 3;
        }
    }

    while (mBody.size - 1 >= snakeLength) {
        mBody.removeLast();

    }
}

public void draw(int width, int height, OrthographicCamera camera) {
    shapeRenderer.setProjectionMatrix(camera.combined);
    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);




    //Board colour
    shapeRenderer.setColor(1, 1, 1, 1);
    shapeRenderer.rect(0, yOffset, width, width);

    shapeRenderer.setColor(0, 0, 0, 1);
    shapeRenderer.rect(0+5, yOffset+5, width-5*2, width-5*2);

    shapeRenderer.setColor(MathUtils.sin(colourCounter),-MathUtils.sin(colourCounter),1,1);

    //Controls
    shapeRenderer.rect(235, 265, 130, 130);
    shapeRenderer.rect(235, 0, 130, 135);
    shapeRenderer.rect(105, 135, 130, 130);
    shapeRenderer.rect(365, 135, 130, 130);


    float scaleSnake = width/boardSize;
    for (Bodypart bp : mBody) {
        shapeRenderer.rect(bp.getX()*scaleSnake, bp.getY()*scaleSnake + yOffset,
                scaleSnake, scaleSnake);


        //FOOD
    shapeRenderer.rect(mFood.getX() * scaleSnake, mFood.getY() * scaleSnake + yOffset, scaleSnake, scaleSnake);
        }
    shapeRenderer.end();
    }
}