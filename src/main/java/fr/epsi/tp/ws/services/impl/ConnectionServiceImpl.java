package fr.epsi.tp.ws.services.impl;

import fr.epsi.tp.ws.beans.User;
import fr.epsi.tp.ws.services.ConnectionService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fx on 26/02/2015.
 */
public class ConnectionServiceImpl implements ConnectionService {

    private Map<String, User> tokenToUser = new HashMap<String, User>();

    @Override
    public boolean isConnected(String token) {
        return tokenToUser.keySet().contains(token);
    }

    @Override
    public void login(String tokenValue, User user) {
        tokenToUser.put(tokenValue, user);
    }
}
