package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
 

public class UpdateProductViewAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		Product prod = new Product(); 
		
		int  prodNo = Integer.parseInt(request.getParameter("prodNo")) ; 
		
		ProductService service = new ProductServiceImpl() ; 
		
    	prod= service.getProduct(prodNo);
		
		
		request.setAttribute("Product", prod);

		
		
	//	ProductService service = new ProductServiceImpl() ; 
//		service.addProduct(prodVO) ;
		
		
		
	//	return  null ;
		return  "forward:/product/updateProductView.jsp" ;  
		
	}

}
