package biz.service.impl;

import biz.model.request.StudentSearchRequest;
import biz.service.dal.EduStudentDalService;
import biz.service.facade.IEduStudentService;
import common.dal.aspect.source.TargetDataSource;
import common.lib.JsonHelper;
import common.model.request.BaseRequest;
import common.model.response.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public class EduStudentServiceImpl implements IEduStudentService {
    @Override
    public ApiResponse getEduStudentByIdNumber(BaseRequest baseRequest) {
        String idNumber = baseRequest.getJsonNodeParameter().get("idNumber").asText();
        return new ApiResponse<>(EduStudentDalService.getEduStudentByIdNumber(idNumber));
    }

    @Override
    @TargetDataSource(schemaKey = "student")
    public ApiResponse getEduStudentByIdNumberOrPhone(BaseRequest baseRequest) {
        StudentSearchRequest studentSearchRequest = JsonHelper.jsonStringToPojo(baseRequest.getJsonStringParameter(), StudentSearchRequest.class);
        return new ApiResponse<>(EduStudentDalService.getEduStudentByIdNumberOrPhone(studentSearchRequest));
    }

    @Override
    public ApiResponse getAllActivityStudentSearchResponse(BaseRequest baseRequest) {
        return new ApiResponse<>(EduStudentDalService.getAllActivityStudentSearchResponse());
    }

    @Override
    public ApiResponse getAllActivityStudent(BaseRequest baseRequest) {
        return new ApiResponse<>(EduStudentDalService.getAllActivityStudent());
    }
}
