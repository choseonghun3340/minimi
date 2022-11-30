package com.minimi.backend.community.likes.controller;

import com.minimi.backend.community.likes.domain.LikesDTO;
import com.minimi.backend.community.likes.service.LikesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikesController {

    private final LikesService likesService;

    //post like
    @Operation(summary="좋아요 생성", description="")
    @PostMapping("")
    private ResponseEntity postLikes(@RequestBody LikesDTO likesDTO) {
        likesService.createLikes(likesDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    //delete like
    @Operation(summary="좋아요 삭제", description="")
        @DeleteMapping("/{likesId}")
        private ResponseEntity deleteLikes(@PathVariable Long likesId){
            likesService.deleteLikes(likesId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
}
