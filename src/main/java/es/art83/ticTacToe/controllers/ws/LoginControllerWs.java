package es.art83.ticTacToe.controllers.ws;

import es.art83.ticTacToe.controllers.LoginController;
import es.art83.ticTacToe.models.entities.PlayerEntity;
import es.art83.ticTacToe.webService.PlayerResource;
import es.art83.ticTacToe.webService.SessionPlayerResource;
import es.art83.ticTacToe.webService.SessionResource;

public class LoginControllerWs extends ControllerWebService implements LoginController {
    private final String pathSessionsId;

    public LoginControllerWs(String sessionId) {
        super(sessionId);
        this.pathSessionsId = SessionResource.PATH_SESSIONS + "/" + this.getSessionId();
    }

    @Override
    public boolean login(PlayerEntity player) {
        return ControllerWebService.buildWebServiceManager(pathSessionsId, SessionPlayerResource.PATH_PLAYER)
                .create(player);
    }

    @Override
    public boolean register(PlayerEntity player) {
        boolean result = false;
        WebServicesManager webServicesManager = ControllerWebService.buildWebServiceManager(
                PlayerResource.PATH_PLAYERS);
        if (webServicesManager.create(player)) {
            result = this.login(player);
        }
        return result;
    }

    @Override
    public boolean loggedIn() {
       return ControllerWebService.buildWebServiceManager(
               pathSessionsId, SessionResource.PATH_LOGGED).entityBoolean();
    }

}
