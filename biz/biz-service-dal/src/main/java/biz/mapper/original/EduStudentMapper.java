package biz.mapper.original;

import biz.model.entity.EduStudent;
import biz.model.entity.EduStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduStudentMapper {
    long countByExample(EduStudentExample example);

    int deleteByExample(EduStudentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EduStudent record);

    int insertSelective(EduStudent record);

    List<EduStudent> selectByExample(EduStudentExample example);

    EduStudent selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EduStudent record, @Param("example") EduStudentExample example);

    int updateByExample(@Param("record") EduStudent record, @Param("example") EduStudentExample example);

    int updateByPrimaryKeySelective(EduStudent record);

    int updateByPrimaryKey(EduStudent record);
}