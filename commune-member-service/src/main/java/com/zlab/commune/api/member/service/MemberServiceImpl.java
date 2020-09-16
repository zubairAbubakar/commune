package com.zlab.commune.api.member.service;

import com.zlab.commune.api.member.dao.MemberRepository;
import com.zlab.commune.api.member.entity.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository theMemberRepository){

        memberRepository = theMemberRepository;
    }


    @Override
    @Transactional
    public List<Member> findAll() {
        return memberRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    @Transactional
    public void save(Member member) {

        memberRepository.save(member);

    }

    @Override
    @Transactional
    public Member findById(int memberId) {

        Optional<Member> result = memberRepository.findById(memberId);

        Member theMember = null;
        if(result.isPresent()){
            theMember = result.get();
        }
        else {
            throw new RuntimeException("Did now find member with ID: "+memberId);
        }
        return theMember;
    }

    @Override
    @Transactional
    public void deleteById(int memberId) {

        memberRepository.deleteById(memberId);
    }

//    @Override
//    @Transactional
//    public List<Member> searchMember(String firstName) {
//
//        return memberRepository.findAll(firstName);
//    }
}
