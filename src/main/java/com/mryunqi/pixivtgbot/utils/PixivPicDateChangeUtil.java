package com.mryunqi.pixivtgbot.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author mryunqi
 * @date 2022/12/24
 */
public class PixivPicDateChangeUtil {
    public static String pixivPicDateChange(long timestamp){
        // 创建SimpleDateFormat对象
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
        // 获取日历对象
        Calendar calendar = Calendar.getInstance();
        // 将时间戳转换为日期
        calendar.setTimeInMillis(timestamp * 1000);
        // 将日期加上1小时，不清楚为什么，Pixiv获取到的上传时间与实际的时间错了1小时
        calendar.add(Calendar.HOUR, 1);
        // 获取新的日期
        Date newDate = calendar.getTime();
        // 将新的日期转换为字符串并返回结果
        return dateFormat.format(newDate);
    }
}
