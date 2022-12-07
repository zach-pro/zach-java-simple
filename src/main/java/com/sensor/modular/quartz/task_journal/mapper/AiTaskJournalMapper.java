package com.sensor.modular.quartz.task_journal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sensor.modular.quartz.task_journal.entity.AiTaskJournal;
import com.sensor.modular.quartz.task_journal.vo.reqeust.AiTaskJournalRequest;
import com.sensor.modular.quartz.task_journal.vo.response.AiTaskJournalResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author apple
 * 
 */
@Mapper
public interface AiTaskJournalMapper extends BaseMapper<AiTaskJournal> {
    /**
     * @param id
     * @return 返回单条记录详情
     */
    AiTaskJournalResponse selectTaskJournal(String id);


    /**
     * @param taskJournalRequest
     * @return 返回任务的执行记录
     */
    List<AiTaskJournalResponse> selectTaskJournalList(AiTaskJournalRequest taskJournalRequest);

    /**
     * @param id
     * 更新自动批次号自增1
     */
    void updateTaskBatchAddOne(@Param("id") String id);
}
