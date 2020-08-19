package com.blj.mapper;

import com.blj.pojo.User;
import com.blj.pojo.excel.UploadData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author BaiLiJun on 2020/8/19 7:22 下午
 */
public interface ExcelMapper {

     void batchSave(@Param("uploadList") List<UploadData> list);

}
