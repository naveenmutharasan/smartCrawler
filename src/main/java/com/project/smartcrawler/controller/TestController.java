package com.project.smartcrawler.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/test"})
public class TestController {
	@GetMapping(value = {""})
	public String index1() {
		return "Test";
	}
}
