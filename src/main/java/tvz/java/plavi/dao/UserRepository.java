package tvz.java.plavi.dao;

import org.springframework.data.repository.CrudRepository;
import tvz.java.plavi.model.entity.User;

import java.util.List;

/**
 * Created by NIS on 28.5.2017..
 */
public interface UserRepository extends CrudRepository<User, Long>{
    User findByUsername(String username);
    User findById(Long id);
    Long countByGender(String gender);
}
