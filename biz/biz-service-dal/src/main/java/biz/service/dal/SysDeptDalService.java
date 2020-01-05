package biz.service.dal;

import biz.enumeration.StatusEnum;
import biz.model.entity.SysDept;
import biz.model.entity.SysDeptExample;
import biz.mapper.original.SysDeptMapper;
import common.lib.application.BeanTools;

import java.util.List;

public class SysDeptDalService {
    private static SysDeptMapper sysDeptMapper = (SysDeptMapper) BeanTools.getBean(SysDeptMapper.class);

    public static List<SysDept> getSysDeptList() {
        SysDeptExample example = new SysDeptExample();
        example.or().andStatusEqualTo(StatusEnum.enabled.getIndex());
        return sysDeptMapper.selectByExample(example);
    }
}
