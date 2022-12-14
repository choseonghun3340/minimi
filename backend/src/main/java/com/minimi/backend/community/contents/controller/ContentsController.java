package com.minimi.backend.community.contents.controller;

import com.minimi.backend.auth.userdetails.MemberDetailsService;
import com.minimi.backend.community.contents.domain.Contents;
import com.minimi.backend.community.contents.domain.ContentsDTO;
import com.minimi.backend.community.contents.service.ContentsService;
import com.minimi.backend.exception.BusinessLogicException;
import com.minimi.backend.exception.ExceptionCode;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contents")
public class ContentsController {
    private final ContentsService contentsService;
    @Operation(summary="게시글 생성", description="리퀘스트랑 리스폰스 example이 잘못 들어가있는데 바꾸는 법을 못찾겠음ㅜㅜ \n" +
            "request body\n" +
            "/contents\n" +
            "{\n" +
            "    \"title\":\"제목\",\n" +
            "    \"contents\":\"내용\",\n" +
            "    \"username\": \"유저이름\"\n" +
            "}")
    @PostMapping("")
    public ResponseEntity<ContentsDTO.response> postContents(@Valid @RequestBody ContentsDTO contentsDTO){
        contentsService.crateContents(contentsDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Operation(summary="게시글 수정")
    @PatchMapping("/{contentsId}")
    public ResponseEntity patchContents(@PathVariable Long contentsId,
                                        @RequestBody ContentsDTO.patch contentsPatch){
        contentsService.patchContents(contentsPatch, contentsId);
        return new ResponseEntity(HttpStatus.RESET_CONTENT);
    }
    @Operation(summary="게시글 조회", description="이거도 리스폰스 잘못 들어가있음ㅠ \n" +
            "{\n" +
            "    \"@id\": 1,\n" +
            "    \"contentsId\": 1,\n" +
            "    \"title\": \"제목\",\n" +
            "    \"contents\": \"내용\",\n" +
            "    \"username\": \"유저이름\",\n" +
            "    \"commentNumber\": 0,\n" +
            "    \"createdAt\": [\n" +
            "        2022,11,30,11,49,12,130753000\n" +
            "    ],\n" +
            "    \"views\": 0,\n" +
            "    \"likes\": 0,\n" +
            "    \"commentList\": []\n" +
            "}")
    @GetMapping("/{contentsId}")
    public ResponseEntity<ContentsDTO.response> getContents(@PathVariable Long contentsId, HttpServletRequest request, HttpServletResponse response){
        contentsService.viewCountUp(contentsId,request,response);
        Contents contents = contentsService.findContents(contentsId);

        return new ResponseEntity(contents,HttpStatus.CREATED);
    }

//    @GetMapping("/test/{contentsId}")
//    public ResponseEntity<ContentsDTO.response> gettestContents(@PathVariable Long contentsId){
//        return new ResponseEntity(contentsService.getContents(contentsId),HttpStatus.CREATED);
//    }
    @Operation(summary="게시글 목록조회", description="")
    @GetMapping("")
    public ResponseEntity<Slice<Contents>> getContentsList(@RequestParam int page, Pageable pageable){
        Slice<Contents> contentsSlice = contentsService.findContentsList(page-1, 10);
        List<Contents> contents = contentsSlice.getContent();
        return new ResponseEntity<>(contentsSlice,HttpStatus.OK);
    }
    @Operation(summary="게시글 삭제", description="")
    @DeleteMapping("/{contentsId}")
    public ResponseEntity deleteContents(@PathVariable Long contentsId){
        contentsService.deleteContents(contentsId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
