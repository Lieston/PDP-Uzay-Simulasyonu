/**
 * 
 * @author Bilal Kaan Yüksel - bilal.yuksel@ogr.sakarya.edu.tr
 * @since 26.04.2025
 * <p>
 * Gezegen sınıfı, gezegenlerin adını, saatlik gün uzunluğunu ve zaman bilgilerini tutar.
 * Ayrıca her gezegenin sabit başlangıç zamanını saklar ve zaman ilerlemesini sağlar.
 * </p>
 */package simulasyon;
 
 

public class Gezegen {
    private String ad;
    private int gunSaat;
    private Zaman zaman;                // Simülasyon sırasında değişen zaman
    private Zaman baslangicZamani;     // Sabit, başlangıç zamanı
    private int nufus;

    public Gezegen(String ad, int gunSaat, Zaman zaman) {
        this.ad = ad;
        this.gunSaat = gunSaat;
        this.zaman = zaman;

        // Başlangıç zamanı kopyalanıyor (ilerlemeden etkilenmeyecek)
        this.baslangicZamani = new Zaman(
            zaman.getZamanGunu(),
            zaman.getZamanAyi(),
            zaman.getZamanYili()
        );

        this.nufus = 0;
    }

    public void saatIlerle() {
        zaman.ileriSaatEkle(1, gunSaat);
    }

    public void nufusArttir(int miktar) {
        this.nufus += miktar;
    }

    public String getAd() {
        return ad;
    }

    public int getGunSaat() {
        return gunSaat;
    }

    public Zaman getZaman() {
        return zaman;
    }

    public Zaman getBaslangicZamani() {
        return baslangicZamani;
    }

    public int getNufus() {
        return nufus;
    }

    public String tarihSaatYazdir() {
        return zaman.tarihYazdir() + " " + zaman.saatYazdir();
    }
}
