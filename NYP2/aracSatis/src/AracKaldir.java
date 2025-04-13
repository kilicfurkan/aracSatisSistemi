import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class AracKaldir extends Frame implements ActionListener {
    int id;
    DecimalFormat df = new DecimalFormat("#,###");


    Label idLbl, aracTuruLbl, markaLbl, modelLbl, uretimYiliLbl, fiyatLbl, ek1Lbl, ek2Lbl, ek3Lbl;

    Button ara, sil, geri;

    TextField idTxt, markaTxt, modelTxt, uretimYiliTxt, fiyatTxt, ek1Txt, ek2Txt, ek3Txt;
    Choice aracTuruTxt;

    Font f1 = new Font("TimesRoman", Font.BOLD, 20);

    GridBagConstraints gbc = new GridBagConstraints();
    GridBagLayout gbl = new GridBagLayout();

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
                ek2Txt.setText("Motor Hacmi");
                break;
            case 4:
                ek1Lbl.setVisible(true);
                ek2Lbl.setVisible(false);
                ek3Lbl.setVisible(false);
                ek1Txt.setVisible(true);
                ek2Txt.setVisible(false);
                ek3Txt.setVisible(false);
                ek1Lbl.setText("Tip");
                ek2Txt.setText("Motor Hacmi");
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
    public AracKaldir() {

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

        idTxt.setEditable(true);
        markaTxt.setEditable(false);
        modelTxt.setEditable(false);
        uretimYiliTxt.setEditable(false);
        fiyatTxt.setEditable(false);
        ek1Txt.setEditable(false);
        ek2Txt.setEditable(false);
        ek3Txt.setEditable(false);

        ara = new Button("Ara");
        ara.setFont(f1);
        sil = new Button("Sil");
        sil.setFont(f1);
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
        set(2, 11, sil);
        set(4, 11, geri);


        gorunumAyari(0);

        ara.addActionListener(this);
        sil.addActionListener(this);
        geri.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ara) {
            aracTuruTxt.setEnabled(false);
            id = Integer.parseInt(idTxt.getText());
            idTxt.setText(Integer.toString(id));

            Arac arac = VeritabaniBaglantisi.aracGetir(id);

            if(arac == null){
                Mesaj.goster(this,"Bu araç bulunamadı..");
                gorunumAyari(0);
            }

            markaTxt.setText(arac.getMarka());
            modelTxt.setText(arac.getModel());
            uretimYiliTxt.setText(Integer.toString(arac.getUretimYili()));
            fiyatTxt.setText(df.format(arac.getFiyat()));

            if (arac instanceof Otomobil) {
                gorunumAyari(1);
                ek1Lbl.setText(("Km"));
                ek2Lbl.setText(("Motor Gücü"));
                ek1Txt.setText(Integer.toString(((Otomobil) arac).getKm()));
                ek2Txt.setText(Integer.toString(((Otomobil) arac).getMotorGucu()));
                aracTuruTxt.select(1);
            }
            else if (arac instanceof Ticari) {
                gorunumAyari(2);
                ek1Lbl.setText(("Km"));
                ek1Txt.setText(Integer.toString(((Ticari) arac).getKm()));
                ek2Txt.setText(Integer.toString(((Ticari) arac).getMotorGucu()));
                ek3Txt.setText(Integer.toString(((Ticari) arac).getYukKapasitesi()));
                aracTuruTxt.select(2);
            }
            else if (arac instanceof Motosiklet) {
                gorunumAyari(3);
                ek1Txt.setText(Integer.toString(((Motosiklet) arac).getKm()));
                ek2Txt.setText(Integer.toString(((Motosiklet) arac).getMotorHacmi()));
                aracTuruTxt.select(3);
            }
            else if(arac instanceof DenizAraci){
                gorunumAyari(4);
                ek1Txt.setText(((DenizAraci) arac).getTip());
                aracTuruTxt.select(4);
            }
            else if(arac instanceof HavaAraci){
                gorunumAyari(5);
                ek1Txt.setText(((HavaAraci) arac).getTip());
                ek2Txt.setText(Integer.toString(((HavaAraci) arac).getMotorSayisi()));
                aracTuruTxt.select(5);
            }
        }

        if(e.getSource() == sil){
            int id = Integer.parseInt(idTxt.getText());
            try {
                boolean sonuc = VeritabaniBaglantisi.aracSil(id);

                if (sonuc) {
                    Mesaj.goster(this,"Araç başarıyla silindi");
                    gorunumAyari(0);
                } else {
                    Mesaj.goster(this,"Belirtilen ID'ye sahip araç bulunamadı.");
                    gorunumAyari(0);
                }
            } catch (Exception err) {
                Mesaj.goster(this, "Araç silinirken bir hata oluştu: " + err.getMessage());
                err.printStackTrace();
            }
        }

        else if (e.getSource() == geri){
            Menu menu = new Menu();
            menu.setVisible(true);
        }
    }

    public static void main(String[] args) {
        AracKaldir arac = new AracKaldir();
        arac.setVisible(true);
    }
}