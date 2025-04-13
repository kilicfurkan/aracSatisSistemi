package model;

public class Motosiklet extends Arac{
    private int km;
    private int motorHacmi;

    public Motosiklet(int uretimYili, String marka, String model, double fiyat, int km, int motorHacmi) {
        super(uretimYili, marka, model, fiyat);
        setAracTuru("Motosiklet");
        this.km = km;
        this.motorHacmi = motorHacmi;
    }

    public int getKm() {return km;}
    public int getMotorHacmi() {return motorHacmi;}

    public void setKm(int km) {this.km = km;}
    public void setMotorHacmi(int motorHacmi) {this.motorHacmi = motorHacmi;}
}
