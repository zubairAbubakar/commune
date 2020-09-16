package com.zlab.commune.api.member.controller;


import com.zlab.commune.api.member.entity.Member;
import com.zlab.commune.api.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;


@Controller
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        memberService = memberService;
    }

    private List<Member> members;

    @PostConstruct
    public void loadData(){

    }


    @GetMapping("/list")
    public String listMember(Model model){

        members = memberService.findAll();

        model.addAttribute("members", members);

        return "members/list-members";
    }

    @GetMapping("/showFormForAdd")
    public String showForm(Model model){

        //create member object
        Member member = new Member();
        model.addAttribute("member", member);

        return "members/member-form";
    }

    @PostMapping("/save")
    public String saveMember(@ModelAttribute("member") Member member){

        memberService.save(member);

        return "redirect:/members/list";
    }

    @GetMapping("/showFromForUpdate")
    public String showFormForUpdate(@RequestParam("memberId") int memberId, Model model){

        Member member = memberService.findById(memberId);
        model.addAttribute("member", member);

        return "members/member-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("memberId") int memberId) {

        Member member = memberService.findById(memberId);

        if (member == null){
            throw new MemberNotFoundException("Member with Member ID: "+memberId+" not found");
        }

        memberService.deleteById(memberId);

        return "redirect:/members/list";
    }

}
