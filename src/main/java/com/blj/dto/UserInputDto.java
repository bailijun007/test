package com.blj.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * UserInputDto
 * <p>
 * 校验规则参考：https://blog.csdn.net/qq920447939/article/details/80198438
 *
 * @author bailj@linkstec.com
 * @date 2020/6/23 14:56
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserInputDto implements Serializable {
//    @NotNull(message = "id不能为空")
//    private  Long id;

    @NotNull(message = "name不能为空")
    @Length(min = 4, max = 30, message = "name字符串长度要求4到30之间")
    private String name;

    @Min(value = 1, message = "最小值为1") // 最小值为1
    @Max(value = 100, message = "年龄最大值为100") // 最大值88
//    @Range(min = 1, max = 100, message = "年龄范围为1至100岁之间") // 限定范围
    @NotNull // 不能为空
    private Integer age;


}
