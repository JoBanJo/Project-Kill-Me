package project.banjos.snake;

import com.badlogic.gdx.math.MathUtils;

public class Food {

    private int x;
    private int y;

    public void randomPos(int boardSize) {
        x = MathUtils.random(boardSize-1);
        y = MathUtils.random(boardSize-1);
    }

    public Food(int boardSize) {
        randomPos(boardSize);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
