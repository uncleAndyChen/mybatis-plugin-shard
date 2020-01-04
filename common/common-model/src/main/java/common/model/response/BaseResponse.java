package common.model.response;

import lombok.Data;
import java.io.Serializable;

@Data
public class BaseResponse implements Serializable {
    private int code; // 调用结果状态码，定义在枚举 ResponseCodeEnum 中，是数字，如成功为1，失败为0，其他错误为负数。
    private String message; // 调用结果必要的中文信息
    private String errMessage; // 调用异常时，返回异常信息，在开发时联调非常有用。如果有必要，这个信息也可以直接抛给客户，有利于客户遇到问题了向我们反馈错误信息

    /**
     * 重要：初始化默认值，不能删除
     */
    public BaseResponse() {
        this.code = ResponseCodeEnum.success.getIndex();
        this.message = ResponseCodeEnum.success.getDesc();
        this.errMessage = "";
    }
}
