package com.example.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {
    private static final String ERROR_MAPPING = "/error";
    private static final String ERROR_TEMPLATE = "error";

    @RequestMapping(ERROR_MAPPING)
    public String handleError(HttpServletRequest request, Model model) {
        // Get the error status code
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            switch (HttpStatus.valueOf(statusCode)) {
                case NOT_FOUND:
                    model.addAttribute("error", "Page not found");
                    break;
                case METHOD_NOT_ALLOWED:
                    model.addAttribute("error", "Unsupported HTTP method");
                    break;
                default:
                    model.addAttribute("error", "An error has occurred");
            }
        }
        return ERROR_TEMPLATE;
    }
}
