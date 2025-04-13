package model;

public class HavaAraci extends Arac{
    private String tip;
    private int motorSayisi;

    public HavaAraci(int uretimYili, String marka, String model, double fiyat, String tip, int motorSayisi) {
        super(uretimYili, marka, model, fiyat);
        setAracTuru("Hava Aracı");
        this.tip = tip;
        this.motorSayisi = motorSayisi;
    }

    public String getTip() {return tip;}
    public int getMotorSayisi() {return motorSayisi;}

    public void setMotorSayisi(int motorSayisi) {this.motorSayisi = motorSayisi;}
    public void setTip(String tip) {this.tip = tip;}
}
