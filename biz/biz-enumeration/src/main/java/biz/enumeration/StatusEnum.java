package biz.enumeration;

/**
 * 状态: 0.锁定 1.激活 2.删除
 */
public enum StatusEnum {
    disabled(0, "锁定/停用"),
    enabled(1, "激活"),
    deleted(2, "删除");

    private int index; // 枚举序号（枚举值）
    private String desc; // 枚举值中文描述

    StatusEnum(int index, String desc) {
        this.index = index;
        this.desc = desc;
    }

    public static StatusEnum getEnumByIndex(int index) {
        for (StatusEnum item : StatusEnum.values()) {
            if (index == item.index) {
                return item;
            }
        }

        return null;
    }

    public static StatusEnum getEnumByDesc(String desc) {
        for (StatusEnum item : StatusEnum.values()) {
            if (item.getDesc().equals(desc)) {
                return item;
            }
        }

        return null;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
