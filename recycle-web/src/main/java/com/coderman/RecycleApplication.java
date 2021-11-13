package com.coderman;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author zhangyukang
 * @Date 2020/12/15 13:14
 * @Version 1.0
 **/
@SpringBootApplication
@EnableTransactionManagement  //开啟事務管理
@MapperScan("com.coderman.*.mapper") //扫描mapper
@Import(FdfsClientConfig.class)
public class RecycleApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecycleApplication.class,args);
    }
}
