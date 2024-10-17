package in.forFresher.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	/**
     * @author Saravanan Raja
     * @since 1.0
     * @provides html
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler(Exception.class)  // You can handle specific exceptions here as well
    public String handleNotFound(Model model, Exception ex) {
        model.addAttribute("error", "Page Not Found ");
        return "error/error-404";
    }
    
    /**
     * @author Saravanan Raja
     * @since 1.0
     * @provides html
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 500
    @ExceptionHandler(RuntimeException.class)
    public String handleInternalServerError(Model model, Exception ex) {
        model.addAttribute("error", "This page seems to be playing hide-and-seek. We’re hunting it down!");
        return "error/error-500";
    }

}
