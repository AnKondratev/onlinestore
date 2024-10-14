package an.kondratev.onlinestore.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDTO {
    private String orderName;
    private BigDecimal totalAmount;
    private String status;
}