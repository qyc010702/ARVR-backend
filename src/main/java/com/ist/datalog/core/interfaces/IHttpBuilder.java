package com.ist.datalog.core.interfaces;

/**
 * @author : LiYiFan
 * @date : 2023/10/25 15:48
 * @desc :
 */
public interface IHttpBuilder <T extends IHttpDTO>{

    T build();
}
