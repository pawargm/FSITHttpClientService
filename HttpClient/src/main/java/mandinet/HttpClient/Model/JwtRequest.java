package mandinet.HttpClient.Model;

public class JwtRequest {

	String mail;
	String password;
	
	
	/**public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}*/
	public String getPassword() {
		return password;
	}
	public JwtRequest() {
	
	}
	public JwtRequest(String userName, String password) {
		
		this.mail = userName;
		this.password = password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
}
