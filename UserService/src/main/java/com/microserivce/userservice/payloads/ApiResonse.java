package com.microserivce.userservice.payloads;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResonse {
    private String message;
    private boolean success;
}
