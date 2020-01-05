package biz.mapper.extend;

import biz.model.request.StudentSearchRequest;
import biz.model.response.StudentSearchResponse;

import java.util.List;

public interface EduStudentMapperExt {
    List<StudentSearchResponse> getEduStudentListByIdNumberOrPhone(StudentSearchRequest studentSearchRequest);
    List<StudentSearchResponse> getEduStudentListByIdNumber(String idNumber);
    List<StudentSearchResponse> getAllActivityStudentSearchResponse(int status);
}
