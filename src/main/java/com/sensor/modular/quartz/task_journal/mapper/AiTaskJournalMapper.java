package com.sensor.modular.quartz.task_journal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sensor.modular.quartz.task_journal.entity.AiTaskJournal;
import com.sensor.modular.quartz.task_journal.vo.reqeust.AiTaskJournalRequest;
import com.sensor.modular.quartz.task_journal.vo.response.AiTaskJournalResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zyh
 * @since 2022-08-26
 */
@Mapper
public interface AiTaskJournalMapper extends BaseMapper<AiTaskJournal> {
    AiTaskJournalResponse selectTaskJournal(String id);
    List<AiTaskJournalResponse> selectTaskJournalList(AiTaskJournalRequest taskJournalRequest);
    void updateTaskBatchAddOne(String id);
}
