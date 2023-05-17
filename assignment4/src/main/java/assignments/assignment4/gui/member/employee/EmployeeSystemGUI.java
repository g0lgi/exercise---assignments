package assignments.assignment4.gui.member.employee;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;

import assignments.assignment3.user.Employee;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import static assignments.assignment3.nota.NotaManager.notaList;

public class EmployeeSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "EMPLOYEE";

    public EmployeeSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }


    @Override
    public String getPageName(){
        return KEY;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements Employee.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        // TODO
        JButton nyuciTime = new JButton("It's nyuci time!");
        JButton displayNota = new JButton("Display List Nota");
        return new JButton[]{nyuciTime, displayNota
        };
    }

    /**
     * Method ini mensupply ActionListener korespondensi dengan button yang dibuat createButtons()
     * sesuai dengan requirements MemberSystem.
     *
     * @return Array of ActionListener.
     * */
    @Override
    protected ActionListener[] createActionListeners() {
        return new ActionListener[]{
                e -> cuci(),
                e -> displayNota(),
        };
    }

    /**
     * Menampilkan semua Nota yang ada pada sistem.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void displayNota() {
        // TODO
        JTextArea allNota = new JTextArea();
        allNota.setEditable(false);
        for (Nota nota: notaList){
            allNota.append("Nota " + nota.getIdNota() + " : " + nota.getNotaStatus() + "\n");
        }
        JOptionPane.showMessageDialog(null, allNota);
    }

    /**
     * Menampilkan dan melakukan action mencuci.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void cuci() {
        // TODO
        JTextArea allNota = new JTextArea();
        for (Nota nota: notaList){
            allNota.append("Nota " + nota.getIdNota() + " : " + nota.kerjakan() + "\n");
        }
        JOptionPane.showMessageDialog(null, allNota);
    }
    public void addEmployee(ArrayList <Employee> employees) {
        /*Member[] result = new Member[employees.length + memberList.length];


        System.arraycopy(memberList, 0, result, 0, memberList.length);
        System.arraycopy(employees, 0, result, memberList.length, employees.length);

        memberList = result;*/
    }

}
