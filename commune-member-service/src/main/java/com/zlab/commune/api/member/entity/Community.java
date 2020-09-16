package com.zlab.commune.api.member.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="community")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;


//    @ManyToMany(fetch = FetchType.LAZY,
//                cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
//    @JoinTable(name = "community_member",
//               joinColumns = @JoinColumn(name = "community_id"),
//               inverseJoinColumns = @JoinColumn(name = "member_id"))
//    private List<Member> members;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "community_id")
    private List<Review> reviews;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Community() {

    }

    public Community(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public List<Member> getMembers() {
//        return members;
//    }
//
//    public void setMembers(List<Member> members) {
//        this.members = members;
//    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Community{" +
                "name='" + name + '\'' +
                '}';
    }

    public void addReview(Review review){

        if(reviews == null){
            reviews = new ArrayList<>();
        }

        reviews.add(review);
    }

//    public void addMember(Member member){
//
//        if(members == null){
//            members = new ArrayList<>();
//        }
//
//        members.add(member);
//    }

}
