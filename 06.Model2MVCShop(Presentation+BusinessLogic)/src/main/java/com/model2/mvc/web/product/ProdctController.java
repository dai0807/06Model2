package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.user.UserService;


@Controller
public class ProdctController {

	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method 구현 않음
		
	public ProdctController(){
		System.out.println(this.getClass());
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml 참조 할것
	//==> 아래의 두개를 주석을 풀어 의미를 확인 할것
	
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
	@RequestMapping("/addProductView.do")
	public String addProductView() throws Exception {

		System.out.println("/addProductView.do");
		
		return "redirect:/product/addProductView.jsp";
	}
	
	
	@RequestMapping("/addProduct.do") // d이건 다시 하기! ,,,, 데이터가 운다 울어 너는 왜 못가고 있니 
	public String addProduct( @ModelAttribute("product1 ") Product  product1 , Model model    ) throws Exception {


		System.out.println("add Product View + product ::  제조일자 변경전 " + product1 );
		product1.setManuDate(product1.getManuDate().replaceAll("-", "")) ;  // 제조일자 
		System.out.println("add Product View + product ::  제조일자 변경 후 " + product1 );

		productService.addProduct(product1) ;
		//Business Logic
 		
		
 		model.addAttribute("product1", product1);

		return "forward:/product/addgetProduct.jsp";
	}	
	
	
	@RequestMapping("/listProduct.do")
	public String listUser( @ModelAttribute("search") Search search , Model model ,@RequestParam("menu") String menu  ,HttpServletRequest request) throws Exception{
	
		System.out.println("/listProduct.do");

		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		System.out.println("search 정보들 "+search);
		// Business logic 수행
		Map<String , Object> map=productService.getProductList(search);
		
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		System.out.println("menu :::  " + menu +" 출력됨 ");
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		model.addAttribute("menu", menu);  // 메뉴로 searh 와 manage 나누기 

		
		return "forward:/product/listProduct.jsp";
	}
	
	@RequestMapping("/getProduct.do")
	public String getProduct(@RequestParam("prodNo" ) int prodNo , Model model ) throws Exception 
	{
		System.out.println("/getProduct.do");
		Product product  = productService.getProduct(prodNo) ;
	
		System.out.println("product ::: 출력하기 " + product );
		model.addAttribute("Product" , product ) ;
	 	
	
		return "forward:/product/getProduct.jsp";
		
	
	}
	
	@RequestMapping("/updateProductView.do")
	public String updateProductView(@RequestParam("prodNo" ) int prodNo , Model model ) throws Exception 
	{
		System.out.println("/updateProductView.do");
		Product product  = productService.getProduct(prodNo) ;
	
		System.out.println("product ::: 출력하기 " + product );
		model.addAttribute("Product" , product ) ;
	 	
	
		return "forward:/product/updateProductView.jsp";
		
	
	}
		
	@RequestMapping("/updateProduct.do")
	public String updateProduct( @ModelAttribute("product ") Product  product , Model model ) throws Exception 
	{
		System.out.println("/updateProductView.do");
//		Product product  = productService.getProduct(prodNo) ;
		product.setManuDate(product.getManuDate().replaceAll("-", "")) ;  // 제조일자 

		productService.updateProduct(product) ;
		
		System.out.println("product ::: 출력하기 " + product );
	//	model.addAttribute("Product" , product ) ;
	 	System.out.println("업데이투!! ");
	
		return "redirect:/getProduct.do?prodNo="+product.getProdNo();
		
	
	}	
	
}
