package rest;

import model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.annotation.Repeatable;
import java.util.List;

@Path("surname")
public class PersonEndpoint {

    @PersistenceContext
    EntityManager em;


    @Produces(MediaType.APPLICATION_JSON)
    @GET
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

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String specific(@PathParam("id") long id)
    {
        return em.createNamedQuery("Person.getById",Person.class).setParameter("id",id).getSingleResult().toString();
    }

    @Path("{id}")
    @DELETE
    public Response remove(@PathParam("id") long id)
    {

        Person p = null;
        p = em.createNamedQuery("Person.getById",Person.class).setParameter("id",id).getSingleResult();
        if(p != null)
        {
            em.remove(p);
            return Response.ok().entity(p.toString() + " deleted!").build();
        }
        return Response.serverError().entity("Not found in Database!").build();

    }

}
