package com.example.controller;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.application.service.UserApplicationService;
import com.example.form.GroupOrder;
import com.example.form.SignupForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class SignupController {
	@Autowired
	private UserApplicationService userApplicationService;


	@GetMapping("/signup")
	public String getSignup(Model model, Locale locale, @ModelAttribute SignupForm form) {
		Map<String, Integer> genderMap = userApplicationService.getGenderMap(locale);
		model.addAttribute("genderMap",genderMap);

		return "user/signup";

	}

	@PostMapping("/signup")
	public String postSignup(Model model, Locale locale,
			@ModelAttribute @Validated(GroupOrder.class) SignupForm form, BindingResult bindingResult) {

		if(bindingResult.hasErrors()) {
			//NG: ユーザ登録画面に戻る
			return getSignup(model,locale, form);
		}

		log.info(form.toString());




		return "redirect:/login";
	}

}
