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

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrdersServiceClient ordersServiceClient;

    @Transactional
    public PaymentResponse processPayment(ProcessPaymentRequest request) {
        // 1. Consultar orden real a Orders Service
        OrderInfoDto order = ordersServiceClient.getOrderById(request.getOrderId());

        // 2. Validar que el monto coincida
        if (request.getAmount().compareTo(order.getTotalAmount()) != 0) {
            Payment rejectedPayment = Payment.builder()
                    .orderId(request.getOrderId())
                    .amount(request.getAmount())
                    .method(request.getMethod())
                    .status(PaymentStatus.REJECTED)
                    .message("Amount mismatch: expected " + order.getTotalAmount() + " but got " + request.getAmount())
                    .build();
            return mapToResponse(paymentRepository.save(rejectedPayment));
        }

        // 3. Simular procesamiento
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 4. Simular probabilidad (80% aprobado)
        boolean approved = Math.random() > 0.2;

        Payment payment = Payment.builder()
                .orderId(request.getOrderId())
                .amount(request.getAmount())
                .method(request.getMethod())
                .status(approved ? PaymentStatus.APPROVED : PaymentStatus.REJECTED)
                .transactionId(approved ? "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase() : null)
                .message(approved ? "Payment approved successfully" : "Payment rejected")
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