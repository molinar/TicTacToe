package es.art83.ticTacToe.controllers.ws;

import es.art83.ticTacToe.controllers.PlacePieceController;
import es.art83.ticTacToe.models.entities.CoordinateEntity;
import es.art83.ticTacToe.webService.SessionGameResource;
import es.art83.ticTacToe.webService.SessionResource;

public class PlacePieceControllerWs extends ControllerWebService implements PlacePieceController {

    private String pathSessionsIdGame;

    public PlacePieceControllerWs(String sessionId) {
        super(sessionId);
        this.pathSessionsIdGame = SessionResource.PATH_SESSIONS + "/" + this.getSessionId()
                + SessionGameResource.PATH_GAME;
    }

    @Override
    public void placePiece(CoordinateEntity coordinate) {
        ControllerWebService.buildWebServiceManager(pathSessionsIdGame, SessionGameResource.PATH_PIECE)
                .create(coordinate);
    }

    @Override
    public void placePiece(CoordinateEntity sourceCoordinate, CoordinateEntity destinationCoordinate) {
        WebServicesManager webServicesManager = ControllerWebService.buildWebServiceManager(pathSessionsIdGame,
                SessionGameResource.PATH_PIECE);
        webServicesManager.addMatrixParams("row", String.valueOf(sourceCoordinate.getRow()));
        webServicesManager.addMatrixParams("column", String.valueOf(sourceCoordinate.getColumn()));
        webServicesManager.delete();
        ControllerWebService.buildWebServiceManager(pathSessionsIdGame, SessionGameResource.PATH_PIECE)
                .create(destinationCoordinate);
    }

}
