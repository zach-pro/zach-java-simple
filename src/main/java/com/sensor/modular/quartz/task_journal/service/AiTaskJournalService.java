package com.sensor.modular.quartz.task_journal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sensor.modular.quartz.task_journal.entity.AiTaskJournal;
import com.sensor.modular.quartz.task_journal.vo.reqeust.AiChangeTaskJournalRequest;
import com.sensor.modular.quartz.task_journal.vo.reqeust.AiTaskJournalRequest;
import com.sensor.modular.quartz.task_journal.vo.response.AiTaskJournalResponse;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyh
 * @since 2022-08-26
 */
public interface AiTaskJournalService extends IService<AiTaskJournal> {
    List<AiTaskJournalResponse> selectTaskJournalList(AiTaskJournalRequest taskJournalRequest);
    String saveTaskJournal(AiTaskJournalRequest taskJournalRequest);
    void updateTaskJournal(AiChangeTaskJournalRequest changeTaskJournalRequest);
    String selectJournalAfterUp(AiTaskJournalRequest taskJournalRequest);
}
