package edu.phystech.hw5.annotation.validation

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FEILD)

public @interface NotBlank{
    String message() default "Field must not be blank"
}