import java.awt.*;
import java.awt.event.*;

public class Mesaj extends Dialog {

    public Mesaj(Frame parent, String mesaj) {
        super(parent, "Bilgilendirme", true); // Modal pencere
        setLayout(new BorderLayout());

        Label mesajLabel = new Label(mesaj, Label.CENTER);
        mesajLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        add(mesajLabel, BorderLayout.CENTER);

        Button tamam = new Button("Tamam");
        tamam.setFont(new Font("Arial", Font.BOLD, 14));
        add(tamam, BorderLayout.SOUTH);

        tamam.addActionListener(e -> setVisible(false));

        setSize(300, 150);
        setLocationRelativeTo(parent); // Ortada açılır
    }

    public static void goster(Frame parent, String mesaj) {
        Mesaj mk = new Mesaj(parent, mesaj);
        mk.setVisible(true);
    }
}
