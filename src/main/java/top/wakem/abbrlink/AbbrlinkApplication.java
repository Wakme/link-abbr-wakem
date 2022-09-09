package top.wakem.abbrlink;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.wakem.abbrlink.dao.mapper")
public class AbbrlinkApplication {

    public static void main(String[] args) {
        SpringApplication.run(AbbrlinkApplication.class, args);
    }

}
