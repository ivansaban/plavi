package tvz.java.plavi.model.dto;

import lombok.Data;
import tvz.java.plavi.model.entity.Role;

/**
 * Created by NIS on 28.5.2017..
 */
@Data
public class LoggedUser {

    private String firstname;
    private String lastname;
    private String username;
    private Role role;
}
