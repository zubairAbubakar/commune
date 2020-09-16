package com.zlab.commune.api.member.entity;

import javax.persistence.*;


@Entity
@Table(name = "member_detail")
public class MemberDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "youtube_channel")
    private String youtubeChannel;


//    @OneToOne(mappedBy = "memberDetail", cascade = CascadeType.ALL)
//    private Member member;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYoutubeChannel() {
        return youtubeChannel;
    }

    public void setYoutubeChannel(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

//    public Member getMember() {
//        return member;
//    }
//
//    public void setMember(Member member) {
//        this.member = member;
//    }

    public MemberDetail(){

    }

    public MemberDetail(String youtubeChannel) {
        this.youtubeChannel = youtubeChannel;
    }

    @Override
    public String toString() {
        return "MemberDetail{" +
                "id=" + id +
                ", youtubeChannel='" + youtubeChannel + '\'' +
                '}';
    }
}
