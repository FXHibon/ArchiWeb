package fr.epsi.tp.ws.controllers.rest;

import fr.epsi.tp.ws.beans.User;
import fr.epsi.tp.ws.listeners.DbListener;
import fr.epsi.tp.ws.services.ConnectionService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class ConnectController {

    Logger logger = Logger.getLogger(ConnectController.class);

    @Resource
    ConnectionService connectionService;

	@RequestMapping(value="/connect", method=RequestMethod.POST)
    public
    @ResponseBody
    void connect(@RequestBody User user, HttpServletRequest request, HttpServletResponse httpServletResponse) {

        logger.info("connection requested");

        String login = user.getLogin();
        String password = user.getPassword();

        boolean authorized;
        if (login == null || password == null || login.equals("") || password.equals("")) {
			authorized = false;
		} else {
			authorized = false;
            /*for (User u : DbListener.getListOfUsers()) {
                if (loginMatch(login, u) && passwordMatch(password, u)) {
                    authorized = true;
				}
			}*/
            if (connectionService.checkLogin(login, password)) {
                authorized = true;
            }

		}

		if (authorized) {
			String tokenValue = UUID.randomUUID().toString();
            request.getSession(true).setAttribute("token", tokenValue);
            connectionService.login(tokenValue, user);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
	}

    /*private boolean passwordMatch(String password, User u) {
        return u.getPassword().equals(password);
    }

    private boolean loginMatch(String login, User u) {
        return u.getLogin().equals(login);
    }*/
}
