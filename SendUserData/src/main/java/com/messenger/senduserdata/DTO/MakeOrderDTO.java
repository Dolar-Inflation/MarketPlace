package com.messenger.senduserdata.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MakeOrderDTO {

    ProductDTO productDTO;
    AccountDTO accountDTO;
    String token;
}
