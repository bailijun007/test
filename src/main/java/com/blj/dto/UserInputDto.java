package com.blj.dto;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * UserInputDto
 *
 * 校验规则参考：https://blog.csdn.net/qq920447939/article/details/80198438
 *
 * @author bailj@linkstec.com
 * @date 2020/6/23 14:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInputDto implements Serializable {
//    @NotNull(message = "id不能为空")
//    private  Long id;

    @NotNull(message = "name不能为空")
    @Size(min = 4, max = 30, message = "字符串长度要求4到30之间。")
    private  String name;

    @Min(value = 1, message = "最小值为1") // 最小值为1
    @Max(value = 100, message = "最大值为100") // 最大值88
    @NotNull // 不能为空
    private  Integer age;



}
