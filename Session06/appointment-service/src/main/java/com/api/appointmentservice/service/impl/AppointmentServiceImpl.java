package com.api.appointmentservice.service.impl;

import com.api.appointmentservice.entity.Appointment;
import com.api.appointmentservice.repository.AppointmentRepository;
import com.api.appointmentservice.service.AppointmentService;
import com.api.appointmentservice.service.client.DoctorClient;
import com.api.appointmentservice.service.client.PatientClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorClient doctorClient;
    private final PatientClient patientClient;

    @Override
    public Appointment createAppointment(Appointment appointment) {
        if (doctorClient.getDoctorById(appointment.getDoctorId()) == null) {
            throw new RuntimeException("Doctor not found with id: " + appointment.getDoctorId());
        }
        if (patientClient.getPatientById(appointment.getPatientId()) == null) {
            throw new RuntimeException("Patient not found with id: " + appointment.getPatientId());
        }

        appointment.setDoctorId(appointment.getDoctorId());
        appointment.setPatientId(appointment.getPatientId());
        appointment.setAppointmentDate(appointment.getAppointmentDate());
        appointment.setReason(appointment.getReason());
        appointment.setStatus(appointment.getStatus());
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
}
