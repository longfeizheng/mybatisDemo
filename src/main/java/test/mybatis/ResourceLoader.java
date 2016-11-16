package test.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created on 2016/11/14 0014.
 *
 * @author zlf
 * @since 1.0
 */
public class ResourceLoader {

    ClassLoader defaultClassLoader;
    ClassLoader systemClassLoader;

    ResourceLoader() {
        try {
            //初始化类加载器
            systemClassLoader = ClassLoader.getSystemClassLoader();
        } catch (SecurityException ignored) {
            // AccessControlException on Google App Engine
        }
    }

    public static void main(String[] args) throws IOException {
        ResourceLoader resourceLoader = new ResourceLoader();
        resourceLoader.loadProperties1();//ClassLoader
        resourceLoader.loadProperties2();//classLoader
        resourceLoader.loadProperties3();//class
        resourceLoader.loadProperties4();//class
        resourceLoader.loadProperties5();//class
        resourceLoader.loadProperties6();//mybatis中调用系统classLoader
        resourceLoader.loadProperties7();//mybatis中调用系统classLoader

    }

    public void loadProperties1() throws IOException {
        try (
                InputStream input = ResourceLoader.class.getClassLoader().getResourceAsStream("test/mybatis/test.properties");
        ) {
            printProperties(input);
        }

    }

    public void loadProperties2() throws IOException {
        try (
                InputStream input = ResourceLoader.class.getClassLoader().getResourceAsStream("test.properties");
        ) {
            printProperties(input);
        }

    }

    public void loadProperties3() throws IOException {
        try (
                InputStream input = ResourceLoader.class.getResourceAsStream("test.properties");
        ) {
            printProperties(input);
        }

    }

    public void loadProperties4() throws IOException {
        try (
                InputStream input = ResourceLoader.class.getResourceAsStream("/test.properties");
        ) {
            printProperties(input);
        }

    }

    public void loadProperties5() throws IOException {
        try (
                InputStream input = ResourceLoader.class.getResourceAsStream("/test/mybatis/test.properties");
        ) {
            printProperties(input);
        }

    }

    public void loadProperties6() throws IOException {
        ClassLoader classLoader = new ClassLoader() {
        };
        try (
                InputStream input = getResourceAsStream("test/mybatis/test.properties");
        ) {
            printProperties(input);
        }

    }

    public void loadProperties7() throws IOException {
        try (
                InputStream input = getResourceAsStream("test.properties");
        ) {
            printProperties(input);
        }

    }

    public InputStream getResourceAsStream(String resource) {
        return getResourceAsStream(null, resource);
    }

    public InputStream getResourceAsStream(ClassLoader classLoader, String resource) {
        return getResourceAsStream(resource, getClassLoaders(classLoader));
    }
    //用5个类加载器一个个查找资源，只要其中任何一个找到，就返回
    InputStream getResourceAsStream(String resource, ClassLoader[] classLoader) {
        for (ClassLoader cl : classLoader) {
            if (null != cl) {
                // try to find the resource as passed
                InputStream returnValue = cl.getResourceAsStream(resource);

                // now, some class loaders want this leading "/", so we'll add it and try again if we didn't find the resource
                if (null == returnValue) {
                    returnValue = cl.getResourceAsStream("/" + resource);
                }

                if (null != returnValue) {
                    return returnValue;
                }
            }
        }
        return null;
    }

    private void printProperties(InputStream input) throws IOException {
        Properties properties = new Properties();
        properties.load(input);
        System.out.println(properties.getProperty("name"));
    }

    //一共5个类加载器
    ClassLoader[] getClassLoaders(ClassLoader classLoader) {
        return new ClassLoader[]{
                classLoader,
                defaultClassLoader,
                Thread.currentThread().getContextClassLoader(),
                getClass().getClassLoader(),
                systemClassLoader};
    }
}
