package query;

import org.hibernate.criterion.CriteriaQuery;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DIOHibernate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        State stateToAdd = new State("Sao Paulo", "SP");
        entityManager.persist(stateToAdd);
        entityManager.persist(new State("Rio Grande do Sul", "RS"));

        entityManager.persist(new Student("Manuelito", 23, stateToAdd));
        entityManager.persist(new Student("Jair Marreta", 18, stateToAdd));
        entityManager.persist(new Student("Luneta", 6, stateToAdd));
        entityManager.persist(new Student("Vesceslau", 16, stateToAdd));

        entityManager.getTransaction().commit();

        // EntityManager native
        Student studentEntityManager = entityManager.find(Student.class, 3);

        System.out.println(studentEntityManager);

        // SQL query
        String name = "Luneta";

        //String sql = "select * from STUDENT where NAME = :name ";
        String sql = "select * from STUDENT where NAME = :name ";
        Student studentSQL = (Student) entityManager
                        .createNativeQuery(sql, Student.class)
                        .setParameter("name", name)
                        .getSingleResult();

        String sqlList = "select * from STUDENT";
        List<Student> studentSQLList = entityManager
                        .createNativeQuery(sqlList, Student.class)
                        .getResultList();

        System.out.println("SQL result: " + studentSQL);
        studentSQLList.forEach(Student -> System.out.println("Query studentSQLList: " + Student));

        System.out.println();

        // JPQL
        String jpql = "select a from Student a where a.name = :name";
        Student studentJPQL = entityManager
                .createQuery(jpql, Student.class)
                .setParameter("name", name)
                .getSingleResult();

        String jpqlList = "select a from Student a where a.state.name = :stateName ";
        List<Student> studentJPQLList = entityManager
                .createQuery(jpqlList, Student.class)
                .setParameter("stateName", "Sao Paulo")
                .getResultList();

        System.out.println("JPQL result: " + studentJPQL);
        studentJPQLList.forEach(Student -> System.out.println("Query studentJPQLList: " + Student));

        System.out.println();

        // JPA Criteria
        CriteriaQuery<Student> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Student.class);

        entityManager.close();
        entityManagerFactory.close();

    }
}
