package com.blj.config.validator;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * 入参校验配置类
 *
 * 快速失败模式: 只要有一个验证失败，则返回
 *
 * @author bailj@linkstec.com
 * @date 2020/6/24 17:28
 */
@Configuration
public class ValidatorConf {

    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .failFast( true )
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        return validator;
    }
}
