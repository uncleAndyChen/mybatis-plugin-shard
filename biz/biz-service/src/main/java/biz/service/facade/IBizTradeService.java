package biz.service.facade;

import common.model.request.BaseRequest;
import common.model.response.ApiResponse;

public interface IBizTradeService {
    ApiResponse getBizTradeByBizId(BaseRequest baseRequest);
}
