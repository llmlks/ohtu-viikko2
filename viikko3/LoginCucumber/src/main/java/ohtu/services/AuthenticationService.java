package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;
import org.springframework.util.StringUtils;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        boolean wrongForm = !hasRightForm(username, false)
                || !hasRightForm(password, true);

        return wrongForm || userDao.findByName(username) != null;
    }

    private boolean hasRightForm(String string, boolean password) {
        if (password) {
            return string.length() >= 8 && !string.matches("[a-zA-Z]+");
        }

        return string.length() >= 3 && string.matches("[a-z]+");
    }
}
