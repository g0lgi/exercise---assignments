package assignments.assignment2;
import assignments.assignment1.NotaGenerator;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

import static assignments.assignment1.NotaGenerator.*;
public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static Nota[] notaList;
    private static Member[] memberList;

    public static void main(String[] args) {
        notaList = new Nota[0];
        memberList = new Member[0];
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            System.out.print("Pilihan : ");
            String command = input.next();
            input.nextLine();
            System.out.println("================================");
            switch (command){
                case "1" -> handleGenerateUser();
                case "2" -> handleGenerateNota();
                case "3" -> handleListNota();
                case "4" -> handleListUser();
                case "5" -> handleAmbilCucian();
                case "6" -> handleNextDay();
                case "0" -> isRunning = false;
                default -> System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
    }

    private static void handleGenerateUser() {
        // TODO: handle generate user
        System.out.println("Masukkan nama Anda:");
        String inputNama = input.nextLine();
        System.out.println("Masukkan nomor handphone Anda:");
        String nomorHandphone = input.nextLine();
        while (! nomorHandphone.matches("[0-9]+")){ //memastikan nomor hp hanya digit
            System.out.println("Nomor hp hanya menerima digit");
            nomorHandphone = input.nextLine();
        }
        Member baru = new Member(inputNama, nomorHandphone);
        boolean udahAda = false;
        for (Member member : memberList) { //check apa member sudah ada
            if (member.getId().equals(baru.getId())) {
                udahAda = true;
                break;
            }
        }
        if (udahAda){
            System.out.println("Member dengan nama " + inputNama + " dan nomor hp " + nomorHandphone + " sudah ada!");
        }
        else{
            System.out.println("Berhasil membuat member dengan ID " + baru.getId());
            memberList = Arrays.copyOf(memberList, memberList.length + 1);
            memberList[memberList.length - 1] = baru;
        }
    }

    private static void handleGenerateNota() {
        // TODO: handle ambil cucian
        System.out.println("Masukkan ID member:");
        String inputID = input.nextLine();
        boolean idCheck = false;
        Member user;
        for (Member member : memberList) { //cek apa member ada di list
            if (inputID.equals(member.getId())) {
                user = member;
                System.out.println("Masukkan paket laundry: ");
                String paket = input.nextLine();
                while ((!paket.equalsIgnoreCase("express")) && (!paket.equalsIgnoreCase("fast") && (!paket.equalsIgnoreCase("reguler")))) {
                    if (paket.equals("?")) {
                        System.out.println("""
                                +-------------Paket-------------+
                                | Express | 1 Hari | 12000 / Kg |
                                | Fast    | 2 Hari | 10000 / Kg |
                                | Reguler | 3 Hari |  7000 / Kg |
                                +-------------------------------+""");
                    } else {
                        System.out.println("Paket " + paket + " tidak diketahui\n" +
                                "[ketik ? untuk mencari tahu jenis paket]");
                    }
                    System.out.println("Masukkan paket laundry:");
                    paket = input.nextLine();
                }
                System.out.println("Masukan berat cucian Anda [Kg]:");
                int berat;
                while (true) {
                    try { //memastikan berat bilangan positif
                        berat = input.nextInt();
                        if (berat < 2) {
                            berat = 2;
                            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                        }
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                        //input.nextLine();
                    }
                }
                String formatter = fmt.format(cal.getTime());
                Nota baru = new Nota(user, paket, berat, formatter);
                notaList = Arrays.copyOf(notaList, notaList.length + 1); //bikin notalist baru
                notaList[notaList.length - 1] = baru;
                baru.getMember().updateCounter();
                System.out.println("Berhasil menambahkan nota!");
                System.out.println("[ID Nota = " + baru.getIdNota() + "]");
                System.out.println(NotaGenerator.generateNota(inputID, paket, berat, formatter, baru.getMember().getBonusCounter()));
                System.out.println("Status\t\t: " + baru.status());
                idCheck = true;
                break;
            }
        }
        if (!idCheck){
            System.out.println("Member dengan ID " + inputID + " tidak ditemukan!\n");
        }
    }

    private static void handleListNota() {
        // TODO: handle list semua nota pada sistem
        System.out.println("Terdaftar " + notaList.length + " nota dalam sistem");
        for (Nota nota : notaList) { //loop through tiap nota di list dan print nota tersebut
            System.out.println("- " + "[" + nota.getIdNota() + "] Status\t" + " : " + nota.status());
        }
    }

    private static void handleListUser() {
        // TODO: handle list semua user pada sistem
        if (memberList.length > 0) {//cek apa udah ada member di list
            System.out.println("Terdaftar " + memberList.length + " member dalam sistem");
            for (Member member : memberList) {
                System.out.println("- " + member.getId() + " : " + member.getNama());
            }
        }
        else{
            System.out.println("Terdaftar 0 member dalam sistem");
        }
    }
    private static void handleAmbilCucian() {
        // TODO: handle ambil cucian
        System.out.println("Masukan ID nota yang akan diambil:");
        int inputID;
        while (true) { //memastikan input integer
            try {
                inputID = input.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("ID nota berbentuk angka!");
                //input.nextLine();
            }
        }
        int index = -1; //index list mulai dari 0, jadi biar aman
        for (int i = 0; i < notaList.length; i++){ //cari apa id nota sudah ada di list
            if (notaList[i].getIdNota() == inputID){
                index = i;
                break;
            }
        }
        if (index >= 0) {// kalo index >= 0, berarti nota ada
            if (notaList.length > 1 && notaList[index].isIsReady()) {//buat kalo udah ada nota di dalam list dan udah ready
                System.out.println("Nota dengan ID " + notaList[index].getIdNota() + " berhasil diambil!");
                for (int j = index; j < (notaList.length - 1); j++) {
                    notaList[j] = notaList[j + 1];
                }
                notaList = Arrays.copyOf(notaList, notaList.length - 1);
            } else if (notaList.length == 1 && notaList[index].isIsReady()) {//buat kalo cuma ada nota di dalam list dan udah ready
                System.out.println("Nota dengan ID " + notaList[index].getIdNota() + " berhasil diambil!");
                notaList = Arrays.copyOf(notaList, notaList.length - 1);
            } else if (notaList.length >= 1 && !notaList[index].isIsReady()) {//buat kalo udah ada nota di dalam list dan belum ready
                System.out.println("Nota dengan ID " + notaList[index].getIdNota() + " gagal diambil!");
            }
        }
        else{ //buat kalo nota gada di dalem list
            System.out.println("Nota dengan ID " + inputID + " tidak ditemukan!");
        }
    }

    private static void handleNextDay() {
        // TODO: handle ganti hari
        System.out.println("Dek Depe tidur hari ini... zzz...");
        cal.add(Calendar.DATE, 1); //ganti hari di calendar
        for (Nota nota : notaList) {//update sisahari tiap nota
            nota.updateSisaHari();
            if (nota.isIsReady()) {
                System.out.println("Laundry dengan nota ID " + nota.getIdNota() + " sudah dapat diambil!");
            }
        }
        System.out.println("""
                Selamat pagi dunia!
                Dek Depe: It's CuciCuci Time.
                """);
    }

    private static void printMenu() {
        System.out.println("\nSelamat datang di CuciCuci!");
        System.out.printf("Sekarang Tanggal: %s\n", fmt.format(cal.getTime()));
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate User");
        System.out.println("[2] Generate Nota");
        System.out.println("[3] List Nota");
        System.out.println("[4] List User");
        System.out.println("[5] Ambil Cucian");
        System.out.println("[6] Next Day");
        System.out.println("[0] Exit");
    }

}
