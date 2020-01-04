package biz.facade;

import common.dal.aspect.shard.ShardView;
import common.model.request.BaseRequest;
import common.model.response.ApiResponse;

public interface IApiAdapterService {
    ApiResponse getApiResponse(ShardView shardView, BaseRequest baseRequest);
}
