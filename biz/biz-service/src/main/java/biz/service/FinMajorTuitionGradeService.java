package biz.service;

import biz.service.dal.FinMajorTuitionGradeDalService;
import common.model.response.ApiResponse;

public class FinMajorTuitionGradeService {
    public static ApiResponse getFinMajorTuitionGradeList() {
        return new ApiResponse<>(FinMajorTuitionGradeDalService.getFinMajorTuitionGradeList());
    }
}
