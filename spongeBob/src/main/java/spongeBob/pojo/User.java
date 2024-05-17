package spongeBob.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int uid;
    private String name;
    private String password;
    private Date birthday;
    private int role;
    private String email;
    private String address;
    private int status;
    private Date loginTime;
    private Date registerTime;
}
