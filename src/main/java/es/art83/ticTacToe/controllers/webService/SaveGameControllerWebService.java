package es.art83.ticTacToe.controllers.webService;

import es.art83.ticTacToe.controllers.SaveGameController;
import es.art83.ticTacToe.webService.GameResource;
import es.art83.ticTacToe.webService.SessionGameResource;
import es.art83.ticTacToe.webService.SessionResource;
import es.art83.ticTacToe.webService.utils.WebServicesManager;

public class SaveGameControllerWebService extends ControllerWebService implements SaveGameController {

    public SaveGameControllerWebService(String sessionId) {
        super(sessionId);
    }

    @Override
    public void saveGame(String gameName) {
        new WebServicesManager<>(SessionResource.PATH_SESSIONS, this.getSessionId(),
                SessionGameResource.PATH_GAME, SessionGameResource.PATH_NAME).create(gameName);
        WebServicesManager<?> webServicesManager = new WebServicesManager<>(GameResource.PATH_GAMES);
        webServicesManager.addParams("sessionId", this.getSessionId());
        webServicesManager.create();
    }

    @Override
    public void overWriteGame(String gameName) {
        // Se busca y se borra
        WebServicesManager<String> webServicesManager = new WebServicesManager<String>(
                GameResource.PATH_GAMES, GameResource.PATH_SEARCH);
        webServicesManager.addParams("sessionId", this.getSessionId());
        webServicesManager.addParams("name", gameName);
        String gameId = webServicesManager.entity(String.class);
        new WebServicesManager<>(GameResource.PATH_GAMES, gameId).delete();

        this.saveGame(gameName);
    }

    @Override
    public void saveGame() {
        String gameName = new WebServicesManager<String>(SessionResource.PATH_SESSIONS,
                this.getSessionId(), SessionGameResource.PATH_GAME, SessionGameResource.PATH_NAME)
                .entity(String.class);
        this.overWriteGame(gameName);
    }

}
