package jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DIOHibernate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        State stateToAdd = new State("Republica de Curitiba", "RC");
        Student studentToAdd = new Student("Manuelito", 23, stateToAdd);
        Student studentToAdd2 = new Student("Jair Marreta", 18, stateToAdd);

        // Insert and persist

        entityManager.getTransaction().begin();

        entityManager.persist(stateToAdd);
        entityManager.persist(studentToAdd);
        entityManager.persist(studentToAdd2);

        entityManager.getTransaction().commit();

        // Finding data

        State foundState = entityManager.find(State.class, 1);
        Student foundStudent = entityManager.find(Student.class, 1);

        System.out.println(foundState);
        System.out.println(foundStudent);

        // Changing data

        entityManager.getTransaction().begin();

        foundStudent.setName("Kurapika");
        foundStudent.setAge(19);

        entityManager.getTransaction().commit();

        // Deleting data

        entityManager.getTransaction().begin();

        entityManager.remove(foundStudent);

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

    }
}
