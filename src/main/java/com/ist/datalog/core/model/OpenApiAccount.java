package com.ist.datalog.core.model;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author : LiYiFan
 * @date : 2023/10/27 10:25
 * @desc : OpenApi账户信息
 */
public class OpenApiAccount {
    @ApiModelProperty("账户名称")
    private String accountName;

    @ApiModelProperty("用户总余额（美元)")
    private double hardLimited;

    @ApiModelProperty("总使用金额（美分)")
    private BigDecimal totalUsed;

    @ApiModelProperty("账户总余额（美元)")
    private BigDecimal totalGranted;

    @ApiModelProperty("账户总剩余金额（美元)")
    private BigDecimal totalAvailable;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getHardLimited() {
        return hardLimited;
    }

    public void setHardLimited(double hardLimited) {
        this.hardLimited = hardLimited;
    }

    public BigDecimal getTotalUsed() {
        return totalUsed;
    }

    public void setTotalUsed(BigDecimal totalUsed) {
        this.totalUsed = totalUsed;
    }

    public BigDecimal getTotalGranted() {
        return totalGranted;
    }

    public void setTotalGranted(BigDecimal totalGranted) {
        this.totalGranted = totalGranted;
    }

    public BigDecimal getTotalAvailable() {
        return totalAvailable;
    }

    public void setTotalAvailable(BigDecimal totalAvailable) {
        this.totalAvailable = totalAvailable;
    }
}
