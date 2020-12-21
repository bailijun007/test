package com.blj.pojo.ehr;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SafeTools {
    public static int ToInt(String data, int default_value) {
        int result = default_value;
        try {
            result = Integer.parseInt(data);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public static Long ToLong(String data, long default_value) {
        Long result = default_value;
        try {
            result = Long.parseLong(data);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    public static Boolean StringIsNullOrEmpty(String str) {
        if (str != null && str.length() != 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Java将Unix时间戳转换成指定格式日期字符串
     *
     * @param timestampString 时间戳 如："1473048265";
     * @param formats         要格式化的格式 默认："yyyy-MM-dd HH:mm:ss";
     * @return 返回结果 如："2016-09-05 16:06:42";
     */
    public static String TimeStamp2Date(long timestampString, String formats) {
        if (StringIsNullOrEmpty(formats))
            formats = "yyyy-MM-dd HH:mm:ss";
        Long timestamp = timestampString * 1000;
        String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
        return date;
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param dateStr 字符串日期
     * @param format  如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static long Date2TimeStamp(String dateStr, String format) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(dateStr).getTime() / 1000;
    }

    /**
     * 取得当前时间戳（精确到秒）
     *
     * @return nowTimeStamp
     */
    public static long getNowTimeStamp() {
        //long time = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Date date = calendar.getTime();
        long time = date.getTime();
        return time / 1000;
    }


    public static String SafeBase64ToBase64String(String data) {
        data = data.replace('-', '+').replace('_', '/');
        switch (data.length() % 4) {
            case 2:
                data += "==";
                break;
            case 3:
                data += "=";
                break;
        }
        return data;
    }

    public static String Base64StringToSafeBase64(String data) {
        return data.replace('+', '-').replace('/', '_').replace("=", "");
    }
}
