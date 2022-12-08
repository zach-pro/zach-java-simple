package com.zach.modular.quartz.task_journal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zach.common.constant.SchemaUsedEnum;
import com.zach.modular.quartz.task_journal.entity.AiTaskJournal;
import com.zach.modular.quartz.task_journal.mapper.AiTaskJournalMapper;
import com.zach.modular.quartz.task_journal.service.AiTaskJournalService;
import com.zach.modular.quartz.task_journal.vo.reqeust.AiChangeTaskJournalRequest;
import com.zach.modular.quartz.task_journal.vo.reqeust.AiTaskJournalRequest;
import com.zach.modular.quartz.task_journal.vo.response.AiTaskJournalResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author apple
 * 
 */
@Service
public class AiTaskJournalServiceImpl extends ServiceImpl<AiTaskJournalMapper, AiTaskJournal> implements AiTaskJournalService {
    private final AiTaskJournalMapper aiTaskJournalMapper;
    public AiTaskJournalServiceImpl(AiTaskJournalMapper aiTaskJournalMapper) {
        this.aiTaskJournalMapper = aiTaskJournalMapper;
    }

    @Override
    public List<AiTaskJournalResponse> selectTaskJournalList(AiTaskJournalRequest taskJournalRequest) {
        return aiTaskJournalMapper.selectTaskJournalList(taskJournalRequest);
    }

    @Override
    public String saveTaskJournal(AiTaskJournalRequest taskJournalRequest) {
        AiTaskJournal journalJournal = new AiTaskJournal();
        journalJournal.setTaskId(taskJournalRequest.getTaskId());
        journalJournal.setTaskMode(taskJournalRequest.getTaskMode());
        journalJournal.setTaskStatus(SchemaUsedEnum.TASK_STATUS_RUNNING.getIndex());
        journalJournal.setStartTime(LocalDateTime.now().toString());
        journalJournal.setTaskBatch(1);
        journalJournal.preInsert();
        aiTaskJournalMapper.insert(journalJournal);
        return journalJournal.getId();
    }

    @Override
    public void updateTaskJournal(AiChangeTaskJournalRequest changeTaskJournalRequest) {
        AiTaskJournal journalJournal = new AiTaskJournal();
        journalJournal.setId(changeTaskJournalRequest.getId());
        journalJournal.setTaskStatus(changeTaskJournalRequest.getTaskStatus());
        journalJournal.setEndTime(LocalDateTime.now().toString());
        aiTaskJournalMapper.updateById(journalJournal);
    }

    @Override
    public String selectJournalAfterUp(AiTaskJournalRequest taskJournalRequest) {
        List<AiTaskJournalResponse> graphTaskJournalResponses = aiTaskJournalMapper.selectTaskJournalList(taskJournalRequest);
        if (!graphTaskJournalResponses.isEmpty()) {
            AiTaskJournalResponse journal = graphTaskJournalResponses.get(0);
            aiTaskJournalMapper.updateTaskBatchAddOne(journal.getId());
            return journal.getId();
        }
        return null;
    }
}
