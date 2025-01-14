package org.perscholas.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
// This class populates the database with seed data
public class InitializerUtil {

    EntityManagerFactory entityManagerFactory;
    @Autowired
    public InitializerUtil(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void initializeData() {

        EntityManager em = entityManagerFactory.createEntityManager();
        Path sqlFilePath;

        sqlFilePath = Paths.get("src/main/resources/sql-scripts/course.sql");
        executeQueries(sqlFilePath, em);

        sqlFilePath = Paths.get("src/main/resources/sql-scripts/student.sql");
        executeQueries(sqlFilePath, em);

        sqlFilePath = Paths.get("src/main/resources/sql-scripts/student_course.sql");
        executeQueries(sqlFilePath, em);

    }

    private void executeQueries(Path sqlFilePath, EntityManager em) {
        // try-with-resources
        try (Stream<String> sqlLines = Files.lines(sqlFilePath)) {
            sqlLines.forEach(line -> {
                em.getTransaction().begin();
                em.createNativeQuery(line).executeUpdate();
                em.getTransaction().commit();
            } );
        } catch (IOException e) {
            System.out.println("Could not open file");
        }
    }
}