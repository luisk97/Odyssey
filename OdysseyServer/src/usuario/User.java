/**
 * 
 */
package usuario;

/**
 * @author Luis Carlos Mora Fonseca
 * Esta clase contiene la informacion de los usuarios registrados
 */
public class User {

	private String usuario;
	private String nombre;
	private String edad;
	private String password;
	
	
	public User(String usuario,String nombre,String edad,String password) {
		this.usuario = usuario;
		this.nombre = nombre;
		this.edad = edad;
		this.password = password;
	}
	
	
	/**
	 * get
	 * @return String 
	 */
	public String getUsuario() {
		return usuario;
	}
	
	/**
	 * set
	 * @param usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	/**
	 * get
	 * @return String nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * set
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * get
	 * @return String edad
	 */
	public String getEdad() {
		return edad;
	}
	
	/**
	 * set
	 * @param edad
	 */
	public void setEdad(String edad) {
		this.edad = edad;
	}
	
	/**
	 * get
	 * @return String paswword
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * set
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}