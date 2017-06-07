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
public class TotalUsers {

    private long allUsers;
    private long maleUsers;
    private long femaleUsers;
}
