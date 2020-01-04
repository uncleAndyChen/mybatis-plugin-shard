package biz.impl;

import biz.facade.IApiAdapterService;
import biz.service.EduStudentService;
import biz.service.FinMajorTuitionGradeService;
import biz.service.SysDeptService;
import common.dal.aspect.shard.ShardView;
import common.model.ModelHelper;
import common.model.request.BaseRequest;
import common.model.response.ApiResponse;
import common.model.response.ResponseCodeEnum;

public class ApiAdapterServiceImpl implements IApiAdapterService {
    @Override
    public ApiResponse getApiResponse(ShardView shardView, BaseRequest baseRequest) {
        return getApiResponseFactory(baseRequest);
    }

    private ApiResponse getApiResponseFactory(BaseRequest baseRequest) {
        if (baseRequest.getMethod() == null) {
            return ModelHelper.getApiResponseByResponseCodeEnum(ResponseCodeEnum.noSuchMethodException);
        }

        switch (baseRequest.getMethod()) {
            case "getEduStudentByIdNumber":
                return EduStudentService.getEduStudentByIdNumber(baseRequest);
            case "getEduStudentByIdNumberOrPhone":
                return EduStudentService.getEduStudentByIdNumberOrPhone(baseRequest);
            case "getAllActivityStudentSearchResponse":
                return EduStudentService.getAllActivityStudentSearchResponse();
            case "getAllActivityStudent":
                return EduStudentService.getAllActivityStudent();
            case "getFinMajorTuitionGradeList":
                return FinMajorTuitionGradeService.getFinMajorTuitionGradeList();
            case "getSysDeptList":
                return SysDeptService.getSysDeptList();
            default:
                return ModelHelper.getApiResponseByResponseCodeEnum(ResponseCodeEnum.noSuchMethodException);
        }
    }
}
