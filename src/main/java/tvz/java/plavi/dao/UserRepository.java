package tvz.java.plavi.dao;

import org.springframework.data.repository.CrudRepository;
import tvz.java.plavi.model.entity.User;

/**
 * Created by NIS on 28.5.2017..
 */
public interface UserRepository extends CrudRepository<User, Long>{
    User findByUsername(String username);
}
