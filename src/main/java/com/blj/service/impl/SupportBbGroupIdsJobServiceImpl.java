package com.blj.service.impl;

import com.blj.config.redis.jedis.RedisUtil;
import com.blj.redis.pubsub.vo.BBSymbol;
import com.blj.service.SupportBbGroupIdsJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * @author BaiLiJun  on 2020/3/18
 */
@Service
public class SupportBbGroupIdsJobServiceImpl implements SupportBbGroupIdsJobService {
    @Autowired
    @Qualifier("metadataRedisUtil")
    private RedisUtil metadataRedisUtil;

    @Value("${bb.trade.symbols}")
    private String symbols;

    @Value("${bb.trade.bbGroupIds}")
    private Integer bbGroupId;

  static    Map<Integer, List<BBSymbol>> map = new ConcurrentHashMap<>();

    @Override
    @Scheduled(cron = "*/1 * * * * *")
    public Map<Integer, List<BBSymbol>> listSymbols() {
        if (symbols.equals("bb_symbol")) {
            Map<String, BBSymbol> symbolMap = metadataRedisUtil.hgetAll(symbols, BBSymbol.class);
            if (!CollectionUtils.isEmpty(symbolMap)) {
                List<BBSymbol> list = symbolMap.values().stream().collect(Collectors.toList());
                map = list.stream().collect(Collectors.groupingBy(bbSymbol -> bbSymbol.getBbGroupId()));
                return map;
            }
        }
        return null;
    }


    /**
     * @return USDT__ETC_USDT
     */
    @Override
    @PostConstruct
    public List<BBSymbol> getSymbols() {
        List<BBSymbol> list = new CopyOnWriteArrayList<>();
        if (CollectionUtils.isEmpty(map)){
            map = listSymbols();
        }
        if (!CollectionUtils.isEmpty(map)) {
            for (Integer integer : map.keySet()) {
                if (bbGroupId.equals(integer)) {
                    List<BBSymbol> bbSymbols = map.get(integer);
                    for (BBSymbol bbSymbol : bbSymbols) {
                        list.add(bbSymbol);
                    }
                }
            }
        }
        return list;
    }



}
