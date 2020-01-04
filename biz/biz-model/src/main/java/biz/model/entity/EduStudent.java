package biz.model.entity;

import java.util.Date;

public class EduStudent {
    private Integer id;

    private Integer stuUniqueNum;

    private String stuUniqueNumStr;

    private String stuName;

    private Integer sex;

    private String idNumber;

    private String phone;

    private String parentsPhone;

    private String address;

    private Integer dormitoryType;

    private Integer gradeId;

    private Integer majorId;

    private Integer registerType;

    private Integer status;

    private String remark;

    private String operateIp;

    private Integer operator;

    private Date jobModifiedAt;

    private Date createdAt;

    private Date modifiedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStuUniqueNum() {
        return stuUniqueNum;
    }

    public void setStuUniqueNum(Integer stuUniqueNum) {
        this.stuUniqueNum = stuUniqueNum;
    }

    public String getStuUniqueNumStr() {
        return stuUniqueNumStr;
    }

    public void setStuUniqueNumStr(String stuUniqueNumStr) {
        this.stuUniqueNumStr = stuUniqueNumStr == null ? null : stuUniqueNumStr.trim();
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName == null ? null : stuName.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber == null ? null : idNumber.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getParentsPhone() {
        return parentsPhone;
    }

    public void setParentsPhone(String parentsPhone) {
        this.parentsPhone = parentsPhone == null ? null : parentsPhone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getDormitoryType() {
        return dormitoryType;
    }

    public void setDormitoryType(Integer dormitoryType) {
        this.dormitoryType = dormitoryType;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getRegisterType() {
        return registerType;
    }

    public void setRegisterType(Integer registerType) {
        this.registerType = registerType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getOperateIp() {
        return operateIp;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp == null ? null : operateIp.trim();
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public Date getJobModifiedAt() {
        return jobModifiedAt;
    }

    public void setJobModifiedAt(Date jobModifiedAt) {
        this.jobModifiedAt = jobModifiedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}