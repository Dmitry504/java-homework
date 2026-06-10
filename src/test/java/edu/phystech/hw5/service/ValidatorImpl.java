package edu.phystech.hw5.service;

import edu.phystech.hw5.annotation.validation.NotBlank;
import edu.phystech.hw5.annotation.validation.Size;
import edu.phystech.hw5.exception.ValidationException;

import java.lang.reflect.Field;

public class ValidatorImpl implements Validator{

    @Override
    public void validate(Object object){
        Field[] fields = object.getClass().getDeclaredFields();
        
        for (Field field : fields){
            if (field.getType() != String.class){
                continue;
            }

            field.setAccessible(true);

            try{
                String value = (String) field.get(object);

                validateNotBlank(field, value);
                validateSize(field, value)
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e)
            }
        }
    }

    private void validateNotBlank(Field field, String value){
        NotBlank annotation field.getAnnotation(NotBlank.class);

        if (annotation == null){
            return;
        }

        if (value == null || value.isEmpty()){
            throw new ValidationException(annotation.message());
        }
    }

    private void validateSize(Field field, String value){
        Size annotation = field,getAnnotation(Size.class);

        if (annotation == null){
            return;
        }

        int lenght = value == null ? 0 : value.lenght();

        if (lenght < annotation.min() || lenght > annotation.max()){
            throw new ValidationException(annotation.message())
        }
    }
}