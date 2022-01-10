package com.fdflib.example.model;
import java.util.Objects;
import com.fdflib.annotation.FdfIgnore;
import com.fdflib.annotations.Column;
import com.fdflib.annotations.Entity;
import com.fdflib.annotations.Id;
import com.fdflib.annotations.JoinColumn;
import com.fdflib.model.state.CommonState;

@Entity(tableName="pokemon")
public class Pokemon extends CommonState{

	@Id(columnName="pokemon_id") // this has been marked as a Primary Key
	public int id;
	
	@Column(columnName="name")
	public String name;
	
	@Column(columnName="color")
	public String color;
	
	@Column(columnName="primaryType")
	public String primaryType;
	
	@Column(columnName="secondaryType")
	public String secondaryType;
	
	@Column(columnName="height")
	public Double height;
	
	@Column(columnName="weight")
	public Double weight;
	
	@Column(columnName="catchRate")
	public Double catchRate;
	
	@Column(columnName="description")
	public String description;
	// this is an example of how we might define a property that serves as a foreign key
	@JoinColumn(columnName="test_relation")
	public Trainer testRelation;
	
    public long currentTrainerId = -1L; // Instead we want this to represent our foreign key in the database

    @FdfIgnore // Compiler reads this and knows to ignore it (this prevents FdfLib from inserting the complex obj into the DB). 
    public Trainer currentTrainer = null;


    public Pokemon() {
        super();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPrimaryType() {
		return primaryType;
	}

	public void setPrimaryType(String primaryType) {
		this.primaryType = primaryType;
	}

	public String getSecondaryType() {
		return secondaryType;
	}

	public void setSecondaryType(String secondaryType) {
		this.secondaryType = secondaryType;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getCatchRate() {
		return catchRate;
	}

	public void setCatchRate(Double catchRate) {
		this.catchRate = catchRate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Trainer getTestRelation() {
		return testRelation;
	}

	public void setTestRelation(Trainer testRelation) {
		this.testRelation = testRelation;
	}

	public long getCurrentTrainerId() {
		return currentTrainerId;
	}

	public void setCurrentTrainerId(long currentTrainerId) {
		this.currentTrainerId = currentTrainerId;
	}

	public Trainer getCurrentTrainer() {
		return currentTrainer;
	}

	public void setCurrentTrainer(Trainer currentTrainer) {
		this.currentTrainer = currentTrainer;
	}

	@Override
	public int hashCode() {
		return Objects.hash(catchRate, color, currentTrainer, currentTrainerId, description, height, id, name,
				primaryType, secondaryType, testRelation, weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pokemon other = (Pokemon) obj;
		return Objects.equals(catchRate, other.catchRate) && Objects.equals(color, other.color)
				&& Objects.equals(currentTrainer, other.currentTrainer) && currentTrainerId == other.currentTrainerId
				&& Objects.equals(description, other.description) && Objects.equals(height, other.height)
				&& id == other.id && Objects.equals(name, other.name) && Objects.equals(primaryType, other.primaryType)
				&& Objects.equals(secondaryType, other.secondaryType)
				&& Objects.equals(testRelation, other.testRelation) && Objects.equals(weight, other.weight);
	}

	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", name=" + name + ", color=" + color + ", primaryType=" + primaryType
				+ ", secondaryType=" + secondaryType + ", height=" + height + ", weight=" + weight + ", catchRate="
				+ catchRate + ", description=" + description + ", testRelation=" + testRelation + ", currentTrainerId="
				+ currentTrainerId + ", currentTrainer=" + currentTrainer + "]";
	}
    
    

}
