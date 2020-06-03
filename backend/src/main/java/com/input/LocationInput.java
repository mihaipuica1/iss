package com.input;

import lombok.*;

@Getter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationInput {

    private String country;
    private String city;
}
