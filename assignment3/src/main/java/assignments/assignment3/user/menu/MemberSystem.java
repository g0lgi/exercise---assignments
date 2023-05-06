package assignments.assignment3.user.menu;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;

import static assignments.assignment3.nota.NotaManager.cal;
import static assignments.assignment3.nota.NotaManager.fmt;

public class MemberSystem extends SystemCLI {
    /**
     * Memproses pilihan dari Member yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        // TODO
        if (choice == 3){logout = true;}
        else if (choice == 1) {
            System.out.println("Masukan paket laundry:");
            System.out.println("+-------------Paket-------------+");
            System.out.println("| Express | 1 Hari | 12000 / Kg |");
            System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
            System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
            System.out.println("+-------------------------------+");
            String paket = in.nextLine();
            System.out.println("Masukan berat cucian Anda [Kg]: ");
            int berat = in.nextInt();
            in.nextLine();
            if (berat < 2) {
                System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                berat = 2;
            }
            String tanggal = fmt.format(cal.getTime());
            Nota nota = new Nota(loginMember, berat, paket, tanggal);
            loginMember.addNota(nota);
            System.out.println("Apakah kamu ingin cucianmu disetrika oleh staff professional kami?\n" +
                    "Hanya tambah 1000 / kg :0\n" +
                    "[Ketik x untuk tidak mau]: ");
            String konfirmasi = in.next();
            in.nextLine();
            if (!konfirmasi.equalsIgnoreCase("x")) {
                nota.addService(new SetrikaService());
            }
            System.out.println("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan!\n" +
                    "Cuma 2000 / 4kg, kemudian 500 / kg\n" +
                    "[Ketik x untuk tidak mau]: ");
            konfirmasi = in.next();
            in.nextLine();
            if (!konfirmasi.equalsIgnoreCase("x")) {
                nota.addService(new AntarService());
            }
            System.out.println("Nota berhasil dibuat!");
        }
        else if (choice == 2){
            for (Nota nota : loginMember.getNotaList()){
                System.out.println("[ID Nota = " + nota.getIdNota() + "]");
                System.out.println(nota.toString());
            }
        }
        return logout;
    }

    /**
     * Displays specific menu untuk Member biasa.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. Saya ingin laundry");
        System.out.println("2. Lihat detail nota saya");
        System.out.println("3. Logout");
    }

    /**
     * Menambahkan Member baru ke sistem.
     *
     * @param member -> Member baru yang akan ditambahkan.
     */
    public void addMember(Member member) {
        // TODO
        this.memberList.add(member);
    }
}