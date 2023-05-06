package assignments.assignment3.nota;
import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.user.Member;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Nota {
    private Member member;
    private String paket;
    private ArrayList<LaundryService> services = new ArrayList<>();
    private long baseHarga;
    private int sisaHariPengerjaan;
    private  int berat;
    private int idNota;
    private String tanggalMasuk;
    private String tanggalSelesai;
    private boolean isDone = false;
    static public int totalNota;
    private int serviceCounter = 0;

    public Nota(Member member, int berat, String paket, String tanggal) {
        //TODO
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        this.idNota = totalNota;
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggal;
        paket = paket.toLowerCase();
        if (paket.equals("express")) {
            this.sisaHariPengerjaan = 1;
            this.baseHarga = 12000;
            cal.add(Calendar.DATE, sisaHariPengerjaan);
            tanggalSelesai = formatter.format(cal.getTime());
        }
        else if (paket.equals("fast")) {
            this.sisaHariPengerjaan = 2;
            this.baseHarga = 10000;
            cal.add(Calendar.DATE, sisaHariPengerjaan);
            tanggalSelesai = formatter.format(cal.getTime());
        }
        else if (paket.equals("reguler")) {
            this.sisaHariPengerjaan = 3;
            this.baseHarga = 7000;
            cal.add(Calendar.DATE, sisaHariPengerjaan);
            tanggalSelesai = formatter.format(cal.getTime());
        }
        services.add(new CuciService());
        totalNota++;
        NotaManager.addNota(this);
    }

    public void addService(LaundryService service){
        //TODO
        this.services.add(service);
    }

    public String kerjakan(){
        // TODO
        serviceCounter += 1;
        if (serviceCounter == services.size()){
            this.isDone = true;
        }
        if (serviceCounter <= services.size()){
            return services.get(serviceCounter - 1).doWork();
        }

        else{
            return "Sudah selesai.";
        }
    }
    public boolean toNextDay() {
        // TODO
        sisaHariPengerjaan--;
        /*if(sisaHariPengerjaan <= 0 && serviceCounter == services.size()){
            isDone = true;
        }*/
        if (isDone){
            sisaHariPengerjaan++;
        }
        return isDone;
    }

    public long calculateHarga(){
        // TODO
        long harga = this.berat * this.baseHarga;
        for (LaundryService service : services){
            harga += service.getHarga(this.berat);
        }
        if (sisaHariPengerjaan < 0){
            harga += sisaHariPengerjaan * 2000;
        }
        return harga;
    }

    public String getNotaStatus(){
        // TODO
        if (this.isDone){
            return "Sudah selesai.";
        }
        else {
            return "Belum selesai.";
        }
    }

    @Override
    public String toString(){
        // TODO
        /*SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        int year = Integer.parseInt(tanggalMasuk.substring(6));
        int month = Integer.parseInt(tanggalMasuk.substring(3, 5)) - 1;
        int date = Integer.parseInt(tanggalMasuk.substring(0, 2));
        cal.set(year, month, date);*/

        String nota = "";
        nota += "ID    : " + idNota + "\n";
        nota += "Paket : " + paket + "\n";
        nota += "Harga :\n";
        nota += String.format("%d kg x %d = %d", berat, this.baseHarga, (berat * this.baseHarga));
        nota += "\nTanggal Terima  : " + tanggalMasuk + "\n";
        //cal.add(Calendar.DATE, sisaHariPengerjaan + 1);
        nota += "Tanggal Selesai : " + tanggalSelesai + "\n";
        nota += "--- SERVICE LIST ---" + "\n";
        for (LaundryService service : services){
            nota += "-" + service.getServiceName() + " @ Rp." + service.getHarga(this.berat) + "\n";
        }
        nota += "Harga Akhir: " + calculateHarga() + "\n";
        if (this.sisaHariPengerjaan < 0){
            nota += "Ada kompensasi keterlambatan " + (-sisaHariPengerjaan) + " * " + 2000 + " hari" + "\n";
        }
        return nota;
    }

    // Dibawah ini adalah getter

    public String getPaket() {
        return paket;
    }

    public int getBerat() {
        return berat;
    }

    public String getTanggal() {
        return tanggalMasuk;
    }

    public int getSisaHariPengerjaan(){
        return sisaHariPengerjaan;
    }
    public boolean isDone() {
        return isDone;
    }

    public ArrayList<LaundryService> getServices(){
        return services;
    }

    public int getIdNota() {
        return idNota;
    }

    public int getServiceCounter() {
        return serviceCounter;
    }
}
