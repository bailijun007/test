package com.blj.service;

import com.blj.redis.pubsub.vo.BBSymbol;

import java.util.List;
import java.util.Map;

/**
 * @author BaiLiJun  on 2020/3/18
 */
public interface SupportBbGroupIdsJobService {
    public Map<Integer, List<BBSymbol>> listSymbols();

    List<BBSymbol> getSymbols();

}
