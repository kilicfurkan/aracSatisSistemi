package model;

public class Otomobil extends Arac{
    private int km;
    private int motorGucu;

    public Otomobil (int uretimYili, String marka, String model, double fiyat, int km, int motorGucu) {
        super(uretimYili, marka, model, fiyat);
        setAracTuru("Otomobil");
        this.km = km;
        this.motorGucu = motorGucu;
    }

    public int getKm() {return km;}
    public int getMotorGucu() {return motorGucu;}

    public void setKm(int km) {this.km = km;}
    public void setMotorGucu(int motorGucu) {this.motorGucu = motorGucu;}
}
