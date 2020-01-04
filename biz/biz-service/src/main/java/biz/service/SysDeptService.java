package biz.service;

import biz.service.dal.SysDeptDalService;
import common.model.response.ApiResponse;

public class SysDeptService {
    public static ApiResponse getSysDeptList() {
        return new ApiResponse<>(SysDeptDalService.getSysDeptList());
    }
}
