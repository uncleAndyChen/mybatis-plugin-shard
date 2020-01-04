package biz.model.entity;

import java.math.BigDecimal;

public class FinMajorTuitionGrade {
    private Integer id;

    private Integer majorId;

    private Integer payYear;

    private BigDecimal tuition;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getPayYear() {
        return payYear;
    }

    public void setPayYear(Integer payYear) {
        this.payYear = payYear;
    }

    public BigDecimal getTuition() {
        return tuition;
    }

    public void setTuition(BigDecimal tuition) {
        this.tuition = tuition;
    }
}