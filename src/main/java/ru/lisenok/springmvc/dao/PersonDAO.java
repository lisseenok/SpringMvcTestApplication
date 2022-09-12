package ru.lisenok.springmvc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.lisenok.springmvc.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index(){
//        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
        Session session = sessionFactory.getCurrentSession();
        List<Person> people = session.createQuery("select p from Person p", Person.class)
                .getResultList();
        return people;
    }

    @Transactional(readOnly = true)
    public Person show(int id){
//        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
//                .stream().findAny().orElse(null);
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        return person;
    }

    @Transactional
    public void save(Person person){
//        jdbcTemplate.update("INSERT INTO Person(name, age, email, address) VALUES(?, ?, ?, ?)", person.getName(),
//                person.getAge(), person.getEmail(), person.getAddress());

        Session session = sessionFactory.getCurrentSession();
        session.persist(person);

    }

    @Transactional
    public void update(int id, Person updatedPerson){
//        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=?, address=? WHERE id=?", updatedPerson.getName(),
//                updatedPerson.getAge(), updatedPerson.getEmail(), updatedPerson.getAddress(), id);

        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        person.setName(updatedPerson.getName());
        person.setAge(updatedPerson.getAge());
    }

    @Transactional
    public void delete(int id){
//        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        session.remove(person);
    }

    // Testing batch update performance

//    public void testMultipleUpdate(){
//         List<Person> people = createThousandPeople();
//
//         long before = System.currentTimeMillis();
//
//         for (Person person : people){
//             jdbcTemplate.update("INSERT INTO Person VALUES(?, ?, ?, ?)", person.getId(), person.getName(),
//                     person.getAge(), person.getEmail());
//         }
//         long after = System.currentTimeMillis();
//         System.out.println("Time:" + (after - before));
//    }

//    public void testBatchUpdate(){
//        List<Person> people = createThousandPeople();
//
//        long before = System.currentTimeMillis();
//
//        jdbcTemplate.batchUpdate("INSERT INTO Person VALUES(?, ?, ?, ?)", new BatchPreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps, int i) throws SQLException {
//                ps.setInt(1, people.get(i).getId());
//                ps.setString(2, people.get(i).getName());
//                ps.setInt(3, people.get(i).getAge());
//                ps.setString(4, people.get(i).getEmail());
//            }
//
//            @Override
//            public int getBatchSize() {
//                return people.size();
//            }
//        });
//
//        long after = System.currentTimeMillis();
//        System.out.println("Time:" + (after - before));
//    }

//    private List<Person> createThousandPeople() {
//        List<Person> people = new ArrayList<>();
//
//        for (int i = 0; i < 1000; i++){
//            people.add(new Person(i, "Name" + i, 15, "test" + i + "mail.ru", "some address"));
//        }
//
//        return people;
//    }
}
