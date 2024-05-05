package org.example.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Merchant {
    private Long id;
    private String merchant_name;
    private String merchant_location;
    private boolean open;
}
