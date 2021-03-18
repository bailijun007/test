package com.blj.pojo;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author BaiLiJun on 2020/6/11
 */
public class InstB {
    public InstB() {
        System.out.println("InstB 的无参构造器");
    }


}
