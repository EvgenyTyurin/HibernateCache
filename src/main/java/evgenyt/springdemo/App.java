package evgenyt.springdemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;

/**
 * Spring Cache: 1st level (session), 2nd level(between sessions, failed for now),
 * query cache
 * Aug 2019 EvgenyT
 */

public class App {
    public static void main( String[] args ) {
        // Get application context from file
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        // Get factory bean and create session
        SessionFactory sessionFactory = context.getBean("sessionFactory",
                SessionFactory.class);
        // 1st level cache creates automatically with session
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM PersonEntity");
        // Set query cache
        query.setCacheable(true);
        query.setCacheRegion("person");
        List users = query.list();
        System.out.println(Arrays.toString(users.toArray()));
        session.close();
    }
}
