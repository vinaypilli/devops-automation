package com.teamcomputers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamcomputers.dto.OrderProductDto;
import com.teamcomputers.exception.ResourceNotFoundException;
import com.teamcomputers.model.Order_Product;
import com.teamcomputers.repository.OrderProductRepository;
import com.teamcomputers.repository.OrderRepository;
import com.teamcomputers.repository.ProductRepository;
import com.teamcomputers.repository.UserRepository;

import javassist.NotFoundException;

@Service
public class OrderProductService {

	@Autowired
	private OrderProductRepository orderProductRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	public Order_Product add(OrderProductDto orderProductDto) {

		Order_Product orderProduct = new Order_Product();
		orderProduct.setOpId(orderProductDto.getOpId());
		orderProduct.setOrderProductName(orderProductDto.getOrderProductName());
		orderProduct.setOrderProductQuantity(orderProductDto.getOrderProductQuantity());
		orderProduct.setOrderProductAmount(orderProductDto.getOrderProductAmount());
//		orderProduct.setGst(orderProductDto.getGst());
//		orderProduct.setTotalPrice(orderProductDto.getTotalPrice());
//		orderProduct.setPaymentMethod(orderProductDto.getPaymentMethod());
		orderProduct.setCreatedBy(userRepository.findById((int) (orderProductDto.getCreatedBy())).orElse(null));
		orderProduct.setCreatedDate(orderProductDto.getCreatedDate());
		orderProduct.setUpdatedBy(userRepository.findById((int) (orderProductDto.getUpdatedBy())).orElse(null));
		orderProduct.setUpdatedDate(orderProductDto.getUpdatedDate());
		orderProduct.setProduct(productRepository.findById(orderProductDto.getProductId()).orElse(null));
		orderProduct.setOrders(orderRepository.findById(orderProductDto.getOrderId()).orElse(null));

		return orderProductRepository.save(orderProduct);
	}

	public Order_Product getById(long opId) {
		return orderProductRepository.findById(opId)
				.orElseThrow(() -> new ResourceNotFoundException("OP Id : " + opId + " Unavailable"));
	}

	public List<Order_Product> getAll() {
		return orderProductRepository.findAll();
	}

	public Order_Product update(OrderProductDto orderProductDto) {

		Order_Product orderProduct = new Order_Product();
		orderProduct.setOpId(orderProductDto.getOpId());
//		orderProduct.setOrderProductId(orderProductDto.getOrderProductId());
		orderProduct.setOrderProductName(orderProductDto.getOrderProductName());
		orderProduct.setOrderProductQuantity(orderProductDto.getOrderProductQuantity());
		orderProduct.setOrderProductAmount(orderProductDto.getOrderProductAmount());
//		orderProduct.setGst(orderProductDto.getGst());
//		orderProduct.setTotalPrice(orderProductDto.getTotalPrice());
//		orderProduct.setPaymentMethod(orderProductDto.getPaymentMethod());
		orderProduct.setCreatedBy(userRepository.findById((int) (orderProductDto.getCreatedBy())).orElse(null));
		orderProduct.setCreatedDate(orderProductDto.getCreatedDate());
		orderProduct.setUpdatedBy(userRepository.findById((int) (orderProductDto.getUpdatedBy())).orElse(null));
		orderProduct.setUpdatedDate(orderProductDto.getUpdatedDate());
		orderProduct.setProduct(productRepository.findById(orderProductDto.getProductId()).orElse(null));
		orderProduct.setOrders(orderRepository.findById(orderProductDto.getOrderId()).orElse(null));

		return orderProductRepository.save(orderProduct);
	}

	public boolean deleteById(long opId) throws NotFoundException {
//		 Order order = 
		orderProductRepository.findById(opId);
//				.orElseThrow(() -> new ResourceNotFoundException("OP Id : " + opId + " Unavailable"));
//         order.setStatus(false);
//		 orderRepository.save(order);
		return true;
	}

}
