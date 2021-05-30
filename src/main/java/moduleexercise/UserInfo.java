package moduleexercise;

import lombok.Data;

import java.util.List;

/**
 * @Describe:
 * @Author: Gezx
 * @Date: 2020/4/1 23:49
 */
@Data
public class UserInfo {
    private String username;
    private String password;
    private List<String> interests;
}
