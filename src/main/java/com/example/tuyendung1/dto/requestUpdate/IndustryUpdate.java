package com.example.tuyendung1.dto.requestUpdate;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IndustryUpdate {
    String code;
    String name;
    boolean active;
    String description;
}
