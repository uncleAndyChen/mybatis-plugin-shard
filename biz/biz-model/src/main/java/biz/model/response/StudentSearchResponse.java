package biz.model.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class StudentSearchResponse {
    private Integer id;
    private String stuName;
    private String stuUniqueNum;
    private String idNumber; // 身份证号码
    private String phone;
    private String address;
}
