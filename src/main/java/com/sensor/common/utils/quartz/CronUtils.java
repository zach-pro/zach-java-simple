package com.sensor.common.utils.quartz;

import com.cronutils.builder.CronBuilder;
import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.sensor.common.constant.SchemaUsedEnum;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronExpression;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.cronutils.model.field.expression.FieldExpressionFactory.*;


// every(执行时间,间隔)
// withYear        年
// withMonth       月
// withDoM         所在月的天数
// withDoW         周
// withHour        小时
// withMinute      分钟
// withSecond      秒
// questionMark    ?符号
// on              时间表达式
// always          当前时间
// between         区间
// always()        总是
/**
 * @author by 张益豪
 * @Classname CronUtils
 * @Description 根据业务生成匹配的cron表达式
 */
@Slf4j
public class CronUtils {

    public static String getCron(Date startDate, Integer type, Integer withHour, Integer withMinute, Integer withSecond, String cronStr) {
        String returnCron;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        if (SchemaUsedEnum.TASK_MODE_CYCLE.getIndex().equals(type)) {
            Cron cron;
            if((withHour == null || withHour == 0) && (withMinute == null || withMinute == 0)){
                cron = CronBuilder.cron(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ))
                        .withYear(always())
                        .withMonth(always())
                        .withDoM(always())
                        .withDoW(questionMark())
                        .withHour(always())
                        .withMinute(always())
                        .withSecond(every(withSecond))
                        .instance();
            }else if (withHour == null || withHour == 0) {
                cron = CronBuilder.cron(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ))
                        .withYear(always())
                        .withMonth(always())
                        .withDoM(always())
                        .withDoW(questionMark())
                        .withHour(always())
                        .withMinute(every(withMinute))
                        .withSecond(on(withSecond))
                        .instance();
            } else {
                cron = CronBuilder.cron(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ))
                        .withYear(always())
                        .withMonth(always())
                        .withDoM(always())
                        .withDoW(questionMark())
                        .withHour(every(withHour))
                        .withMinute(on(withMinute))
                        .withSecond(on(withSecond))
                        .instance();
            }
            returnCron = cron.asString();
        } else if (SchemaUsedEnum.TASK_MODE_DAY.getIndex().equals(type)) {
            Cron cron = CronBuilder.cron(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ))
                    .withYear(always())
                    .withMonth(always())
                    .withDoM(always())
                    .withDoW(questionMark())
                    .withHour(on(withHour))
                    .withMinute(on(withMinute))
                    .withSecond(on(withSecond))
                    .instance();
            returnCron = cron.asString();
        }else{
            returnCron  = cronStr;
        }
        return returnCron;
    }

    /**
     * 返回⼀个布尔值代表⼀个给定的Cron表达式的有效性
     * @param cronExpression Cron表达式
     * @return boolean 表达式是否有效
     */
    public static boolean isValid(String cronExpression) {
        return CronExpression.isValidExpression(cronExpression);
    }
    /**
     * 返回一个字符串值, 表示该消息无效Cron表达式给出有效性
     * @param cronExpression Cron表达式
     * @return String 无效时返回表达式错误描述, 如果有效返回null
     */
    public static String getInvalidMessage(String cronExpression){
        try {
            new CronExpression(cronExpression);
            return null;
        }catch (ParseException e){
            return e.getMessage();
        }
    }

    /**
     * 返回下一个执行时间根据给定的Cron表达式
     * @param cronExpression Cron表达式
     * @return Date 下次Cron表达式执行时间
     */
    public static Date getNextExecution(String cronExpression){
        try {
            CronExpression cron = new CronExpression(cronExpression);
            return cron.getNextValidTimeAfter(new Date(System.currentTimeMillis()));
        }catch (ParseException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.info(dateFormat.format(new Date()));

        String cron = getCron(new Date(), 1, 5, 10, 0, "");
        log.info(cron);
        log.info(String.valueOf(isValid(cron)));
        log.info(CronExpParserUtil.translateToChinese(cron));
        log.info(dateFormat.format(getNextExecution(cron)));
    }
}