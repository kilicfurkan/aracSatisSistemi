import model.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import static java.lang.Integer.parseInt;

public class AracEkle extends Frame implements ActionListener{
    Arac arac = null;

    Label aracTuruLbl, markaLbl, modelLbl, uretimYiliLbl, fiyatLbl, ek1Lbl, ek2Lbl, ek3Lbl;

    Button kaydet, geri;

    TextField markaTxt, modelTxt, uretimYiliTxt, fiyatTxt, ek1Txt, ek2Txt, ek3Txt;
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

    public AracEkle() {

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setTitle("Araç Ekle");
        setBackground(Color.WHITE);
        setLayout(gbl);
        setExtendedState(MAXIMIZED_BOTH);

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

        markaTxt = new TextField(20);
        modelTxt = new TextField(20);
        uretimYiliTxt = new TextField(20);
        fiyatTxt = new TextField(20);
        ek1Txt = new TextField(20);
        ek2Txt = new TextField(20);
        ek3Txt = new TextField(20);

        kaydet = new Button("Kaydet");
        kaydet.setFont(f1);
        geri = new Button("Geri");
        geri.setFont(f1);

        aracTuruTxt = new Choice();
        aracTuruTxt.add("Seçiniz");
        aracTuruTxt.add("Otomobil");
        aracTuruTxt.add("Ticari");
        aracTuruTxt.add("Motosiklet");
        aracTuruTxt.add("Deniz Aracı");
        aracTuruTxt.add("Hava Aracı");

        gbc.weighty = 0.0001;

        set(0, 2, aracTuruLbl);
        set(2, 2, aracTuruTxt);
        set(0, 4, markaLbl);
        set(2, 4, markaTxt);
        set(0, 6, modelLbl);
        set(2, 6, modelTxt);
        set(0, 8, uretimYiliLbl);
        set(2, 8, uretimYiliTxt);
        set(0, 10, fiyatLbl);
        set(2, 10, fiyatTxt);
        set(0, 18, kaydet);
        set(2, 18, geri);
        set(0, 12, ek1Lbl);
        set(2, 12, ek1Txt);
        set(0, 14, ek2Lbl);
        set(2, 14, ek2Txt);
        set(0, 16, ek3Lbl);
        set(2, 16, ek3Txt);

        gorunumAyari(0);

        kaydet.addActionListener(this);
        geri.addActionListener(this);

        aracTuruTxt.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {

                if (aracTuruTxt.getSelectedItem().equals("Otomobil")) {gorunumAyari(1);}
                else if (aracTuruTxt.getSelectedItem().equals("Ticari")) {gorunumAyari(2);}
                else if (aracTuruTxt.getSelectedItem().equals("Motosiklet")) {gorunumAyari(3);}
                else if (aracTuruTxt.getSelectedItem().equals("Deniz Aracı")) {gorunumAyari(4);}
                else if (aracTuruTxt.getSelectedItem().equals("Hava Aracı")) {gorunumAyari(5);}
            }
        });
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == kaydet){
            try {
                int uretimYili = parseInt(uretimYiliTxt.getText().trim());
                String marka = markaTxt.getText();
                String model = modelTxt.getText();
                double fiyat = Double.parseDouble(fiyatTxt.getText().trim());

                if (aracTuruTxt.getSelectedItem().equals("Otomobil")) {
                    int km = parseInt(ek1Txt.getText());
                    int motorGucu = parseInt(ek2Txt.getText());
                    arac = new Otomobil(uretimYili, marka, model, fiyat, km, motorGucu);

                } else if (aracTuruTxt.getSelectedItem().equals("Ticari")) {
                    int km = parseInt(ek1Txt.getText());
                    int motorGucu = parseInt(ek2Txt.getText());
                    int yukKapasitesi = parseInt(ek3Txt.getText());
                    arac = new Ticari(uretimYili, marka, model, fiyat, km, motorGucu, yukKapasitesi);

                } else if (aracTuruTxt.getSelectedItem().equals("Motosiklet")) {
                    int km = parseInt(ek1Txt.getText());
                    int motorHacmi = parseInt(ek2Txt.getText());
                    arac = new Motosiklet(uretimYili, marka, model, fiyat, km, motorHacmi);

                } else if (aracTuruTxt.getSelectedItem().equals("Deniz Aracı")) {
                    String tip = ek1Txt.getText();
                    arac = new DenizAraci(uretimYili, marka, model, fiyat, tip);

                } else if (aracTuruTxt.getSelectedItem().equals("Hava Aracı")) {
                    String tip = ek1Txt.getText();
                    int motorSayisi = parseInt(ek2Txt.getText());
                    arac = new HavaAraci(uretimYili, marka, model, fiyat, tip, motorSayisi);
                }

                int durum = VeritabaniBaglantisi.aracEkle(arac);

                if (durum > 0) {
                    Mesaj.goster(this, "Araç Eklendi \nAraç ID: " + durum);
                    gorunumAyari(0);
                } else {
                    Mesaj.goster(this,"Araç Eklenemedi");
                }

            } catch (NumberFormatException ex) {
                Mesaj.goster(this, "Lütfen tüm sayısal alanlara geçerli değerler giriniz!");
            }
        }
        else if(e.getSource() == geri){
            Menu menu = new Menu();
            menu.setVisible(true);
            this.setVisible(false);
        }
    }
}