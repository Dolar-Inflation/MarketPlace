package com.messenger.productlist.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MakeOrderDTO {

    ProductDTO productDTO;
    AccountDTO accountDTO;
    String token;


}
