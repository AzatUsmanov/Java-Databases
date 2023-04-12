package javaDB.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

/**
 * Класс, необходимый для инициализации SqlSessionFactory.
 */
public class MyBaitsUtil {
    private static Logger logger = Logger.getLogger(MyBaitsUtil.class.getName());
    private static SqlSessionFactory factory = null;

    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream;
        try{
            inputStream = Resources.getResourceAsStream(resource);
            factory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException exception){
            logger.warning(exception.getMessage());
        }
    }
    public static SqlSessionFactory getSqlSessionFactory(){ return factory; }
}
