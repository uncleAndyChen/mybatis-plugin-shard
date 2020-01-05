package biz.service.facade;

import common.shard.TargetDataSource;
import common.model.request.BaseRequest;
import common.model.response.ApiResponse;

public interface IEduStudentService {
    @TargetDataSource(schemaKey = "student")
    ApiResponse getEduStudentByIdNumber(BaseRequest baseRequest);
    ApiResponse getEduStudentByIdNumberOrPhone(BaseRequest baseRequest);
    ApiResponse getAllActivityStudentSearchResponse(BaseRequest baseRequest);
    ApiResponse getAllActivityStudent(BaseRequest baseRequest);
}
