package es.art83.ticTacToe.controllers;

import java.util.List;

import es.art83.ticTacToe.models.entities.CoordinateEntity;
import es.art83.ticTacToe.models.utils.ColorModel;

public interface ShowGameController {

    String getNameGame();

    ColorModel[][] colors();

    boolean isGameOver();

    ColorModel winner();

    boolean isSavedGame();

    ColorModel turnColor();

    boolean hasAllPieces();

    List<CoordinateEntity> validSourceCoordinates();

    List<CoordinateEntity> validDestinationCoordinates();

    boolean createdGame();

}
