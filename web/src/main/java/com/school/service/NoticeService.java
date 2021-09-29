package com.school.service;

import com.school.pojo.Notice;

/**
 * 寻物启事相关service
 *
 * @author hnuer
 */
public interface NoticeService {

    /**
     * 增加一条寻物启事
     * @param notice
     * @return 操作结果
     */
    public int addNotice(Notice notice);

    /**
     * 通过寻物启事的id删除帖子
     * @param noticeId
     * @return 操作结果
     */
    public int removeNoticeById(Long noticeId);

}
