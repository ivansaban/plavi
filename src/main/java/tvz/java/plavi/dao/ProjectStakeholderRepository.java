package tvz.java.plavi.dao;

import org.springframework.data.repository.CrudRepository;
import tvz.java.plavi.model.entity.Project;
import tvz.java.plavi.model.entity.ProjectStakeholder;
import tvz.java.plavi.model.entity.User;

import java.util.List;

/**
 * Created by NIS on 28.5.2017..
 */
public interface ProjectStakeholderRepository extends CrudRepository<ProjectStakeholder, Long> {
    List<ProjectStakeholder> findByUser(User user);
    List<ProjectStakeholder> findByProject(Project project);
}
