package com.zlab.commune.api.member.dao;

import com.zlab.commune.api.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import java.util.List;

@RepositoryRestResource(path = "members")
public interface MemberRepository extends JpaRepository<Member, Integer> {

    public List<Member> findAllByOrderByLastNameAsc();
}
