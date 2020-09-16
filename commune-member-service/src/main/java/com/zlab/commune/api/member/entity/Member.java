package com.zlab.commune.api.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Member")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Member implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="member_id", nullable = false, unique = true)
    private String memberId;

    @Column(name = "encrypted_password", nullable = false)
    private String encryptedPassword;

    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "required")
    @Size(min = 1, message = "required")
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "country")
    private String country;

    //private LinkedHashMap<String, String> countryOptions;

    @Column(name = "gender")
    private String gender;

//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
//    @JoinTable(name = "community_member",
//            joinColumns = @JoinColumn(name = "member_id"),
//            inverseJoinColumns = @JoinColumn(name = "community_id"))
//    private List<Community> communities;

    /*private String[] interests; */

    /*@NotNull(message = "required")
    @Min(value = 0, message = "Must be greater than or equal to zero(0)")
    @Max(value = 10, message = "Must be less than or equal to 10")
    private Integer freepasses; */

    /*@Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 chars/digits")
    private String postalCode; */

    /*@ReferralCode(value={"REF", "LUV"}, message="must start with REF or LUV")
    private String referralCode; */

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "member_detail_id")
//    private MemberDetail memberDetail;



    public Member(){

        /*countryOptions = new LinkedHashMap<>();
        countryOptions.put("NIG", "Nigeria");
        countryOptions.put("GHA", "Ghana");
        countryOptions.put("CAN", "Canada"); */

    }

    public Member(String firstName, @NotNull(message = "required") @Size(min = 1, message = "required")
                  String lastName, String email, String country, String gender) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

//    public MemberDetail getMemberDetail() {
//        return memberDetail;
//    }
//
//    public void setMemberDetail(MemberDetail memberDetail) {
//        this.memberDetail = memberDetail;
//    }

//    public List<Community> getCommunities() {
//        return communities;
//    }
//
//    public void setCommunities(List<Community> communities) {
//        this.communities = communities;
//    }
//
//
//    public void addCommunity(Community community){
//
//        if(communities == null){
//            communities = new ArrayList<>();
//        }
//
//        communities.add(community);
//    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
