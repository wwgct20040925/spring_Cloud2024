package com.gct.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gct.mapper.PayMapper;
import com.gct.pojo.Pay;
import com.gct.result.ResultData;
import com.gct.result.ReturnCodeEnum;
import com.gct.service.PayService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author 高超添
 * @description 针对表【t_pay(支付交易表)】的数据库操作Service实现
 * @createDate 2024-03-01 10:38:44
 */
@Service
public class PayServiceImpl extends ServiceImpl<PayMapper, Pay>
        implements PayService {

    @Resource
    private PayMapper payMapper;

    @Override
    public ResultData add(Pay pay) {
        int row = payMapper.insert(pay);
        if (row > 0) {
            return ResultData.success(null);
        } else {
            return ResultData.fail(ReturnCodeEnum.RC203);
        }
    }



    @Override
    public ResultData del(int i) {
        int row = payMapper.deleteById(i);
        if (row > 0) {
            return ResultData.success(null);
        } else {
            return ResultData.fail(ReturnCodeEnum.RC500);
        }
    }



    @Override
    public ResultData updateByid(Pay pay) {
        int row = payMapper.updateById(pay);
        if (row > 0) {
            return ResultData.success(null);
        } else {
            return ResultData.fail(ReturnCodeEnum.RC500);
        }
    }
    @Override
    public ResultData get(int i) {
        Pay pay = payMapper.selectById(i);
        if(pay!=null){
            return ResultData.success(pay);
        }
        else{
            return ResultData.fail(ReturnCodeEnum.RC999);
        }
    }
}




