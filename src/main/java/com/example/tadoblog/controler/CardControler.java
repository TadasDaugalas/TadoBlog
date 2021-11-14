package com.example.tadoblog.controler;

import com.example.tadoblog.data.CardData;
import com.example.tadoblog.service.CardService;
import com.example.tadoblog.service.MessageService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Id;
import javax.validation.Valid;

@Controller
@RequestMapping("/cards")
public class CardControler {
    private final CardService cardService;
    private final MessageService messageService;

    public CardControler(CardService cardService, MessageService messageService) {
        this.cardService = cardService;
        this.messageService = messageService;
    }

    @GetMapping("/create")
    public String loadCardForm(Model model, String message) {
        model.addAttribute("card", new CardData());
        if (message != null) {
            model.addAttribute("success", messageService.getMessage(message));
        }
        return "card";
    }

    @PostMapping("/create")
    public String createCard(@Valid CardData cardData, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "card";
        }
        cardService.saveCard(cardData);
        return "redirect:/cards/create?message=com.eshop.cardData.create.succ";
    }

    @GetMapping
    public String loadCards(Model model, @PageableDefault(size = 2) @SortDefault(sort = ("title"), caseSensitive = false) Pageable pageable) {
        model.addAttribute("pageOfCards", cardService.getCard(pageable));
        return "cards";
    }

    @GetMapping("/update")
    public String loadUpdateCard(Model model, Id id) {
        model.addAttribute("/card", cardService.getCard(id));
        return "/card";
    }

    @PostMapping("/update")
    public String updateCard(CardData cardData) {
        cardService.updateCard(cardData);
        return "redirect:/cards";
    }

    @GetMapping("/{id}/delete")
    public String deleteCard(@PathVariable Id id) {
        cardService.deleteCard(id);
        return "redirect:/cards";
    }
}
