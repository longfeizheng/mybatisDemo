package test.mybatis.mapper;

import test.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created on 2016/11/14 0014.
 *
 * @author zlf
 * @since 1.0
 */
public class UserMapperTest {

    private UserMapper userMapper;

    @Before
    public void setUp() throws Exception {
        InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        this.userMapper =sqlSession.getMapper(UserMapper.class);
    }

    @Test
    public void testSaveUser(){
        User user = new User();
        user.setId(null);
        user.setUserName("zhangsan1");
        user.setPassword("123456");
        this.userMapper.saveUser(user);

        assertNotNull(user.getId());
    }

    @Test
    public void testQueryUserById() {
        User user = userMapper.queryUserById(9l);

        assertNotNull(user);
        assertEquals("123456",user.getPassword());
    }

    @Test
    public void testUpdateUserById(){
        User user = new User();
        user.setId(11l);
        user.setName("aaa");
        user.setPassword("1234");
        user.setUserName("lisi");
        user.setAge(33);
        user.setSex(1);

        this.userMapper.updateUserById(user);

        assertEquals("1234",user.getPassword());
    }

    @Test
    public void testDeleteById(){
//        this.userMapper.deleteById(8l);
    }
}
