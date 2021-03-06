package com.clinics.doctors.bootstrap;

import com.clinics.doctors.ui.model.*;
import com.clinics.doctors.ui.model.Calendar;
import com.clinics.doctors.ui.repositorie.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Component
public class BootStrapDoctors implements CommandLineRunner {

	private final DoctorRepository doctorRepository;
	private final CalendarRepository calendarRepository;
	private final SpecializationRepository specializationRepository;
	private final AppointmentRepository appointmentRepository;

	public BootStrapDoctors(
			DoctorRepository doctorRepository,
			CalendarRepository calendarRepository,
			SpecializationRepository specializationRepository,
			AppointmentRepository appointmentRepository) {
		this.doctorRepository = doctorRepository;
		this.calendarRepository = calendarRepository;
		this.specializationRepository = specializationRepository;
		this.appointmentRepository = appointmentRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		Specialization specializationPediatric = Specialization
				.builder()
				.name("Pediatric Specialization")
				.build();

		Specialization specializationGP = Specialization
				.builder()
				.name("GP Specialization")
				.build();

		Calendar calendarPediatric = Calendar
				.builder()
				.calendaruuid(UUID.fromString("edcec296-2566-412f-9e58-b574562e8891"))
				.name("Pediatric Calendar")
				.build();

		Calendar calendarGP = Calendar
				.builder()
				.calendaruuid(UUID.fromString("2d22b701-56e4-4dca-8216-0cec4a06279d"))
				.name("GP Calendar")
				.build();

		Calendar calendarNeurologist = Calendar
				.builder()
				.calendaruuid(UUID.fromString("63c7bedf-b6a5-4d83-a8a6-aa2e57f21dcd"))
				.name("Neurologist Calendar")
				.build();

		Calendar calendarCardiologist = Calendar
				.builder()
				.calendaruuid(UUID.fromString("f87a24bb-6e98-4dbf-b674-97e406a43344"))
				.name("Cardiologist Calendar")
				.build();

		Calendar calendarUrologist = Calendar
				.builder()
				.calendaruuid(UUID.fromString("39efd271-8448-40d8-932b-182ee14d4a2b"))
				.name("Urologist Calendar")
				.build();

		Calendar calendarGynecologist = Calendar
				.builder()
				.calendaruuid(UUID.fromString("fb6b8084-82b4-4830-9b0d-339e1e3a325c"))
				.name("Gynecologist Calendar")
				.build();


		Doctor doctor1 = Doctor
				.builder()
				.doctoruuid(UUID.fromString("03f0f891-b243-4547-803b-605f72b11be9"))
				.firstName("Ola")
				.lastName("Olkowska")
				.photoUrl("http://ola.pl")
				.licence("Licence example doctor 1")
				.build();
		Doctor doctor2 = Doctor
				.builder()
				.doctoruuid(UUID.fromString("fbb44683-a210-4a93-8a17-c84f16954d8d"))
				.firstName("Ala")
				.lastName("Alowsla")
				.photoUrl("http://ala.pl")
				.licence("Licence example doctor 2")
				.build();
		Doctor doctor3 = Doctor
				.builder()
				.doctoruuid(UUID.randomUUID())  //todo <--- get from auth, how ?! when I make boostrap there aren't any UUID !
				.firstName("Ela")
				.lastName("Elkowska")
				.photoUrl("http://ela.pl")
				.licence("Licence example doctor 3")
				.build();

		Appointment appointment1 = Appointment
				.builder()
				.appointmentuuid(UUID.randomUUID())
				.dateTime(LocalDateTime.now())
				.duration(Duration.ofMinutes(60))
				.build();

		Appointment appointment2 = Appointment
				.builder()
				.appointmentuuid(UUID.randomUUID())
				.dateTime(LocalDateTime.now().plusMinutes(60))
				.duration(Duration.ofMinutes(60))
				.build();

		Appointment appointment3 = Appointment
				.builder()
				.appointmentuuid(UUID.randomUUID())
				.dateTime(LocalDateTime.now().plusMinutes(120))
				.duration(Duration.ofMinutes(60))
				.build();

		Appointment appointment4 = Appointment
				.builder()
				.appointmentuuid(UUID.randomUUID())
				.dateTime(LocalDateTime.now().plusMinutes(180))
				.duration(Duration.ofMinutes(60))
				.build();




		calendarGP.setDoctor(doctor1);
		calendarPediatric.setDoctor(doctor1);
		calendarCardiologist.setDoctor(doctor1);
		calendarGynecologist.setDoctor(doctor2);
		calendarNeurologist.setDoctor(doctor2);
		calendarUrologist.setDoctor(doctor3);

		specializationGP.getDoctors().add(doctor1);
		specializationGP.getDoctors().add(doctor2);
		specializationPediatric.getDoctors().add(doctor1);

		appointment1.setCalendar(calendarGP);
		appointment2.setCalendar(calendarGP);
		appointment3.setCalendar(calendarGP);
		appointment4.setCalendar(calendarCardiologist);


		doctorRepository.saveAll(Arrays.asList(doctor1, doctor2, doctor3));
		specializationRepository.saveAll(Arrays.asList(specializationGP, specializationPediatric));
		calendarRepository.saveAll(Arrays.asList(
				calendarGP,
				calendarPediatric,
				calendarCardiologist,
				calendarGynecologist,
				calendarNeurologist,
				calendarUrologist));
		appointmentRepository.saveAll(Arrays.asList(appointment1, appointment2, appointment3, appointment4));

//	todo https://stackoverflow.com/questions/3927091/save-child-objects-automatically-using-jpa-hibernate
//		specializationGP.getDoctors().addAll(Arrays.asList(doctor1, doctor2, doctor3)); // todo BUG doesn't work , and shouldn't work without additional methods
	}
}
