package shard.boot.controller;

import biz.facade.facade.IApiAdapterService;
import common.model.request.BaseRequest;
import common.model.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

        return apiAdapterService.getApiResponse(baseRequest, request);
    }
}
