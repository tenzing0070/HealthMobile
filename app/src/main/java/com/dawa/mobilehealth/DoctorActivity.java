package com.dawa.mobilehealth;

public class DoctorActivity extends DoctorDetailActivity {

    private String firstName;
    private String lastName;
    private String specialist;
    private int image;

    public DoctorActivity(String firstName, String lastName, String specialist, int image, String about){

        this.firstName = firstName;
        this.lastName = lastName;
        this.specialist = specialist;
        this.image = image;
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

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
