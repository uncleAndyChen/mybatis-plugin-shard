package common.lib.application;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @author AndyChen
 * @Description: 解决在静态方法内不能调用非静态Mapper实例的问题
 * 用法：
 * static MapperName mapperName;
 * if (null == mapperName)
 * {
 * mapperName = (MapperName)BeanTools.getBean(MapperName.class);
 * }
 *
 * @date 2017-01-11
 */
@Configuration
public class BeanTools implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanTools.applicationContext = applicationContext;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Object getBean(Class classname) {
        try {
            return applicationContext.getBean(classname);
        } catch (Exception e) {
            return "";
        }
    }
}