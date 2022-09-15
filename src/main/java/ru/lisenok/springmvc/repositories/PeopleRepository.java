package ru.lisenok.springmvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lisenok.springmvc.models.Person;

import java.util.List;

public interface PeopleRepository extends JpaRepository<Person, Integer> {

    public List<Person> findByName(String name);

    public List<Person> findByNameOrderByAge(String name);


}
