package guru.springframework.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * Created by igorg on 31 січ. 2023 р.
 * @author igorg 
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(NumberFormatException.class)
  public ModelAndView handleNumberFormatException(Exception exception) {
    log.error("Handing Number Format Exception");
    log.error(exception.getMessage());

    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("400error");
    modelAndView.addObject("exception", exception);

    return modelAndView;
  }

//  @RequestMapping("/url")
//  public String page(Model model) {
//    model.addAttribute("attribute", "value");
//    return "view.name";
//  }
//
//  @ExceptionHandler({Exception.class})
//  public String databaseError() {
//    return "error-view-name";
//  }

}
