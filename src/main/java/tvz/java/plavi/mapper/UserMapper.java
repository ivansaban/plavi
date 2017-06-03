package tvz.java.plavi.mapper;

import tvz.java.plavi.model.dto.LoggedUser;
import tvz.java.plavi.model.entity.User;

/**
 * Created by NIS on 3.6.2017..
 */
public class UserMapper {
    public static LoggedUser mapUser(User user) {
        LoggedUser loggedUser = new LoggedUser();
        loggedUser.setFirstname(user.getFirstname());
        loggedUser.setLastname(user.getLastname());
        loggedUser.setUsername(user.getUsername());
        loggedUser.setRole(user.getRole().getName());

        return loggedUser;
    }
}
