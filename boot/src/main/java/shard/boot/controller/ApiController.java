package shard.boot.controller;

import biz.facade.IApiAdapterService;
import common.dal.aspect.shard.ShardView;
import common.model.request.BaseRequest;
import common.model.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ApiController {
    @Autowired
    IApiAdapterService apiAdapterService;

    @RequestMapping("/getApiResponse")
    public ApiResponse getApiResponse(BaseRequest baseRequest, HttpServletRequest request) {
        if (baseRequest == null) {
            baseRequest = new BaseRequest(request.getRequestURI());
        }

        ShardView shardView = new ShardView();
        shardView.setShardKeySchema("student");
        ApiResponse apiResponse = apiAdapterService.getApiResponse(shardView, baseRequest, request);

        return apiResponse;
    }
}
