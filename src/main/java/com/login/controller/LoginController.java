package com.login.controller;

import com.login.model.Member;
import com.login.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;


@Controller
public class LoginController  {

    @Autowired
    MemberService memberService;
    Member member;

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/loginCheck")
    public String lgoinCheck(@RequestParam String id, @RequestParam String pw, Model model, HttpSession session) throws SQLException, ClassNotFoundException {

        boolean rs = this.memberService.loginCheck(id);

        if(rs) {
            this.member = this.memberService.viewMember(id, session);
            if(this.member != null ) {
                if (this.member.getPassword().equals(pw)) {
                    model.addAttribute("id", this.member.getId());
                    model.addAttribute("password", this.member.getPassword());
                    model.addAttribute("name", this.member.getName());
                    model.addAttribute("sessionId", session.getAttribute("id"));
                    model.addAttribute("sessionName", session.getAttribute("name"));
                    return "loginOk";
                } else {
                    model.addAttribute("msg", "비밀번호 불일치. 다시 시도해주세요");
                    return "login";
                }
            } else {
                return "error";
            }
        } else {
            model.addAttribute("msg", "존재하지않는 아이디. 회원가입 필요");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession, Model model) {
        this.memberService.logout(httpSession);
        return "login";
    }
}
