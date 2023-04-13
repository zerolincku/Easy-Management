package com.linck.management.common.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 代码生成工具
 *
 * @author linck
 */
public class CodeGenerator {

    private static final String url = "jdbc:mysql://management-mysql:3306/management";
    private static final String user_name = "root";
    private static final String password = "root";
    private static final String author = "linck";

    public static void main(String[] args) {
        String moduleName = "test";
        List<String> tableNames = Arrays.asList("sys_user");
        generate(moduleName, tableNames);

    }

    public static void generate(String moduleName, List<String> tableNames) {
        String parentPath = "com.linck.management";
        FastAutoGenerator.create(url, user_name, password)
                .globalConfig(builder -> {
                    builder.author(author) // 设置作者
                            .disableOpenDir()
                            .outputDir(System.getProperty("user.dir") + "/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(parentPath) // 设置父包名
                            .entity("model.entity")
                            .moduleName(moduleName)
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "/src/main/java/" + parentPath.replace(".", "/") + "/" + moduleName + "/mapper/xml")); // 设置mapperXml生成路径
                })
                .templateConfig(builder -> builder.disable(TemplateType.ENTITY)
                        .entity("/template/entity.java")
                        .service("/template/service.java")
                        .controller("/template/controller.java")
                        .mapper("/template/mapper.java")
                        // 不生成 serviceImpl
                        .serviceImpl(null)
                        .mapperXml("/template/mapper.xml"))
                .strategyConfig(builder -> {
                    // 设置需要生成的表名
                    builder.addInclude(tableNames)
                            .addTablePrefix("m_", "p_", "b_");

                    // 实体类配置
                    builder.entityBuilder()
                            .enableLombok()
                            .enableChainModel();

                    builder.controllerBuilder()
                            .enableRestStyle();

                    builder.mapperBuilder()
                            .enableBaseResultMap()
                            .enableBaseColumnList();

                    builder.serviceBuilder()
                            .formatServiceFileName("%sService");

                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
