package com.zlab.commune.api.member.controller;


import com.zlab.commune.api.member.entity.Member;
import com.zlab.commune.api.member.model.AdModel;
import com.zlab.commune.api.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.util.List;


@Controller
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

//    @Autowired
//    RestTemplate restTemplate;

    @Autowired
    WebClient webClient;

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

    @GetMapping("/ads")
    public String getAds(Model model, @AuthenticationPrincipal OidcUser principal) {

//        System.out.println("Principal = "+principal);
//
//        OidcIdToken idToken = principal.getIdToken();
//        String idTokenValue = idToken.getTokenValue();
//        System.out.println("ID Token = "+idTokenValue);
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
//
//        OAuth2AuthorizedClient oauth2Client = oAuth2AuthorizedClientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(),
//                oauthToken.getName());
//
//        String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
//        System.out.println("JWT Access Token: "+jwtAccessToken);

        String adsUrl = "http://localhost:9019/users/1/ads";
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Authorization", "Bearer "+jwtAccessToken);
//
//        HttpEntity<List<AdModel>> entity = new HttpEntity<>(headers);
//
//        ResponseEntity<List<AdModel>> responseEntity = restTemplate.exchange(adsUrl, HttpMethod.GET, entity,
//                new ParameterizedTypeReference<List<AdModel>>() {});



        List<AdModel> ads = webClient.get()
                .uri(adsUrl)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<AdModel>>() {})
                .block();

        for(AdModel ad : ads){
            System.out.println("\n\nAd Name: "+ad.getName());
            System.out.println("Ad Description: "+ad.getDescription());
        }

        model.addAttribute("ads", ads);

        return "members/list-ads";
    }

}
