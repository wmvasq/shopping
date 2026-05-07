package com.shopping.payment_service.service;

import com.shopping.payment_service.client.OrdersServiceClient;
import com.shopping.payment_service.dto.OrderInfoDto;
import com.shopping.payment_service.dto.PaymentResponse;
import com.shopping.payment_service.dto.ProcessPaymentRequest;
import com.shopping.payment_service.entity.Payment;
import com.shopping.payment_service.entity.PaymentStatus;
import com.shopping.payment_service.exception.ResourceNotFoundException;
import com.shopping.payment_service.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service for processing payments.
 * Simulates payment processing with success/failure about validate amount
 */
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrdersServiceClient ordersServiceClient;

    @Transactional
    public PaymentResponse processPayment(ProcessPaymentRequest request) {
        //get Order from Orders Service
        OrderInfoDto order = ordersServiceClient.getOrderById(request.getOrderId());

        // Validate amount
        if (request.getAmount().compareTo(order.getTotalAmount()) != 0) {
            // payment rejected
            Payment rejectedPayment = Payment.builder()
                    .orderId(request.getOrderId())
                    .amount(request.getAmount())
                    .method(request.getMethod())
                    .status(PaymentStatus.REJECTED)
                    .message("Amount mismatch: expected " + order.getTotalAmount() + " but got " + request.getAmount())
                    .build();
            return mapToResponse(paymentRepository.save(rejectedPayment));
        }
        // payment completed
        Payment payment = Payment.builder()
                .orderId(request.getOrderId())
                .amount(request.getAmount())
                .method(request.getMethod())
                .status(PaymentStatus.APPROVED)
                .transactionId("TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase())
                .message("Payment approved successfully")
                .build();

        Payment saved = paymentRepository.save(payment);
        return mapToResponse(saved);
    }

    public PaymentResponse getPaymentByOrderId(Long orderId) {
        Payment payment = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found for order: " + orderId));
        return mapToResponse(payment);
    }

    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private PaymentResponse mapToResponse(Payment payment) {
        return PaymentResponse.builder()
                .id(payment.getId())
                .orderId(payment.getOrderId())
                .amount(payment.getAmount())
                .method(payment.getMethod())
                .status(payment.getStatus())
                .transactionId(payment.getTransactionId())
                .message(payment.getMessage())
                .createdAt(payment.getCreatedAt())
                .build();
    }
}