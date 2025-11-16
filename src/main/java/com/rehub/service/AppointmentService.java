package com.rehub.service;

import com.rehub.dto.AppointmentDto;
import com.rehub.model.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    List<Appointment> findAll();
    Optional<Appointment> findById(Long id);
    Appointment create(AppointmentDto dto);
    Appointment update(Long id, AppointmentDto dto);
    void delete(Long id);
    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByUserId(Long userId);
}
