package model;

public class Ticari extends Otomobil{
    private int yukKapasitesi;

    public Ticari(int uretimYili, String marka, String model, double fiyat,int km,int motorGucu, int yukKapasitesi) {
        super(uretimYili, marka, model, fiyat, km, motorGucu);
        setAracTuru("Ticari");
        this.yukKapasitesi = yukKapasitesi;
    }

    public int getYukKapasitesi() {return yukKapasitesi;}

    public void setYukKapasitesi(int yukKapasitesi) {this.yukKapasitesi = yukKapasitesi;}
}
