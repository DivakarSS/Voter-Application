package com.example.votingapp;

public class Vote {
    String Cse;
    String Eee;
    String It;
    String Civil;
    String Mech;
    String Ece;
    public Vote(){

    }
    public  Vote(String Cse, String Eee, String It, String Civil, String Mech, String Ece){
        this.Cse = Cse;
        this.Eee = Eee;
        this.It = It;
        this.Civil = Civil;
        this.Mech = Mech;
        this.Ece = Ece;
    }

    public String getCse() {
        return Cse;
    }

    public void setCse(String cse) {
        Cse = cse;
    }

    public String getEee() {
        return Eee;
    }

    public void setEee(String eee) {
        Eee = eee;
    }

    public String getIt() {
        return It;
    }

    public void setIt(String it) {
        It = it;
    }

    public String getCivil() {
        return Civil;
    }

    public void setCivil(String civil) {
        Civil = civil;
    }

    public String getMech() {
        return Mech;
    }

    public void setMech(String mech) {
        Mech = mech;
    }

    public String getEce() {
        return Ece;
    }

    public void setEce(String ece) {
        Ece = ece;
    }
}

