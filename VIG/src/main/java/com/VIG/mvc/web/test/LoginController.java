package com.VIG.mvc.web.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.social.google.connect.GoogleConnectionFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.VIG.mvc.service.domain.ImageColor;
import com.VIG.mvc.service.domain.ImageKeyword;
import com.VIG.mvc.service.domain.KakaoUserInfo;
import com.VIG.mvc.service.domain.Kakao_restapi;
import com.VIG.mvc.service.domain.User;

import com.VIG.mvc.util.Translater;
import com.VIG.mvc.util.VisionInfo;
import com.fasterxml.jackson.databind.JsonNode;







@Controller
@RequestMapping("/kakao*")
public class LoginController {
	 private Kakao_restapi kakao_restapi=new Kakao_restapi();
	
	
	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;
	private ServletContext context;	
	

	
	public LoginController() {
		// TODO Auto-generated constructor stub
		
	}	
//	@RequestMapping(value = "/memberloginform", method = RequestMethod.GET) 
//	public ModelAndView memberLoginForm(HttpSession session) { 
//		ModelAndView mav = new ModelAndView(); /* 네아로 인증 URL을 생성하기 위하여 getAuthorizationUrl을 호출 */ 
//	  String kakaoUrl = first.getAuthorizationUrl(session); /* 생성한 인증 URL을 View로 전달 */ 
//	  mav.setViewName("memberloginform"); 
//	  mav.addObject("kakao_url", kakaoUrl); 
//	  return mav; 
//	  
//	}

	
	 @RequestMapping(value="/oauth",method= RequestMethod.GET)
	 public String kakaoConnect() {

	  StringBuffer url = new StringBuffer();
	  url.append("https://kauth.kakao.com/oauth/authorize?");
	  url.append("client_id=" + "3115db8a0ecbf7f689771a807b66263b");
	  url.append("&redirect_uri=http://127.0.0.1:8080/VIG/kakao/login"); 
	  url.append("&response_type=code");

	  	System.out.println("유알엘"+url);
	  
	  return "redirect:" + url.toString();
	 }
	 
	 @RequestMapping(value="/login",produces="application/json",method= {RequestMethod.GET, RequestMethod.POST})
	 public String kakaoLogin(@RequestParam("code")String code,RedirectAttributes ra,HttpSession session,HttpServletResponse response )throws IOException {
	  
	  
	  System.out.println("kakao code:"+code); 

	  System.out.println("kakao code:"+code);
	  JsonNode access_token=kakao_restapi.getKakaoAccessToken(code); 
	  System.out.println(access_token);
	  
	  
	  JsonNode userInfo = KakaoUserInfo.getKakaoUserInfo(access_token.get("access_token"));

       
      String member_id = userInfo.get("id").asText();

      String member_name = null;
      String email=null;
       //유저정보 카카오에서 가져오기 Get properties
      JsonNode properties = userInfo.path("properties");
      JsonNode kakao_account = userInfo.path("kakao_account");
      member_name = properties.path("nickname").asText(); //이름 정보 가져오는 것
      email = kakao_account.path("email").asText();
      if(member_name!=null) {
       session.setAttribute("isLogOn",true);
       session.setAttribute("member_id",member_name);        
      }
      System.out.println("id : " + member_id);    
      System.out.println("name : " + member_name);
      System.out.println("email : " + email);
	  
	  
	  return "forward:/login.jsp";
	 }
}

	
	


	
	


	
