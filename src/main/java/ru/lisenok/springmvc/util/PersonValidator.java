package ru.lisenok.springmvc.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.lisenok.springmvc.dao.PersonDAO;
import ru.lisenok.springmvc.models.Person;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        if (personDAO.show(person.getEmail()).isPresent()){
            // помещаем в объект errors ошибку, три параметра: 1 - на каком поле в персон ошибка
            // 2 - код ошибки, 3 - текст ошибки
            errors.rejectValue("email", "", "sorry, this email is already taken ( ");
        }
    }
}
