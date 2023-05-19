package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateNotaGUI extends JPanel {
    public static final String KEY = "CREATE_NOTA";
    private JLabel paketLabel;
    private JComboBox<String> paketComboBox;
    private JButton showPaketButton;
    private JLabel beratLabel;
    private JTextField beratTextField;
    private JCheckBox setrikaCheckBox;
    private JCheckBox antarCheckBox;
    private JButton createNotaButton;
    private JButton backButton;
    private JPanel mainPanel;
    private final SimpleDateFormat fmt;
    private final Calendar cal;
    private final MemberSystemGUI memberSystemGUI;

    public CreateNotaGUI(MemberSystemGUI memberSystemGUI) {
        this.memberSystemGUI = memberSystemGUI;
        this.fmt = NotaManager.fmt;
        this.cal = NotaManager.cal;

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        // TODO
        paketLabel = new JLabel("Paket Laundry:");
        showPaketButton = new JButton("Show Paket");
        showPaketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPaket();
            }
        });
        String[] arrayPaket = {"Express", "Fast", "Reguler"};
        paketComboBox = new JComboBox<>(arrayPaket);

        beratLabel = new JLabel("Berat Cucian (Kg)");
        beratTextField = new JTextField();

        setrikaCheckBox = new JCheckBox("Tambah Setrika Service (1000/kg)");
        antarCheckBox = new JCheckBox("Tambah Antar Service (2000/4kg pertama, kemudian 500/kg)");

        createNotaButton = new JButton("Buat Nota");
        createNotaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNota();
            }
        });

        backButton = new JButton("Kembali");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBack();
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0); // top, left, bottom, right
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(paketLabel, gbc);

        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(paketComboBox, gbc);

        gbc.gridx = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(showPaketButton, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(beratLabel, gbc);

        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(setrikaCheckBox, gbc);

        gbc.gridy = 3;
        mainPanel.add(antarCheckBox, gbc);

        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(createNotaButton, gbc);

        gbc.gridy = 5;
        gbc.insets = new Insets(0, 0, 0, 0);
        mainPanel.add(backButton, gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill =GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(beratTextField, gbc);
    }

    /**
     * Menampilkan list paket pada user.
     * Akan dipanggil jika pengguna menekan "showPaketButton"
     * */
    private void showPaket() {
        String paketInfo = """
                        <html><pre>
                        +-------------Paket-------------+
                        | Express | 1 Hari | 12000 / Kg |
                        | Fast    | 2 Hari | 10000 / Kg |
                        | Reguler | 3 Hari |  7000 / Kg |
                        +-------------------------------+
                        </pre></html>
                        """;

        JLabel label = new JLabel(paketInfo);
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(this, label, "Paket Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method untuk melakukan pengecekan input user dan mendaftarkan nota yang sudah valid pada sistem.
     * Akan dipanggil jika pengguna menekan "createNotaButton"
     * */
    private void createNota() {
        // TODO
        String paket = paketComboBox.getSelectedItem().toString();
        int berat = 0;
        try {
            berat = Integer.parseInt(beratTextField.getText());
            if (berat > 0){
                if (berat < 2) {
                    berat = 2;
                    JOptionPane.showMessageDialog(null, "Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Harap masukan berat cucian Anda dalam bentuk bilangan positif!");
                beratTextField.setText("");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Harap masukan berat cucian Anda dalam bentuk bilangan positif!");
            beratTextField.setText("");
        }
        String tanggalTerima = fmt.format(cal.getTime());
        Nota nota = new Nota(memberSystemGUI.getLoggedInMember(),  berat, paket, tanggalTerima);
        if (setrikaCheckBox.isSelected()){
            nota.addService(new SetrikaService());
        }
        if (antarCheckBox.isSelected()){
            nota.addService(new AntarService());
        }
        memberSystemGUI.getLoggedInMember().addNota(nota);
        JOptionPane.showMessageDialog(null, "Nota berhasil dibuat!");

        //reset semua input
        paketComboBox.setSelectedIndex(0);
        beratTextField.setText("");
        setrikaCheckBox.setSelected(false);
        antarCheckBox.setSelected(false);
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        // TODO
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.navigateTo("MEMBER");
    }
}
