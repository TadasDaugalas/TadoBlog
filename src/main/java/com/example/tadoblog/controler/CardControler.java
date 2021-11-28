package com.example.tadoblog.controler;

import com.example.tadoblog.data.Card;
import com.example.tadoblog.data.Comment;
import com.example.tadoblog.service.CardService;
import com.example.tadoblog.service.MessageService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

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
        model.addAttribute("card", new Card());
        if (message != null) {
            model.addAttribute("success", messageService.getMessage(message));
        }
        return "card";
    }

    @PostMapping("/create")
    public String createCard(@Valid Card card, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "card";
        }
        cardService.saveCard(card);
        return "redirect:/cards/create?message=com.eshop.card.create.succ";
    }

    @GetMapping
    public String loadCards(Model model, @PageableDefault(size = 2) @SortDefault(sort = ("cardTitle"), caseSensitive = false) Pageable pageable) {
        model.addAttribute("pageOfCards", cardService.getCard(pageable));
        return "cards";
    }

    @GetMapping("/update")
    public String loadUpdateCard(Model model,@RequestParam UUID id) {
        model.addAttribute("card", cardService.getCard(id));
        return "/card";
    }

    @PostMapping("/update")
    public String updateCard(Card card) {
        cardService.updateCard(card);
        return "redirect:/cards";
    }

    @GetMapping("/{id}/delete")
    public String deleteCard(@PathVariable UUID id) {
        cardService.deleteCard(id);
        return "redirect:/cards";
    }
    @GetMapping("/readMore")
    public String loadOneCard(@RequestParam UUID id,Model model){
        model.addAttribute("readMore",cardService.getOneCardData(id));
        model.addAttribute("comment",new Comment());
        return "readMore";
    }
}
