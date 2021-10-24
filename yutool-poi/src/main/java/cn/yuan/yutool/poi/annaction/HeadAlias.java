package cn.yuan.yutool.poi.annaction;


import java.lang.annotation.*;

/**
 * @description: <p>excel表格内容解析到指定类的对应属性时，需使用该注解指定excel表格中表头与类属性的对应关系</p>
 * @author: PanFowa
 * @time: 2021/10/23 22:37
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
@Documented
public @interface HeadAlias {
    /**
     * <p>表头与属性映射描述。<br/>
     * 类注解时：rels格式为"age,name:年龄,姓名"<br/>
     * </p>
     */
    String rels();

}