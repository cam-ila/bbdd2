/**
 * 
 */
package bd2.Muber.dto;

import bd2.Muber.model.User;

/**
 * @author cami
 *
 */
public class UserDTO {
	
	private Long idUser;
	private String fullName;
	private String password;
	
	public UserDTO(User anUser){
		this.setIdUser(anUser.getIdUser());
		this.setFullName(anUser.getFullName());
		this.setPassword(anUser.getPassword());		
	}

	/**
	 * @return the idUser
	 */
	public Long getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
