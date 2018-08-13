package com.login.service;

import com.login.model.Member;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public interface MemberService {

    // 1. 회원 로그인 체크
    public boolean loginCheck(String id) throws SQLException, ClassNotFoundException;

    // 2. 회원 로그인 정보
    public Member viewMember(String id, HttpSession session);

    // 3. 회원 로그아웃
    public void logout(HttpSession session);
}
