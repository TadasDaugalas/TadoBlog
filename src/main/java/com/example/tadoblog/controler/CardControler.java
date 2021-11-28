package com.example.tadoblog.controler;

import com.example.tadoblog.data.Card;
import com.example.tadoblog.data.Comment;
import com.example.tadoblog.data.Role;
import com.example.tadoblog.data.User;
import com.example.tadoblog.service.CardService;
import com.example.tadoblog.service.CommentService;
import com.example.tadoblog.service.MessageService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

@Controller
public class CardControler {
    private final CardService cardService;
    private final MessageService messageService;
    private final CommentService commentService;

    public CardControler(CardService cardService, MessageService messageService, CommentService commentService) {
        this.cardService = cardService;
        this.messageService = messageService;
        this.commentService = commentService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/private/cards/create")
    public String loadCardForm(Model model, String message) {
        model.addAttribute("card", new Card());
        if (message != null) {
            model.addAttribute("success", messageService.getMessage(message));
        }
        return "card";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/private/cards/create")
    public String createCard(@Valid Card card, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "card";
        }
        cardService.saveCard(card);
        return "redirect:/private/cards/create?message=com.eshop.card.create.succ";
    }

    @GetMapping("/public/cards")
    public String loadCards(Model model, @PageableDefault(size = 2) @SortDefault(sort = ("cardTitle"), caseSensitive = false) Pageable pageable) {
        model.addAttribute("pageOfCards", cardService.getCard(pageable));
        return "cards";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/private/cards/update")
    public String loadUpdateCard(Model model,@RequestParam UUID id) {
        model.addAttribute("card", cardService.getCard(id));
        return "card";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/private/cards/update/{id}")
    public String updateCard(@PathVariable UUID id, Card card) {
        card.setId(id);
        cardService.updateCard(card);
        return "redirect:/public/cards";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/private/cards/{id}/delete")
    public String deleteCard(@PathVariable UUID id) {
        cardService.deleteCard(id);
        return "redirect:/public/cards";
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/private/cards/readMore/{id}/editComment/{commentId}")
    public String editComment(@PathVariable UUID id, @PathVariable UUID commentId, Model model, Principal principal) {
        Comment comment = commentService.getComment(commentId);
        addAttributes(model, principal, id, commentId,new UUID(0L, 0L), comment.getCommentText());
        return "readMore";
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/private/cards/readMore/{id}/replyComment/{commentId}")
    public String replyComment(@PathVariable UUID id, @PathVariable UUID commentId, Model model, Principal principal) {
        addAttributes(model, principal, id,new UUID(0L, 0L), commentId, null);
        return "readMore";
    }
    @GetMapping("/public/cards/readMore")
    public String loadOneCard(@RequestParam UUID id, Model model, Principal principal){
        addAttributes(model, principal, id, new UUID(0L, 0L),new UUID(0L, 0L), null);
        return "readMore";
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/private/cards/readMore/{id}/removeComment/{commentId}")
    public String removeComment(@PathVariable UUID id, @PathVariable UUID commentId, Model model, Principal principal) {
        commentService.removeComment(commentId);
        addAttributes(model, principal, id,new UUID(0L, 0L),new UUID(0L, 0L), null);
        return "redirect:/public/cards/readMore?id=" + id;
    }

    private void addAttributes(Model model, Principal principal, UUID id, UUID editCommentId,UUID replyCommentId, String commentText) {
        model.addAttribute("readMore",cardService.getOneCardData(id));
        Comment comment = new Comment();
        if (commentText != null) {
            comment.setCommentText(commentText);
        }
        model.addAttribute("replyCommentId",replyCommentId);
        model.addAttribute("comment",comment);
        if(principal != null){
            User user = (User)((UsernamePasswordAuthenticationToken)principal).getPrincipal();
            Optional<Role> role = user.getRoles().stream().filter(i -> i.getAuthority().equals("ROLE_ADMIN")).findAny();
            model.addAttribute("isAdmin", role.isPresent());
            model.addAttribute("currentUserId", user.getId());
        } else {
            model.addAttribute("isAdmin", false);
        }
        model.addAttribute("editCommentId", editCommentId);
    }
}
