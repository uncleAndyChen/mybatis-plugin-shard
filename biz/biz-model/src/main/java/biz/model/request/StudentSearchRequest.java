package biz.model.request;

import lombok.Data;

@Data
public class StudentSearchRequest {
    private String idNumber; // 身份证号码
    private String phone; // 学生联系电话
}
