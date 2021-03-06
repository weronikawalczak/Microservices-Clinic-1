package com.clinics.doctors.ui.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@ToString
@DynamicInsert
@DynamicUpdate
@Entity
public class Specialization {

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(
			updatable = false,
			nullable = false,
			unique = true)
	@Builder.Default
	private UUID specializationuuid = UUID.randomUUID(); //todo bad name because JPA <---> sqlQuery
	//todo move creation uuid into method

	@NotBlank(message = "name is mandatory")
	@Size(min = 2, max = 100, message = "name length out of range")
	private String name;

	@Builder.Default
	@JsonBackReference
	@ManyToMany(targetEntity = Doctor.class)
	@JoinTable(
			name = "doctor_specialization",
			joinColumns = {@JoinColumn(name = "spacialization_id")},
			inverseJoinColumns = {@JoinColumn(name = "doctor_id")})
	Collection<Doctor> doctors = new HashSet<>();
	//todo move creation doctors into method
}
