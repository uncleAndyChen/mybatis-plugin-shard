package biz.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EduStudentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EduStudentExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andStuUniqueNumIsNull() {
            addCriterion("stu_unique_num is null");
            return (Criteria) this;
        }

        public Criteria andStuUniqueNumIsNotNull() {
            addCriterion("stu_unique_num is not null");
            return (Criteria) this;
        }

        public Criteria andStuUniqueNumEqualTo(Integer value) {
            addCriterion("stu_unique_num =", value, "stuUniqueNum");
            return (Criteria) this;
        }

        public Criteria andStuUniqueNumNotEqualTo(Integer value) {
            addCriterion("stu_unique_num <>", value, "stuUniqueNum");
            return (Criteria) this;
        }

        public Criteria andStuUniqueNumGreaterThan(Integer value) {
            addCriterion("stu_unique_num >", value, "stuUniqueNum");
            return (Criteria) this;
        }

        public Criteria andStuUniqueNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("stu_unique_num >=", value, "stuUniqueNum");
            return (Criteria) this;
        }

        public Criteria andStuUniqueNumLessThan(Integer value) {
            addCriterion("stu_unique_num <", value, "stuUniqueNum");
            return (Criteria) this;
        }

        public Criteria andStuUniqueNumLessThanOrEqualTo(Integer value) {
            addCriterion("stu_unique_num <=", value, "stuUniqueNum");
            return (Criteria) this;
        }

        public Criteria andStuUniqueNumIn(List<Integer> values) {
            addCriterion("stu_unique_num in", values, "stuUniqueNum");
            return (Criteria) this;
        }

        public Criteria andStuUniqueNumNotIn(List<Integer> values) {
            addCriterion("stu_unique_num not in", values, "stuUniqueNum");
            return (Criteria) this;
        }

        public Criteria andStuUniqueNumBetween(Integer value1, Integer value2) {
            addCriterion("stu_unique_num between", value1, value2, "stuUniqueNum");
            return (Criteria) this;
        }

        public Criteria andStuUniqueNumNotBetween(Integer value1, Integer value2) {
            addCriterion("stu_unique_num not between", value1, value2, "stuUniqueNum");
            return (Criteria) this;
        }

        public Criteria andStuUniqueNumStrIsNull() {
            addCriterion("stu_unique_num_str is null");
            return (Criteria) this;
        }

        public Criteria andStuUniqueNumStrIsNotNull() {
            addCriterion("stu_unique_num_str is not null");
            return (Criteria) this;
        }

        public Criteria andStuUniqueNumStrEqualTo(String value) {
            addCriterion("stu_unique_num_str =", value, "stuUniqueNumStr");
            return (Criteria) this;
        }

        public Criteria andStuUniqueNumStrNotEqualTo(String value) {
            addCriterion("stu_unique_num_str <>", value, "stuUniqueNumStr");
            return (Criteria) this;
        }

        public Criteria andStuUniqueNumStrGreaterThan(String value) {
            addCriterion("stu_unique_num_str >", value, "stuUniqueNumStr");
            return (Criteria) this;
        }

        public Criteria andStuUniqueNumStrGreaterThanOrEqualTo(String value) {
            addCriterion("stu_unique_num_str >=", value, "stuUniqueNumStr");
            return (Criteria) this;
        }

        public Criteria andStuUniqueNumStrLessThan(String value) {
            addCriterion("stu_unique_num_str <", value, "stuUniqueNumStr");
            return (Criteria) this;
        }

        public Criteria andStuUniqueNumStrLessThanOrEqualTo(String value) {
            addCriterion("stu_unique_num_str <=", value, "stuUniqueNumStr");
            return (Criteria) this;
        }

        public Criteria andStuUniqueNumStrLike(String value) {
            addCriterion("stu_unique_num_str like", value, "stuUniqueNumStr");
            return (Criteria) this;
        }

        public Criteria andStuUniqueNumStrNotLike(String value) {
            addCriterion("stu_unique_num_str not like", value, "stuUniqueNumStr");
            return (Criteria) this;
        }

        public Criteria andStuUniqueNumStrIn(List<String> values) {
            addCriterion("stu_unique_num_str in", values, "stuUniqueNumStr");
            return (Criteria) this;
        }

        public Criteria andStuUniqueNumStrNotIn(List<String> values) {
            addCriterion("stu_unique_num_str not in", values, "stuUniqueNumStr");
            return (Criteria) this;
        }

        public Criteria andStuUniqueNumStrBetween(String value1, String value2) {
            addCriterion("stu_unique_num_str between", value1, value2, "stuUniqueNumStr");
            return (Criteria) this;
        }

        public Criteria andStuUniqueNumStrNotBetween(String value1, String value2) {
            addCriterion("stu_unique_num_str not between", value1, value2, "stuUniqueNumStr");
            return (Criteria) this;
        }

        public Criteria andStuNameIsNull() {
            addCriterion("stu_name is null");
            return (Criteria) this;
        }

        public Criteria andStuNameIsNotNull() {
            addCriterion("stu_name is not null");
            return (Criteria) this;
        }

        public Criteria andStuNameEqualTo(String value) {
            addCriterion("stu_name =", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameNotEqualTo(String value) {
            addCriterion("stu_name <>", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameGreaterThan(String value) {
            addCriterion("stu_name >", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameGreaterThanOrEqualTo(String value) {
            addCriterion("stu_name >=", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameLessThan(String value) {
            addCriterion("stu_name <", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameLessThanOrEqualTo(String value) {
            addCriterion("stu_name <=", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameLike(String value) {
            addCriterion("stu_name like", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameNotLike(String value) {
            addCriterion("stu_name not like", value, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameIn(List<String> values) {
            addCriterion("stu_name in", values, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameNotIn(List<String> values) {
            addCriterion("stu_name not in", values, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameBetween(String value1, String value2) {
            addCriterion("stu_name between", value1, value2, "stuName");
            return (Criteria) this;
        }

        public Criteria andStuNameNotBetween(String value1, String value2) {
            addCriterion("stu_name not between", value1, value2, "stuName");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(Integer value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(Integer value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(Integer value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(Integer value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(Integer value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(Integer value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<Integer> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<Integer> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(Integer value1, Integer value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(Integer value1, Integer value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andIdNumberIsNull() {
            addCriterion("id_number is null");
            return (Criteria) this;
        }

        public Criteria andIdNumberIsNotNull() {
            addCriterion("id_number is not null");
            return (Criteria) this;
        }

        public Criteria andIdNumberEqualTo(String value) {
            addCriterion("id_number =", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotEqualTo(String value) {
            addCriterion("id_number <>", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberGreaterThan(String value) {
            addCriterion("id_number >", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberGreaterThanOrEqualTo(String value) {
            addCriterion("id_number >=", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberLessThan(String value) {
            addCriterion("id_number <", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberLessThanOrEqualTo(String value) {
            addCriterion("id_number <=", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberLike(String value) {
            addCriterion("id_number like", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotLike(String value) {
            addCriterion("id_number not like", value, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberIn(List<String> values) {
            addCriterion("id_number in", values, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotIn(List<String> values) {
            addCriterion("id_number not in", values, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberBetween(String value1, String value2) {
            addCriterion("id_number between", value1, value2, "idNumber");
            return (Criteria) this;
        }

        public Criteria andIdNumberNotBetween(String value1, String value2) {
            addCriterion("id_number not between", value1, value2, "idNumber");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andParentsPhoneIsNull() {
            addCriterion("parents_phone is null");
            return (Criteria) this;
        }

        public Criteria andParentsPhoneIsNotNull() {
            addCriterion("parents_phone is not null");
            return (Criteria) this;
        }

        public Criteria andParentsPhoneEqualTo(String value) {
            addCriterion("parents_phone =", value, "parentsPhone");
            return (Criteria) this;
        }

        public Criteria andParentsPhoneNotEqualTo(String value) {
            addCriterion("parents_phone <>", value, "parentsPhone");
            return (Criteria) this;
        }

        public Criteria andParentsPhoneGreaterThan(String value) {
            addCriterion("parents_phone >", value, "parentsPhone");
            return (Criteria) this;
        }

        public Criteria andParentsPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("parents_phone >=", value, "parentsPhone");
            return (Criteria) this;
        }

        public Criteria andParentsPhoneLessThan(String value) {
            addCriterion("parents_phone <", value, "parentsPhone");
            return (Criteria) this;
        }

        public Criteria andParentsPhoneLessThanOrEqualTo(String value) {
            addCriterion("parents_phone <=", value, "parentsPhone");
            return (Criteria) this;
        }

        public Criteria andParentsPhoneLike(String value) {
            addCriterion("parents_phone like", value, "parentsPhone");
            return (Criteria) this;
        }

        public Criteria andParentsPhoneNotLike(String value) {
            addCriterion("parents_phone not like", value, "parentsPhone");
            return (Criteria) this;
        }

        public Criteria andParentsPhoneIn(List<String> values) {
            addCriterion("parents_phone in", values, "parentsPhone");
            return (Criteria) this;
        }

        public Criteria andParentsPhoneNotIn(List<String> values) {
            addCriterion("parents_phone not in", values, "parentsPhone");
            return (Criteria) this;
        }

        public Criteria andParentsPhoneBetween(String value1, String value2) {
            addCriterion("parents_phone between", value1, value2, "parentsPhone");
            return (Criteria) this;
        }

        public Criteria andParentsPhoneNotBetween(String value1, String value2) {
            addCriterion("parents_phone not between", value1, value2, "parentsPhone");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andDormitoryTypeIsNull() {
            addCriterion("dormitory_type is null");
            return (Criteria) this;
        }

        public Criteria andDormitoryTypeIsNotNull() {
            addCriterion("dormitory_type is not null");
            return (Criteria) this;
        }

        public Criteria andDormitoryTypeEqualTo(Integer value) {
            addCriterion("dormitory_type =", value, "dormitoryType");
            return (Criteria) this;
        }

        public Criteria andDormitoryTypeNotEqualTo(Integer value) {
            addCriterion("dormitory_type <>", value, "dormitoryType");
            return (Criteria) this;
        }

        public Criteria andDormitoryTypeGreaterThan(Integer value) {
            addCriterion("dormitory_type >", value, "dormitoryType");
            return (Criteria) this;
        }

        public Criteria andDormitoryTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("dormitory_type >=", value, "dormitoryType");
            return (Criteria) this;
        }

        public Criteria andDormitoryTypeLessThan(Integer value) {
            addCriterion("dormitory_type <", value, "dormitoryType");
            return (Criteria) this;
        }

        public Criteria andDormitoryTypeLessThanOrEqualTo(Integer value) {
            addCriterion("dormitory_type <=", value, "dormitoryType");
            return (Criteria) this;
        }

        public Criteria andDormitoryTypeIn(List<Integer> values) {
            addCriterion("dormitory_type in", values, "dormitoryType");
            return (Criteria) this;
        }

        public Criteria andDormitoryTypeNotIn(List<Integer> values) {
            addCriterion("dormitory_type not in", values, "dormitoryType");
            return (Criteria) this;
        }

        public Criteria andDormitoryTypeBetween(Integer value1, Integer value2) {
            addCriterion("dormitory_type between", value1, value2, "dormitoryType");
            return (Criteria) this;
        }

        public Criteria andDormitoryTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("dormitory_type not between", value1, value2, "dormitoryType");
            return (Criteria) this;
        }

        public Criteria andGradeIdIsNull() {
            addCriterion("grade_id is null");
            return (Criteria) this;
        }

        public Criteria andGradeIdIsNotNull() {
            addCriterion("grade_id is not null");
            return (Criteria) this;
        }

        public Criteria andGradeIdEqualTo(Integer value) {
            addCriterion("grade_id =", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdNotEqualTo(Integer value) {
            addCriterion("grade_id <>", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdGreaterThan(Integer value) {
            addCriterion("grade_id >", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("grade_id >=", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdLessThan(Integer value) {
            addCriterion("grade_id <", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdLessThanOrEqualTo(Integer value) {
            addCriterion("grade_id <=", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdIn(List<Integer> values) {
            addCriterion("grade_id in", values, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdNotIn(List<Integer> values) {
            addCriterion("grade_id not in", values, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdBetween(Integer value1, Integer value2) {
            addCriterion("grade_id between", value1, value2, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("grade_id not between", value1, value2, "gradeId");
            return (Criteria) this;
        }

        public Criteria andMajorIdIsNull() {
            addCriterion("major_id is null");
            return (Criteria) this;
        }

        public Criteria andMajorIdIsNotNull() {
            addCriterion("major_id is not null");
            return (Criteria) this;
        }

        public Criteria andMajorIdEqualTo(Integer value) {
            addCriterion("major_id =", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotEqualTo(Integer value) {
            addCriterion("major_id <>", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdGreaterThan(Integer value) {
            addCriterion("major_id >", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("major_id >=", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdLessThan(Integer value) {
            addCriterion("major_id <", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdLessThanOrEqualTo(Integer value) {
            addCriterion("major_id <=", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdIn(List<Integer> values) {
            addCriterion("major_id in", values, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotIn(List<Integer> values) {
            addCriterion("major_id not in", values, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdBetween(Integer value1, Integer value2) {
            addCriterion("major_id between", value1, value2, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotBetween(Integer value1, Integer value2) {
            addCriterion("major_id not between", value1, value2, "majorId");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeIsNull() {
            addCriterion("register_type is null");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeIsNotNull() {
            addCriterion("register_type is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeEqualTo(Integer value) {
            addCriterion("register_type =", value, "registerType");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeNotEqualTo(Integer value) {
            addCriterion("register_type <>", value, "registerType");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeGreaterThan(Integer value) {
            addCriterion("register_type >", value, "registerType");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("register_type >=", value, "registerType");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeLessThan(Integer value) {
            addCriterion("register_type <", value, "registerType");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeLessThanOrEqualTo(Integer value) {
            addCriterion("register_type <=", value, "registerType");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeIn(List<Integer> values) {
            addCriterion("register_type in", values, "registerType");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeNotIn(List<Integer> values) {
            addCriterion("register_type not in", values, "registerType");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeBetween(Integer value1, Integer value2) {
            addCriterion("register_type between", value1, value2, "registerType");
            return (Criteria) this;
        }

        public Criteria andRegisterTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("register_type not between", value1, value2, "registerType");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andOperateIpIsNull() {
            addCriterion("operate_ip is null");
            return (Criteria) this;
        }

        public Criteria andOperateIpIsNotNull() {
            addCriterion("operate_ip is not null");
            return (Criteria) this;
        }

        public Criteria andOperateIpEqualTo(String value) {
            addCriterion("operate_ip =", value, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpNotEqualTo(String value) {
            addCriterion("operate_ip <>", value, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpGreaterThan(String value) {
            addCriterion("operate_ip >", value, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpGreaterThanOrEqualTo(String value) {
            addCriterion("operate_ip >=", value, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpLessThan(String value) {
            addCriterion("operate_ip <", value, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpLessThanOrEqualTo(String value) {
            addCriterion("operate_ip <=", value, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpLike(String value) {
            addCriterion("operate_ip like", value, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpNotLike(String value) {
            addCriterion("operate_ip not like", value, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpIn(List<String> values) {
            addCriterion("operate_ip in", values, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpNotIn(List<String> values) {
            addCriterion("operate_ip not in", values, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpBetween(String value1, String value2) {
            addCriterion("operate_ip between", value1, value2, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperateIpNotBetween(String value1, String value2) {
            addCriterion("operate_ip not between", value1, value2, "operateIp");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNull() {
            addCriterion("operator is null");
            return (Criteria) this;
        }

        public Criteria andOperatorIsNotNull() {
            addCriterion("operator is not null");
            return (Criteria) this;
        }

        public Criteria andOperatorEqualTo(Integer value) {
            addCriterion("operator =", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotEqualTo(Integer value) {
            addCriterion("operator <>", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThan(Integer value) {
            addCriterion("operator >", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorGreaterThanOrEqualTo(Integer value) {
            addCriterion("operator >=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThan(Integer value) {
            addCriterion("operator <", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorLessThanOrEqualTo(Integer value) {
            addCriterion("operator <=", value, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorIn(List<Integer> values) {
            addCriterion("operator in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotIn(List<Integer> values) {
            addCriterion("operator not in", values, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorBetween(Integer value1, Integer value2) {
            addCriterion("operator between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andOperatorNotBetween(Integer value1, Integer value2) {
            addCriterion("operator not between", value1, value2, "operator");
            return (Criteria) this;
        }

        public Criteria andJobModifiedAtIsNull() {
            addCriterion("job_modified_at is null");
            return (Criteria) this;
        }

        public Criteria andJobModifiedAtIsNotNull() {
            addCriterion("job_modified_at is not null");
            return (Criteria) this;
        }

        public Criteria andJobModifiedAtEqualTo(Date value) {
            addCriterion("job_modified_at =", value, "jobModifiedAt");
            return (Criteria) this;
        }

        public Criteria andJobModifiedAtNotEqualTo(Date value) {
            addCriterion("job_modified_at <>", value, "jobModifiedAt");
            return (Criteria) this;
        }

        public Criteria andJobModifiedAtGreaterThan(Date value) {
            addCriterion("job_modified_at >", value, "jobModifiedAt");
            return (Criteria) this;
        }

        public Criteria andJobModifiedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("job_modified_at >=", value, "jobModifiedAt");
            return (Criteria) this;
        }

        public Criteria andJobModifiedAtLessThan(Date value) {
            addCriterion("job_modified_at <", value, "jobModifiedAt");
            return (Criteria) this;
        }

        public Criteria andJobModifiedAtLessThanOrEqualTo(Date value) {
            addCriterion("job_modified_at <=", value, "jobModifiedAt");
            return (Criteria) this;
        }

        public Criteria andJobModifiedAtIn(List<Date> values) {
            addCriterion("job_modified_at in", values, "jobModifiedAt");
            return (Criteria) this;
        }

        public Criteria andJobModifiedAtNotIn(List<Date> values) {
            addCriterion("job_modified_at not in", values, "jobModifiedAt");
            return (Criteria) this;
        }

        public Criteria andJobModifiedAtBetween(Date value1, Date value2) {
            addCriterion("job_modified_at between", value1, value2, "jobModifiedAt");
            return (Criteria) this;
        }

        public Criteria andJobModifiedAtNotBetween(Date value1, Date value2) {
            addCriterion("job_modified_at not between", value1, value2, "jobModifiedAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNull() {
            addCriterion("created_at is null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNotNull() {
            addCriterion("created_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualTo(Date value) {
            addCriterion("created_at =", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIn(List<Date> values) {
            addCriterion("created_at in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotIn(List<Date> values) {
            addCriterion("created_at not in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtBetween(Date value1, Date value2) {
            addCriterion("created_at between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("created_at not between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andModifiedAtIsNull() {
            addCriterion("modified_at is null");
            return (Criteria) this;
        }

        public Criteria andModifiedAtIsNotNull() {
            addCriterion("modified_at is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedAtEqualTo(Date value) {
            addCriterion("modified_at =", value, "modifiedAt");
            return (Criteria) this;
        }

        public Criteria andModifiedAtNotEqualTo(Date value) {
            addCriterion("modified_at <>", value, "modifiedAt");
            return (Criteria) this;
        }

        public Criteria andModifiedAtGreaterThan(Date value) {
            addCriterion("modified_at >", value, "modifiedAt");
            return (Criteria) this;
        }

        public Criteria andModifiedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("modified_at >=", value, "modifiedAt");
            return (Criteria) this;
        }

        public Criteria andModifiedAtLessThan(Date value) {
            addCriterion("modified_at <", value, "modifiedAt");
            return (Criteria) this;
        }

        public Criteria andModifiedAtLessThanOrEqualTo(Date value) {
            addCriterion("modified_at <=", value, "modifiedAt");
            return (Criteria) this;
        }

        public Criteria andModifiedAtIn(List<Date> values) {
            addCriterion("modified_at in", values, "modifiedAt");
            return (Criteria) this;
        }

        public Criteria andModifiedAtNotIn(List<Date> values) {
            addCriterion("modified_at not in", values, "modifiedAt");
            return (Criteria) this;
        }

        public Criteria andModifiedAtBetween(Date value1, Date value2) {
            addCriterion("modified_at between", value1, value2, "modifiedAt");
            return (Criteria) this;
        }

        public Criteria andModifiedAtNotBetween(Date value1, Date value2) {
            addCriterion("modified_at not between", value1, value2, "modifiedAt");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}