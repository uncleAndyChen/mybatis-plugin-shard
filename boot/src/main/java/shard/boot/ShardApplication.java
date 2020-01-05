package shard.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@MapperScan({"biz.mapper.original", "biz.mapper.extend"})
@ImportResource(locations = {"classpath*:db-source.xml"})
// 如果不配置 @ComponentScan，spring boot 启动时，会主动扫描 spring boot 启动类所在 package 以及子 package，所以，这里一般不用配置扫描路径。
// 如果配置了，则会严格按照配置来扫描，所以，一定要增加配置 spring boot 启动类所在 package，否则 controller 将失效。
@ComponentScan(basePackages = {
        "common.lib.application",
        "shard.boot",
        "biz.facade.impl",
        "biz.service.impl"})
@SpringBootApplication
public class ShardApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardApplication.class, args);
    }
}
