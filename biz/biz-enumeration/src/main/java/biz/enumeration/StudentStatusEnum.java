package biz.enumeration;

/**
 * 学生状态
 */
public enum StudentStatusEnum {
    disabled(0, "冻结"),
    enabled(1, "正常"),
    droppedOut(2, "已退学"),
    ;

    private int index; // 枚举序号（枚举值）
    private String desc; // 枚举值中文描述

    StudentStatusEnum(int index, String desc) {
        this.index = index;
        this.desc = desc;
    }

    public static StudentStatusEnum getEnumByIndex(int index) {
        for (StudentStatusEnum item : StudentStatusEnum.values()) {
            if (index == item.index) {
                return item;
            }
        }

        return null;
    }

    public static String getDescByIndex(int index) {
        StudentStatusEnum item = getEnumByIndex(index);
        return item == null ? "" : item.getDesc();
    }

    public static StudentStatusEnum getEnumByDesc(String desc) {
        for (StudentStatusEnum item : StudentStatusEnum.values()) {
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
