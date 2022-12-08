package com.zach.modular.quartz.task_journal.controller;


import com.zach.common.constant.ResultCode;
import com.zach.common.vo.JsonResult;
import com.zach.common.vo.ResultTool;
import com.zach.modular.quartz.task_journal.service.impl.AiTaskJournalServiceImpl;
import com.zach.modular.quartz.task_journal.vo.reqeust.AiTaskJournalRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author apple
 * 
 */
@Tag(name = "任务日志模块", description = "任务日志模块")
@RestController
@RequestMapping("/ai_task_journal/aiTaskJournal")
public class AiTaskJournalController {
    private final AiTaskJournalServiceImpl aiTaskJournalService;
    public AiTaskJournalController(AiTaskJournalServiceImpl aiTaskJournalService) {
        this.aiTaskJournalService = aiTaskJournalService;
    }

    @PostMapping("/selTaskJournalLists")
    @Operation(summary  = "查询任务日志列表")
    public JsonResult selTaskJournalLists(@RequestBody AiTaskJournalRequest journal){
        if (StringUtils.isBlank(journal.getTaskId())) {
            return ResultTool.fail(ResultCode.PARAM_IS_BLANK);
        }
        return ResultTool.success(aiTaskJournalService.selectTaskJournalList(journal));
    }
}
