package biz.service;

import biz.model.request.StudentSearchRequest;
import biz.service.dal.EduStudentDalService;
import common.lib.JsonHelper;
import common.model.request.BaseRequest;
import common.model.response.ApiResponse;

public class EduStudentService {
    public static ApiResponse getEduStudentByIdNumber(BaseRequest baseRequest) {
        String idNumber = baseRequest.getJsonNodeParameter().get("idNumber").asText();
        return new ApiResponse<>(EduStudentDalService.getEduStudentByIdNumber(idNumber));
    }

    public static ApiResponse getEduStudentByIdNumberOrPhone(BaseRequest baseRequest) {
        StudentSearchRequest studentSearchRequest = JsonHelper.jsonStringToPojo(baseRequest.getJsonStringParameter(), StudentSearchRequest.class);
        return new ApiResponse<>(EduStudentDalService.getEduStudentByIdNumberOrPhone(studentSearchRequest));
    }

    public static ApiResponse getAllActivityStudentSearchResponse() {
        return new ApiResponse<>(EduStudentDalService.getAllActivityStudentSearchResponse());
    }

    public static ApiResponse getAllActivityStudent() {
        return new ApiResponse<>(EduStudentDalService.getAllActivityStudent());
    }
}
