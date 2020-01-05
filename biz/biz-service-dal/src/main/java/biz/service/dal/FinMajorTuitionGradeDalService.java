package biz.service.dal;

import biz.model.entity.FinMajorTuitionGrade;
import biz.model.entity.FinMajorTuitionGradeExample;
import biz.mapper.original.FinMajorTuitionGradeMapper;
import common.lib.application.BeanTools;

import java.util.List;

public class FinMajorTuitionGradeDalService {
    private static FinMajorTuitionGradeMapper finMajorTuitionGradeMapper = (FinMajorTuitionGradeMapper) BeanTools.getBean(FinMajorTuitionGradeMapper.class);

    public static List<FinMajorTuitionGrade> getFinMajorTuitionGradeList() {
        return finMajorTuitionGradeMapper.selectByExample(new FinMajorTuitionGradeExample());
    }

    public static FinMajorTuitionGrade getFinMajorTuitionGradeByPrimaryKey(int id) {
        return finMajorTuitionGradeMapper.selectByPrimaryKey(id);
    }
}
