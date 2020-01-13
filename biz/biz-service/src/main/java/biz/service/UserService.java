package biz.service;

import biz.model.view.UserShardView;
import common.model.ModelHelper;
import common.model.request.BaseRequest;
import common.model.response.ApiResponse;
import common.model.response.ResponseCodeEnum;

public class UserService {
    /**
     * 注意：这里为了演示，简单的返回用户 bizId 作为分表用的 shardKeyTableNumber，而数据库 key 则假设为 biz
     * 通过用户 bizId 获取用户分表用的 shardKeyTableNumber
     * @param bizId 用户 ID
     * @return shardKeyTableNumber
     */
    public static UserShardView getShardKeyTableNumberByBizId(int bizId) {
        // 获取 shardKeyTableNumber 的代码
        // 可能是从数据库取
        // 可能是从 Session 取
        // 如果是 JWT 机制，那么请求过来就能唯一确定用户信息
        UserShardView userShardView = new UserShardView();

        userShardView.setShardKeySchema("biz");
        userShardView.setShardKeyTableNumber(bizId);

        return userShardView;
    }

    public static ApiResponse getCheckUserResult(BaseRequest baseRequest) {
        if (baseRequest.getJsonNodeParameter() == null
                || !baseRequest.getJsonNodeParameter().has("bizId")) {
            return ModelHelper.getApiResponseByResponseCodeEnumAndMessageReplace(ResponseCodeEnum.noNecessaryParameter, "bizId");
        }

        int bizId = baseRequest.getJsonNodeParameter().get("bizId").asInt();

        if (bizId == 0) {
            return ModelHelper.getApiResponseByResponseCodeEnumAndMessageReplace(ResponseCodeEnum.parameterIntError, "bizId");
        }

        return null;
    }
}
