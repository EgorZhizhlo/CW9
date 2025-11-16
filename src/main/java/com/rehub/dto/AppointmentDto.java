package com.rehub.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentDto {
    private Long id;
    private Long userId;
    private Long doctorId;
    private LocalDateTime appointmentDateTime;
    private String message;
}
