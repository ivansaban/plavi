package tvz.java.plavi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by NIS on 28.5.2017..
 */
@Entity
@Table(name="roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Role {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(unique=true, nullable=false)
    private String name;
}
