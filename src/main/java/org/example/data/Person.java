package org.example.data;

/**
 * Класс, представляющий человека
 */
public class Person {
    private String passportID; //Длина строки 4-49, может быть null
    private Color eyeColor; //Поле может быть null
    private Color hairColor; //Поле может быть null

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != Person.class) {
            return false;
        }

        Person person = (Person)obj;

        if (this.eyeColor != person.eyeColor) {
            return false;
        }

        if (this.hairColor != person.hairColor) {
            return false;
        }

        return this.passportID.equals(person.passportID);
    }

    public Person(String passportID, Color eyeColor, Color hairColor) {
        this.passportID = passportID;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
    }

    // Геттеры и сеттеры
    public String getPassportID() { return passportID; }
    public Color getEyeColor() { return eyeColor; }
    public Color getHairColor() { return hairColor; }

    public void setPassportID(String passportID) {
        if (passportID != null && (passportID.length() < 4 || passportID.length() > 49)) {
            throw new IllegalArgumentException("Passport ID length must be between 4 and 49 characters");
        }
        this.passportID = passportID;
    }
    public void setEyeColor(Color eyeColor) { this.eyeColor = eyeColor; }
    public void setHairColor(Color hairColor) { this.hairColor = hairColor; }

    @Override
    public String toString() {
        return "Person{" +
                "passportID='" + passportID + '\'' +
                ", eyeColor=" + eyeColor +
                ", hairColor=" + hairColor +
                '}';
    }
}