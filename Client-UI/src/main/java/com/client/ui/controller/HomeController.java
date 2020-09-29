package com.client.ui.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.client.ui.model.Category;
import com.client.ui.model.Product;
import com.client.ui.model.User;
import com.client.ui.service.CategoryService;
import com.client.ui.service.ProductService;
import com.client.ui.service.UserService;

@Controller
public class HomeController {
	
	private static boolean condition = true;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	Long userSession = null;
	
	//Home Page
	@GetMapping({"index", "/"})
	public String index(Model model, HttpSession session) {
		Long sessionId = (Long) session.getAttribute("MY_SESSION");
		if (sessionId != null) {
			model.addAttribute("userId",sessionId);
			User user = userService.getUser(sessionId);
			int items = user.getProductList().size();
			model.addAttribute("items", items);
		}
		List<Product> productList = productService.listProduct();
		//model.addAttribute("categoryList", categoryService.listCategory());
		model.addAttribute("productList", productList);
		return "index";
		
	}
	
	@GetMapping("allProduct")
	public String allProduct(Model model, HttpSession session) {
		Long sessionId = (Long) session.getAttribute("MY_SESSION");
		if (sessionId != null) {
			model.addAttribute("userId",sessionId);
			User user = userService.getUser(sessionId);
			int items = user.getProductList().size();
			model.addAttribute("items", items);
		}
		model.addAttribute("productList", productService.listProduct());
		model.addAttribute("categoryList", categoryService.listCategory());
		//model.addAttribute("data-seq", true);
		return "product";
	}
	
	@GetMapping("getProduct/{categoryId}")
	public String getProductFromCategory(@PathVariable("categoryId")String categoryId, Model model, HttpSession session) {
		//long categoryLongId = Long.parseLong(categoryId);
		Long sessionId = (Long) session.getAttribute("MY_SESSION");
		if (sessionId != null) {
			model.addAttribute("userId",sessionId);
			User user = userService.getUser(sessionId);
			int items = user.getProductList().size();
			model.addAttribute("items", items);
		}
		Category tempList = productService.findByCategory(categoryId);
		model.addAttribute("productList", tempList.getProductList());
		model.addAttribute("categoryList", categoryService.listCategory());
		return "product";
	}
	
	@PostMapping("signup")
	public String signUp(@ModelAttribute("user") User user,
			HttpSession session, HttpServletRequest request, Model model) {
		Long sessionId = (Long) session.getAttribute("MY_SESSION");
		if (sessionId != null) {
			User user1 = userService.getUser(sessionId);
			int items = user1.getProductList().size();
			model.addAttribute("items", items);
			return "/index";
		}
		 
		userService.save(user);
		userSession = user.getUserId();
		request.getSession().setAttribute("MY_SESSION", userSession);
		return "account";
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@PostMapping("login")
	public String login(@ModelAttribute("user") User user, Model model, HttpServletRequest request) {
		String useremail = user.getEmail();
		String password = user.getPassword();
		
		User[] allUser = userService.getAllUser();
		for(User u: allUser) {
			if(u.getEmail().equals(useremail) && u.getPassword().equals(password)) {
				condition = false;
				model.addAttribute("productList", productService.listProduct());
				model.addAttribute("categoryList", categoryService.listCategory());
				model.addAttribute("condition", condition);
				userSession = u.getUserId();
				request.getSession().setAttribute("MY_SESSION", userSession);
				model.addAttribute("userId",userSession);
				int items = u.getProductList().size();
				model.addAttribute("items", items);
				return "product";
			}
		}
		return "account";
	}
	
	@GetMapping("contact")
	public String contact(Model model, HttpSession session) {
		Long sessionId = (Long) session.getAttribute("MY_SESSION");
		if (sessionId != null) {
			User user = userService.getUser(sessionId);
			model.addAttribute("userId",sessionId);
			int items = user.getProductList().size();
			model.addAttribute("items", items);
		}
		return "contact";
		
	}
	
	@GetMapping("logout")
	public String logout(Model model, HttpServletRequest request) {
		model.addAttribute("condition", true);
		request.getSession().invalidate();
		return "index";
		
	}
	
	@GetMapping("register")
	public ModelAndView register(HttpSession session) {
		ModelAndView mv = new ModelAndView("account");
		Long sessionId = (Long) session.getAttribute("MY_SESSION");
		if (sessionId != null) {
			mv.addObject("userId",sessionId);
			mv = new ModelAndView("/");
			return mv;
		}
		return mv;
		
	}
	

}
