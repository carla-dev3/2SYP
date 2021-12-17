package es.florida.AE4;

import java.io.Serializable;

public class EncriptarContrasenya implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String contrasenya_texto;
	String contrasenya_encript;

	public EncriptarContrasenya(String contrasenya_texto, String contrasenya_encript) {
		super();
		this.contrasenya_texto = contrasenya_texto;
		this.contrasenya_encript = contrasenya_encript;
	}

	public EncriptarContrasenya() {
		super();
	}

	public String getContrasenyaTexto() {
		return contrasenya_texto;
	}

	public void setContrasenyaTexto(String contrasenya_texto) {
		this.contrasenya_texto = contrasenya_texto;
	}

	public String getContrasenyaEncriptada() {
		return contrasenya_encript;
	}

	public void setContrasenyaEncriptada(String contrasenya_encript) {
		this.contrasenya_encript = contrasenya_encript;
	}
}
