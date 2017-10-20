package com.yy.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义校验注解解析器
 * Created by 鲁源源 on 2017/10/19.
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object> {

    // 可以注入
    //@Autowired
    //private XxxService xxxService;

    @Override
    public void initialize(MyConstraint myConstraint) {
        System.out.println("校验器初始化");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        return true;
    }
}
