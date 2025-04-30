/**
 * 
 * @author Bilal Kaan Yüksel - bilal.yuksel@ogr.sakarya.edu.tr
 * @since 26.04.2025
 * <p>
 * Zaman sınıfı, gün, ay, yıl ve saat bilgilerini tutarak zamanın ilerletilmesini sağlar.
 * Tarih ve saat hesaplamalarında kullanılır.
 * </p>
 */package simulasyon;

public class Zaman {
    private int gun;
    private int ay;
    private int yil;
    private int saat;

    public Zaman(int gun, int ay, int yil) {
        this.gun = gun;
        this.ay = ay;
        this.yil = yil;
        this.saat = 0;
    }

    public void ileriSaatEkle(int saatMiktari, int gunSaat) {
        this.saat += saatMiktari;
        while (this.saat >= gunSaat) {
            this.saat -= gunSaat;
            gun++;
            gunuKontrolEt();
        }
    }

    private void gunuKontrolEt() {
        if (gun > 30) {  // Basit varsayım: her ay 30 gün
            gun = 1;
            ay++;
            if (ay > 12) {
                ay = 1;
                yil++;
            }
        }
    }

    public String tarihYazdir() {
        return String.format("%02d.%02d.%d", gun, ay, yil);
    }

    public String saatYazdir() {
        return String.format("%02d:00", saat);
    }

    public boolean ayniMi(Zaman diger) {
        return this.gun == diger.gun && this.ay == diger.ay && this.yil == diger.yil;
    }

    public int getZamanGunu() {
        return gun;
    }

    public int getZamanAyi() {
        return ay;
    }

    public int getZamanYili() {
        return yil;
    }
}
