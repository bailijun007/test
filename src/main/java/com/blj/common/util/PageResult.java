package com.blj.common.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author BaiLiJun on 2020/1/8 0008
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PageResult<T> {

    private Integer pageNo;

    private Integer pageCount;

    private Long rowTotal;

    private List<T> list;

}
