package biz.service.dal;

import biz.enumeration.StudentStatusEnum;
import biz.model.entity.EduStudent;
import biz.model.entity.EduStudentExample;
import biz.model.request.StudentSearchRequest;
import biz.model.response.StudentSearchResponse;
import biz.service.dal.mapper.extend.EduStudentMapperExt;
import biz.service.dal.mapper.original.EduStudentMapper;
import common.lib.application.BeanTools;

import java.util.List;

public class EduStudentDalService {
    private static EduStudentMapper eduStudentMapper = (EduStudentMapper) BeanTools.getBean(EduStudentMapper.class);
    private static EduStudentMapperExt eduStudentMapperExt = (EduStudentMapperExt) BeanTools.getBean(EduStudentMapperExt.class);

    public static StudentSearchResponse getEduStudentByIdNumber(String idNumber) {
        List<StudentSearchResponse> studentSearchResponseList = eduStudentMapperExt.getEduStudentListByIdNumber(idNumber);
        return studentSearchResponseList.size() > 0 ? studentSearchResponseList.get(0) : null;
    }

    public static StudentSearchResponse getEduStudentByIdNumberOrPhone(StudentSearchRequest studentSearchRequest) {
        List<StudentSearchResponse> studentSearchResponseList = eduStudentMapperExt.getEduStudentListByIdNumberOrPhone(studentSearchRequest);
        return studentSearchResponseList.size() > 0 ? studentSearchResponseList.get(0) : null;
    }

    public static List<StudentSearchResponse> getAllActivityStudentSearchResponse() {
        return eduStudentMapperExt.getAllActivityStudentSearchResponse(StudentStatusEnum.enabled.getIndex());
    }

    public static List<EduStudent> getAllActivityStudent() {
        EduStudentExample example = new EduStudentExample();
        example.or().andStatusEqualTo(StudentStatusEnum.enabled.getIndex());
        return eduStudentMapper.selectByExample(example);
    }
}
