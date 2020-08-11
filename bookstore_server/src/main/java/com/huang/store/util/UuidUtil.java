package com.huang.store.util;

import java.util.UUID;

/**
 * @author: 黄龙
 * @date: 2020/7/22 9:52
 * @description: UUID工具类
 */
public final class UuidUtil {
    private UuidUtil(){}
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
