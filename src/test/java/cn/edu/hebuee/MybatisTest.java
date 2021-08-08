package cn.edu.hebuee;

import cn.edu.hebuee.dao.IUserDao;
import cn.edu.hebuee.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sun.text.resources.FormatData;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MybatisTest {
    /**
     * 1.读取配置文件
     * 2.创建SqlSessionFactory对象
     * 3.创建工厂 生产SqlSession对象
     * 4.SqlSession创建Dao接口代理对象
     * 5.使用代理对象执行方法
     * 6.获取资源
     */
    private  InputStream in;
    private SqlSession sqlSession;
    private static IUserDao mapper;

    @Before
    public void init() throws IOException{
        in = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        sqlSession = factory.openSession();
        mapper = sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws IOException{

        sqlSession.commit();
        sqlSession.close();
        in.close();

    }

    @Test
    public  void findAll() throws IOException {

        List<User> users = mapper.findAll();
        for (User user : users){
            System.out.println(user);
        }

    }

    @Test
    public void testSave(){
        Date birthday = new Date();
        User user = new User(null,"杨军望"+new Random().nextInt(2000),birthday,"男","beijing");
        mapper.saveUser(user);

    }
    @Test
    public void testUpadate(){
        User user = new User("45","1111",new Date(),"女","三分之");
        mapper.updateUser(user);
    }

    @Test
    public void testDelete(){
        String id = "42";
        mapper.deleteUser(id);
    }
}
