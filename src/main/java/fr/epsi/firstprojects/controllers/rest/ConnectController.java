package fr.epsi.firstprojects.controllers.rest;

import fr.epsi.firstprojects.beans.User;
import fr.epsi.firstprojects.listeners.DbListener;
import fr.epsi.firstprojects.services.ConnectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class ConnectController {

    @Resource
    ConnectionService connectionService;

	@RequestMapping(value="/connect", method=RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity connect(@RequestBody User user, HttpServletRequest request, HttpServletResponse httpServletResponse) {

        String login = user.getLogin();
        String password = user.getPassword();

        boolean authorized;
        if (login == null || password == null || login.equals("") || password.equals("")) {
			authorized = false;
		} else {
			authorized = false;
            for (User u : DbListener.getListOfUsers()) {
                if (loginMatch(login, u) && passwordMatch(password, u)) {
                    authorized = true;
				}
			}
		}

		if (authorized) {
			String tokenValue = UUID.randomUUID().toString();
            httpServletResponse.addCookie(new Cookie("token", tokenValue));
            connectionService.login(tokenValue, user);
            return new ResponseEntity(null, HttpStatus.OK);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
		}
	}

    private boolean passwordMatch(String password, User u) {
        return u.getPassword().equals(password);
    }

    private boolean loginMatch(String login, User u) {
        return u.getLogin().equals(login);
    }
}
