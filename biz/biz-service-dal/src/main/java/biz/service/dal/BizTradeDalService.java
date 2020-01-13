package biz.service.dal;

import biz.mapper.original.BizTradeMapper;
import biz.model.entity.BizTrade;
import biz.model.entity.BizTradeExample;
import common.lib.application.BeanTools;

import java.util.List;

public class BizTradeDalService {
    private static BizTradeMapper bizTradeMapper = (BizTradeMapper) BeanTools.getBean(BizTradeMapper.class);

    public static List<BizTrade> getBizTradeByBizId(int bizId) {
        BizTradeExample example = new BizTradeExample();
        example.or().andBizIdEqualTo(bizId);
        return bizTradeMapper.selectByExample(example);
    }
}
