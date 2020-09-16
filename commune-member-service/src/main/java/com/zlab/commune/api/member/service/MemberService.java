package com.zlab.commune.api.member.service;

import com.zlab.commune.api.member.entity.Member;
import java.util.List;


public interface MemberService {

    public List<Member> findAll();

    public void save(Member member);

    public Member findById(int memberId);

    public void deleteById(int memberId);

    //public List<Member> searchMember(String theSearchName);
}
