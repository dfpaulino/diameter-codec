package org.example.diameter.avp;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AvpRegister {
    String avpBuilderMethod() default "";
    int avpCode() default 0;
}
