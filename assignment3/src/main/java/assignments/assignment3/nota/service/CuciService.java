package assignments.assignment3.nota.service;

public class CuciService implements LaundryService{
    private boolean done;
    @Override
    public String doWork() {
        // TODO
        this.done = true;
        return "Sedang mencuci...";
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
        return 0;
    }

    @Override
    public String getServiceName() {
        return "Cuci";
    }
}
