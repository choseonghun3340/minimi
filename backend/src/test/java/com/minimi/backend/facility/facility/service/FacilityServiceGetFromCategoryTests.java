package com.minimi.backend.facility.facility.service;


import com.minimi.backend.facility.dto.responsedto.ResponseFacilityDto;
import com.minimi.backend.facility.facility.domain.FacilityRepository;
import com.minimi.backend.facility.facility.domain.FacilityStatus;
import com.minimi.backend.facility.facility.mapper.FacilityMapper;
import com.minimi.backend.facility.facility.service.listener.FacaMappingGetListenerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayName("getFacility form category test")
public class FacilityServiceGetFromCategoryTests {

    @Mock
    private FacilityRepository facilityRepository;

    @Mock
    private FacaMappingGetListenerImpl facilityCategoryListGetListener;

    @Mock
    private FacilityMapper facilityMapper;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private FacilityServiceImpl facilityService;

    private List<ResponseFacilityDto.facilityPageFromCategory> facilityList;
    private String categoryCode;
    private int page;

    @BeforeEach
    public void setup() {
        categoryCode = "220901";
        page = 1;
        facilityList = new ArrayList<>(Arrays.asList(
                new ResponseFacilityDto.facilityPageFromCategory(
                        1L,"???????????????", "???????????????",
                        "??????????????? ?????????",3,"35.123456, 119.123456",
                        new ArrayList<>(Arrays.asList("??????")), FacilityStatus.ACTIVE),
                new ResponseFacilityDto.facilityPageFromCategory(
                        2L,"???????????????","???????????????",
                        "??????????????? ?????????",2,"35.123456, 120.123456",
                        new ArrayList<>(Arrays.asList("??????", "PT")),FacilityStatus.INACTIVE),
                new ResponseFacilityDto.facilityPageFromCategory(
                        3L,"??????????????????", "???????????????",
                        "??????????????? ?????????",5,"35.123456, 119.123456",
                        new ArrayList<>(Arrays.asList("??????", "??????")),FacilityStatus.ACTIVE)
        ));
    }

    @Nested
    @DisplayName("success getSliceFacilityCategory case")
    class getSliceFacilityCategorySuccess {

        @Test
        @DisplayName("success getSliceFacilityCategory test 1 -> getFacilitySlice")
        public void successGetTitleCategoryTest() throws Exception {
            Slice<ResponseFacilityDto.facilityPageFromCategory> categorySlice = new SliceImpl<>(facilityList, PageRequest.of(page-1, 5),false);
            given(facilityCategoryListGetListener.getFacilityFromCategory(Mockito.anyString(),Mockito.anyInt())).willReturn(categorySlice);

            Slice<ResponseFacilityDto.facilityPageFromCategory> result = facilityService.getCategoryFacility(categoryCode,page);

            then(facilityCategoryListGetListener).should(times(1)).getFacilityFromCategory(anyString(), anyInt());
            assertThat(result, equalTo(categorySlice));
        }
    }

    @Nested
    @DisplayName("fail getSliceFacilityCategory case")
    class getSliceFacilityCategoryFail {
        @Test
        @DisplayName("fail getSliceFacilityCategory test 1 -> null")
        public void categoryFacilityListener() throws Exception {
            given(facilityCategoryListGetListener.getFacilityFromCategory(Mockito.anyString(),Mockito.anyInt()))
                    .willReturn(null);

            Slice<ResponseFacilityDto.facilityPageFromCategory> result = facilityService.getCategoryFacility(categoryCode, page);

            then(facilityCategoryListGetListener).should(times(1)).getFacilityFromCategory(anyString(), anyInt());
            assertThat(result, is(nullValue()));
        }
    }
}