/**
 * 
 * @author Bilal Kaan Yüksel - bilal.yuksel@ogr.sakarya.edu.tr
 * @since 26.04.2025
 * <p>
 * Kisi sınıfı, yolcuların isim, yaş ve kalan ömür bilgilerini tutar.
 * Yolcuların her saatlik simülasyon ilerleyişinde yaşam süreleri azalır.
 * </p>
 */package simulasyon;

public class Kisi {
    private String isim;
    private int yas;
    private int kalanOmur; // Saat cinsinden
    private String uzayAraciAdi;
    private boolean hayatta;

    public Kisi(String isim, int yas, int kalanOmur, String uzayAraciAdi) {
        this.isim = isim;
        this.yas = yas;
        this.kalanOmur = kalanOmur;
        this.uzayAraciAdi = uzayAraciAdi;
        this.hayatta = true;
    }

    public void saatIlerle() {
        if (hayatta) {
            kalanOmur--;
            if (kalanOmur <= 0) {
                hayatta = false;
            }
        }
    }

    public boolean isHayatta() {
        return hayatta;
    }

    public String getUzayAraciAdi() {
        return uzayAraciAdi;
    }
}
