package com.minimi.backend.community.likes.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikesDTO {
    @ApiModelProperty(value="게시글번호", example="게시글번호", required=true)
    private Long contentsId;
    @ApiModelProperty(value="유저이름", example="유저이름", required=true)
    private String username;

}
