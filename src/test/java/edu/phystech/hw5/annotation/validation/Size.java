package edu.phystech.hw5.annotation.validation

import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FEILD)

public @interface Size{
    int min() default 1;
    int max() default Integer.MAX_VALUE;
    String message() default "Invalid size";
}