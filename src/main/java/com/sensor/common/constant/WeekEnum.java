package com.sensor.common.constant;
/**
 * @author by apple
 * @Classname WeekEnum
 * @Description TODO
 * @Date 2022/8/12 15:52
 */
public enum WeekEnum
{
    SECONDS("1", "星期天", "Sunday "),
    MONDAY("2", "星期一", "Monday "),
    TUESDAY("3", "星期二", "Tuesday "),
    WEDNESDAY("4", "星期三", "Wednesday "),
    THURSDAY("5", "星期四", "Thursday "),
    FRIDAY("6", "星期五", "Friday "),
    SATURDAY("7", "星期六", "Saturday ");
    private String key;
    private String nameCn;
    private String nameEn;

    WeekEnum(String key, String nameCn, String nameEn)
    {
        this.key = key;
        this.nameCn = nameCn;
        this.nameEn = nameEn;
    }

    public static String matchNameCn(String code)
    {
        for(WeekEnum m: WeekEnum.values())
        {
            if(m.getKey().equals(code))
            {
                return m.getNameCn();
            }
        }
        return "";
    }

    public static String matchNameEn(String code)
    {
        for(WeekEnum m: WeekEnum.values())
        {
            if(m.getKey().equals(code))
            {
                return m.getNameEn();
            }
        }
        return "";
    }

    public String getKey()
    {
        return key;
    }

    public String getNameCn()
    {
        return nameCn;
    }

    public String getNameEn()
    {
        return nameEn;
    }
}