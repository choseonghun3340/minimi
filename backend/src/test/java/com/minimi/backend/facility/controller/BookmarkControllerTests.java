//package com.minimi.backend.facility.controller;
//
//
//import com.google.gson.Gson;
//import com.minimi.backend.facility.bookmark.BookmarkController;
//import com.minimi.backend.facility.bookmark.BookmarkDto;
//import com.minimi.backend.facility.bookmark.BookmarkService;
//import com.minimi.backend.facility.facility.domain.FacilityDto;
//import com.minimi.backend.facility.facility.domain.FacilityStatus;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.minimi.backend.ApiDocumentUtils.getRequestPreProcessor;
//import static com.minimi.backend.ApiDocumentUtils.getResponsePreProcessor;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.restdocs.request.RequestDocumentation.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@WebMvcTest(BookmarkController.class)
//@MockBean(JpaMetamodelMappingContext.class)
//@AutoConfigureRestDocs
//public class BookmarkControllerTests {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private Gson gson;
//
//    @MockBean
//    private BookmarkService bookmarkService;
//
//    @Test
//    public void postBookmark() throws Exception{
//        BookmarkDto.request bookmarkReq = new BookmarkDto.request("???????????????", 1L);
//        String content = gson.toJson(bookmarkReq);
//        ResultActions actions = mockMvc.perform(
//                post("/bookmark")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(content)
//        );
//        actions.andExpect(status().isCreated())
//                .andDo(document(
//                        "post-bookmark",
//                        getRequestPreProcessor(),
//                        requestFields(
//                                List.of(
//                                        fieldWithPath("username").description("?????? ?????????"),
//                                        fieldWithPath("facilityId").description("?????? ?????? ID")
//                                ))
//                ));
//    }
//    @Test
//    public void deleteBookmark() throws Exception {
//        Long facilityId = 1L;
//        ResultActions actions = mockMvc.perform(
//                delete("/bookmark/{facilityId}",facilityId)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//        );
//        actions.andExpect(status().isNoContent())
//                .andDo(document(
//                        "delete-bookmark",
//                        getRequestPreProcessor(),
//                        pathParameters(
//                                parameterWithName("facilityId").description("???????????? ?????? ID")
//                        )));
//    }
//
//    @Test
//    public void getMyFacilitys() throws Exception {
//        String username = "MiniMiUser";
//        List<FacilityDto.responseMyFacility> facilityList = new ArrayList<>();
//        FacilityDto.responseMyFacility facility = new FacilityDto.responseMyFacility(
//                1L,"?????????GYM","34.12345, 199.12345", FacilityStatus.ACTIVE);
//        FacilityDto.responseMyFacility facility1 = new FacilityDto.responseMyFacility(
//                2L,"?????????????????????", "35.12345, 199.12345", FacilityStatus.ACTIVE);
//        facilityList.add(facility);
//        facilityList.add(facility1);
//        BookmarkDto.response response = new BookmarkDto.response(username,facilityList);
//        given(bookmarkService.getBookmark(Mockito.anyString())).willReturn(response);
//
//        ResultActions actions = mockMvc.perform(
//                get("/bookmark/{username}", username)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//        );
//        actions.andExpect(status().isOk())
//                .andExpect(jsonPath(".username").value(response.getUsername()))
//                .andExpect(jsonPath(".facilityList[0].facilityName").value(response.getFacilityList().get(0).getFacilityName()))
//                .andExpect(jsonPath(".facilityList[1].facilityName").value(response.getFacilityList().get(1).getFacilityName()))
//                .andExpect(jsonPath(".facilityList[0].facilityStatus").value(String.valueOf(response.getFacilityList().get(0).getFacilityStatus())))
//                .andExpect(jsonPath(".facilityList[1].facilityStatus").value(String.valueOf(response.getFacilityList().get(1).getFacilityStatus())))
//                .andExpect(jsonPath(".facilityList[0].location").value(response.getFacilityList().get(0).getLocation()))
//                .andExpect(jsonPath(".facilityList[1].location").value(response.getFacilityList().get(1).getLocation()))
//                .andDo(document(
//                        "get-bookmark",
//                        getResponsePreProcessor(),
//                        pathParameters(
//                                parameterWithName("username").description("?????? ?????????")
//                        ),
//                        responseFields(
//                                List.of(
//                                        fieldWithPath("username").type(JsonFieldType.STRING).description("?????? ?????????"),
//                                        fieldWithPath("facilityList").type(JsonFieldType.ARRAY).description("??? ???????????? ?????????"),
//                                        fieldWithPath("facilityList[0]").type(JsonFieldType.ARRAY).description("??? ????????????1"),
//                                        fieldWithPath("facilityList[0].facilityId").type(JsonFieldType.NUMBER).description("??? ???????????? ID"),
//                                        fieldWithPath("facilityList[0].facilityName").type(JsonFieldType.STRING).description("??? ???????????? ??????"),
//                                        fieldWithPath("facilityList[0].location").type(JsonFieldType.STRING).description("??? ???????????? ??????"),
//                                        fieldWithPath("facilityList[0].facilityStatus").type(JsonFieldType.STRING).description("??? ???????????? ??????"),
//                                        fieldWithPath("facilityList[1]").type(JsonFieldType.ARRAY).description("??? ????????????2"),
//                                        fieldWithPath("facilityList[1].facilityId").type(JsonFieldType.NUMBER).description("??? ???????????? ID"),
//                                        fieldWithPath("facilityList[1].facilityName").type(JsonFieldType.STRING).description("??? ???????????? ??????"),
//                                        fieldWithPath("facilityList[1].location").type(JsonFieldType.STRING).description("??? ???????????? ??????"),
//                                        fieldWithPath("facilityList[1].facilityStatus").type(JsonFieldType.STRING).description("??? ???????????? ??????")
//                                )
//                        )
//                ));
//    }
//}
