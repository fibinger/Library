package pl.fibinger;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class LibraryConfiguration extends Configuration {

    @JsonProperty
    @NotEmpty
    public String dbhost = "localhost";

    @Min(1)
    @Max(65535)
    @JsonProperty
    public int dbport = 27017;

    @JsonProperty @NotEmpty
    public String dbname = "library";

    public String getDbhost() {
        return dbhost;
    }

    public void setDbhost(String dbhost) {
        this.dbhost = dbhost;
    }

    public int getDbport() {
        return dbport;
    }

    public void setDbport(int dbport) {
        this.dbport = dbport;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }
}
