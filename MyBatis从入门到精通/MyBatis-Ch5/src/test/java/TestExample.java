import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import tk.mybatis.simple.mapper.CountryMapper;
import tk.mybatis.simple.model.Country;
import tk.mybatis.simple.model.CountryExample;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class TestExample {

    private static SqlSessionFactory sqlSessionFactory;
    private static SqlSession sqlSession;

    @Before
    public void init(){
        try {
            /* 使用reader将MyBatis配置文件传入并获取工厂类 */
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
            sqlSession = sqlSessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExample(){

        CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);

        //创建Example对象
        CountryExample countryExample = new CountryExample();
        //设置排序规则
        countryExample.setOrderByClause("id desc, countryname asc");
        //设置是否distinct
        countryExample.setDistinct(true);
        //创建条件
        CountryExample.Criteria criteria = countryExample.createCriteria();
        //id >= 1
        criteria.andIdGreaterThanOrEqualTo(1);
        //id < 4
        criteria.andIdLessThan(4);
        //注意通配符%
        criteria.andCountrycodeLike("%U%");
        //or的情况
        CountryExample.Criteria or = countryExample.or();
        or.andCountrynameEqualTo("中国");
        List<Country> countries = mapper.selectByExample(countryExample);
        for(Country country:countries){
            System.out.println(country);
        }
        sqlSession.close();
    }
}
