package common.model.response;

/**
 * 返回码枚举
 */
public enum ResponseCodeEnum {
    failed(0, "失败"),
    success(1, "成功"),
    unknownException(-20, "系统异常"),
    noSuchMethodException(-30, "调用了不存在的方法，请联系管理员。"),
    noNecessaryParameter(-40, "缺少必要参数【%s】"),
    parameterIntError(-50, "参数【%s】请传大于0的整数"),
    ;

    private int index;
    private String desc;

    ResponseCodeEnum(int index, String desc) {
        this.index = index;
        this.desc = desc;
    }

    public final int getIndex() {
        return index;
    }

    public final void setIndex(int index) {
        this.index = index;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static ResponseCodeEnum getEnumByIndex(int index) {
        for (ResponseCodeEnum item : ResponseCodeEnum.values()) {
            if (index == item.index) {
                return item;
            }
        }

        return null;
    }
}
