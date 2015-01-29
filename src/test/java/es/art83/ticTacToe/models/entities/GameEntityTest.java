package es.art83.ticTacToe.models.entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.art83.ticTacToe.models.entities.data.CoordinateEntityTestData;
import es.art83.ticTacToe.models.entities.data.GameEntityTestData;

public class GameEntityTest {

    private GameEntityTestData data;

    @Before
    public void init() {
        data = new GameEntityTestData();
    }

    @Test
    public void testGame() {
        while (data.hasNextGame()) {
            GameEntity game = new GameEntity(null, null, new BoardEntity(data.getPiecesGame()), data.getTurnGame());
            assertEquals(data.message(), data.getPiecesGame(), game.pieces());
            assertEquals(data.message(), data.getTurnGame().getColor(), game.turnColor());
            data.nextGame();
        }
    }
    
    @Test
    public void testExistTicTacToe() {
        while (data.hasNextGame()) {
            GameEntity game = new GameEntity(null, null, new BoardEntity(data.getPiecesGame()), data.getTurnGame());
            assertEquals(data.message(), data.getExistTitTacToeGame(), game.existTicTacToe());
            data.nextGame();
        }
    }

    @Test
    public void testWinner() {
        
    }

    @Test
    public void testTurnColor() {
        while (data.hasNextGame()) {
            GameEntity game = new GameEntity(null, null, new BoardEntity(data.getPiecesGame()), data.getTurnGame());
            assertEquals(data.message(), data.getTurnGame().getColor(), game.turnColor());
            data.nextGame();
        }
    }

    @Test
    public void testHasAllPieces() {
        while (data.hasNextGame()) {
            GameEntity game = new GameEntity(null, null, new BoardEntity(data.getPiecesGame()), data.getTurnGame());
            assertEquals(data.message(), data.getHasAllPiecesGame(), game.hasAllPieces());
            data.nextGame();
        }
    }

    @Test
    public void testValidSourceCoordinates() {
        while (data.hasNextGame()) {
            GameEntity game = new GameEntity(null, null, new BoardEntity(data.getPiecesGame()), data.getTurnGame());            
            assertEquals(data.message(), data.getValidSourceCoordinatesGame(), game.validSourceCoordinates());
            data.nextGame();
        }
    }

    @Test
    public void testValidDestinationCoordinates() {
        while (data.hasNextGame()) {
            GameEntity game = new GameEntity(null, null, new BoardEntity(data.getPiecesGame()), data.getTurnGame());
            assertEquals(data.message(), data.getValidDestinationCoordinatesGame(), game.validDestinationCoordinates());
            data.nextGame();
        }
    }

    @Test
    public void testPlacePieceCoordinateEntity() {
        while (data.hasNextGame()) {
            GameEntity game = new GameEntity(null, null, new BoardEntity(data.getPiecesGame()), data.getTurnGame());
            if (data.getPutPieceGame()!=null){
                game.placePiece(data.getPutPieceGame().getCoordinate());
                assertEquals(data.message(), data.getPutPiecesGame(), game.pieces());
            }
            data.nextGame();
        }
    }

    @Test
    public void testPlacePieceCoordinateEntityCoordinateEntity() {
        while (data.hasNextGame()) {
            GameEntity game = new GameEntity(null, null, new BoardEntity(data.getPiecesGame()), data.getTurnGame());
            if (data.getSourceMovePieceGame()!=null){
                game.placePiece(data.getSourceMovePieceGame().getCoordinate(), data.getDestinationMovePieceGame().getCoordinate());
                assertEquals(data.message(), data.getMovePiecesGame(), game.pieces());
            }
            data.nextGame();
        }       
    }

    @Test
    public void testDeletePiece() {
        //TODO
    }

}