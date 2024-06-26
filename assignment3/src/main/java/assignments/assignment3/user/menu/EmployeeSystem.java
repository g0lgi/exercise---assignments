package assignments.assignment3.user.menu;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Employee;
import assignments.assignment3.user.Member;

import java.util.ArrayList;
import java.util.Arrays;

import static assignments.assignment3.nota.NotaManager.notaList;

public class EmployeeSystem extends SystemCLI {

    /**
     * Membuat object baru EmployeeSystem dan mendaftarkan Employee pada CuciCuci
     */
    public EmployeeSystem() {
        ArrayList <Employee> employees = new ArrayList<>(Arrays.asList(
                new Employee("Dek Depe", "akuDDP"),
                new Employee("Depram", "musiktualembut"),
                new Employee("Lita Duo", "gitCommitPush"),
                new Employee("Ivan Hoshimachi", "SuamiSahSuisei")
        ));
        memberList.addAll(employees);
    }

    /**
     * Memproses pilihan dari employee yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        // TODO:
        if (choice == 3){logout = true;}
        else if (choice == 1){//kerjakan tiap nota
            for (Nota nota: notaList){
                System.out.println("Nota " + nota.getIdNota() + " : " + nota.kerjakan());
            }
        }
        else if (choice == 2){//lihat status tiap nota
            for (Nota nota: notaList){
                System.out.println("Nota " + nota.getIdNota() + " : " + nota.getNotaStatus());
            }
        }
        return logout;
    }

    /**
     * Displays specific menu untuk Employee.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. It's nyuci time");
        System.out.println("2. Display List Nota");
        System.out.println("3. Logout");
    }
}