package com.clinics.patient.service;

import com.clinics.common.DTO.request.outer.RegisterPatientDTO;
import com.clinics.common.DTO.response.outer.PatientRegisterResponseDTO;
import com.clinics.patient.entity.Patient;
import com.clinics.patient.entity.Visit;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientService {
    PatientRegisterResponseDTO addPatient(RegisterPatientDTO registerPatientDTO, HttpServletRequest request);
    List<Patient> findAll();
    Patient findByUuid(UUID UUID);
    Optional<Patient> findById(Long ID);
    void deleteById(Long ID);
    Patient editPatient(Patient patient);
    List<Visit> findAllVisits(UUID UUID);
}
