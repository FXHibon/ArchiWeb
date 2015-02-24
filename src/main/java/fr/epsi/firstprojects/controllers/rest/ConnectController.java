package fr.epsi.firstprojects.controllers.rest;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fr.epsi.firstprojects.beans.User;
import fr.epsi.firstprojects.listeners.MyListener;

@Controller
public class ConnectController {

	@RequestMapping(value="/connect", method=RequestMethod.POST)
    public
    @ResponseBody
    String connect(@RequestBody User user, HttpServletRequest request, HttpServletResponse httpServletResponse) {
        String login = user.getLogin();
        Logger.getRootLogger().info("login=" + login);
        String password = user.getPassword();
        Logger.getRootLogger().info("password=" + password);

        boolean authorized;
        if (login == null || password == null || login.equals("") || password.equals("")) {
			authorized = false;
		} else {
			authorized = false;
            for (User u : MyListener.getListOfUsers()) {
                if (u.getLogin().equals(login)
						&& u.getPassword().equals(password)) {
					authorized = true;
				}
			}
		}

		if (authorized) {
			String tokenValue = UUID.randomUUID().toString();
			request.getSession().setAttribute("token", tokenValue);
			httpServletResponse.addCookie(new Cookie("token",tokenValue));
			Logger.getRootLogger().info(request.getSession().getId());
			return tokenValue;
		} else {
			httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
}
