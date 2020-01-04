package common.model.response;

/**
 * 接口的响应视图
 */
public class ApiResponse<T> extends BaseResponse {
    private T responseBody;

    public T getResponseBody() {
        return responseBody;
    }

    public ApiResponse() {
    }

    public ApiResponse(T t) {
        this.responseBody = t;
    }

    public void setResponseBody(T responseBody) {
        this.responseBody = responseBody;
    }
}
