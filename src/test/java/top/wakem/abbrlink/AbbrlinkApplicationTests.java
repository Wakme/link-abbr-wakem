package top.wakem.abbrlink;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.wakem.abbrlink.dao.entity.LinkAbbr;
import top.wakem.abbrlink.dao.mapper.LinkAbbrMapper;

import java.util.List;

@SpringBootTest
class AbbrlinkApplicationTests {

    @Autowired
    private LinkAbbrMapper linkAbbrMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testSelect() {
        final List<LinkAbbr> linkAbbrs = linkAbbrMapper.selectList(null);
        System.out.println(linkAbbrs);
    }

}
