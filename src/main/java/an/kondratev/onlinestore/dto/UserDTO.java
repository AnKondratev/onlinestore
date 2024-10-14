package an.kondratev.onlinestore.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class UserDTO {
    private String name;
    private String email;
    private List<OrderDTO> orders;
}
