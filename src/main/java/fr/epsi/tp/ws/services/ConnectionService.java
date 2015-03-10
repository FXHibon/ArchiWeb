package fr.epsi.tp.ws.services;

import fr.epsi.tp.ws.beans.User;

/**
 * Created by Fx on 26/02/2015.
 */
public interface ConnectionService {

    /**
     * Check is a token is valid
     *
     * @param token connection token
     * @return true if connected, else false
     */
    public boolean isConnected(String token);

    /**
     * add user as connected
     *
     * @param tokenValue connection token
     * @param user       user to connect
     */
    void login(String tokenValue, User user);
}
