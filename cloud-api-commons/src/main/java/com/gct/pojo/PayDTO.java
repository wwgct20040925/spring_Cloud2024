package com.gct.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PayDTO {

    private String payNo;

    private String orderNo;

    private Integer userId;

    private BigDecimal amount;
}
