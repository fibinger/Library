package pl.fibinger.resources;

import com.yammer.dropwizard.auth.Auth;
import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.WriteResult;
import pl.fibinger.model.Book;
import pl.fibinger.model.Staff;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/book/")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {

    private JacksonDBCollection<Book, String> books;

    public BookResource(JacksonDBCollection<Book, String> books){
        this.books = books;
    }

    @GET
    public List<Book> getBooks(@Auth Staff member){
        return books.find().toArray();
    }

    @GET
    @Path("{id}")
    public Book getBook(@PathParam("id") String id){
        return books.findOneById(id);
    }

    @DELETE
    @Path("{id}")
    public Response deleteBook(@PathParam("id") String id){
        books.removeById(id);
        return Response.noContent().build();
    }

    @POST
    public Response createBook(@Valid Book book){
        WriteResult<Book,String> result = books.save(book);
        String error = result.getError();
        if(error == null){
            return Response.noContent().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

}
