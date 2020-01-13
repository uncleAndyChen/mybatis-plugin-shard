package biz.facade.impl;

import biz.facade.facade.IApiAdapterService;
import biz.model.view.UserShardView;
import biz.service.UserService;
import biz.service.facade.IBizTradeService;
import biz.service.facade.IEduStudentService;
import biz.service.facade.IFinMajorTuitionGradeService;
import biz.service.SysDeptService;
import common.model.ModelHelper;
import common.model.request.BaseRequest;
import common.model.response.ApiResponse;
import common.model.response.ResponseCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class ApiAdapterServiceImpl implements IApiAdapterService {
    @Autowired
    IFinMajorTuitionGradeService finMajorTuitionGradeService;
    @Autowired
    IEduStudentService eduStudentService;
    @Autowired
    IBizTradeService bizTradeService;

    @Override
    public ApiResponse getApiResponse(BaseRequest baseRequest, HttpServletRequest request) {
        try {
            return getApiResponseFactory(baseRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return ModelHelper.getApiResponseByResponseCodeEnum(ResponseCodeEnum.unknownException, e);
        }
    }

    private ApiResponse getApiResponseFactory(BaseRequest baseRequest) {
        if (baseRequest.getMethod() == null) {
            return ModelHelper.getApiResponseByResponseCodeEnum(ResponseCodeEnum.noSuchMethodException);
        }

        switch (baseRequest.getMethod()) {
            // 分表，后缀为 2
            case "getAllActivityStudentSearchResponse":
                baseRequest.setShardKeySchema("student");
                baseRequest.setShardKeyTableNumber(2);
                break;
            // 分表，后缀为 3
            case "getAllActivityStudent":
                baseRequest.setShardKeySchema("student");
                baseRequest.setShardKeyTableNumber(3);
                break;
        }

        switch (baseRequest.getMethod()) {
            case "getEduStudentByIdNumber":
                return eduStudentService.getEduStudentByIdNumber(baseRequest);
            case "getEduStudentByIdNumberOrPhone":
                return eduStudentService.getEduStudentByIdNumberOrPhone(baseRequest);
            case "getAllActivityStudentSearchResponse":
                return eduStudentService.getAllActivityStudentSearchResponse(baseRequest);
            case "getAllActivityStudent":
                return eduStudentService.getAllActivityStudent(baseRequest);
            case "getFinMajorTuitionGradeList":
                return finMajorTuitionGradeService.getFinMajorTuitionGradeList();
            case "getFinMajorTuitionGradeByPrimaryKey":
                return finMajorTuitionGradeService.getFinMajorTuitionGradeByPrimaryKey(baseRequest);
            case "getSysDeptList":
                return SysDeptService.getSysDeptList();
            case "getBizTradeByBizId":
                return getBizTrade(baseRequest);
            default:
                return ModelHelper.getApiResponseByResponseCodeEnum(ResponseCodeEnum.noSuchMethodException);
        }
    }

    private ApiResponse getBizTrade(BaseRequest baseRequest) {
        ApiResponse checkUserResult = UserService.getCheckUserResult(baseRequest);

        if (checkUserResult != null) {
            return checkUserResult;
        }

        // 获取用户分库分表信息
        UserShardView userShardView = UserService
                .getShardKeyTableNumberByBizId(baseRequest.getJsonNodeParameter().get("bizId").asInt());

        baseRequest.setShardKeySchema(userShardView.getShardKeySchema());
        baseRequest.setShardKeyTableNumber(userShardView.getShardKeyTableNumber());

        return bizTradeService.getBizTradeByBizId(baseRequest);
    }
}
