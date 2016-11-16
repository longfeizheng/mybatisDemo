package test.mybatis.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created on 2016/11/15 0015.
 *
 * @author zlf
 * @since 1.0
 */
public class MapperProxy implements InvocationHandler {

    public <T> T newInstance(Class<T> clz){
        return (T)Proxy.newProxyInstance(clz.getClassLoader(),new Class[]{clz} , this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(Object.class.equals(method.getDeclaringClass())){
            try {
                // 诸如hashCode()、toString()、equals()等方法，将target指向当前对象this
                return method.invoke(this,args);
            }catch (Throwable t){

            }
        }
        return new User((Integer) args[0],"zhangsan",18);
    }
}
