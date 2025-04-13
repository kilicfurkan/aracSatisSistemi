package model;

public class Arac {
    private int id;
    private String aracTuru;
    private int uretimYili;
    private String marka;
    private String model;
    private double fiyat;

    public Arac(int uretimYili, String marka, String model, double fiyat) {
        this.uretimYili = uretimYili;
        this.marka = marka;
        this.model = model;
        this.fiyat = fiyat;
    }

    public int getId() {return id;}
    public String getAracTuru() {return aracTuru;}
    public int getUretimYili() {return uretimYili;}
    public String getMarka() {return marka;}
    public String getModel() {return model;}
    public double getFiyat() {return fiyat;}

    public void setId(int id) {this.id = id;}
    public void setAracTuru(String aracTuru) {this.aracTuru = aracTuru;}
    public void setUretimYili(int uretimYili) {this.uretimYili = uretimYili;}
    public void setMarka(String marka) {this.marka = marka;}
    public void setModel(String model) {this.model = model;}
    public void setFiyat(double fiyat) {this.fiyat = fiyat;}
}
