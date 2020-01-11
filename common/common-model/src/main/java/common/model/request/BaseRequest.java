package common.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import common.shard.ShardRequest;
import common.lib.JsonHelper;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 继承自 ShardRequest 是为了动态切换数据源
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseRequest extends ShardRequest {
    private String method; // 调用的业务方法名
    private String jsonStringParameter; // 传递完整的应用级参数信息，json 格式
    @JsonIgnore
    private JsonNode jsonNodeParameter; // 对 apiParas 处理之后得到，方便程序加工处理。

    public BaseRequest(String method) {
        this.method = method;
    }

    /**
     * 不能删除 setJsonStringParameter
     */
    public void setJsonStringParameter(String jsonStringParameter) {
        if (jsonStringParameter != null && jsonStringParameter.length() > 0) {
            //this.jsonStringParameter = CommonFunction.urlAndBase64Decode(jsonStringParameter);
            this.jsonStringParameter = jsonStringParameter;
            this.setJsonNodeParameter(JsonHelper.jsonStringToJsonNode(this.jsonStringParameter));
        } else {
            this.jsonStringParameter = jsonStringParameter;
        }
    }
}