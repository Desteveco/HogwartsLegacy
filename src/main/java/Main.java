import entity.Course;
import entity.House;
import entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
//        House slytherin = entityManager.find(House.class, 4);
        Query queryHouse = entityManager.createQuery("SELECT h FROM House h Where h.name = 'Slytherin'");
        House slytherin = (House) queryHouse.getSingleResult();

//        Course astronomy = entityManager.find(Course.class, 6);
        Query queryCourse = entityManager.createQuery("SELECT c FROM Course as c WHERE c.name = 'Astronomy'");
        Course astronomy = (Course) queryCourse.getSingleResult();
        Set<Course> courses = new HashSet<Course>();
        courses.add(astronomy);

        if (entityManager.find(Person.class, 109) == null) {
            Person newPerson = new Person();
            newPerson.setId(109);
            newPerson.setFirstName("Albus");
            newPerson.setLastName("Severus Potter");
            newPerson.setHouse(slytherin);
            entityManager.persist(newPerson);
            System.out.println(" Añadido Albus Severus Potter");
        } else {
            System.out.println(" Albus Severus Potter ya existe (id 109)");
        }

        if (entityManager.find(Person.class, 110) == null) {
            Person newPerson2 = new Person();
            newPerson2.setId(110);
            newPerson2.setFirstName("James");
            newPerson2.setLastName("Sirius Potter");
            newPerson2.setHouse(slytherin);
            entityManager.persist(newPerson2);
            System.out.println(" Añadido James Sirius Potter");
        } else {
            System.out.println(" James Sirius Potter ya existe (id 110)");
        }

        if (entityManager.find(Person.class, 111) == null) {
            Person newPerson3 = new Person();
            newPerson3.setId(111);
            newPerson3.setFirstName("Lily");
            newPerson3.setLastName("Luna Potter");
            newPerson3.setHouse(slytherin);
            entityManager.persist(newPerson3);
            System.out.println(" Añadida Lily Luna Potter");
        } else {
            System.out.println(" Lily Luna Potter ya existe (id 111)");
        }

        Person lily = entityManager.find(Person.class, 111);
        lily.setCourses(courses);
        astronomy.getPeople().add(lily);
        System.out.println(" Lily Luna Potter matriculada en Astronomía");


        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
