package com.cy.jt.system.service.impl;

import com.cy.jt.system.dao.SysNoticeDao;
import com.cy.jt.common.domain.SysNotice;
import com.cy.jt.system.service.SysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysNoticeServiceImpl implements SysNoticeService {
    @Autowired
    private SysNoticeDao sysNoticeDao;

    @Override
    public List<SysNotice> selectNotices(SysNotice notice) {
        return sysNoticeDao.selectNotices(notice);
    }

    @Override
    public SysNotice seleceById(Long id) {
        return sysNoticeDao.selectById(id);
    }

    @Override
    public int insertNotice(SysNotice notice) {
        return sysNoticeDao.insertNotice(notice);
    }

    @Override
    public int updateNotice(SysNotice notice) {
        return sysNoticeDao.updateNotice(notice);
    }

    @Override
    public int deleteById(Long ...id) {
        return sysNoticeDao.deleteById(id);
    }
}
