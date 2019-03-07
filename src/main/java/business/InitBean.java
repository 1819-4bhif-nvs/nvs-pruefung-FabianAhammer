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

        new BufferedReader(new InputStreamReader(this.getClass()
                .getResourceAsStream(FILE_MALE), Charset.defaultCharset()))
                .lines()
                .map(a -> new Person(Gender.Male.toString(),a))
                .forEach(em::persist);

        new BufferedReader(new InputStreamReader(this.getClass()
                .getResourceAsStream(FILE_FEMALE), Charset.defaultCharset()))
                .lines()
                .map(a -> new Person(Gender.Female.toString(),a))
                .forEach(em::persist);
    }
}