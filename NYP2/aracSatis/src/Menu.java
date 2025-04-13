import java.awt.*;
import java.awt.event.*;

public class Menu extends Frame implements ActionListener {
    Button aracEkle, aracKaldir, aracAra, tumAraclar, kapat;

    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    void setButton(int x, int y, Button b){
        gbc.gridx = x;
        gbc.gridy = y;
        gbl.setConstraints(b, gbc);
        add(b);
    }

    void setLabel(int x, int y, Label l){
        gbc.gridx = x;
        gbc.gridy = y;
        gbl.setConstraints(l, gbc);
        add(l);
    }

    public Menu(){
        setTitle("Araç Satış Sistemi");
        setBackground(Color.WHITE);
        setExtendedState(MAXIMIZED_BOTH);

        Font font1 = new Font("TimesRoman", Font.BOLD, 30);

        Label welcome = new Label("Araç Satış Sistemi", Label.CENTER);
        welcome.setFont(font1);
        welcome.setForeground(Color.BLACK);
        welcome.setBackground(Color.WHITE);

        aracEkle = new Button("Araç Ekle");
        aracEkle.setFont(font1);
        aracKaldir = new Button("Araç Sil");
        aracKaldir.setFont(font1);
        aracAra = new Button("Araçları Ara");
        aracAra.setFont(font1);
        tumAraclar = new Button("Tüm Araçları Göster");
        tumAraclar.setFont(font1);
        kapat = new Button("Çıkış");
        kapat.setFont(font1);
        setLayout(gbl);
        gbc.weighty=0.01;
        setLabel(1,0,welcome);
        gbc.weighty=0.001;
        setButton(0,1,aracEkle);
        setButton(2,1,aracKaldir);
        setButton(0,3,aracAra);
        setButton(2,3,tumAraclar);
        gbc.weighty=0.01;
        setButton(1,5,kapat);

        aracEkle.addActionListener(this);
        aracKaldir.addActionListener(this);
        aracAra.addActionListener(this);
        tumAraclar.addActionListener(this);
        kapat.addActionListener(this);

    }

    public void actionPerformed(ActionEvent ae){
       if (ae.getSource() == aracEkle){
            AracEkle a = new AracEkle();
            a.setVisible(true);
        }
         if (ae.getSource() == aracKaldir){
            AracKaldir r = new AracKaldir();
            r.setVisible(true);
        }
        if (ae.getSource() == aracAra){
            AracAra s = new AracAra();
            s.setVisible(true);
        }
        if (ae.getSource() == tumAraclar){
            TumAraclar s = new TumAraclar();
            s.setVisible(true);
        }
        if (ae.getSource() == kapat){
            System.exit(0);
        }
    }
}