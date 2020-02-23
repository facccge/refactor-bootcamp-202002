package cc.xpbootcamp.warmup.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public static int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static boolean isWednesday(Date date) {
        return getDayOfWeek(date) == Calendar.WEDNESDAY ? true : false;
    }

    public static String parseDateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年M月dd日,EEEE\n", Locale.CHINA);
        return simpleDateFormat.format(date);
    }
}
