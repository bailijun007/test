CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `order_user` varchar(255) DEFAULT NULL COMMENT '订单用户信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=267 DEFAULT CHARSET=utf8;


-- ---------------------------

CREATE TABLE `t_stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '库存商品名称',
  `stock` bigint(20) DEFAULT NULL COMMENT '库存数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


-- ---------------------------

CREATE TABLE `t_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `age` int(10) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


-- ---------------------------

-- 随机产生字符串
delimiter //
create function rand_string(n int) returns varchar(255)
begin
 declare chars_str varchar(100) default 'qazwsxedcrfvtgbyhnujmikolpQAZWSXEDCRFVTGBYHNUJMIKOLP';
 declare return_str varchar(255) default '';
 declare i int default 0;
 while i < n do
   set return_str = concat(return_str,substring(chars_str,floor(1+rand()*52),1));
   set i = i + 1;
 end while;
 return return_str;
end //


-- ---------------------------

-- 随机产生编号

delimiter //
 create function rand_num() returns int(5)
 begin
   declare i int default 0;
   set i = floor(20 + rand()*10);
 return i;
end //

-- 创建存储过程
--
-- 创建往emp表中插入数据的存储过
delimiter //
create procedure insert_user(in max_num int(10))
begin
 declare i int default 0;
 set autocommit = 0;
 repeat
  set i = i + 1;
  insert into t_user(name,age,password,create_date,create_time)
  values
  (rand_string(6),rand_num(),rand_string(6),DATE(NOW()),NOW());
  until i = max_num
  end repeat;
  commit;
end //


-- 调用存储过程---------------------------
delimiter ;
call insert_user(30000);



SELECT * FROM t_user;




















































