package ar.com.example.escribanos.webclient.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
	
		@RequestMapping("/error")
	    public String handleError(Model model, HttpServletRequest request) {
	        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

	        if (status != null) {
	            Integer statusCode = Integer.valueOf(status.toString());

	            if (statusCode == HttpStatus.NOT_FOUND.value()) {
	                model.addAttribute("errorCode", "404");
	                model.addAttribute("errorMessage", "Página no encontrada.");
	            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
	                model.addAttribute("errorCode", "403");
	                model.addAttribute("errorMessage", "Acceso denegado.");
	            } else {
	                model.addAttribute("errorCode", statusCode);
	                model.addAttribute("errorMessage", "Ha ocurrido un error inesperado.");
	            }
	        }

	        return "error_page";
	    }

		@Override
		public String getErrorPath() {
			return "/error";
		}

}
