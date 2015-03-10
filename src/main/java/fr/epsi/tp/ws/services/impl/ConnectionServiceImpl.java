package fr.epsi.tp.ws.services.impl;

import fr.epsi.tp.ws.beans.User;
import fr.epsi.tp.ws.services.ConnectionService;
import fr.epsi.tp.ws.listeners.DbListener;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public boolean checkLogin(String login, String password) {
        boolean res = false;
        try {
            ResultSet results;
            String request = "SELECT * FROM USERS WHERE ID='" + login + "' AND PASSWORD='" + password + "'";

            try {
                Statement stmt = DbListener.getConnection().createStatement();
                results = stmt.executeQuery(request);

                while (results.next()) {
                    String db_login = results.getString("id");
                    String db_password = results.getString("password");
                    if (db_login.equals(login) && db_password.equals(password)) {
                        res = true;
                    }
                }
                results.close();
                DbListener.getConnection().close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;


    }
}
