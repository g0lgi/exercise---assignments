package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Nota {
    // TODO: tambahkan attributes yang diperlukan untuk class ini
    private static int counter;
    int idNota;
String paket;
Member member;
int berat;
String tanggalMasuk;
int sisaHariPengerjaan;
boolean isReady;

    public Nota(Member Member, String Paket, int Berat, String TanggalMasuk) {
        // TODO: buat constructor untuk class ini
        member = Member;
        paket = Paket;
        berat = Berat;
        tanggalMasuk = TanggalMasuk;
        if (Paket.equalsIgnoreCase("express")){
            sisaHariPengerjaan = 1;
        }
        else if (Paket.equalsIgnoreCase("fast")) {
            sisaHariPengerjaan = 2;
        }
        else if (Paket.equalsIgnoreCase("reguler")){
            sisaHariPengerjaan = 3;
        }
        idNota = counter;
        counter ++;
        isReady = false;
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini

    public Member getMember() {
        return member;
    }

    public void updateSisaHari() {
        if(sisaHariPengerjaan > 1){
            this.sisaHariPengerjaan --;
        }
        else{
            this.isReady = true;
        }
    }

    public int getIdNota() {
        return idNota;
    }
    public String status(){
        if(this.isReady){
            return "Sudah dapat diambil!";
        }
        else{
            return "Belum bisa diambil :(";
        }
    }

    public boolean isIsReady() {
        return isReady;
    }

    public int getSisaHariPengerjaan() {
        return sisaHariPengerjaan;
    }
}

