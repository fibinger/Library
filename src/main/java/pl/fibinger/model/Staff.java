package pl.fibinger.model;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity @Table(name = "staff")
public class Staff implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3128309722400571169L;

    @Id
    @ObjectId
	private String id;

    @NotEmpty
	private String username;

    @NotEmpty
	private String password;

    @NotEmpty
	private String name;

    private String token;
	
	Staff() {}

	public Staff(String username, String password, String name) {
		this.username = username;
		this.password = password;
		this.name = name;
	}

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
	public String toString() {
		return String.format("staff id: %s, username: %s, name: %s", getId(), getUsername(), getName());
	}
	
}
