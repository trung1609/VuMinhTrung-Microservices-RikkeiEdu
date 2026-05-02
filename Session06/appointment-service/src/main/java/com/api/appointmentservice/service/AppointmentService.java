package com.api.appointmentservice.service;

import com.api.appointmentservice.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment createAppointment(Appointment appointment);
    List<Appointment> getAllAppointments();
}
