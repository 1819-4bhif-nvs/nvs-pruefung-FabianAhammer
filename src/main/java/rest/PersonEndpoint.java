package rest;

import model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

public class PersonEndpoint {

    @PersistenceContext
    EntityManager em;


    @Path("surname")
    @Produces("application/json")
    public String surname()
    {
        int peopleCnt,maleCnt = 0,femaleCnt = 0;
        List<Person> people =  em.createNamedQuery("Person.total", Person.class).getResultList();
        peopleCnt = people.size() - 1;
        for(Person p : people)
        {
            if(p.gender == "MALE")
                maleCnt++;
            else
                femaleCnt++;
        }
        return "{ total_all : "+ peopleCnt+", totalMale"+ maleCnt +", totalFemale"+ femaleCnt +"}";
    }

}
