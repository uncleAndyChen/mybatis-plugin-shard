package common.model;

import common.lib.LogHelper;
import common.model.response.ApiResponse;
import common.model.response.BaseResponse;
import common.model.response.ResponseCodeEnum;

public class ModelHelper<T> {
    public static BaseResponse getBaseResponseByResponseCodeEnum(ResponseCodeEnum responseCodeEnum) {
        return getBaseResponseByResponseCodeEnum(responseCodeEnum, null);
    }

    public static BaseResponse getBaseResponseByResponseCodeEnumAndMessage(ResponseCodeEnum responseCodeEnum, String message) {
        BaseResponse baseResponse = getBaseResponseByResponseCodeEnum(responseCodeEnum, null);
        baseResponse.setMessage(baseResponse.getMessage() + message);

        return baseResponse;
    }

    public static BaseResponse getBaseResponseByResponseCodeEnum(ResponseCodeEnum responseCodeEnum, Exception e) {
        return getApiResponseByResponseCodeEnum(responseCodeEnum, e);
    }

    public static ApiResponse getApiResponseByResponseCodeEnum(ResponseCodeEnum responseCodeEnum) {
        return getApiResponseByResponseCodeEnum(responseCodeEnum, null);
    }

    public static ApiResponse getApiResponseByResponseCodeEnumAndMessage(ResponseCodeEnum responseCodeEnum, String message) {
        return getApiResponseByResponseCodeEnumAndMessage(responseCodeEnum, message, false);
    }

    public static ApiResponse getApiResponseByResponseCodeEnumAndMessageReplace(ResponseCodeEnum responseCodeEnum, String message) {
        ApiResponse apiResponse = getApiResponseByResponseCodeEnum(responseCodeEnum, null);
        apiResponse.setMessage(String.format(apiResponse.getMessage(), message));
        return apiResponse;
    }

    public static ApiResponse getApiResponseByResponseCodeEnumAndMessage(ResponseCodeEnum responseCodeEnum, String message, boolean  isAddMessageToHead) {
        ApiResponse apiResponse = getApiResponseByResponseCodeEnum(responseCodeEnum, null);

        if (isAddMessageToHead) {
            apiResponse.setMessage(message + apiResponse.getMessage());
        } else {
            apiResponse.setMessage(apiResponse.getMessage() + message);
        }

        return apiResponse;
    }

    public static ApiResponse getApiResponseByResponseCodeEnum(ResponseCodeEnum responseCodeEnum, Exception e) {
        ApiResponse apiResponse = new ApiResponse();
        setApiResponseByResponseCodeEnum(apiResponse, responseCodeEnum, e);
        return apiResponse;
    }

    public ApiResponse getApiResponseByResponseCodeEnumAndResponseBody(T object, ResponseCodeEnum responseCodeEnum, Exception e) {
        ApiResponse apiResponse = new ApiResponse<>(object);
        setApiResponseByResponseCodeEnum(apiResponse, responseCodeEnum, e);
        return apiResponse;
    }

    public static void setApiResponseByResponseCodeEnum(ApiResponse apiResponse, ResponseCodeEnum responseCodeEnum, Exception e) {
        apiResponse.setCode(responseCodeEnum.getIndex());
        apiResponse.setMessage(responseCodeEnum.getDesc());

        if (e != null) {
            apiResponse.setErrMessage(LogHelper.getExceptionMessage(e));
        }
    }
}
