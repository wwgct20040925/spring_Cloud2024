package com.gct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gct.pojo.Pay;
import com.gct.result.ResultData;

/**
* @author 高超添
* @description 针对表【t_pay(支付交易表)】的数据库操作Service
* @createDate 2024-03-01 10:38:44
*/
public interface PayService extends IService<Pay> {

    ResultData add(Pay pay);

    ResultData del(int i);

    ResultData updateByid(Pay pay);

    ResultData get(int i);
}
