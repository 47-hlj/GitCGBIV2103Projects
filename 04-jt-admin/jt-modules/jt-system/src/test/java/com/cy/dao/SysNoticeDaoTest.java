package com.cy.dao;

import com.cy.common.domain.SysNotice;
import com.cy.system.dao.SysNoticeDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class SysNoticeDaoTest {

    @Autowired
    private SysNoticeDao sysNoticeDao;

    @Test
    void testDeleteById(){
        int rows=sysNoticeDao.deleteById(40L,41L);
        log.debug("delete.rows {} ",rows);
    }

    @Test
    void testSelectById(){
        SysNotice notice=sysNoticeDao.selectById(1L);
        log.info("notice->{}",notice);
    }

    @Test
    void testSelectNotices(){
        SysNotice notice =new SysNotice();
        notice.setTitle("title");
        notice.setType("1");
        List<SysNotice> noticeList=sysNoticeDao.selectNotices(notice);
        System.out.println(noticeList.size());
//        for(SysNotice n:noticeList){
//            System.out.println(n);
//        }
//        JDK8
//        noticeList.forEach(item-> System.out.println(item));
//        noticeList.forEach(new Consumer<SysNotice>() {
//            @Override
//            public void accept(SysNotice sysNotice) {
//                System.out.println(sysNotice);
//            }
//        });
    }

    @Test
    public void test(){
        double a=27;
        a%=-3d;
        a+=10f;
        a*=-4;
        System.out.println(a);
    }
}

