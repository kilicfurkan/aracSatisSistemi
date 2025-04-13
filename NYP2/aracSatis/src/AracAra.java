import model.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class AracAra extends Frame implements ActionListener{
    ArrayList<Arac> bulunanAraclar = new ArrayList<Arac>();
    int sira = 0;
    DecimalFormat df = new DecimalFormat("#,###");


    Label idLbl, aracTuruLbl, markaLbl, modelLbl, uretimYiliLbl, fiyatLbl, ek1Lbl, ek2Lbl, ek3Lbl;

    Button ara, gec, geri, sifirla;

    TextField idTxt, markaTxt, modelTxt, uretimYiliTxt, fiyatTxt, ek1Txt, ek2Txt, ek3Txt;
    Choice aracTuruTxt;

    Font f1 = new Font("TimesRoman", Font.BOLD, 20);

    GridBagConstraints gbc = new GridBagConstraints();
    GridBagLayout gbl = new GridBagLayout();

    void gorunumAyari(int durum){
        switch (durum){
            case 0:
                ek1Lbl.setVisible(false);
                ek2Lbl.setVisible(false);
                ek3Lbl.setVisible(false);
                ek1Txt.setVisible(false);
                ek2Txt.setVisible(false);
                ek3Txt.setVisible(false);
                idTxt.setText("");
                aracTuruTxt.select(0);
                markaTxt.setText("");
                modelTxt.setText("");
                uretimYiliTxt.setText("");
                fiyatTxt.setText("");
                ek1Txt.setText("");
                ek2Txt.setText("");
                ek3Txt.setText("");
                break;
            case 1:
                ek1Lbl.setVisible(true);
                ek2Lbl.setVisible(true);
                ek3Lbl.setVisible(false);
                ek1Txt.setVisible(true);
                ek2Txt.setVisible(true);
                ek3Txt.setVisible(false);
                ek1Lbl.setText("Km");
                ek2Lbl.setText("Motor Gücü");
                break;
            case 2:
                ek1Lbl.setVisible(true);
                ek2Lbl.setVisible(true);
                ek3Lbl.setVisible(true);
                ek1Txt.setVisible(true);
                ek2Txt.setVisible(true);
                ek3Txt.setVisible(true);
                ek1Lbl.setText("Km");
                ek2Lbl.setText("Motor Gücü");
                ek3Lbl.setText("Yük Kapasitesi");
                break;
            case 3:
                ek1Lbl.setVisible(true);
                ek2Lbl.setVisible(true);
                ek3Lbl.setVisible(false);
                ek1Txt.setVisible(true);
                ek2Txt.setVisible(true);
                ek3Txt.setVisible(false);
                ek1Lbl.setText("Km");
                ek2Lbl.setText("Motor Hacmi");
                break;
            case 4:
                ek1Lbl.setVisible(true);
                ek2Lbl.setVisible(false);
                ek3Lbl.setVisible(false);
                ek1Txt.setVisible(true);
                ek2Txt.setVisible(false);
                ek3Txt.setVisible(false);
                ek1Lbl.setText("Tip");
                ek2Lbl.setText("Motor Hacmi");
                break;
            case 5:
                ek1Lbl.setVisible(true);
                ek2Lbl.setVisible(true);
                ek3Lbl.setVisible(false);
                ek1Txt.setVisible(false);
                ek2Txt.setVisible(true);
                ek3Txt.setVisible(false);
                ek1Lbl.setText("Tip");
                ek2Lbl.setText("Motor Sayısı");
                break;
        }
        invalidate();
        validate();
        repaint();
    }

    void set(int x, int y, Button b){
        gbc.gridx = x;
        gbc.gridy = y;
        gbl.setConstraints(b, gbc);
        add(b);
    }

    void set(int x, int y, Label l){
        gbc.gridx = x;
        gbc.gridy = y;
        gbl.setConstraints(l, gbc);
        add(l);
    }

    void set(int x, int y, TextField t){
        gbc.gridx = x;
        gbc.gridy = y;
        gbl.setConstraints(t, gbc);
        add(t);
    }

    void set(int x, int y, Choice c){
        gbc.gridx = x;
        gbc.gridy = y;
        gbl.setConstraints(c, gbc);
        add(c);
    }

    public AracAra() {

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setTitle("Tüm Araçlar");
        setBackground(Color.WHITE);
        setLayout(gbl);
        setExtendedState(MAXIMIZED_BOTH);

        idLbl = new Label("ID");
        idLbl.setFont(f1);
        aracTuruLbl = new Label("Turu", Label.LEFT);
        aracTuruLbl.setFont(f1);
        markaLbl = new Label("Marka", Label.LEFT);
        markaLbl.setFont(f1);
        modelLbl = new Label("Model", Label.LEFT);
        modelLbl.setFont(f1);
        uretimYiliLbl = new Label("Yili", Label.LEFT);
        uretimYiliLbl.setFont(f1);
        fiyatLbl = new Label("Fiyat", Label.LEFT);
        fiyatLbl.setFont(f1);
        ek1Lbl = new Label("Ekle", Label.LEFT);
        ek1Lbl.setFont(f1);
        ek2Lbl = new Label("Ekle", Label.LEFT);
        ek2Lbl.setFont(f1);
        ek3Lbl = new Label("Ekle", Label.LEFT);
        ek3Lbl.setFont(f1);

        idTxt = new TextField(10);
        markaTxt = new TextField(20);
        modelTxt = new TextField(20);
        uretimYiliTxt = new TextField(20);
        fiyatTxt = new TextField(20);
        ek1Txt = new TextField(20);
        ek2Txt = new TextField(20);
        ek3Txt = new TextField(20);

        ara = new Button("Ara");
        ara.setFont(f1);
        gec = new Button("Geç");
        gec.setFont(f1);
        geri = new Button("Geri");
        geri.setFont(f1);
        sifirla = new Button("Sıfırla");
        sifirla.setFont(f1);

        aracTuruTxt = new Choice();
        aracTuruTxt.add("Seçiniz");
        aracTuruTxt.add("Otomobil");
        aracTuruTxt.add("Ticari");
        aracTuruTxt.add("Motosiklet");
        aracTuruTxt.add("Deniz Aracı");
        aracTuruTxt.add("Hava Aracı");

        gbc.weighty = 0.0001;

        set(0, 2, idLbl);
        set(2, 2, idTxt);

        set(0, 3, aracTuruLbl);
        set(2, 3, aracTuruTxt);

        set(0, 4, markaLbl);
        set(2, 4, markaTxt);

        set(0, 5, modelLbl);
        set(2, 5, modelTxt);

        set(0, 6, uretimYiliLbl);
        set(2, 6, uretimYiliTxt);

        set(0, 7, fiyatLbl);
        set(2, 7, fiyatTxt);

        set(0, 8, ek1Lbl);
        set(2, 8, ek1Txt);

        set(0, 9, ek2Lbl);
        set(2, 9, ek2Txt);

        set(0, 10, ek3Lbl);
        set(2, 10, ek3Txt);

        set(0, 11, ara);
        set(2, 11, gec);
        set(4, 11, geri);
        set(2, 13, sifirla);


        gorunumAyari(0);

        ara.addActionListener(this);
        gec.addActionListener(this);
        geri.addActionListener(this);
        sifirla.addActionListener(this);

        aracTuruTxt.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {

                if (aracTuruTxt.getSelectedItem().equals("Otomobil")) {
                    gorunumAyari(1);
                } else if (aracTuruTxt.getSelectedItem().equals("Ticari")) {
                    gorunumAyari(2);
                } else if (aracTuruTxt.getSelectedItem().equals("Motosiklet")) {
                    gorunumAyari(3);
                } else if (aracTuruTxt.getSelectedItem().equals("Deniz Aracı")) {
                    gorunumAyari(4);
                } else if (aracTuruTxt.getSelectedItem().equals("Hava Aracı")) {
                    gorunumAyari(5);
                }
            }
        });
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == ara){
            String idText = idTxt.getText().trim();
            Integer id = idText.isEmpty() ? null : Integer.parseInt(idText);

            String aracTuru = aracTuruTxt.getSelectedItem().toString();
            String marka = markaTxt.getText().trim();
            marka = marka.isEmpty() ? null : marka;

            String model = modelTxt.getText().trim();
            model = model.isEmpty() ? null : model;

            String uretimYiliText = uretimYiliTxt.getText().trim();
            Integer uretimYili = uretimYiliText.isEmpty() ? null : Integer.parseInt(uretimYiliText);

            String fiyatText = fiyatTxt.getText().trim();
            Double fiyat = fiyatText.isEmpty() ? null : Double.parseDouble(fiyatText);

            bulunanAraclar = VeritabaniBaglantisi.aracAra(id, marka, model, uretimYili, fiyat, aracTuru);

            if (bulunanAraclar.size() > 0 ){
                String Id = Integer.toString(bulunanAraclar.get(0).getId());
                String AracTuru = bulunanAraclar.get(0).getAracTuru();
                String Marka = bulunanAraclar.get(0).getMarka();
                String Model = bulunanAraclar.get(0).getModel();
                String UretimYili = Integer.toString(bulunanAraclar.get(0).getUretimYili());
                String Fiyat = df.format(bulunanAraclar.get(0).getFiyat());

                idTxt.setText(Id);
                markaTxt.setText(Marka);
                modelTxt.setText(Model);
                uretimYiliTxt.setText(UretimYili);
                fiyatTxt.setText(Fiyat);
                if (bulunanAraclar.get(0) instanceof  Otomobil) {
                    aracTuruTxt.select(1);
                    String Km = Integer.toString(((Otomobil) bulunanAraclar.get(0)).getKm());
                    String MotorGucu = Integer.toString(((Otomobil) bulunanAraclar.get(0)).getMotorGucu());
                    ek1Txt.setText(Km);
                    ek2Txt.setText(MotorGucu);

                    gorunumAyari(1);
                }
                if (bulunanAraclar.get(0) instanceof  Ticari) {
                    aracTuruTxt.select(2);
                    String Km = Integer.toString(((Ticari) bulunanAraclar.get(0)).getKm());
                    ek1Txt.setText(Km);
                    String MotorGucu = Integer.toString(((Ticari) bulunanAraclar.get(0)).getMotorGucu());
                    ek2Txt.setText(MotorGucu);
                    String YukKapasitesi = Integer.toString(((Ticari) bulunanAraclar.get(0)).getYukKapasitesi());
                    ek3Txt.setText(YukKapasitesi);

                    gorunumAyari(2);
                }
                if(bulunanAraclar.get(0) instanceof  Motosiklet) {
                    aracTuruTxt.select(3);
                    String Km = Integer.toString(((Motosiklet) bulunanAraclar.get(0)).getKm());
                    ek1Txt.setText(Km);
                    String MotorHacmi = Integer.toString(((Motosiklet) bulunanAraclar.get(0)).getMotorHacmi());
                    ek2Txt.setText(MotorHacmi);

                    gorunumAyari(3);
                }
                if(bulunanAraclar.get(0) instanceof  DenizAraci){
                    aracTuruTxt.select(4);
                    String Tip = ((DenizAraci) bulunanAraclar.get(0)).getTip();
                    ek1Txt.setText(Tip);

                    gorunumAyari(4);
                }
                if(bulunanAraclar.get(0) instanceof  HavaAraci){
                    aracTuruTxt.select(5);
                    String Tip = ((HavaAraci) bulunanAraclar.get(0)).getTip();
                    ek1Txt.setText(Tip);
                    String MotorSayisi = Integer.toString(((HavaAraci) bulunanAraclar.get(0)).getMotorSayisi());
                    ek2Txt.setText(MotorSayisi);

                    gorunumAyari(5);
                }
            }
            else {
                Mesaj.goster(this,"Araç bulunamadı");
                gorunumAyari(0);
            }
        }
        else if(e.getSource() == gec){
            if(bulunanAraclar.size() > 0) {
                sira++;

                if(bulunanAraclar.size() <= sira){
                    Mesaj.goster(this,"Araçlar bitti, başa dönülüyor..");
                    sira = 0;
                }

                if (bulunanAraclar.size() > 0 ){
                    String Id = Integer.toString(bulunanAraclar.get(sira).getId());
                    String AracTuru = bulunanAraclar.get(sira).getAracTuru();
                    String Marka = bulunanAraclar.get(sira).getMarka();
                    String Model = bulunanAraclar.get(sira).getModel();
                    String UretimYili = Integer.toString(bulunanAraclar.get(sira).getUretimYili());
                    String Fiyat = df.format(bulunanAraclar.get(0).getFiyat());


                    idTxt.setText(Id);
                    markaTxt.setText(Marka);
                    modelTxt.setText(Model);
                    uretimYiliTxt.setText(UretimYili);
                    fiyatTxt.setText(Fiyat);
                    if (bulunanAraclar.get(sira) instanceof  Otomobil) {
                        aracTuruTxt.select(1);
                        String Km = Integer.toString(((Otomobil) bulunanAraclar.get(sira)).getKm());
                        String MotorGucu = Integer.toString(((Otomobil) bulunanAraclar.get(sira)).getMotorGucu());
                        ek1Txt.setText(Km);
                        ek2Txt.setText(MotorGucu);

                        gorunumAyari(1);
                    }
                    if (bulunanAraclar.get(sira) instanceof  Ticari) {
                        aracTuruTxt.select(2);
                        String Km = Integer.toString(((Ticari) bulunanAraclar.get(sira)).getKm());
                        ek1Txt.setText(Km);
                        String MotorGucu = Integer.toString(((Ticari) bulunanAraclar.get(sira)).getMotorGucu());
                        ek2Txt.setText(MotorGucu);
                        String YukKapasitesi = Integer.toString(((Ticari) bulunanAraclar.get(sira)).getYukKapasitesi());
                        ek3Txt.setText(YukKapasitesi);

                        gorunumAyari(2);
                    }
                    if(bulunanAraclar.get(sira) instanceof  Motosiklet) {
                        aracTuruTxt.select(3);
                        String Km = Integer.toString(((Motosiklet) bulunanAraclar.get(sira)).getKm());
                        ek1Txt.setText(Km);
                        String MotorHacmi = Integer.toString(((Motosiklet) bulunanAraclar.get(sira)).getMotorHacmi());
                        ek2Txt.setText(MotorHacmi);

                        gorunumAyari(3);
                    }
                    if(bulunanAraclar.get(sira) instanceof  DenizAraci){
                        aracTuruTxt.select(4);
                        String Tip = ((DenizAraci) bulunanAraclar.get(sira)).getTip();
                        ek1Txt.setText(Tip);

                        gorunumAyari(4);
                    }
                    if(bulunanAraclar.get(sira) instanceof  HavaAraci){
                        aracTuruTxt.select(5);
                        String Tip = ((HavaAraci) bulunanAraclar.get(sira)).getTip();
                        ek1Txt.setText(Tip);
                        String MotorSayisi = Integer.toString(((HavaAraci) bulunanAraclar.get(0)).getMotorSayisi());
                        ek2Txt.setText(MotorSayisi);

                        gorunumAyari(5);
                    }
                }
            }
        }
        else if(e.getSource() == geri){
            Menu menu = new Menu();
            menu.setVisible(true);
            this.setVisible(false);
        }
        else if(e.getSource() == sifirla){
            gorunumAyari(0);
        }
        }
    }