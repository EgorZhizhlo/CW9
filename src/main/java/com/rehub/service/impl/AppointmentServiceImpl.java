package com.rehub.service.impl;

import com.rehub.dto.AppointmentDto;
import com.rehub.model.Appointment;
import com.rehub.model.User;
import com.rehub.repository.AppointmentRepository;
import com.rehub.repository.UserRepository;
import com.rehub.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> findById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Override
    @Transactional
    public Appointment create(AppointmentDto dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + dto.getUserId()));

        User doctor = userRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found: " + dto.getDoctorId()));

        if (doctor.getRole() != User.Role.DOCTOR) {
            throw new IllegalArgumentException("Provided doctorId does not belong to a DOCTOR");
        }

        Appointment ap = Appointment.builder()
                .user(user)
                .doctor(doctor)
                .appointmentDateTime(dto.getAppointmentDateTime())
                .message(dto.getMessage())
                .build();

        return appointmentRepository.save(ap);
    }

    @Override
    @Transactional
    public Appointment update(Long id, AppointmentDto dto) {
        Appointment existing = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found: " + id));

        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found: " + dto.getUserId()));
            existing.setUser(user);
        }

        if (dto.getDoctorId() != null) {
            User doctor = userRepository.findById(dto.getDoctorId())
                    .orElseThrow(() -> new IllegalArgumentException("Doctor not found: " + dto.getDoctorId()));
            if (doctor.getRole() != User.Role.DOCTOR) {
                throw new IllegalArgumentException("Provided doctorId does not belong to a DOCTOR");
            }
            existing.setDoctor(doctor);
        }

        if (dto.getAppointmentDateTime() != null) {
            existing.setAppointmentDateTime(dto.getAppointmentDateTime());
        }

        if (dto.getMessage() != null) {
            existing.setMessage(dto.getMessage());
        }

        return appointmentRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public List<Appointment> findByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    @Override
    public List<Appointment> findByUserId(Long userId) {
        return appointmentRepository.findByUserId(userId);
    }
}
