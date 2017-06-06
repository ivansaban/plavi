package tvz.java.plavi.dao;

import org.springframework.data.repository.CrudRepository;
import tvz.java.plavi.model.entity.Project;

/**
 * Created by NIS on 28.5.2017..
 */
public interface ProjectRepository extends CrudRepository<Project, Long> {
    Project findByName(String name);
    Project findById(Long id);
}
