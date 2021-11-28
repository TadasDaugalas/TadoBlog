package com.example.tadoblog.controler;

import com.example.tadoblog.data.Card;
import com.example.tadoblog.data.Comment;
import com.example.tadoblog.data.User;
import com.example.tadoblog.service.CardService;
import com.example.tadoblog.service.CommentService;
import com.example.tadoblog.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

@Controller
@SessionAttributes("readMore")
public class CommentController {
    private final CommentService commentService;
    private final CardService cardService;
    private final UserService userService;

    public CommentController(CommentService commentService, CardService cardService, UserService userService) {
        this.commentService = commentService;
        this.cardService = cardService;
        this.userService = userService;
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping(value = {"/private/cards/readMore/{id}/addComment","/private/cards/readMore/{id}/addComment/{commentId}"})
    public String addComment(@PathVariable UUID id, @PathVariable(required = false) UUID commentId, Comment comment, Principal principal){
        Card card = cardService.getCard(id);
        comment.setCard(card);
        UUID currentUserId = ((User)((UsernamePasswordAuthenticationToken)principal).getPrincipal()).getId();
          User user = userService.getUser(currentUserId);
          comment.setUser(user);
        if(commentId != null){
            Comment parentComment = commentService.getComment(commentId);
            comment.setParentComment(parentComment);
        }
        commentService.saveComment(comment);
        return "redirect:/public/cards/readMore?id="+id;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping(value = {"/private/cards/readMore/{id}/editComment/{commentId}"})
    public String editComment(@PathVariable UUID id, @PathVariable UUID commentId, Comment comment){
        Comment commentFromDb = commentService.getComment(commentId);
        commentFromDb.setCommentText(comment.getCommentText());
        commentService.saveComment(commentFromDb);

        return "redirect:/public/cards/readMore?id=" + id;
    }

}

