package com.example.tadoblog.controler;

import com.example.tadoblog.data.Card;
import com.example.tadoblog.data.Comment;
import com.example.tadoblog.data.User;
import com.example.tadoblog.service.CardService;
import com.example.tadoblog.service.CommentService;
import com.example.tadoblog.service.MessageService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/cards")
public class CardControler {
    private final CardService cardService;
    private final MessageService messageService;
    private final CommentService commentService;

    public CardControler(CardService cardService, MessageService messageService, CommentService commentService) {
        this.cardService = cardService;
        this.messageService = messageService;
        this.commentService = commentService;
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

    @PostMapping("/update/{id}")
    public String updateCard(@PathVariable UUID id, Card card) {
        card.setId(id);
        cardService.updateCard(card);
        return "redirect:/cards";
    }

    @GetMapping("/{id}/delete")
    public String deleteCard(@PathVariable UUID id) {
        cardService.deleteCard(id);
        return "redirect:/cards";
    }
    @GetMapping("/readMore/{id}/editComment/{commentId}")
    public String editComment(@PathVariable UUID id, @PathVariable UUID commentId, Model model, Principal principal) {
        Comment comment = commentService.getComment(commentId);
        addAttributes(model, principal, id, commentId,new UUID(0L, 0L), comment.getCommentText());
        return "readMore";
    }
    @GetMapping("/readMore/{id}/replyComment/{commentId}")
    public String replyComment(@PathVariable UUID id, @PathVariable UUID commentId, Model model, Principal principal) {
        addAttributes(model, principal, id,new UUID(0L, 0L), commentId, null);
        return "readMore";
    }
    @GetMapping("/readMore")
    public String loadOneCard(@RequestParam UUID id, Model model, Principal principal){
        addAttributes(model, principal, id, new UUID(0L, 0L),new UUID(0L, 0L), null);
        return "readMore";
    }

    private void addAttributes(Model model, Principal principal, UUID id, UUID editCommentId,UUID replyCommentId, String commentText) {
        model.addAttribute("readMore",cardService.getOneCardData(id));
        Comment comment = new Comment();
        if (commentText != null) {
            comment.setCommentText(commentText);
        }
        model.addAttribute("replyCommentId",replyCommentId);
        model.addAttribute("comment",comment);
        model.addAttribute("currentUserId", ((User)((UsernamePasswordAuthenticationToken)principal).getPrincipal()).getId());
        model.addAttribute("editCommentId", editCommentId);
    }
}
