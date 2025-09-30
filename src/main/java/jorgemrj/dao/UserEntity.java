package jorgemrj.dao;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserEntity {
    private Long id;
    private String name;
    private String username;
    private String email;
    private Date createAt;
    private Date updateAt;
}
