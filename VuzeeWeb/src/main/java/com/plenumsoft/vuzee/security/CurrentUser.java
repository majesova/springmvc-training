package com.plenumsoft.vuzee.security;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.servlet.ModelAndView;

/**
 * Anotación para obtener el usuario actual
 * Ejemplo de uso:  
 * 	public ModelAndView Index(@CurrentUser AuthenticatedUser user, HttpServletRequest request) 
 * @author msoberanis
 *
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {
	
}