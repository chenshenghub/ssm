package com.itcast.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcast.Mappers.UserMapper;
import com.itcast.domin.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class UserTest {
    //
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testMapper() {
        //通过类加载器，加载核心配置类
        InputStream is = UserTest.class.getClassLoader().getResourceAsStream("SqlMapperConfig.xml");
        //build
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        //factory
        UserMapper userMapper = factory.openSession(true).getMapper(UserMapper.class);
        List<User> userList = userMapper.findAll();
        for (User user : userList) {
            try {
                String s = objectMapper.writeValueAsString(user);
                System.out.println(s);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void testLike() {
        InputStream stream = UserTest.class.getClassLoader().getResourceAsStream("SqlMapperConfig.xml");
        UserMapper mapper = new SqlSessionFactoryBuilder().build(stream).openSession(true).getMapper(UserMapper.class);
        List<User> userList = mapper.findUserByName("王");
        for (User user : userList
        ) {
            System.out.println(user);

        }
    }

}
