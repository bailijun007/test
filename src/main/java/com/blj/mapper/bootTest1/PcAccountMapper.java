
package com.blj.mapper.bootTest1;

import com.blj.pojo.PcAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author wangjg
 *
 */
@Component
@Mapper
public interface PcAccountMapper {
	
	public PcAccount get(@Param("userId") Long userId, @Param("asset") String asset);

    public List<PcAccount> queryList(@Param("userIdList") List<Long> userIdList);

    public List<PcAccount> queryList2(Map<String, Object> map);


}
