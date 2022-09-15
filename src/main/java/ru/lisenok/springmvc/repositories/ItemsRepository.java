package ru.lisenok.springmvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lisenok.springmvc.models.Item;
import ru.lisenok.springmvc.models.Person;

import java.util.List;

public interface ItemsRepository extends JpaRepository<Item, Integer> {

    List<Item> findByOwner(Person person);
    List<Item> findByItemName(String name);
}
