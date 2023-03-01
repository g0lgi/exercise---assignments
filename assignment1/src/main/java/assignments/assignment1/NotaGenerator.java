package assignments.assignment1;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.time.LocalDate;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);

    /**
     * Method main, program utama kalian berjalan disini.
     */
    public static void main(String[] args) {
        // TODO: Implement interface menu utama
        while (true) { //infinite loop menu
            printMenu();
            String pilihan = input.next();
            input.nextLine();
            System.out.println("================================");
            if (pilihan.equalsIgnoreCase("1")) {
                System.out.println("Masukkan nama Anda:");
                String inputNama = input.nextLine();
                System.out.println("Masukkan nomor handphone Anda:");
                String nomorHandphone = input.nextLine();
                while (! nomorHandphone.matches("[0-9]+")){
                    System.out.println("Nomor hp hanya menerima digit");
                    nomorHandphone = input.nextLine();
                }
                String id = generateId(inputNama, nomorHandphone);
                System.out.println("ID Anda: " + id);
            } else if (pilihan.equalsIgnoreCase("2")) {
                System.out.println("Masukkan nama Anda:");
                String nama = input.nextLine();
                System.out.println("Masukkan nomor handphone Anda:");
                String nomorHandphone = input.nextLine();
                while (!nomorHandphone.matches("[0-9]+")) {
                    System.out.println("Nomor hp hanya menerima digit");
                    nomorHandphone = input.nextLine();
                }
                System.out.println("Masukkan tanggal terima:");
                String tanggalTerima = input.nextLine();
                System.out.println("Masukkan paket laundry: ");
                String paket = input.nextLine();
                while ((!paket.equalsIgnoreCase("express")) && (!paket.equalsIgnoreCase("fast") && (!paket.equalsIgnoreCase("reguler")))) {
                    if (paket.equals("?")) {
                        System.out.println("+-------------Paket-------------+\n" +
                                "| Express | 1 Hari | 12000 / Kg |\n" +
                                "| Fast    | 2 Hari | 10000 / Kg |\n" +
                                "| Reguler | 3 Hari |  7000 / Kg |\n" +
                                "+-------------------------------+");
                        System.out.println("Masukkan paket laundry:");
                        paket = input.nextLine();
                    } else {
                        System.out.println("Paket " + paket + " tidak diketahui\n" +
                                "[ketik ? untuk mencari tahu jenis paket]");
                        System.out.println("Masukkan paket laundry:");
                        paket = input.nextLine();
                    }
                }
                int berat;
                System.out.println("Masukkan berat cucian Anda [Kg]: ");
                while (true) {
                    try {
                        berat = input.nextInt();
                        if (berat >= 2) {
                            break;
                        }
                        else {
                            berat = 2;
                            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                        input.nextLine();
                    }
                }
                String id = generateId(nama, nomorHandphone);
                System.out.println(generateNota(id, paket, berat, tanggalTerima));
            } else if (pilihan.equalsIgnoreCase("0")) {
                break;
            }
            else {
                System.out.println("Perintah tidak dikenali");
            }
        }
    }

    /**
     * Method untuk menampilkan menu di NotaGenerator.
     */
    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }

    /**
     * Method untuk menampilkan paket.
     */
    private static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    /**
     * Method untuk membuat ID dari nama dan nomor handphone.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing
     *
     * @return String ID anggota dengan format [NAMADEPAN]-[nomorHP]-[2digitChecksum]
     */
    public static String generateId(String nama, String nomorHP){
        // TODO: Implement generate ID sesuai soal.
        nama = nama.toUpperCase();
        //nama = nama.split(" ")[0];
        int indexSpasi = nama.indexOf(" ");
        String kataPertama;
        if (indexSpasi == -1) {
            kataPertama = nama; // Input hanya punya satu kata
        } else {
            kataPertama = nama.substring(0, indexSpasi);
        }
        int sumString = checksumString(kataPertama, kataPertama.length());
        int sumInt = 0;
        for (int i = 0; i < nomorHP.length(); i++){
            char digit = nomorHP.charAt(i);
            int value = Character.getNumericValue(digit);
            sumInt += value;
        }
        String baru;
        if (sumInt < 9){
            baru = kataPertama + "-" + nomorHP + "-" + "0" + (sumString + sumInt + 7);
        }
        else{
            baru = kataPertama + "-" + nomorHP + "-" + (sumString + sumInt + 7);
        }
        return baru;
    }

    /**
     *
     * Method untuk membuat Nota.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing.
     *
     * @return string nota dengan format di bawah:
     *         <p>ID    : [id]
     *         <p>Paket : [paket]
     *         <p>Harga :
     *         <p>[berat] kg x [hargaPaketPerKg] = [totalHarga]
     *         <p>Tanggal Terima  : [tanggalTerima]
     *         <p>Tanggal Selesai : [tanggalTerima + LamaHariPaket]
     */

    public static String generateNota(String id, String paket, int berat, String tanggalTerima) {
        // TODO: Implement generate nota sesuai soal.
        int hargaPaketPerKg = 0;
        int LamaHariPaket = 0;
        if (paket.equalsIgnoreCase("express")) {
            hargaPaketPerKg = 12000;
            LamaHariPaket = 1;
        } else if (paket.equalsIgnoreCase("fast")) {
            hargaPaketPerKg = 10000;
            LamaHariPaket = 2;
        } else if (paket.equalsIgnoreCase("reguler")) {
            hargaPaketPerKg = 7000;
            LamaHariPaket = 3;
        }
        LocalDate date;
        String tanggalSelesai;
        try {
            DateTimeFormatter formatter = //ganti format tanggal
                    DateTimeFormatter.ofPattern("dd/MM/yyyy");
            date = LocalDate.parse(tanggalTerima, formatter);
            tanggalSelesai = date.plusDays(LamaHariPaket).format(formatter);
            System.out.printf("%s%n", date);
        } catch (DateTimeParseException exc) {
            System.out.printf("%s is not parsable!%n", input);
            throw exc;      // Rethrow the exception.
        }
        int totalHarga = berat * hargaPaketPerKg;
        return "ID    : " + id + "\n" +
                "Paket : " + paket + "\n" +
                "Harga :\n" +
                berat + " kg x " + hargaPaketPerKg + " = " + totalHarga + "\n" +
                "Tanggal Terima  : " + tanggalTerima + "\n" +
                "Tanggal Selesai : " + tanggalSelesai;
    }
    public static int checksumString(String namaTemp, int n) { // rekursi checksum
        if (n == 0) {
            return 0;
        }
        else {
            return ((namaTemp.charAt(n - 1) - 'A' + 1) + checksumString(namaTemp, (n - 1)));
        }
    }
}
