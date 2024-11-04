package com.cocreate.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/comments")
@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable int id) {
        commentService.deleteComment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateComment(@PathVariable int id, @RequestBody CommentDTO newContent) {
        commentService.updateComment(id, newContent);
        System.out.println("HEEREER");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /*@GetMapping("/")
    public ResponseEntity<List<CommentDTO>> getAllComments() {
        List<CommentDTO> comments
    }*/





}
