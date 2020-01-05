package biz.service.impl;

import biz.service.dal.FinMajorTuitionGradeDalService;
import biz.service.facade.IFinMajorTuitionGradeService;
import common.model.request.BaseRequest;
import common.model.response.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public class FinMajorTuitionGradeServiceImpl implements IFinMajorTuitionGradeService {
    @Override
    public ApiResponse getFinMajorTuitionGradeList() {
        return new ApiResponse<>(FinMajorTuitionGradeDalService.getFinMajorTuitionGradeList());
    }

    @Override
    public ApiResponse getFinMajorTuitionGradeByPrimaryKey(BaseRequest baseRequest) {
        int id = baseRequest.getJsonNodeParameter().get("id").asInt();
        return new ApiResponse<>(FinMajorTuitionGradeDalService.getFinMajorTuitionGradeByPrimaryKey(id));
    }
}
