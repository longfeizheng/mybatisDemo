package test.mybatis.proxy;

/**
 * Created on 2016/11/15 0015.
 *
 * @author zlf
 * @since 1.0
 */
public class MapperProxyTest {
    public static void main(String[] args) {
        MapperProxy proxy = new MapperProxy();
        UserMapper mapper = proxy.newInstance(UserMapper.class);

        User user = mapper.getUserById(1001);
        System.out.println(user.toString());

        System.out.println(mapper.toString());
    }
}
