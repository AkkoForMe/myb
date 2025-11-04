package com.example.demo.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Controller
public class MybController {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

    static String holiday2022 = "{\"code\":0,\"holiday\":{\"01-01\":{\"holiday\":true,\"name\":\"元旦\",\"wage\":3,\"date\":\"2022-01-01\",\"rest\":85},\"01-02\":{\"holiday\":true,\"name\":\"元旦\",\"wage\":2,\"date\":\"2022-01-02\",\"rest\":1},\"01-03\":{\"holiday\":true,\"name\":\"元旦\",\"wage\":2,\"date\":\"2022-01-03\",\"rest\":1},\"01-29\":{\"holiday\":false,\"name\":\"春节前补班\",\"after\":false,\"wage\":1,\"target\":\"春节\",\"date\":\"2022-01-29\",\"rest\":14},\"01-30\":{\"holiday\":false,\"name\":\"春节前补班\",\"after\":false,\"wage\":1,\"target\":\"春节\",\"date\":\"2022-01-30\",\"rest\":1},\"01-31\":{\"holiday\":true,\"name\":\"除夕\",\"wage\":2,\"date\":\"2022-01-31\",\"rest\":16},\"02-01\":{\"holiday\":true,\"name\":\"初一\",\"wage\":3,\"date\":\"2022-02-01\",\"rest\":1},\"02-02\":{\"holiday\":true,\"name\":\"初二\",\"wage\":3,\"date\":\"2022-02-02\",\"rest\":1},\"02-03\":{\"holiday\":true,\"name\":\"初三\",\"wage\":3,\"date\":\"2022-02-03\",\"rest\":1},\"02-04\":{\"holiday\":true,\"name\":\"初四\",\"wage\":2,\"date\":\"2022-02-04\",\"rest\":1},\"02-05\":{\"holiday\":true,\"name\":\"初五\",\"wage\":2,\"date\":\"2022-02-05\",\"rest\":1},\"02-06\":{\"holiday\":true,\"name\":\"初六\",\"wage\":2,\"date\":\"2022-02-06\",\"rest\":1},\"04-02\":{\"holiday\":false,\"name\":\"清明节前补班\",\"after\":false,\"wage\":1,\"target\":\"清明节\",\"date\":\"2022-04-02\",\"rest\":19},\"04-03\":{\"holiday\":true,\"name\":\"清明节\",\"wage\":2,\"date\":\"2022-04-03\",\"rest\":20},\"04-04\":{\"holiday\":true,\"name\":\"清明节\",\"wage\":2,\"date\":\"2022-04-04\",\"rest\":1},\"04-05\":{\"holiday\":true,\"name\":\"清明节\",\"wage\":3,\"date\":\"2022-04-05\",\"rest\":1},\"04-24\":{\"holiday\":false,\"name\":\"劳动节前补班\",\"after\":false,\"wage\":1,\"target\":\"劳动节\",\"date\":\"2022-04-24\",\"rest\":19},\"04-30\":{\"holiday\":true,\"name\":\"劳动节\",\"wage\":2,\"date\":\"2022-04-30\",\"rest\":25},\"05-01\":{\"holiday\":true,\"name\":\"劳动节\",\"wage\":3,\"date\":\"2022-05-01\",\"rest\":1},\"05-02\":{\"holiday\":true,\"name\":\"劳动节\",\"wage\":2,\"date\":\"2022-05-02\",\"rest\":1},\"05-03\":{\"holiday\":true,\"name\":\"劳动节\",\"wage\":2,\"date\":\"2022-05-03\",\"rest\":1},\"05-04\":{\"holiday\":true,\"name\":\"劳动节\",\"wage\":2,\"date\":\"2022-05-04\",\"rest\":1},\"05-07\":{\"holiday\":false,\"name\":\"劳动节后补班\",\"after\":true,\"wage\":1,\"target\":\"劳动节\",\"date\":\"2022-05-07\",\"rest\":1},\"06-03\":{\"holiday\":true,\"name\":\"端午节\",\"wage\":3,\"date\":\"2022-06-03\",\"rest\":9},\"06-04\":{\"holiday\":true,\"name\":\"端午节\",\"wage\":2,\"date\":\"2022-06-04\",\"rest\":1},\"06-05\":{\"holiday\":true,\"name\":\"端午节\",\"wage\":2,\"date\":\"2022-06-05\",\"rest\":1},\"09-10\":{\"holiday\":true,\"name\":\"中秋节\",\"wage\":3,\"date\":\"2022-09-10\",\"rest\":75},\"09-11\":{\"holiday\":true,\"name\":\"中秋节\",\"wage\":2,\"date\":\"2022-09-11\",\"rest\":1},\"09-12\":{\"holiday\":true,\"name\":\"中秋节\",\"wage\":2,\"date\":\"2022-09-12\",\"rest\":1},\"10-01\":{\"holiday\":true,\"name\":\"国庆节\",\"wage\":3,\"date\":\"2022-10-01\",\"rest\":18},\"10-02\":{\"holiday\":true,\"name\":\"国庆节\",\"wage\":3,\"date\":\"2022-10-02\",\"rest\":1},\"10-03\":{\"holiday\":true,\"name\":\"国庆节\",\"wage\":3,\"date\":\"2022-10-03\",\"rest\":1},\"10-04\":{\"holiday\":true,\"name\":\"国庆节\",\"wage\":2,\"date\":\"2022-10-04\",\"rest\":1},\"10-05\":{\"holiday\":true,\"name\":\"国庆节\",\"wage\":2,\"date\":\"2022-10-05\",\"rest\":1},\"10-06\":{\"holiday\":true,\"name\":\"国庆节\",\"wage\":2,\"date\":\"2022-10-06\",\"rest\":1},\"10-07\":{\"holiday\":true,\"name\":\"国庆节\",\"wage\":2,\"date\":\"2022-10-07\",\"rest\":1},\"10-08\":{\"holiday\":false,\"after\":true,\"wage\":1,\"name\":\"国庆节后补班\",\"target\":\"国庆节\",\"date\":\"2022-10-08\"},\"10-09\":{\"holiday\":false,\"after\":true,\"wage\":1,\"name\":\"国庆节后补班\",\"target\":\"国庆节\",\"date\":\"2022-10-09\"}}}";
    static String holiday2024 = "{\"code\":0,\"holiday\":{\"01-01\":{\"holiday\":true,\"name\":\"元旦\",\"wage\":3,\"date\":\"2024-01-01\",\"rest\":1},\"02-04\":{\"holiday\":false,\"name\":\"春节前补班\",\"wage\":1,\"after\":false,\"target\":\"春节\",\"date\":\"2024-02-04\",\"rest\":9},\"02-10\":{\"holiday\":true,\"name\":\"初一\",\"wage\":3,\"date\":\"2024-02-10\",\"rest\":15},\"02-11\":{\"holiday\":true,\"name\":\"初二\",\"wage\":3,\"date\":\"2024-02-11\",\"rest\":1},\"02-12\":{\"holiday\":true,\"name\":\"初三\",\"wage\":3,\"date\":\"2024-02-12\",\"rest\":1},\"02-13\":{\"holiday\":true,\"name\":\"初四\",\"wage\":2,\"date\":\"2024-02-13\",\"rest\":1},\"02-14\":{\"holiday\":true,\"name\":\"初五\",\"wage\":2,\"date\":\"2024-02-14\",\"rest\":1},\"02-15\":{\"holiday\":true,\"name\":\"初六\",\"wage\":2,\"date\":\"2024-02-15\",\"rest\":1},\"02-16\":{\"holiday\":true,\"name\":\"初七\",\"wage\":2,\"date\":\"2024-02-16\",\"rest\":1},\"02-17\":{\"holiday\":true,\"name\":\"初八\",\"wage\":2,\"date\":\"2024-02-17\",\"rest\":1},\"02-18\":{\"holiday\":false,\"name\":\"春节后补班\",\"wage\":1,\"after\":true,\"target\":\"春节\",\"date\":\"2024-02-18\",\"rest\":1},\"04-04\":{\"holiday\":true,\"name\":\"清明节\",\"wage\":3,\"date\":\"2024-04-04\",\"rest\":36},\"04-05\":{\"holiday\":true,\"name\":\"清明节\",\"wage\":2,\"date\":\"2024-04-05\"},\"04-06\":{\"holiday\":true,\"name\":\"清明节\",\"wage\":2,\"date\":\"2024-04-06\"},\"04-07\":{\"holiday\":false,\"name\":\"清明节后补班\",\"wage\":1,\"target\":\"清明节\",\"after\":true,\"date\":\"2024-04-07\"},\"04-28\":{\"holiday\":false,\"name\":\"劳动节前补班\",\"wage\":1,\"target\":\"劳动节\",\"after\":false,\"date\":\"2024-04-28\",\"rest\":21},\"05-01\":{\"holiday\":true,\"name\":\"劳动节\",\"wage\":3,\"date\":\"2024-05-01\",\"rest\":24},\"05-02\":{\"holiday\":true,\"name\":\"劳动节\",\"wage\":2,\"date\":\"2024-05-02\",\"rest\":1},\"05-03\":{\"holiday\":true,\"name\":\"劳动节\",\"wage\":3,\"date\":\"2024-05-03\"},\"05-04\":{\"holiday\":true,\"name\":\"劳动节\",\"wage\":3,\"date\":\"2024-05-04\"},\"05-05\":{\"holiday\":true,\"name\":\"劳动节\",\"wage\":3,\"date\":\"2024-05-05\"},\"05-11\":{\"holiday\":false,\"name\":\"劳动节后补班\",\"after\":true,\"wage\":1,\"target\":\"劳动节\",\"date\":\"2024-05-11\"},\"06-08\":{\"holiday\":true,\"name\":\"端午节\",\"wage\":2,\"date\":\"2024-06-08\"},\"06-09\":{\"holiday\":true,\"name\":\"端午节\",\"wage\":2,\"date\":\"2024-06-09\"},\"06-10\":{\"holiday\":true,\"name\":\"端午节\",\"wage\":3,\"date\":\"2024-06-10\",\"rest\":1},\"09-14\":{\"holiday\":false,\"name\":\"中秋节前补班\",\"after\":false,\"wage\":1,\"target\":\"中秋节\",\"date\":\"2024-09-14\",\"rest\":70},\"09-15\":{\"holiday\":true,\"name\":\"中秋节\",\"wage\":2,\"date\":\"2024-09-15\",\"rest\":71},\"09-16\":{\"holiday\":true,\"name\":\"中秋节\",\"wage\":2,\"date\":\"2024-09-16\"},\"09-17\":{\"holiday\":true,\"name\":\"中秋节\",\"wage\":3,\"date\":\"2024-09-17\"},\"09-29\":{\"holiday\":false,\"name\":\"国庆节前补班\",\"after\":false,\"wage\":1,\"target\":\"国庆节\",\"date\":\"2024-09-29\"},\"10-01\":{\"holiday\":true,\"name\":\"国庆节\",\"wage\":3,\"date\":\"2024-10-01\"},\"10-02\":{\"holiday\":true,\"name\":\"国庆节\",\"wage\":3,\"date\":\"2024-10-02\",\"rest\":1},\"10-03\":{\"holiday\":true,\"name\":\"国庆节\",\"wage\":3,\"date\":\"2024-10-03\"},\"10-04\":{\"holiday\":true,\"name\":\"国庆节\",\"wage\":2,\"date\":\"2024-10-04\"},\"10-05\":{\"holiday\":true,\"name\":\"国庆节\",\"wage\":2,\"date\":\"2024-10-05\",\"rest\":1},\"10-06\":{\"holiday\":true,\"name\":\"国庆节\",\"wage\":2,\"date\":\"2024-10-06\",\"rest\":1},\"10-07\":{\"holiday\":true,\"name\":\"国庆节\",\"wage\":2,\"date\":\"2024-10-07\",\"rest\":1},\"10-12\":{\"holiday\":false,\"after\":true,\"wage\":1,\"name\":\"国庆节后补班\",\"target\":\"国庆节\",\"date\":\"2024-10-12\"}}}";
    static String holiday2025 = "{\"code\":0,\"holiday\":{\"01-01\":{\"holiday\":true,\"name\":\"元旦\",\"wage\":3,\"date\":\"2025-01-01\",\"rest\":61},\"01-26\":{\"holiday\":false,\"name\":\"春节前补班\",\"wage\":1,\"after\":false,\"target\":\"春节\",\"date\":\"2025-01-26\",\"rest\":20},\"01-28\":{\"holiday\":true,\"name\":\"除夕\",\"wage\":2,\"date\":\"2025-01-28\",\"rest\":22},\"01-29\":{\"holiday\":true,\"name\":\"初一\",\"wage\":3,\"date\":\"2025-01-29\",\"rest\":1},\"01-30\":{\"holiday\":true,\"name\":\"初二\",\"wage\":3,\"date\":\"2025-01-30\",\"rest\":1},\"01-31\":{\"holiday\":true,\"name\":\"初三\",\"wage\":3,\"date\":\"2025-01-31\",\"rest\":1},\"02-01\":{\"holiday\":true,\"name\":\"初四\",\"wage\":2,\"date\":\"2025-02-01\",\"rest\":1},\"02-02\":{\"holiday\":true,\"name\":\"初五\",\"wage\":2,\"date\":\"2025-02-02\",\"rest\":1},\"02-03\":{\"holiday\":true,\"name\":\"初六\",\"wage\":2,\"date\":\"2025-02-03\"},\"02-04\":{\"holiday\":true,\"name\":\"初七\",\"wage\":2,\"date\":\"2025-02-04\",\"rest\":1},\"02-08\":{\"holiday\":false,\"name\":\"春节后补班\",\"wage\":1,\"target\":\"春节\",\"after\":true,\"date\":\"2025-02-08\",\"rest\":2},\"04-04\":{\"holiday\":true,\"name\":\"清明节\",\"wage\":3,\"date\":\"2025-04-04\",\"rest\":19},\"04-05\":{\"holiday\":true,\"name\":\"清明节\",\"wage\":2,\"date\":\"2025-04-05\",\"rest\":1},\"04-06\":{\"holiday\":true,\"name\":\"清明节\",\"wage\":2,\"date\":\"2025-04-06\",\"rest\":1},\"04-27\":{\"holiday\":false,\"name\":\"劳动节前补班\",\"wage\":1,\"target\":\"劳动节\",\"after\":false,\"date\":\"2025-04-27\",\"rest\":17},\"05-01\":{\"holiday\":true,\"name\":\"劳动节\",\"wage\":3,\"date\":\"2025-05-01\",\"rest\":21},\"05-02\":{\"holiday\":true,\"name\":\"劳动节\",\"wage\":3,\"date\":\"2025-05-02\",\"rest\":1},\"05-03\":{\"holiday\":true,\"name\":\"劳动节\",\"wage\":3,\"date\":\"2025-05-03\",\"rest\":1},\"05-04\":{\"holiday\":true,\"name\":\"劳动节\",\"wage\":2,\"date\":\"2025-05-04\",\"rest\":1},\"05-05\":{\"holiday\":true,\"name\":\"劳动节\",\"wage\":2,\"date\":\"2025-05-05\",\"rest\":1},\"05-31\":{\"holiday\":true,\"name\":\"端午节\",\"wage\":3,\"date\":\"2025-05-31\",\"rest\":15},\"06-01\":{\"holiday\":true,\"name\":\"端午节\",\"wage\":2,\"date\":\"2025-06-01\",\"rest\":1},\"06-02\":{\"holiday\":true,\"name\":\"端午节\",\"wage\":2,\"date\":\"2025-06-02\",\"rest\":1},\"09-28\":{\"holiday\":false,\"name\":\"国庆节前补班\",\"after\":false,\"wage\":1,\"target\":\"国庆节\",\"date\":\"2025-09-28\",\"rest\":101},\"10-01\":{\"holiday\":true,\"name\":\"国庆节\",\"wage\":3,\"date\":\"2025-10-01\",\"rest\":104},\"10-02\":{\"holiday\":true,\"name\":\"国庆节\",\"wage\":3,\"date\":\"2025-10-02\",\"rest\":1},\"10-03\":{\"holiday\":true,\"name\":\"国庆节\",\"wage\":3,\"date\":\"2025-10-03\",\"rest\":1},\"10-04\":{\"holiday\":true,\"name\":\"国庆节\",\"wage\":2,\"date\":\"2025-10-04\",\"rest\":1},\"10-05\":{\"holiday\":true,\"name\":\"国庆节\",\"wage\":2,\"date\":\"2025-10-05\",\"rest\":1},\"10-06\":{\"holiday\":true,\"name\":\"中秋节\",\"wage\":2,\"date\":\"2025-10-06\",\"rest\":1},\"10-07\":{\"holiday\":true,\"name\":\"国庆节\",\"wage\":2,\"date\":\"2025-10-07\",\"rest\":1},\"10-08\":{\"holiday\":true,\"name\":\"国庆节\",\"wage\":2,\"date\":\"2025-10-08\",\"rest\":1},\"10-11\":{\"holiday\":false,\"after\":true,\"wage\":1,\"name\":\"国庆节后补班\",\"target\":\"国庆节\",\"date\":\"2025-10-11\"}}}";

    // 声明静态的 holidayMap 变量
    static Map<String, String> holidayMap = new HashMap<>();

    static {
        // 初始化 holidayMap
        holidayMap.put("2022", holiday2022);
        holidayMap.put("2024", holiday2024);
        holidayMap.put("2025", holiday2025);
        System.out.println("holidayMap initialized with " + holidayMap.size() + " entries");
    }

    @RequestMapping("/myb")
    @ResponseBody
    public String myb() throws ParseException {
//		long nowT = sdf.parse("2022-06-02 05:16:17").getTime();
//		long nowT = sdf.parse("2020-06-02 05:16:17").getTime();
        long nowT = System.currentTimeMillis();
        String nowS = sdf2.format(new Date(nowT)); // 年月日
        long nowSTime = sdf2.parse(nowS).getTime(); // 年月日  时分秒 其中时分秒都是0
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(nowT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(nowT);
        int year = calendar.get(Calendar.YEAR); // 当前年份
        String str = sdf.format(new Date(calendar.getTimeInMillis()));
        System.out.println("当前时间：" + str);
//		calendar.add(Calendar.DATE, -925);
//		str = sdf.format(new Date(calendar.getTimeInMillis()));
//		System.out.println("疫情爆发时间："+str); // 2019-12-16

        calendar.setTimeInMillis(nowT);
        str = sdf.format(new Date(calendar.getTimeInMillis()));
        System.out.println("当前时间：" + str);
        calendar.add(Calendar.DATE, 208);
        str = sdf.format(new Date(calendar.getTimeInMillis()));
        System.out.println("春节时间：" + str); // 2023-01-22

        resMap = getHoliday(year);

        List<Map<String, Object>> fisrtHoliday = new ArrayList<Map<String, Object>>(); // 距离放假的日期信息
        // 先加上周末
        int weekDay = now.get(Calendar.DAY_OF_WEEK); // 获取周几
        weekDay = weekDay - 1;
        if (weekDay == 0) {
            weekDay = 7;
        }
        System.out.println("周" + weekDay);
        // 离周六还剩？
        if (weekDay >= 6) {
            System.out.println("宁已经在周末辣！");
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name", "周末");
            map.put("days", 6 - weekDay);
            fisrtHoliday.add(map);
        }

        List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
        l.add(resMap); // 今年的
        l.add(getHoliday(year + 1)); // 下一年的
        List<String> notIn = Arrays.asList("初一", "初二", "初三", "初四", "初五", "初六"); // 这些节假日不显示哦
        List<String> names = new ArrayList<String>(); // 所有的节假日日期，只存365天内的
        for (Map<String, Object> resMap : l) {
            Map<String, Object> holidayMap = (Map<String, Object>) resMap.get("holiday"); // 假期信息
            for (String monDate : holidayMap.keySet()) { // monDate:月-日 如：01-01、10-01等
//				System.out.println(monDate);
//				System.out.println(holidayMap.get(monDate));
                Map<String, Object> map = (Map<String, Object>) holidayMap.get(monDate);
                boolean holiday = (boolean) map.get("holiday"); // 是否为假期日
                String name = map.get("name").toString(); // 假期名称
                String date = map.get("date").toString(); // 日期 年月日 如:2022-01-01
                if (holiday) { // 得是假期
                    if (nowS.compareTo(date) < 0 && !names.contains(name) && !notIn.contains(name)) { // 只能是大于当前日期的才显示
                        long betweenDays = (sdf2.parse(date).getTime() - nowSTime) / (1000 * 60 * 60 * 24);
//						System.out.println("差值："+betweenDays+"   nowT:"+nowT+"  假日："+sdf2.parse(date).getTime()+" 相减："+(sdf2.parse(date).getTime() - nowT) + " date:"+date+" nowS:"+nowS+" dateStr:"+sdf.format(new Date(sdf2.parse(date).getTime()))+" nowStr:"+sdf.format(new Date(nowT)));
                        Map<String, Object> t = new HashMap<String, Object>();
                        t.put("name", name);
                        t.put("days", betweenDays);
                        fisrtHoliday.add(t);
                        names.add(name);
                    }
                }
            }
        }
        int nowYear = now.get(Calendar.YEAR);
        int nowMonth = now.get(Calendar.MONTH) + 1;
        int nowDay = now.get(Calendar.DATE);

        long nCoVDays = (nowSTime - sdf2.parse("2019-12-16").getTime()) / (1000 * 60 * 60 * 24); // nCoV

        String a = "【摸鱼总办】提醒您：\r\n" +
                "\r\n" +
                nowMonth + "月" + nowDay + "日 上午好，摸鱼人！工作再累，一定不要忘记摸鱼哦，有事没事起身去茶水间，去厕所，去廊道走走别老在工位上坐着，钱是老板的,但命是自己的。\r\n" +
                "自新冠疫情爆发以来已经过了" + nCoVDays + "天了，疫情防控形势再度严峻，注意及时配合防疫政策，做好自我防护！\r\n" +
                "\r\n";
        for (Map<String, Object> map : fisrtHoliday) {
//			System.out.println("距离"+map.get("name")+"还有"+map.get("days")+"天");
            a += "距离" + map.get("name") + "还有" + map.get("days") + "天\r\n";
        }
        a += "\r\n" +
                "认认真真上班，这根本就不叫赚钱，那是用劳动换取报酬。只有偷懒，在上班的时候摸鱼划水，你才是从老板手里赚到了钱。最后，祝愿天下所有摸鱼人都能愉快的渡过每一天！";
        System.out.println(a);
        return a;
    }

    // 2023年开始固定的法定节假日，用于国务院在年末公布下一年的安排时的一种临时解决方案
    String fixedHoliday = "[{\"list\":[{\"date\":\"2023-1-1\",\"name\":\"元旦节\"},{\"date\":\"2023-1-21\",\"name\":\"除夕\"},{\"date\":\"2023-1-22\",\"name\":\"春节\"},{\"date\":\"2023-4-5\",\"name\":\"清明节\"},{\"date\":\"2023-5-1\",\"name\":\"劳动节\"},{\"date\":\"2023-6-22\",\"name\":\"端午节\"},{\"date\":\"2023-9-29\",\"name\":\"中秋节\"},{\"date\":\"2023-10-1\",\"name\":\"国庆节\"}],\"list#num#baidu\":8,\"year\":\"2023\"},{\"list\":[{\"date\":\"2024-1-1\",\"name\":\"元旦节\"},{\"date\":\"2024-2-9\",\"name\":\"除夕\"},{\"date\":\"2024-2-10\",\"name\":\"春节\"},{\"date\":\"2024-4-4\",\"name\":\"清明节\"},{\"date\":\"2024-5-1\",\"name\":\"劳动节\"},{\"date\":\"2024-6-10\",\"name\":\"端午节\"},{\"date\":\"2024-9-17\",\"name\":\"中秋节\"},{\"date\":\"2024-10-1\",\"name\":\"国庆节\"}],\"list#num#baidu\":8,\"year\":\"2024\"},{\"list\":[{\"date\":\"2025-1-1\",\"name\":\"元旦节\"},{\"date\":\"2025-1-28\",\"name\":\"除夕\"},{\"date\":\"2025-1-29\",\"name\":\"春节\"},{\"date\":\"2025-4-4\",\"name\":\"清明节\"},{\"date\":\"2025-5-1\",\"name\":\"劳动节\"},{\"date\":\"2025-5-31\",\"name\":\"端午节\"},{\"date\":\"2025-10-1\",\"name\":\"国庆节\"},{\"date\":\"2025-10-06\",\"name\":\"中秋节\"}],\"list#num#baidu\":8,\"year\":\"2025\"},{\"list\":[{\"date\":\"2026-1-1\",\"name\":\"元旦节\"},{\"date\":\"2026-2-16\",\"name\":\"除夕\"},{\"date\":\"2026-2-17\",\"name\":\"春节\"},{\"date\":\"2026-4-5\",\"name\":\"清明节\"},{\"date\":\"2026-5-1\",\"name\":\"劳动节\"},{\"date\":\"2026-6-19\",\"name\":\"端午节\"},{\"date\":\"2026-9-25\",\"name\":\"中秋节\"},{\"date\":\"2026-10-1\",\"name\":\"国庆节\"}],\"list#num#baidu\":8,\"year\":\"2026\"},{\"list\":[{\"date\":\"2027-1-1\",\"name\":\"元旦节\"},{\"date\":\"2027-2-5\",\"name\":\"除夕\"},{\"date\":\"2027-2-6\",\"name\":\"春节\"},{\"date\":\"2027-4-5\",\"name\":\"清明节\"},{\"date\":\"2027-5-1\",\"name\":\"劳动节\"},{\"date\":\"2027-6-9\",\"name\":\"端午节\"},{\"date\":\"2027-9-15\",\"name\":\"中秋节\"},{\"date\":\"2027-10-1\",\"name\":\"国庆节\"}],\"list#num#baidu\":8,\"year\":\"2027\"},{\"list\":[{\"date\":\"2028-1-1\",\"name\":\"元旦节\"},{\"date\":\"2028-1-25\",\"name\":\"除夕\"},{\"date\":\"2028-1-26\",\"name\":\"春节\"},{\"date\":\"2028-4-4\",\"name\":\"清明节\"},{\"date\":\"2028-5-1\",\"name\":\"劳动节\"},{\"date\":\"2028-5-28\",\"name\":\"端午节\"},{\"date\":\"2028-10-1\",\"name\":\"国庆节\"},{\"date\":\"2028-10-3\",\"name\":\"中秋节\"}],\"list#num#baidu\":8,\"year\":\"2028\"},{\"list\":[{\"date\":\"2029-1-1\",\"name\":\"元旦节\"},{\"date\":\"2029-2-12\",\"name\":\"除夕\"},{\"date\":\"2029-2-13\",\"name\":\"春节\"},{\"date\":\"2029-4-4\",\"name\":\"清明节\"},{\"date\":\"2029-5-1\",\"name\":\"劳动节\"},{\"date\":\"2029-6-16\",\"name\":\"端午节\"},{\"date\":\"2029-9-22\",\"name\":\"中秋节\"},{\"date\":\"2029-10-1\",\"name\":\"国庆节\"}],\"list#num#baidu\":8,\"year\":\"2029\"},{\"list\":[{\"date\":\"2030-1-1\",\"name\":\"元旦节\"},{\"date\":\"2030-2-2\",\"name\":\"除夕\"},{\"date\":\"2030-2-3\",\"name\":\"春节\"},{\"date\":\"2030-4-5\",\"name\":\"清明节\"},{\"date\":\"2030-5-1\",\"name\":\"劳动节\"},{\"date\":\"2030-6-5\",\"name\":\"端午节\"},{\"date\":\"2030-9-22\",\"name\":\"中秋节\"},{\"date\":\"2030-10-1\",\"name\":\"国庆节\"}],\"list#num#baidu\":8,\"year\":\"2030\"},{\"list\":[{\"date\":\"2031-1-1\",\"name\":\"元旦节\"},{\"date\":\"2031-1-22\",\"name\":\"除夕\"},{\"date\":\"2031-1-23\",\"name\":\"春节\"},{\"date\":\"2031-4-5\",\"name\":\"清明节\"},{\"date\":\"2031-5-1\",\"name\":\"劳动节\"},{\"date\":\"2031-6-24\",\"name\":\"端午节\"},{\"date\":\"2031-10-1\",\"name\":\"中秋节\"},{\"date\":\"2031-10-1\",\"name\":\"国庆节\"}],\"list#num#baidu\":8,\"year\":\"2031\"},{\"list\":[{\"date\":\"2032-1-1\",\"name\":\"元旦节\"},{\"date\":\"2032-2-10\",\"name\":\"除夕\"},{\"date\":\"2032-2-11\",\"name\":\"春节\"},{\"date\":\"2032-4-4\",\"name\":\"清明节\"},{\"date\":\"2032-5-1\",\"name\":\"劳动节\"},{\"date\":\"2032-6-12\",\"name\":\"端午节\"},{\"date\":\"2032-9-19\",\"name\":\"中秋节\"},{\"date\":\"2032-10-1\",\"name\":\"国庆节\"}],\"list#num#baidu\":8,\"year\":\"2032\"},{\"list\":[{\"date\":\"2033-1-1\",\"name\":\"元旦节\"},{\"date\":\"2033-1-30\",\"name\":\"除夕\"},{\"date\":\"2033-1-31\",\"name\":\"春节\"},{\"date\":\"2033-4-4\",\"name\":\"清明节\"},{\"date\":\"2033-5-1\",\"name\":\"劳动节\"},{\"date\":\"2033-6-1\",\"name\":\"端午节\"},{\"date\":\"2033-9-8\",\"name\":\"中秋节\"},{\"date\":\"2033-10-1\",\"name\":\"国庆节\"}],\"list#num#baidu\":8,\"year\":\"2033\"},{\"list\":[{\"date\":\"2034-1-1\",\"name\":\"元旦节\"},{\"date\":\"2034-2-18\",\"name\":\"除夕\"},{\"date\":\"2034-2-19\",\"name\":\"春节\"},{\"date\":\"2034-4-5\",\"name\":\"清明节\"},{\"date\":\"2034-5-1\",\"name\":\"劳动节\"},{\"date\":\"2034-6-20\",\"name\":\"端午节\"},{\"date\":\"2034-9-27\",\"name\":\"中秋节\"},{\"date\":\"2034-10-1\",\"name\":\"国庆节\"}],\"list#num#baidu\":8,\"year\":\"2034\"},{\"list\":[{\"date\":\"2035-1-1\",\"name\":\"元旦节\"},{\"date\":\"2035-2-7\",\"name\":\"除夕\"},{\"date\":\"2035-2-8\",\"name\":\"春节\"},{\"date\":\"2035-4-5\",\"name\":\"清明节\"},{\"date\":\"2035-5-1\",\"name\":\"劳动节\"},{\"date\":\"2035-6-10\",\"name\":\"端午节\"},{\"date\":\"2035-9-16\",\"name\":\"中秋节\"},{\"date\":\"2035-10-1\",\"name\":\"国庆节\"}],\"list#num#baidu\":8,\"year\":\"2035\"},{\"list\":[{\"date\":\"2036-1-1\",\"name\":\"元旦节\"},{\"date\":\"2036-1-27\",\"name\":\"除夕\"},{\"date\":\"2036-1-28\",\"name\":\"春节\"},{\"date\":\"2036-4-4\",\"name\":\"清明节\"},{\"date\":\"2036-5-1\",\"name\":\"劳动节\"},{\"date\":\"2036-5-30\",\"name\":\"端午节\"},{\"date\":\"2036-10-1\",\"name\":\"国庆节\"},{\"date\":\"2036-10-4\",\"name\":\"中秋节\"}],\"list#num#baidu\":8,\"year\":\"2036\"},{\"list\":[{\"date\":\"2037-1-1\",\"name\":\"元旦节\"},{\"date\":\"2037-2-14\",\"name\":\"除夕\"},{\"date\":\"2037-2-15\",\"name\":\"春节\"},{\"date\":\"2037-4-4\",\"name\":\"清明节\"},{\"date\":\"2037-5-1\",\"name\":\"劳动节\"},{\"date\":\"2037-6-18\",\"name\":\"端午节\"},{\"date\":\"2037-9-24\",\"name\":\"中秋节\"},{\"date\":\"2037-10-1\",\"name\":\"国庆节\"}],\"list#num#baidu\":8,\"year\":\"2037\"},{\"list\":[{\"date\":\"2038-1-1\",\"name\":\"元旦节\"},{\"date\":\"2038-2-3\",\"name\":\"除夕\"},{\"date\":\"2038-2-4\",\"name\":\"春节\"},{\"date\":\"2038-4-5\",\"name\":\"清明节\"},{\"date\":\"2038-5-1\",\"name\":\"劳动节\"},{\"date\":\"2038-6-7\",\"name\":\"端午节\"},{\"date\":\"2038-9-13\",\"name\":\"中秋节\"},{\"date\":\"2038-10-1\",\"name\":\"国庆节\"}],\"list#num#baidu\":8,\"year\":\"2038\"},{\"list\":[{\"date\":\"2039-1-1\",\"name\":\"元旦节\"},{\"date\":\"2039-1-23\",\"name\":\"除夕\"},{\"date\":\"2039-1-24\",\"name\":\"春节\"},{\"date\":\"2039-4-5\",\"name\":\"清明节\"},{\"date\":\"2039-5-1\",\"name\":\"劳动节\"},{\"date\":\"2039-5-27\",\"name\":\"端午节\"},{\"date\":\"2039-10-1\",\"name\":\"国庆节\"},{\"date\":\"2039-10-2\",\"name\":\"中秋节\"}],\"list#num#baidu\":8,\"year\":\"2039\"},{\"list\":[{\"date\":\"2040-1-1\",\"name\":\"元旦节\"},{\"date\":\"2040-2-11\",\"name\":\"除夕\"},{\"date\":\"2040-2-12\",\"name\":\"春节\"},{\"date\":\"2040-4-4\",\"name\":\"清明节\"},{\"date\":\"2040-5-1\",\"name\":\"劳动节\"},{\"date\":\"2040-6-14\",\"name\":\"端午节\"},{\"date\":\"2040-9-20\",\"name\":\"中秋节\"},{\"date\":\"2040-10-1\",\"name\":\"国庆节\"}],\"list#num#baidu\":8,\"year\":\"2040\"},{\"list\":[{\"date\":\"2041-1-1\",\"name\":\"元旦节\"},{\"date\":\"2041-1-31\",\"name\":\"除夕\"},{\"date\":\"2041-2-1\",\"name\":\"春节\"},{\"date\":\"2041-4-4\",\"name\":\"清明节\"},{\"date\":\"2041-5-1\",\"name\":\"劳动节\"},{\"date\":\"2041-6-3\",\"name\":\"端午节\"},{\"date\":\"2041-9-10\",\"name\":\"中秋节\"},{\"date\":\"2041-10-1\",\"name\":\"国庆节\"}],\"list#num#baidu\":8,\"year\":\"2041\"},{\"list\":[{\"date\":\"2042-1-1\",\"name\":\"元旦节\"},{\"date\":\"2042-1-21\",\"name\":\"除夕\"},{\"date\":\"2042-1-22\",\"name\":\"春节\"},{\"date\":\"2042-4-4\",\"name\":\"清明节\"},{\"date\":\"2042-5-1\",\"name\":\"劳动节\"},{\"date\":\"2042-6-22\",\"name\":\"端午节\"},{\"date\":\"2042-9-28\",\"name\":\"中秋节\"},{\"date\":\"2042-10-1\",\"name\":\"国庆节\"}],\"list#num#baidu\":8,\"year\":\"2042\"},{\"list\":[{\"date\":\"2043-1-1\",\"name\":\"元旦节\"},{\"date\":\"2043-2-9\",\"name\":\"除夕\"},{\"date\":\"2043-2-10\",\"name\":\"春节\"},{\"date\":\"2043-4-5\",\"name\":\"清明节\"},{\"date\":\"2043-5-1\",\"name\":\"劳动节\"},{\"date\":\"2043-6-11\",\"name\":\"端午节\"},{\"date\":\"2043-9-17\",\"name\":\"中秋节\"},{\"date\":\"2043-10-1\",\"name\":\"国庆节\"}],\"list#num#baidu\":8,\"year\":\"2043\"},{\"list\":[{\"date\":\"2044-1-1\",\"name\":\"元旦节\"},{\"date\":\"2044-1-29\",\"name\":\"除夕\"},{\"date\":\"2044-1-30\",\"name\":\"春节\"},{\"date\":\"2044-4-4\",\"name\":\"清明节\"},{\"date\":\"2044-5-1\",\"name\":\"劳动节\"},{\"date\":\"2044-5-31\",\"name\":\"端午节\"},{\"date\":\"2044-10-1\",\"name\":\"国庆节\"},{\"date\":\"2044-10-5\",\"name\":\"中秋节\"}],\"list#num#baidu\":8,\"year\":\"2044\"},{\"list\":[{\"date\":\"2045-1-1\",\"name\":\"元旦节\"},{\"date\":\"2045-2-16\",\"name\":\"除夕\"},{\"date\":\"2045-2-17\",\"name\":\"春节\"},{\"date\":\"2045-4-4\",\"name\":\"清明节\"},{\"date\":\"2045-5-1\",\"name\":\"劳动节\"},{\"date\":\"2045-6-19\",\"name\":\"端午节\"},{\"date\":\"2045-9-25\",\"name\":\"中秋节\"},{\"date\":\"2045-10-1\",\"name\":\"国庆节\"}],\"list#num#baidu\":8,\"year\":\"2045\"},{\"list\":[{\"date\":\"2046-1-1\",\"name\":\"元旦节\"},{\"date\":\"2046-2-5\",\"name\":\"除夕\"},{\"date\":\"2046-2-6\",\"name\":\"春节\"},{\"date\":\"2046-4-4\",\"name\":\"清明节\"},{\"date\":\"2046-5-1\",\"name\":\"劳动节\"},{\"date\":\"2046-6-8\",\"name\":\"端午节\"},{\"date\":\"2046-9-15\",\"name\":\"中秋节\"},{\"date\":\"2046-10-1\",\"name\":\"国庆节\"}],\"list#num#baidu\":8,\"year\":\"2046\"},{\"list\":[{\"date\":\"2047-1-1\",\"name\":\"元旦节\"},{\"date\":\"2047-1-25\",\"name\":\"除夕\"},{\"date\":\"2047-1-26\",\"name\":\"春节\"},{\"date\":\"2047-4-5\",\"name\":\"清明节\"},{\"date\":\"2047-5-1\",\"name\":\"劳动节\"},{\"date\":\"2047-5-29\",\"name\":\"端午节\"},{\"date\":\"2047-10-1\",\"name\":\"国庆节\"},{\"date\":\"2047-10-4\",\"name\":\"中秋节\"}],\"list#num#baidu\":8,\"year\":\"2047\"},{\"list\":[{\"date\":\"2048-1-1\",\"name\":\"元旦节\"},{\"date\":\"2048-2-13\",\"name\":\"除夕\"},{\"date\":\"2048-2-14\",\"name\":\"春节\"},{\"date\":\"2048-4-4\",\"name\":\"清明节\"},{\"date\":\"2048-5-1\",\"name\":\"劳动节\"},{\"date\":\"2048-6-15\",\"name\":\"端午节\"},{\"date\":\"2048-9-22\",\"name\":\"中秋节\"},{\"date\":\"2048-10-1\",\"name\":\"国庆节\"}],\"list#num#baidu\":8,\"year\":\"2048\"},{\"list\":[{\"date\":\"2049-1-1\",\"name\":\"元旦节\"},{\"date\":\"2049-2-1\",\"name\":\"除夕\"},{\"date\":\"2049-2-2\",\"name\":\"春节\"},{\"date\":\"2049-4-4\",\"name\":\"清明节\"},{\"date\":\"2049-5-1\",\"name\":\"劳动节\"},{\"date\":\"2049-6-4\",\"name\":\"端午节\"},{\"date\":\"2049-9-11\",\"name\":\"中秋节\"},{\"date\":\"2049-10-1\",\"name\":\"国庆节\"}],\"list#num#baidu\":8,\"year\":\"2049\"},{\"list\":[{\"date\":\"2050-1-1\",\"name\":\"元旦节\"},{\"date\":\"2050-1-22\",\"name\":\"除夕\"},{\"date\":\"2050-1-23\",\"name\":\"春节\"},{\"date\":\"2050-4-4\",\"name\":\"清明节\"},{\"date\":\"2050-5-1\",\"name\":\"劳动节\"},{\"date\":\"2050-6-23\",\"name\":\"端午节\"},{\"date\":\"2050-9-30\",\"name\":\"中秋节\"},{\"date\":\"2050-10-1\",\"name\":\"国庆节\"}],\"list#num#baidu\":8,\"year\":\"2050\"}]";
    List<Map<String, Object>> fixedHolidayList = new Gson().fromJson(fixedHoliday, new TypeToken<List<Map<String, Object>>>() {
    }.getType());

    Map<String, Object> resMap = new HashMap<String, Object>();

    // 获取法定节假日
    private Map<String, Object> getHoliday(int year) {
        if (resMap != null && resMap.size() > 0 && (year + "").equals(String.valueOf(resMap.get("year")))) {
            System.out.println("---已有" + year + "年缓存---");
        } else if (holidayMap.containsKey(String.valueOf(year))) {
            System.out.println("---有本地字符串:" + year + "年的---");
            String holidayStr = holidayMap.get(String.valueOf(year));
            resMap = new Gson().fromJson(holidayStr, new TypeToken<Map<String, Object>>() {
            }.getType());
            resMap.put("year", year);
        } else {
            LocalDateTime cuDateTime = LocalDateTime.now();
            int nowYear = cuDateTime.getYear();
            if (year > nowYear) {
                System.out.println("---往后的年份另作处理---");
                for (Map<String, Object> map : fixedHolidayList) {
                    String yearStr = map.get("year").toString(); // 年
                    if (yearStr.equals(year + "")) {
                        List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("list");
                        resMap = new HashMap<String, Object>();
                        resMap.put("year", year);
                        Map<String, Object> holidayMap = new LinkedHashMap<String, Object>();
                        for (Map<String, Object> days : list) {
                            String date = days.get("date").toString();
                            String name = days.get("name").toString();
                            Map<String, Object> xx = new HashMap<String, Object>();
                            xx.put("holiday", true);
                            xx.put("name", name);
                            xx.put("date", date);
                            holidayMap.put(date, xx);
                        }
                        resMap.put("holiday", holidayMap);
                    }
                }
            } else {
                System.out.println("---开始调用接口---年份：" + year);
                // 请求免费的获取法定节假日接口
                String url = "http://timor.tech/api/holiday/year/" + year;
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(url)
                        .get()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                        .addHeader("Accept", "application/json, text/plain, */*")
                        .addHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8")
                        .addHeader("Cache-Control", "no-cache")
                        .build();

                Response response;
                boolean apiSuccess = false;
                try {
                    response = client.newCall(request).execute();
                    String rsa = response.body().string();
                    System.out.println("接口接受字符串：" + rsa);

                    // 检查返回内容是否为 JSON
                    if (rsa != null && rsa.trim().startsWith("{")) {
                        resMap = new Gson().fromJson(rsa, new TypeToken<Map<String, Object>>() {
                        }.getType());
                        resMap.put("year", year);
                        apiSuccess = true;
                        System.out.println("---API调用成功---");
                    } else {
                        System.out.println("---API返回非JSON数据，启用备用方案---");
                    }
                } catch (Exception e) {
                    System.out.println("---API调用异常，启用备用方案---");
                    e.printStackTrace();
                }

                // 降级方案：使用固定的节假日数据
                if (!apiSuccess) {
                    System.out.println("---使用本地固定节假日数据---年份：" + year);
                    for (Map<String, Object> map : fixedHolidayList) {
                        String yearStr = map.get("year").toString();
                        if (yearStr.equals(year + "")) {
                            List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("list");
                            resMap = new HashMap<String, Object>();
                            resMap.put("year", year);
                            Map<String, Object> holidayMap = new LinkedHashMap<String, Object>();
                            for (Map<String, Object> days : list) {
                                String date = days.get("date").toString();
                                String name = days.get("name").toString();
                                Map<String, Object> xx = new HashMap<String, Object>();
                                xx.put("holiday", true);
                                xx.put("name", name);
                                xx.put("date", date);
                                holidayMap.put(date, xx);
                            }
                            resMap.put("holiday", holidayMap);
                            break;
                        }
                    }
                }
            }
        }
        System.out.println(resMap);
        return resMap;
    }

    public static void main(String[] args) {
        try {
            new MybController().myb();
            System.out.println("-----");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        try {
            modelAndView.addObject("msg", myb());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping("/love")
    public ModelAndView love() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("love");
        return modelAndView;
    }

    @RequestMapping("/goMyb")
    public String goMyb() {
        return "index";
    }
}
