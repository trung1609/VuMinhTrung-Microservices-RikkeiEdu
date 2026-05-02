package com.api.appointmentservice.service.impl;

import com.api.appointmentservice.dto.ApiResponseError;
import com.api.appointmentservice.entity.Appointment;
import com.api.appointmentservice.repository.AppointmentRepository;
import com.api.appointmentservice.service.AppointmentService;
import com.api.appointmentservice.service.client.DoctorClient;
import com.api.appointmentservice.service.client.PatientClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorClient doctorClient;
    private final PatientClient patientClient;

    @Override
    public Object createAppointment(Appointment appointment) {
        try {
            if (doctorClient.getDoctorById(appointment.getDoctorId()) == null) {
                throw new RuntimeException("Doctor not found with id: " + appointment.getDoctorId());
            }
            if (patientClient.getPatientById(appointment.getPatientId()) == null) {
                throw new RuntimeException("Patient not found with id: " + appointment.getPatientId());
            }
            return appointmentRepository.save(appointment);
        } catch (Exception e) {
            log.error("Sự cố liên dịch vụ: {}", e.getMessage());
            return new ApiResponseError(
                    LocalDateTime.now().toString(),
                    503,
                    "Hệ thống quản lý bác sĩ hiện không khả dụng. Vui lòng đặt lịch sau!",
                    "Service Unavailable"
            );
        }

    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}
