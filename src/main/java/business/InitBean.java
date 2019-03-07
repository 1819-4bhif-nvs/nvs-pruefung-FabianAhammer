package business;

import model.Person;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import static java.lang.System.out;

@Singleton
@Startup
public class InitBean {

    static final String FILE_MALE = "maennlich.csv";
    static final String FILE_FEMALE = "weiblich.csv";


    @PersistenceContext
    EntityManager em;

    @PostConstruct
    public void init() {

        try {
            BufferedReader br = new BufferedReader(
            new InputStreamReader(getClass().getResourceAsStream("/" + FILE_MALE)));
            br.readLine();
            String line;
            Person person;
            while ((line = br.readLine()) != null) {
                person = new Person(Gender.Male.toString(), line);
                em.persist(person);
            }

            br = new BufferedReader(
            new InputStreamReader(getClass().getResourceAsStream("/" + FILE_FEMALE)));
            br.readLine();
            while ((line = br.readLine()) != null) {
                person = new Person(Gender.Male.toString(), line);
                em.persist(person);
            }


            em.flush();

        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }
}