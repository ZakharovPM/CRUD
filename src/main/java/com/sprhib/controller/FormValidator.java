package com.sprhib.controller;

import com.sprhib.model.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lol on 09.08.2015.
 */
public class FormValidator implements Validator {
    private Pattern pattern;
    private Matcher matcher;

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

            ValidationUtils.rejectIfEmptyOrWhitespace(errors,"age", "age.is.empty" ,"Поле \"Возраст\" обязательно для заполнения.");


            if (user.getAge() != null) {
                pattern = Pattern.compile("[0-9]{0,3}");
                matcher = pattern.matcher(user.getAge().toString());
                if (!matcher.matches()) {
                    errors.rejectValue("age", "age.is.bad", "Заполните правильно поле \"Возраст\"");

                }

                else if (user.getAge() > 120) {
                    errors.rejectValue("age", "age.to.long", "Слишком большой возраст");
                }

        }
        ValidationUtils.rejectIfEmpty(errors,"name", "name.empty", "Поле \"Имя\" должно быть заполнено");

    }
}
