package com.minimi.backend.member.controller;

import com.minimi.backend.member.domain.Member;
import com.minimi.backend.member.domain.MemberDTO;
import com.minimi.backend.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @Operation(summary="회원가입", description="이거도 바꾸는 법을 모르겠음ㅠ\n" +

            "/join\n" +
            "{\n" +
            "  \"email\" : \"이메일@gmail.com\",\n" +
            "  \"username\" : \"유저이름\",\n" +
            "  \"password\" : \"비밀번호\"\n" +
            "}\n" +
            "/login\n" +
            "{\n" +
            "  \"email\" : \"이메일@gmail.com\",\n" +
            "  \"password\" : \"비밀번호\"\n" +
            "}")
    @PostMapping("/join")
    public ResponseEntity join(@Valid @RequestBody Member member) {

        memberService.createMember(member);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/patchmember/{memberId}")
    private ResponseEntity patchMember(@PathVariable Long memberId, @RequestBody MemberDTO.patch member){
        memberService.updateMember(member, memberId);
        return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
    }
}
