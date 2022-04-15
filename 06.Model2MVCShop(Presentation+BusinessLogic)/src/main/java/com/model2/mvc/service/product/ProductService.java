package com.model2.mvc.service.product;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;

public interface ProductService {

	// ��ǰ �߰� 
	public void addProduct(Product product) throws Exception;
	
	//��ǰ ������Ʈ
	public void updateProduct(Product product) throws Exception;

	//��ǰ ��������
	public Product getProduct (int prod_no) throws Exception;

	//����Ʈ ��������
	public  Map<String, Object> getProductList(Search search)  throws Exception;
	
	// public int findTrandtranNo(int prodNo )  throws Exception; �̰� ���� 

	//Ʈ���ڵ� ã�� 
	public int findTrandtranNo(int prodNo )  throws Exception;

	
	
}