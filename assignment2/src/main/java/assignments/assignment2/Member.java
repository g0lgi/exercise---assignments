package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Member {
    // TODO: tambahkan attributes yang diperlukan untuk class ini
String nama;
String noHp;
String id;
int bonusCounter;
    public Member(String Nama, String NoHp) {
        // TODO: buat constructor untuk class ini
        nama = Nama;
        noHp = NoHp;
        id = NotaGenerator.generateId(nama, noHp);
        bonusCounter = 0;
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getNoHp() {
        return noHp;
    }
    public void updateCounter(){
        bonusCounter ++;
    }

    public int getBonusCounter() {
        return bonusCounter;
    }
}
