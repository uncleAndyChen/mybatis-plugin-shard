package biz.service.facade;

import common.dal.shard.TargetDataSource;
import common.model.request.BaseRequest;
import common.model.response.ApiResponse;

@TargetDataSource(schemaKey = "finance")
public interface IFinMajorTuitionGradeService {
    ApiResponse getFinMajorTuitionGradeList();
    ApiResponse getFinMajorTuitionGradeByPrimaryKey(BaseRequest baseRequest);
}
