package biz.mapper.original;

import biz.model.entity.BizTrade;
import biz.model.entity.BizTradeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizTradeMapper {
    long countByExample(BizTradeExample example);

    int deleteByExample(BizTradeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BizTrade record);

    int insertSelective(BizTrade record);

    List<BizTrade> selectByExample(BizTradeExample example);

    BizTrade selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BizTrade record, @Param("example") BizTradeExample example);

    int updateByExample(@Param("record") BizTrade record, @Param("example") BizTradeExample example);

    int updateByPrimaryKeySelective(BizTrade record);

    int updateByPrimaryKey(BizTrade record);
}