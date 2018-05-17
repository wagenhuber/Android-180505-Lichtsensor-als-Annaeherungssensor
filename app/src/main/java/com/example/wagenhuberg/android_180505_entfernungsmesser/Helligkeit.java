package com.example.wagenhuberg.android_180505_entfernungsmesser;

/**
 * Created by wagenhuberg on 17.05.2018.
 */

public class Helligkeit {

    private double helligkeit, aenderungsquotient;
    private double groessteAenderung, kleinsteAenderung;


    public Helligkeit() {

        kleinsteAenderung = 1;
        groessteAenderung = 1;

    }

    public double getHelligkeit() {
        return helligkeit;
    }

    public void setHelligkeit(double helligkeit) {
        this.helligkeit = helligkeit;
    }

    public double getAenderungsquotient() {
        return aenderungsquotient;
    }

    public void setAenderungsquotient(double aenderungsquotient) {
        this.aenderungsquotient = aenderungsquotient;
    }

    public double getGroessteAenderung() {
        return groessteAenderung;
    }

    public void setGroessteAenderung(double groessteAenderung) {
        this.groessteAenderung = groessteAenderung;
    }

    public double getKleinsteAenderung() {
        return kleinsteAenderung;
    }

    public void setKleinsteAenderung(double kleinsteAenderung) {
        this.kleinsteAenderung = kleinsteAenderung;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Helligkeit that = (Helligkeit) o;

        if (Double.compare(that.helligkeit, helligkeit) != 0) return false;
        if (Double.compare(that.aenderungsquotient, aenderungsquotient) != 0) return false;
        if (Double.compare(that.groessteAenderung, groessteAenderung) != 0) return false;
        return Double.compare(that.kleinsteAenderung, kleinsteAenderung) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(helligkeit);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(aenderungsquotient);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(groessteAenderung);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(kleinsteAenderung);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Helligkeit{" +
                "helligkeit=" + helligkeit +
                ", aenderungsquotient=" + aenderungsquotient +
                ", groessteAenderung=" + groessteAenderung +
                ", kleinsteAenderung=" + kleinsteAenderung +
                '}';
    }
}
