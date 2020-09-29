package com.client.ui.controller;

import java.security.Principal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.client.ui.model.Ordercart;
import com.client.ui.model.Product;
import com.client.ui.model.User;
import com.client.ui.service.OrderService;
import com.client.ui.service.ProductService;
import com.client.ui.service.UserService;

@RestController
@RequestMapping("profile")
public class ProfileController {

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderService orderService;
	
	Long userSession = null;
	
	

	private int findSum(User user) {
		List<Product> list = user.getProductList();
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			Product p = list.get(i);
			sum += p.getProductPrice();
		}
		return sum;
	}
	
	@GetMapping("cart-product")
	public ModelAndView cart(HttpSession session) {
		ModelAndView mv = new ModelAndView("cart");
		Long sessionId = (Long) session.getAttribute("MY_SESSION");
		if (sessionId == null) {
			mv = new ModelAndView("account");
			return mv;
		}
		mv.addObject("userId",sessionId);
		User user = userService.getUser(sessionId);
		mv.addObject("user", user);
		int items = user.getProductList().size();
		mv.addObject("items", items);
		int total = findSum(user);
		mv.addObject("total", total);
		return mv;
	}
	
	@GetMapping("addToCart/{productId}")
	public ModelAndView addToCart(@PathVariable("productId") String productId, HttpSession session) {
		ModelAndView mv = new ModelAndView("cart");
		Long sessionId = (Long) session.getAttribute("MY_SESSION");
		if (sessionId == null) {
			mv = new ModelAndView("account");
			return mv;
		}

		User user = userService.getUser(sessionId);
		// long productLongId = Long.parseLong(productId);
		Product product = productService.getProductById(productId).getBody();

		List<Product> productList = new ArrayList<Product>();
		productList.add(product);
		user.setProductList(productList);

		List<User> userList = new ArrayList<>();
		userList.add(user);
		product.setUserList(userList);

		userService.update(user);
		productService.addProduct(product);
		int total = findSum(user);
		mv.addObject("userId",sessionId);
		mv.addObject("total", total);
		mv.addObject("user", user);
		int items = user.getProductList().size();
		mv.addObject("items", items);
		return mv;
	}
	
	/*
	 * @GetMapping("/remove/{productId}") public ModelAndView
	 * removeCartproduct(@PathVariable("productId") String productId, HttpSession
	 * session) { ModelAndView mv = new ModelAndView("cart"); Long sessionId =
	 * (Long) session.getAttribute("MY_SESSION"); if(sessionId != null) { User user
	 * = userService.getUser(sessionId); List<Product> cartList =
	 * user.getProductList(); Product product =
	 * productService.getProductById(productId).getBody(); cartList.remove(product);
	 * user.setProductList(cartList); int total = findSum(user); int items =
	 * user.getProductList().size(); mv.addObject("items", items);
	 * mv.addObject("total", total); mv.addObject("user", user); } return mv; }
	 */

	@GetMapping("remove/{productId}")
	public ModelAndView deleteProduct(HttpSession session, @PathVariable("productId") String productId) {
		ModelAndView mv = new ModelAndView("cart");
		Long sessionId = (Long) session.getAttribute("MY_SESSION");
		if (sessionId == null) {
			mv = new ModelAndView("cart");
			return mv;
		}

		User user = userService.getUser(sessionId);
		long productLongId = Long.parseLong(productId);
		// Product product = productService.getProductById(productLongId).get();
		List<Product> productList = new ArrayList<Product>();
		productList = user.getProductList();
		for (int i = 0; i < productList.size(); i++) {
			if (productList.get(i).getProductId() == productLongId) {
				productList.remove(i);
			}
		}
		user.setProductList(productList);
		userService.updateList(user);
		int total = findSum(user);
		int items = user.getProductList().size();
		mv.addObject("userId",sessionId);
		mv.addObject("items", items);
		mv.addObject("total", total);
		mv.addObject("user", user);
		return mv;
	}

	@GetMapping("checkout")
	public ModelAndView checkoutPage(HttpSession session , @ModelAttribute Ordercart order) {// , @ModelAttribute Ordercart order) {
		ModelAndView mv = new ModelAndView("checkout");
		Long sessionId = (Long) session.getAttribute("MY_SESSION");
		if (sessionId == null) {
			mv = new ModelAndView("account");
			return mv;
		}
		Ordercart ordercart = new Ordercart();
		mv.addObject("order", ordercart);
		User user = userService.getUser(sessionId);
		System.out.println(user.getEmail());
		
		mv.addObject("user", user);
		mv.addObject("userId",sessionId);
		int total = findSum(user);
		int items = user.getProductList().size();
		mv.addObject("items", items);
		mv.addObject("total", total);
		return mv;
	}
	
	@PostMapping("OrderConfirm")
	public ModelAndView orderConfirmPage(@ModelAttribute("order") Ordercart order, HttpSession session) {
		ModelAndView mv = new ModelAndView("OrderConfirm");
		Long sessionId = (Long) session.getAttribute("MY_SESSION");
		if (sessionId == null) {
			mv = new ModelAndView("account");
			return mv;
		}
		mv.addObject("userId",sessionId);
		 Random rand = new Random(); 
		User user = userService.getUser(sessionId);
		mv.addObject("user", user);
		Long total = (long) findSum(user);
		int items = user.getProductList().size();
		mv.addObject("items", items);
		mv.addObject("total", total);
		mv.addObject("java8Instant", Instant.now());
		int rand_int1 = rand.nextInt(100);
	    String orderId = rand_int1 +"-"+ user.getUserId() ;
	    Ordercart orderSave = new Ordercart(orderId,user.getUserId(),total,order.getCountry(), order.getState(), order.getZip(),order.getAddress());
		orderService.save(orderSave);
		Optional<Ordercart> orderUse = orderService.findById(orderId);
		Ordercart orderbind = orderUse.get();
		mv.addObject("order", orderbind);
		return mv;
	}
	
	@GetMapping("continue-shopping")
	public ModelAndView continueShopping(HttpSession session) {
		ModelAndView mv = new ModelAndView("index");
		Long sessionId = (Long) session.getAttribute("MY_SESSION");
		if (sessionId == null) {
			mv = new ModelAndView("account");
			return mv;
		}
		mv.addObject("userId",sessionId);
		User user = userService.getUser(sessionId);
		
		//after checkout remove product from user cart
		List<Product> productList = new ArrayList<Product>();
		user.setProductList(productList);
		userService.updateList(user);
		int items = user.getProductList().size();
		mv.addObject("items", items);
		return mv;
	}
	

}
