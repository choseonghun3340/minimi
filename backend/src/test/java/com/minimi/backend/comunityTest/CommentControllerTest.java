//package com.minimi.backend.comunityTest;
//
//import com.google.gson.Gson;
//import com.minimi.backend.community.comment.controller.CommentController;
//import com.minimi.backend.community.comment.domain.CommentDTO;
//import com.minimi.backend.community.comment.service.CommentService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//import java.util.List;
//
//import static com.minimi.backend.ApiDocumentUtils.getRequestPreProcessor;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
//import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
//import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
//import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
//import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(CommentController.class)
//@MockBean(JpaMetamodelMappingContext.class)
//@AutoConfigureRestDocs
//public class CommentControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private Gson gson;
//    @MockBean
//    private CommentService commentService;
//
//    @Test
//    public void postComment() throws Exception {
//        CommentDTO.request request = new CommentDTO.request(
//                1L, "?????????", "????????? ??????","??????");
//        String content = gson.toJson(request);
//        ResultActions actions = mockMvc.perform(
//                post("/comment")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(content));
//        actions.andExpect(status().isCreated())
//                .andDo(document(
//                        "post-comment",
//                        getRequestPreProcessor(),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("contentsId").description("????????? ?????????"),
//                                        fieldWithPath("username").description("?????? ?????????"),
//                                        fieldWithPath("userProfile").description("????????? ??????"),
//                                        fieldWithPath("content").description("?????? ??????")
//                                ))
//                ));
//    }
//
//    @Test
//    public void patchComment() throws Exception {
//        CommentDTO patch = new CommentDTO("?????? ??????");
//        String content = gson.toJson(patch);
//        ResultActions actions = mockMvc.perform(
//                patch("/comment/{commentId}", 1L)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(content)
//        );
//        actions.andExpect(status().isResetContent())
//                .andDo(document(
//                        "patch-comment",
//                        getRequestPreProcessor(),
//                        pathParameters(
//                                parameterWithName("commentId").description("?????? ?????????")
//                        ),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("content").description("?????? ?????? ")
//                                ))
//                ));
//    }
//
//    @Test
//    public void deleteComment() throws Exception {
//        ResultActions actions = mockMvc.perform(
//                delete("/comment/{commentId}", 1L)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//        );
//        actions.andExpect(status().isNoContent())
//                .andDo(document(
//                        "delete-comment",
//                        getRequestPreProcessor(),
//                        pathParameters(
//                                parameterWithName("commentId").description("?????? ?????????")
//                        )));
//    }
//
//}
