package assignments.assignment3.nota.service;

import assignments.assignment3.nota.Nota;

public class AntarService implements LaundryService{
    private boolean done;
    @Override
    public String doWork() {
        // TODO
        this.done = true;
        return "Sedang mengantar...";
    }

    @Override
    public boolean isDone() {
        // TODO
        if (this.done){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public long getHarga(int berat) {
        // TODO
        long harga = berat * 500;
        if (harga <= 2000){
            harga = 2000;
        }
        return harga;
    }

    @Override
    public String getServiceName() {
        return "Antar";
    }
}
