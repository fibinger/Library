package pl.fibinger;

import com.google.common.base.Optional;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.auth.AuthenticationException;
import com.yammer.dropwizard.auth.Authenticator;
import com.yammer.dropwizard.auth.basic.BasicAuthProvider;
import com.yammer.dropwizard.auth.basic.BasicCredentials;
import com.yammer.dropwizard.auth.oauth.OAuthProvider;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import net.vz.mongodb.jackson.JacksonDBCollection;
import pl.fibinger.model.Book;
import pl.fibinger.model.Staff;
import pl.fibinger.resources.AuthenticationResource;
import pl.fibinger.resources.BookResource;
import pl.fibinger.resources.HelloWorldResource;

public class LibraryService extends Service<LibraryConfiguration>{

    public static void main(String[] args) throws Exception {
        new LibraryService().run(args);
    }

    @Override
    public void initialize(Bootstrap<LibraryConfiguration> bootstrap) {
        bootstrap.setName("library");
        bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
    }

    @Override
    public void run(LibraryConfiguration configuration, Environment environment) throws Exception {
        environment.addResource(new HelloWorldResource("Hello, %s!", "Stranger"));

        Mongo mongo = new Mongo(configuration.getDbhost(), configuration.getDbport());
        mongo.getDatabaseNames();
        DB db = mongo.getDB(configuration.getDbname());
        JacksonDBCollection<Book, String> books = JacksonDBCollection.wrap(db.getCollection("books"), Book.class, String.class);
        final JacksonDBCollection<Staff, String> staff = JacksonDBCollection.wrap(db.getCollection("staff"), Staff.class, String.class);

        environment.addResource(new BookResource(books));
        environment.addResource(new AuthenticationResource(staff));

        environment.addHealthCheck(new MongoHealthCheck(mongo));
        environment.manage(new MongoManaged(mongo));
        environment.addProvider(new OAuthProvider<Staff>(new Authenticator<String, Staff>() {
            @Override
            public Optional<Staff> authenticate(String token) throws AuthenticationException {
                Staff member = staff.findOne(new BasicDBObject("token", token));
                if(member != null) {
                    return Optional.of(member);
                }
                return Optional.absent();
            }
        }, "STAFF"));
    }
}
