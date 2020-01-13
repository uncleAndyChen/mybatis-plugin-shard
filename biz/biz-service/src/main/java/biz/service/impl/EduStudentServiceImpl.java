package biz.service.impl;

import biz.model.request.StudentSearchRequest;
import biz.service.dal.EduStudentDalService;
import biz.service.facade.IEduStudentService;
import common.model.ModelHelper;
import common.model.response.ResponseCodeEnum;
import common.shard.TargetDataSource;
import common.lib.JsonHelper;
import common.model.request.BaseRequest;
import common.model.response.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public class EduStudentServiceImpl implements IEduStudentService {
    @Override
    public ApiResponse getEduStudentByIdNumber(BaseRequest baseRequest) {
        if (baseRequest.getJsonNodeParameter() == null
                || !baseRequest.getJsonNodeParameter().has("idNumber")) {
            return ModelHelper.getApiResponseByResponseCodeEnumAndMessageReplace(ResponseCodeEnum.noNecessaryParameter, "idNumber");
        }

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
