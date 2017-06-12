package tvz.java.plavi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by NIS on 28.5.2017..
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTasksCounter {

    private String username;
    private long taskCount;
    private float finishedPercentage;

}
