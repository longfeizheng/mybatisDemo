package test.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import test.mybatis.pojo.User;

/**
 * Created on 2016/11/14 0014.
 *
 * @author zlf
 * @since 1.0
 */
public interface UserMapper {
    public User queryUserById(Long id);

    public User queryUserByUserNameAndPassword(@Param("userName") String userName,
                                               @Param("password") String password);

    public void saveUser(User user);

    public void updateUserById(User user);

    public void deleteById(Long id);
}
