package com.cy.system.service;

import com.cy.common.domain.SysNotice;

import java.util.List;

/**
 * 业务逻辑处理
 */
public interface SysNoticeService {

    List<SysNotice> selectNotices(SysNotice notice);

    SysNotice seleceById(Long id);

    int insertNotice(SysNotice notice);

    int updateNotice(SysNotice notice);

    int deleteById(Long ...id);

}
