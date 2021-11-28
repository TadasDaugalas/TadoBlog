package com.example.tadoblog.controler;

import com.example.tadoblog.data.Card;
import com.example.tadoblog.data.Comment;
import com.example.tadoblog.service.CardService;
import com.example.tadoblog.service.CommentService;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
@SessionAttributes("readMore")
public class CommentController {
    private final CommentService commentService;
    private final CardService cardService;

    public CommentController(CommentService commentService, CardService cardService) {
        this.commentService = commentService;
        this.cardService = cardService;
    }

        @PostMapping(value = {"/cards/readMore/{id}/addComment","/cards/readMore/{id}/addComment/{commentId}"})
    public String addComment(@PathVariable UUID id, @PathVariable(required = false) UUID commentId, Comment comment){
        Card card = cardService.getCard(id);
        comment.setCard(card);
        if(commentId != null){
            Optional<Comment> parentComment = commentService.getComment(commentId);
            if(parentComment.isPresent()){
                comment.setParentComment(parentComment.get());
            }
        }
        commentService.saveComment(comment);
        return "redirect:/cards";
    }

}

