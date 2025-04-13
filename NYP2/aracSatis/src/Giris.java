import java.awt.*;
import java.awt.event.*;

public class Giris extends Frame implements ActionListener{

    String kullaniciAdi = "furkan";
    String sifre = "123";

    TextField kullaniciAdiTxt, sifreTxt;
    Button giris, kapat;

    GridBagLayout gbl;
    GridBagConstraints gbc;

    public Giris() {
        setTitle("Giriş Ekranı");
        setSize(500, 500);
        setBackground(Color.WHITE);

        Font font1 = new Font("Serif", Font.BOLD, 20);

        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        setLayout(gbl);

        Label label1 = new Label("Kullanıcı Adı", Label.CENTER);
        label1.setFont(font1);

        Label label2 = new Label("Sifre", Label.CENTER);
        label2.setFont(font1);

        kullaniciAdiTxt = new TextField(15);
        sifreTxt = new TextField(15);
        sifreTxt.setEchoChar('*');

        giris = new Button("Giris");
        kapat = new Button("Kapat");
        giris.setFont(font1);
        kapat.setFont(font1);

        gbc.gridx = 0; gbc.gridy = 1;
        gbl.setConstraints(label1, gbc);
        add(label1);

        gbc.gridx = 2; gbc.gridy = 1;
        gbl.setConstraints(kullaniciAdiTxt, gbc);
        add(kullaniciAdiTxt);

        gbc.gridx = 0; gbc.gridy = 2;
        gbl.setConstraints(label2, gbc);
        add(label2);

        gbc.gridx = 2; gbc.gridy = 2;
        gbl.setConstraints(sifreTxt, gbc);
        add(sifreTxt);

        gbc.gridx = 0; gbc.gridy = 5;
        gbl.setConstraints(giris, gbc);
        add(giris);

        gbc.gridx = 2; gbc.gridy = 5;
        gbl.setConstraints(kapat, gbc);
        add(kapat);

        giris.addActionListener(this);
        kapat.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == giris) {
            if (kullaniciAdiTxt.getText().equals(kullaniciAdi) && sifreTxt.getText().equals(sifre)) {
                Mesaj.goster(this,"Giriş Başarılı");
                Menu menu = new Menu();
                menu.setVisible(true);

                this.setVisible(false);
            }
            else {
                Mesaj.goster(this, "Kullanıcı adı veya şifre hatalı");
            }
        }

        if (e.getSource() == kapat) {
            System.exit(0);
        }
    }
}