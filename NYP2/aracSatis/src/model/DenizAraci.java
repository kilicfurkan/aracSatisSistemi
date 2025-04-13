package model;

public class DenizAraci extends Arac{
    private String tip;

    public DenizAraci(int uretimYili, String marka, String model, double fiyat, String tip) {
        super(uretimYili, marka, model, fiyat);
        setAracTuru("Deniz Aracı");
        this.tip = tip;
    }

    public String getTip() {return tip;}

    public void setTip(String tip) {this.tip = tip;}
}
