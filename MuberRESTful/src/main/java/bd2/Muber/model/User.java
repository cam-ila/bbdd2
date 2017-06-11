package bd2.Muber.model;

/**
 * User Class. It works as a super class for Passenger and Driver
 */
public class User {
	
	private Long idUser;
	private String fullName;
	private String password;
	public User(){
		
	}
	
	/**
	 * User object's constructor
	 */
	public User(String fullname, String password){
		this.fullName = fullname;
		this.password = password;
	}
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String name) {
		this.fullName = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

}
