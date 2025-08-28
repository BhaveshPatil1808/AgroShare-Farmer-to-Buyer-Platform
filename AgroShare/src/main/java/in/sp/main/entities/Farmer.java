package in.sp.main.entities;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "farmers")
public class Farmer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	
	
	@Column(nullable = false, unique = true)
	private String email;
	
	private String name;
	
	private String contact;
	
	private String password;
	
	private String location;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Farmer(int id, String email, String name, String contact, String password, String location) {
		super();
		Id = id;
		this.email = email;
		this.name = name;
		this.contact = contact;
		this.password = password;
		this.location = location;
	}

	public Farmer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
