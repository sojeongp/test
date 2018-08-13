package com.login.service;

import com.login.model.Member;
import com.login.model.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    MemberDao memberDao;
    Member member;

    //1. 회원 로그인체그
    @Override
    public boolean loginCheck(String id) throws SQLException, ClassNotFoundException {
        boolean result = this.memberDao.findId(id);
        return result;
    }

    //2. 회원 로그인 정보
    @Override
    public Member viewMember(String id, HttpSession session) {

        try {
            this.member = this.memberDao.findMember(id);
            session.setAttribute("id", member.getId());
            session.setAttribute("name", member.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.member;
    }

    //3. 회원 로그아웃
    @Override
    public void logout(HttpSession session) {
        //세션 초기화
        session.invalidate();
    }
}
