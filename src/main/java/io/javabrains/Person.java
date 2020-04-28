package io.javabrains;

import org.springframework.ldap.odm.annotations.Entry;

@Entry(objectClasses = { "person", "top" }, base="ou=people")
public class Person {
	
	private String uid;
    private String fullName;
    private String lastName;
	private String userPassword;
    
    public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

    public Person() {
    }

    public Person(String fullName, String lastName) {
        this.fullName = fullName;
        this.lastName = lastName;
    }

    public Person(String uid, String fullName, String lastName) {
        this.uid = uid;
        this.fullName = fullName;
        this.lastName = lastName;
    }
    
    public Person(String uid, String fullName, String lastName, String userPassword) {
        this.uid = uid;
        this.fullName = fullName;
        this.lastName = lastName;
        this.userPassword = userPassword;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fullName='" + fullName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}