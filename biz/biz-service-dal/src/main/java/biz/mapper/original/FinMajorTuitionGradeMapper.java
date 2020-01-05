package biz.mapper.original;

import biz.model.entity.FinMajorTuitionGrade;
import biz.model.entity.FinMajorTuitionGradeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FinMajorTuitionGradeMapper {
    long countByExample(FinMajorTuitionGradeExample example);

    int deleteByExample(FinMajorTuitionGradeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FinMajorTuitionGrade record);

    int insertSelective(FinMajorTuitionGrade record);

    List<FinMajorTuitionGrade> selectByExample(FinMajorTuitionGradeExample example);

    FinMajorTuitionGrade selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FinMajorTuitionGrade record, @Param("example") FinMajorTuitionGradeExample example);

    int updateByExample(@Param("record") FinMajorTuitionGrade record, @Param("example") FinMajorTuitionGradeExample example);

    int updateByPrimaryKeySelective(FinMajorTuitionGrade record);

    int updateByPrimaryKey(FinMajorTuitionGrade record);
}