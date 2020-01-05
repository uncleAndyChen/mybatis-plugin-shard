package biz.facade.facade;

import common.dal.aspect.shard.ShardView;
import common.model.request.BaseRequest;
import common.model.response.ApiResponse;

import javax.servlet.http.HttpServletRequest;

public interface IApiAdapterService {
    ApiResponse getApiResponse(BaseRequest baseRequest, HttpServletRequest request);
}
