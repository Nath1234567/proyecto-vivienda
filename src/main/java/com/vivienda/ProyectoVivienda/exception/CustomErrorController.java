package com.vivienda.ProyectoVivienda.exception;

import org.springframework.http.HttpStatus;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class CustomErrorController {

    @RequestMapping("/error-error")
    @ResponseBody
    public String handleError() {
        return "{\"error\": \"Ha ocurrido un error, por favor actualiza y vuelve a intentarlo.\"}";
    }

//    @ExceptionHandler(AuthenticationException.class)
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
//    @ResponseBody
//    public String handleAuthenticationException(AuthenticationException ex) {
//        return "{\"error\": \"No estás autorizado para consumir este recurso.\"}";
//    }
//
//    @ExceptionHandler(AccessDeniedException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    @ResponseBody
//    public String handleAccessDeniedException(AccessDeniedException ex) {
//        return "{\"error\": \"No estás autorizado para consumir este recurso.\"}";
//    }
}
