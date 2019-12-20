package com.blj.pojo;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Version;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 永续合约_账户
 * @author lw
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PcAccount  {

	//用户ID
	private Long userId;
	
	//资产类型
	private String asset;
	
	//余额
	private BigDecimal balance;
	
	//版本
	private Long version;

	private Date  created;

    private Date  modified;

}
