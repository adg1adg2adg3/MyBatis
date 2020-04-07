package tk.mybatis.simple.mapper;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import tk.mybatis.simple.model.Country;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class CountryMapperTest {

    private static SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void init() {
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectAll(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Country> countryList = sqlSession.selectList("selectAll");
        printCountryList(countryList);
        sqlSession.close();
    }

    private void printCountryList(List<Country> countryList){
        for(Country country : countryList){
            System.out.println(country);
        }
    }











}
