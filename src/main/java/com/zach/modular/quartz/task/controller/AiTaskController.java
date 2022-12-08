package com.zach.modular.quartz.task.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zach.common.constant.ResultCode;
import com.zach.common.constant.SchemaUsedEnum;
import com.zach.common.tasks.ScheduledJob;
import com.zach.common.utils.quartz.CronExpParserUtil;
import com.zach.common.utils.quartz.CronUtils;
import com.zach.common.utils.quartz.QuartzManager;
import com.zach.common.utils.sys.UserUtils;
import com.zach.common.vo.JsonResult;
import com.zach.common.vo.ResultTool;
import com.zach.modular.authentication.user.entity.AiUser;
import com.zach.modular.quartz.task.entity.AiTask;
import com.zach.modular.quartz.task.service.impl.AiTaskServiceImpl;
import com.zach.modular.quartz.task_journal.service.impl.AiTaskJournalServiceImpl;
import com.zach.modular.quartz.task_journal.vo.reqeust.AiTaskJournalRequest;
import com.zach.modular.quartz.task_journal.vo.response.AiTaskJournalResponse;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 * @author apple
 * 
 */
@Tag(name = "定时任务模块", description = "定时任务模块")
@RestController
@RequestMapping("/ai_task/aiTask")
public class AiTaskController {
    final QuartzManager quartzManager;
    final AiTaskServiceImpl taskService;
    final AiTaskJournalServiceImpl taskJournalService;

    public AiTaskController(QuartzManager quartzManager, AiTaskServiceImpl taskService, AiTaskJournalServiceImpl taskJournalService) {
        this.quartzManager = quartzManager;
        this.taskService = taskService;
        this.taskJournalService = taskJournalService;
    }

    @PostMapping("/saveTask")
    @Operation(summary  = "新建定时任务")
    @Transactional(rollbackFor=Exception.class)
    public JsonResult<AiTask> saveProject(@RequestBody AiTask task){
        if (StringUtils.isBlank(task.getTaskName()) || task.getTaskMode() == null) {
            return ResultTool.fail(ResultCode.PARAM_NOT_COMPLETE);
        }
        Integer taskMode = task.getTaskMode();
        if(SchemaUsedEnum.TASK_MODE_CRON.getIndex().equals(taskMode)){
            if(StringUtils.isBlank(task.getCronText())){
                return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
            }
        } else if (SchemaUsedEnum.TASK_MODE_CYCLE.getIndex().equals(taskMode) || SchemaUsedEnum.TASK_MODE_DAY.getIndex().equals(taskMode)) {
            if (task.getTimeHour() == null || task.getTimeMinute() == null || task.getTimeSecond() == null) {
                return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
            }
        }
        task.preInsert();
        task.setEnabled(SchemaUsedEnum.TASK_DISABLE.getIndex());
        String corn = CronUtils.getCron(new Date(), task.getTaskMode(), task.getTimeHour(), task.getTimeMinute(), task.getTimeSecond(), task.getCronText());
        if (!CronUtils.isValid(corn)) {
            return ResultTool.fail(ResultCode.PARAM_NOT_CHECK);
        }
        taskService.save(task);
        return ResultTool.success(task);
    }

    @PostMapping("/runTask")
    @Operation(summary = "执行定时任务")
    @Transactional(rollbackFor=Exception.class)
    public JsonResult runTask(String  taskId){
        if (StringUtils.isBlank(taskId)) {
            return ResultTool.fail(ResultCode.PARAM_NOT_COMPLETE);
        }

        AiTaskJournalRequest request = new AiTaskJournalRequest(SchemaUsedEnum.TASK_TYPE_MANUAL.getIndex(), taskId);
        List<AiTaskJournalResponse> response = taskJournalService.selectTaskJournalList(request);
        if (!response.isEmpty()) {
            Integer taskStatus = response.get(0).getTaskStatus();
            if (SchemaUsedEnum.TASK_STATUS_RUNNING.getIndex().equals(taskStatus)) {
                return ResultTool.fail(ResultCode.TASK_TYPE_MANUAL);
            }
        }
        return ResultTool.success();
    }

    @PostMapping("/delTask")
    @Operation(summary  = "删除定时任务")
    @Transactional(rollbackFor=Exception.class)
    public JsonResult delTask(@Parameter(name = "id", description = "定时任务id", required = true) String id){
        if (StringUtils.isBlank(id)) {
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
        LambdaQueryWrapper<AiTask> qw = new LambdaQueryWrapper<>();
        qw.eq(AiTask::getId,id);
        AiTask task = taskService.getById(id);
        taskService.remove(qw);
        if (task.getEnabled().equals(SchemaUsedEnum.TASK_ENABLED.getIndex())) {
            quartzManager.removeJob(AiTask.generateJobName(1,id),AiTask.generateJobName(2,id),AiTask.generateJobName(3,id),AiTask.generateJobName(4,id));
        }
        return ResultTool.success();
    }

    @PostMapping("/upTask")
    @Operation(summary  = "修改定时任务")
    @Transactional(rollbackFor=Exception.class)
    public JsonResult<AiTask> upTask(@RequestBody AiTask graphTask){
        if (graphTask==null || StringUtils.isBlank(graphTask.getId()) || StringUtils.isBlank(graphTask.getTaskName()) || graphTask.getTaskMode() == null) {
            return ResultTool.fail(ResultCode.PARAM_NOT_COMPLETE);
        }
        Integer taskMode = graphTask.getTaskMode();
        if(SchemaUsedEnum.TASK_MODE_CRON.getIndex().equals(taskMode)){
            if(StringUtils.isBlank(graphTask.getCronText())){
                return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
            }
        } else if (SchemaUsedEnum.TASK_MODE_CYCLE.getIndex().equals(taskMode) || SchemaUsedEnum.TASK_MODE_DAY.getIndex().equals(taskMode)) {
            if (graphTask.getTimeHour() == null || graphTask.getTimeMinute() == null || graphTask.getTimeSecond() == null) {
                return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
            }
        }
        String id = graphTask.getId();
        taskService.updateById(graphTask);
        if (graphTask.getEnabled().equals(SchemaUsedEnum.TASK_ENABLED.getIndex())) {
            String corn = CronUtils.getCron(new Date(), graphTask.getTaskMode(), graphTask.getTimeHour(), graphTask.getTimeMinute(), graphTask.getTimeSecond(), graphTask.getCronText());
            if (!CronUtils.isValid(corn)) {
                return ResultTool.fail(ResultCode.PARAM_NOT_CHECK);
            }
            quartzManager.modifyJobTime(AiTask.generateJobName(3, id), AiTask.generateJobName(4, id), corn);
        }
        return ResultTool.success(graphTask);
    }

    @PostMapping("/selTaskLists")
    @Operation(summary  = "查询定时任务列表")
    @Transactional(rollbackFor=Exception.class)
    public JsonResult<List<AiTask>> selTaskLists(@RequestBody AiTask graphTask){
        AiUser user = UserUtils.getUser();
        graphTask.setCreateUser(user.getId());
        LambdaQueryWrapper<AiTask> qw = new LambdaQueryWrapper<>();
        qw.setEntity(graphTask);
        List<AiTask> list = taskService.list(qw);
        for (AiTask task : list) {
            String corn = CronUtils.getCron(new Date(), task.getTaskMode(), task.getTimeHour(), task.getTimeMinute(), task.getTimeSecond(), task.getCronText());
            task.setCronChinese(CronExpParserUtil.translateToChinese(corn));
        }
        return ResultTool.success(list);
    }

    @PostMapping("/selTaskInfo")
    @Operation(summary  = "查询定时任务详情")
    @Transactional(rollbackFor=Exception.class)
    public JsonResult<AiTask> selTaskInfo(String id){
        if (StringUtils.isBlank(id)) {
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
        AiTask task = taskService.getById(id);
        return ResultTool.success(task);
    }

    @PostMapping("/enableTask")
    @Operation(summary  = "启用定时任务")
    @Transactional(rollbackFor=Exception.class)
    public JsonResult enableTask(String id){
        if (StringUtils.isBlank(id)) {
            return ResultTool.fail(ResultCode.PARAM_NOT_COMPLETE);
        }
        AiTask task = taskService.getById(id);
        task.setEnabled(SchemaUsedEnum.TASK_ENABLED.getIndex());
        taskService.updateById(task);
        String corn = CronUtils.getCron(new Date(), task.getTaskMode(), task.getTimeHour(), task.getTimeMinute(), task.getTimeSecond(), task.getCronText());
        if (!CronUtils.isValid(corn)) {
            return ResultTool.fail(ResultCode.PARAM_NOT_CHECK);
        }
        quartzManager.addJob(AiTask.generateJobName(1, id), AiTask.generateJobName(2, id), AiTask.generateJobName(3, id), AiTask.generateJobName(4, id), ScheduledJob.class, corn, task.getId());
        return ResultTool.success();
    }

    @PostMapping("/ceaseTask")
    @Operation(summary  = "停止定时任务")
    @Transactional(rollbackFor=Exception.class)
    public JsonResult ceaseTask(String id){
        if (StringUtils.isBlank(id)) {
            return ResultTool.fail(ResultCode.PARAM_NOT_COMPLETE);
        }
        AiTask task = taskService.getById(id);
        task.setEnabled(SchemaUsedEnum.TASK_DISABLE.getIndex());
        taskService.updateById(task);
        String corn = CronUtils.getCron(new Date(), task.getTaskMode(), task.getTimeHour(), task.getTimeMinute(), task.getTimeSecond(), task.getCronText());
        if (!CronUtils.isValid(corn)) {
            return ResultTool.fail(ResultCode.PARAM_NOT_CHECK);
        }
        quartzManager.removeJob(AiTask.generateJobName(1, id), AiTask.generateJobName(2, id), AiTask.generateJobName(3, id), AiTask.generateJobName(4, id));
        return ResultTool.success();
    }

    @Hidden
    @PostMapping("/enableAllTask")
    @Operation(summary  = "启用所有定时任务")
    @Transactional(rollbackFor=Exception.class)
    public JsonResult enableAllTask(){
        AiTask task = new AiTask();
        task.setEnabled(SchemaUsedEnum.TASK_ENABLED.getIndex());
        taskService.update(task,new LambdaQueryWrapper<>());
        quartzManager.startJobs();
        return ResultTool.success();
    }

    @Hidden
    @PostMapping("/ceaseAllTask")
    @Operation(summary  = "停止所有定时任务")
    @Transactional(rollbackFor=Exception.class)
    public JsonResult ceaseAllTask(){
        AiTask task = new AiTask();
        task.setEnabled(SchemaUsedEnum.TASK_DISABLE.getIndex());
        taskService.update(task,new LambdaQueryWrapper<>());
        quartzManager.shutdownJobs();
        return ResultTool.success();
    }
}
