/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.frontui.error;

import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 *
 * @author hp i3
 */
@Controller
public class ErrorHandlerController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        model.addAttribute("statusCode", statusCode.toString());
        model.addAttribute("exception", exception);
        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            model.addAttribute("errorMessage", "Error code : " + statusCode);
        } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            model.addAttribute("errorMessage", "Error code : " + statusCode);
        } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
            model.addAttribute("errorMessage", "Error code : " + statusCode);
        } else {
            model.addAttribute("errorMessage", "Error code : " + statusCode);
        }

        return "error";

    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}
