package com.emas.common.utils.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author 代码生成器
 */
@Slf4j
public class CodeGenerator {
    private static String module_name;
    // TODO 项目名称
    private static final String PROJECT_NAME = "emas";
    // TODO 微服务项目模块名（可选，和单体二选一）
    private static final String CLOUD_NAME = "emas";
    // TODO 路径包名（生成的文件放在绝地路径下，为下面路径代码铺垫）
    private static final String PACKAGE_DIR = "/com/emas/modular/";
    // TODO 生成包名
    private static final String PACKAGE_EXE = "com.emas.modular";
    // TODO 数据库地址
    private static final String URL = "jdbc:postgresql://localhost:5432/emas";
    // TODO 数据库用户名
    private static final String USERNAME = "postgres";
    // TODO 数据库密码
    private static final String PASSWORD = "root";
    // TODO  作者
    private static final String AUTHOR = "apple";
    // TODO  去除的表前缀（可选）
    private static final String FieldPrefix = "";
    /**
     * 项目路径
     */
    private static final String PARENT_DIR = System.getProperty("user.dir");
    /**
     * xml路径
     */
    private static final String XML_PATH = PARENT_DIR + "/src/main/resources/mapper/"; //单体项目
    //private static final String XML_PATH = PARENT_DIR + "/"+CLOUD_NAME+"/src/main/resources/mapper";
    /**
     * entity路径
     */
    private static final String ENTITY_PATH = PARENT_DIR +"/src/main/java"+PACKAGE_DIR;//单体项目
    //private static final String ENTITY_PATH = PARENT_DIR +"/"+CLOUD_NAME+"/src/main/java"+PACKAGE_DIR+"entity";
    /**
     * mapper（Dao）路径
     */
    private static final String MAPPER_PATH = PARENT_DIR +"/src/main/java"+PACKAGE_DIR;//单体项目
    //private static final String MAPPER_PATH = PARENT_DIR +"/"+CLOUD_NAME+"/src/main/java"+PACKAGE_DIR+"dao";
    /**
     * service路径
     */
    private static final String SERVICE_PATH = PARENT_DIR +"/src/main/java"+PACKAGE_DIR;//单体项目
    //private static final String SERVICE_PATH = PARENT_DIR +"/"+CLOUD_NAME+"/src/main/java"+PACKAGE_DIR+"service";
    /**
     * serviceImpl路径
     */
    private static final String SERVICE_IMPL_PATH = PARENT_DIR +"/src/main/java"+PACKAGE_DIR;//单体项目
    //private static final String SERVICE_IMPL_PATH = PARENT_DIR +"/"+CLOUD_NAME+"/src/main/java"+PACKAGE_DIR+"service/impl/";
    /**
     * controller路径
     */
    private static final String CONTROLLER_PATH = PARENT_DIR +"/src/main/java"+PACKAGE_DIR;//单体项目
    //private static final String CONTROLLER_PATH = PARENT_DIR +"/"+CLOUD_NAME+"/src/main/java"+PACKAGE_DIR+"controller";

    public static void main(String[] args) {
        module_name = scanner("模块名");
        String packageName = PACKAGE_EXE + "." + module_name;
        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                // 全局配置
                .globalConfig(builder -> builder
                        // 设置作者
                        .author(AUTHOR)
                        // 开启swagger注解
                        //.enableSwagger()
                        .disableOpenDir()
                )
                // 包配置
                .packageConfig(builder -> builder
                        .parent("")
                        .xml("mapper")
                        .entity(packageName+".entity")
                        .mapper(packageName+".mapper")
                        .service(packageName+".service")
                        .serviceImpl(packageName+".service.impl")
                        .controller(packageName+".controller")
                        .pathInfo(getPathInfo())
                )
                // 策略配置
                .strategyConfig((scanner, builder) ->
                        builder.addInclude(getTables(scanner.apply("请输入表名，多个表之间用英文逗号分隔，所有输入all")))
                                .addTablePrefix(FieldPrefix)
                                // entity
                                .entityBuilder()
                                .fileOverride()
                                .enableChainModel()
                                .fileOverride()
                                // 开启lombock
                                .enableLombok()
                                .enableTableFieldAnnotation()
                                //.logicDeleteColumnName("deleted")
                                //.logicDeletePropertyName("deleteFlag")
                                .idType(IdType.AUTO)
                                //.addTableFills(new Column("create_time", FieldFill.INSERT))
                                //.addTableFills(new Property("update_time", FieldFill.INSERT_UPDATE))
                                // controller
                                .controllerBuilder()
                                .fileOverride()
                                .enableRestStyle()
                                .formatFileName("%sController")
                                // service
                                .serviceBuilder()
                                .fileOverride()
                                .superServiceClass(IService.class)
                                .formatServiceFileName("%sService")
                                .formatServiceImplFileName("%sServiceImpl")
                                // mapper
                                .mapperBuilder()
                                .fileOverride()
                                .enableBaseResultMap()
                                .enableBaseColumnList()
                                .superClass(BaseMapper.class)
                                .formatMapperFileName("%sMapper")
                                .formatXmlFileName("%sMapper")
                                .enableMapperAnnotation()
                )
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateConfig(builder -> {
                    // 实体类使用我们自定义模板
                    builder.entity("/templates/entity.java");
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
    /**
     * 获取路径信息map
     */
    private static Map<OutputFile, String> getPathInfo() {
        String replace = module_name.replace(".", "/");
        Map<OutputFile, String> pathInfo = new HashMap<>(5);
        pathInfo.put(OutputFile.entity, ENTITY_PATH+replace+"/entity");
        pathInfo.put(OutputFile.mapper, MAPPER_PATH+replace+"/mapper");
        pathInfo.put(OutputFile.service, SERVICE_PATH+replace+"/service");
        pathInfo.put(OutputFile.serviceImpl, SERVICE_IMPL_PATH+replace+"/service/impl/");
        pathInfo.put(OutputFile.controller, CONTROLLER_PATH+replace+"/controller");
        pathInfo.put(OutputFile.xml, XML_PATH+replace+"/");
        return pathInfo;
    }

    // 处理 all 情况
    protected static List<String> getTables (String tables){
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        log.info("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}

