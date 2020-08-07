package com.springbook.board.user;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbook.board.common.Const;
import com.springbook.board.common.KakaoAuth;
import com.springbook.board.common.KakaoUserInfo;
import com.springbook.board.common.MyUtils;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}	
	
	@RequestMapping(value="/loginPost", method=RequestMethod.POST)
	public String login(UserVO param, HttpSession hs) {
		
		return "user/login";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(Model model, @RequestParam(required=false) String err) {
		
		if(err != null) {
			model.addAttribute("msg", err);
		}
		
		return "user/join";
	}
	
	@RequestMapping(value="/joinPost", method=RequestMethod.POST)
	public String join(UserVO param, HttpSession hs, RedirectAttributes ra) {
		String rNumbers = (String)hs.getAttribute("rNumbers");
		if(!rNumbers.equals(param.getPhAuthNumber())) {
			ra.addAttribute("err", "인증번호가 맞지 않습니다.");
			return "redirect:/user/join";
		}
		
		int result = service.join(param);
		
		
		return "redirect:/user/login";
	}
	
	@ResponseBody
	@RequestMapping(value="/phAuth", method=RequestMethod.GET)
	public Map<String, Object> phAuth(@RequestParam String ph, HttpSession hs) {
		System.out.println("ph : " + ph);	
		int len =  6; //인증번호길이
		String rNumbers = MyUtils.makeRandomNumber(len);
		System.out.println("rNumbers: " + rNumbers);
		//ph번호로 인증번호를 문자로 보낸다.
		
		hs.setAttribute("rNumbers", rNumbers);
		
		Map<String, Object> map = new HashMap();
		map.put("result", 1);
		
		return map;
	}
	
	//인증코드 받기 (요청)
	@RequestMapping(value="/loginKAKAO", method=RequestMethod.GET)
	public String loginKAKAO() {
		String uri = String.format("redirect:https://kauth.kakao.com/oauth/authorize?"
				+ "client_id=%s&redirect_uri=%s&response_type=code"
				, Const.KAKAO_CLIENT_ID, Const.KAKAO_AUTH_REDIRECT_URI);
		return uri;
	}
	
	//인증코드 받기 (응답)
	@RequestMapping(value="/joinKakao", method=RequestMethod.GET)
	public String joinKAKAO(@RequestParam(required=false) String code,
			@RequestParam(required=false) String error,
			HttpSession hs) {
		
		System.out.println("code : " + code); //인증코드!!!
		System.out.println("error : " + error);
		
		if(code == null) {			
			return "redirect:/user/login";
		}		
		int result = service.kakaoLogin(code, hs);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession hs) {
		hs.invalidate();
		return "redirect:/user/login";
	}
	
}