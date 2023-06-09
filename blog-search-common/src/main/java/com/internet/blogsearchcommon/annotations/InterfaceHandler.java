package com.internet.blogsearchcommon.annotations;

import com.internet.blogsearchcommon.enums.CompanyCode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface InterfaceHandler {

    CompanyCode value();
}
