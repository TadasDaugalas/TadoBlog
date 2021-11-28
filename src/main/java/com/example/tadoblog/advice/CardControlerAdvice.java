package com.example.tadoblog.advice;

import com.example.tadoblog.exeption.CardNotExistExeption;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.beans.PropertyEditor;
import java.util.Date;


@ControllerAdvice
public class CardControlerAdvice {

    @ExceptionHandler(CardNotExistExeption.class)
    public String hanglingProductNotExist(CardNotExistExeption cardNotExistExeption, Model model) {
        model.addAttribute("cardId", cardNotExistExeption.getCardId());

        return "cardNotFound";
    }

    @InitBinder
    public void initStringBinder(WebDataBinder webDataBinder) {
        PropertyEditor editor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, editor);
    }

    @ModelAttribute("currentDate")
    public Date currentDate() {
        return new Date();
    }
}