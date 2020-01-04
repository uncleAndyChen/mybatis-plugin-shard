package biz.facade;

import common.dal.aspect.shard.ShardView;
import common.model.request.BaseRequest;
import common.model.response.ApiResponse;

import javax.servlet.http.HttpServletRequest;

public interface IApiAdapterService {
    ApiResponse getApiResponse(ShardView shardView, BaseRequest baseRequest, HttpServletRequest request);
}
