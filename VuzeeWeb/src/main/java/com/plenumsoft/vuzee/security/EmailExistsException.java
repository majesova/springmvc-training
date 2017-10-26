package com.plenumsoft.vuzee.security;
/**
 * Excepción para cuando un email se encuentra repetido
 * @author msoberanis
 *
 */
public class EmailExistsException extends Exception {

	private static final long serialVersionUID = -4072047721454465349L;

	public EmailExistsException(String message) {
		super(message);
	}

}