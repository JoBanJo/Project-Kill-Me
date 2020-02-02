package project.banjos.snake;

import com.badlogic.gdx.Game;

public class Main extends Game {

	@Override
	public void create () {

		this.setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
}
