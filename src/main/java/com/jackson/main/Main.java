package com.jackson.main;

import com.jackson.game.Game;
import javafx.application.Application;
import javafx.stage.Stage;
import com.jackson.ui.Board;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Board board = new Board();
        stage.setScene(board.getBoard());
        stage.setTitle("Chess");
        stage.setResizable(false);
        stage.show();

        Game game = new Game();
        game.start(board);
    }
}
