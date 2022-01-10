package com.fdflib.example.model;

import java.util.Objects;

import com.fdflib.annotations.Column;
import com.fdflib.annotations.Entity;
import com.fdflib.annotations.Id;
import com.fdflib.annotations.JoinColumn;
import com.fdflib.model.state.CommonState;

@Entity(tableName="trainer")
public class Trainer extends CommonState { // Both Driver and Car extend CommonState...

	@Id(columnName="pokemon_id") // this has been marked as a Primary Key
	public int id;
	
	@Column(columnName="firstName")
	public String firstName;
	
	@Column(columnName="lastName")
	public String lastName;
	
	@Column(columnName="phoneNumber")
	public String phoneNumber;
	
    public Trainer() {
        super();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, id, lastName, phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trainer other = (Trainer) obj;
		return Objects.equals(firstName, other.firstName) && id == other.id && Objects.equals(lastName, other.lastName)
				&& Objects.equals(phoneNumber, other.phoneNumber);
	}

	@Override
	public String toString() {
		return "Trainer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber="
				+ phoneNumber + "]";
	}
    
    
}
