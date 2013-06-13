package pl.fibinger.resources;

import com.mongodb.BasicDBObject;
import net.vz.mongodb.jackson.JacksonDBCollection;
import pl.fibinger.model.Staff;
import pl.fibinger.model.StaffAdapter;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;
import java.util.UUID;

@Path("/authenticate")
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationResource {

    private final JacksonDBCollection<Staff, String> staffCollection;

    public AuthenticationResource(JacksonDBCollection<Staff, String> staffCollection) {
        this.staffCollection = staffCollection;
    }

    @POST
	public Object authenticate(Staff credentials) {
        Staff member = staffCollection.findOne(new BasicDBObject("username", credentials.getUsername()));
        if(member == null || !Objects.equals(credentials.getUsername(), member.getUsername())){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        String token = UUID.randomUUID().toString();
        member.setToken(token);

        staffCollection.save(member);
		return new StaffAdapter(member);
	}
	
}