package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService{
    private boolean done;
    @Override
    public String doWork() {
        // TODO
        this.done = true;
        return "Sedang menyetrika...";
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
        int harga = berat * 1000;
        return harga;
    }

    @Override
    public String getServiceName() {
        return "Setrika";
    }
}
