package es.art83.ticTacToe.controllers.ws;

import es.art83.ticTacToe.controllers.SaveGameController;
import es.art83.ticTacToe.webService.GameResource;
import es.art83.ticTacToe.webService.SessionGameResource;
import es.art83.ticTacToe.webService.SessionResource;

public class SaveGameControllerWs extends ControllerWebService implements SaveGameController {

    public SaveGameControllerWs(String sessionId) {
        super(sessionId);
    }

    @Override
    public void saveGame(String gameName) {
        ControllerWebService.buildWebServiceManager(SessionResource.PATH_SESSIONS, this.getSessionId(),
                SessionGameResource.PATH_GAME, SessionGameResource.PATH_NAME).create(gameName);
        WebServicesManager webServicesManager = ControllerWebService.buildWebServiceManager(GameResource.PATH_GAMES);
        webServicesManager.addParams("sessionId", this.getSessionId());
        webServicesManager.create();
    }

    @Override
    public void saveGame() {
        String gameName = ControllerWebService.buildWebServiceManager(SessionResource.PATH_SESSIONS,
                this.getSessionId(), SessionGameResource.PATH_GAME, SessionGameResource.PATH_NAME)
                .entity(String.class);
        this.overwriteGame(gameName);
    }
    
    @Override
    public void overwriteGame(String gameName) {
        // Se busca el juego y se borra
        WebServicesManager webServicesManager = ControllerWebService.buildWebServiceManager(
                GameResource.PATH_GAMES, GameResource.PATH_SEARCH);
        webServicesManager.addParams("sessionId", this.getSessionId());
        webServicesManager.addParams("name", gameName);
        String gameId = webServicesManager.entity(String.class);
        ControllerWebService.buildWebServiceManager(GameResource.PATH_GAMES, gameId).delete();
        this.saveGame(gameName);
    }


}
