package com.example.votingapp;

public class Voters {

    String Name;
    String Aadhar;
    String Voter_Id;
    String DOB;
    String Phone;
    String Address;
    public Voters(){

    }
    public Voters(String Name, String Aadhar, String Voter_Id, String DOB,String Phone, String Address){
        this.Name = Name;
        this.Aadhar = Aadhar;
        this.Voter_Id = Voter_Id;
        this.DOB = DOB;
        this.Phone = Phone;
        this.Address = Address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAadhar() {
        return Aadhar;
    }

    public void setAadhar(String aadhar) {
        Aadhar = aadhar;
    }

    public String getVoter_Id() {
        return Voter_Id;
    }

    public void setVoter_Id(String voter_Id) {
        Voter_Id = voter_Id;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
