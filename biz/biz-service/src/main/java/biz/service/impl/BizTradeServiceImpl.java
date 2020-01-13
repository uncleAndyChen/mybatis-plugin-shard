package biz.service.impl;

import biz.model.entity.BizTrade;
import biz.service.dal.BizTradeDalService;
import biz.service.facade.IBizTradeService;
import common.model.ModelHelper;
import common.model.request.BaseRequest;
import common.model.response.ApiResponse;
import common.model.response.ResponseCodeEnum;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BizTradeServiceImpl implements IBizTradeService {
    @Override
    public ApiResponse getBizTradeByBizId(BaseRequest baseRequest) {
        int bizId = baseRequest.getJsonNodeParameter().get("bizId").asInt();
        List<BizTrade> bizTradeList = BizTradeDalService.getBizTradeByBizId(bizId);
        return new ApiResponse<>(bizTradeList);
    }
}
