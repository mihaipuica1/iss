package com.input;


import lombok.*;

import java.util.List;

@Getter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventInput {

    private String name;
    private ProgramInput program;
    private LocationInput location;
}
