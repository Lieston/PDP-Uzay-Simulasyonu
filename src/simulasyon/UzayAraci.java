/**
 * 
 * @author Bilal Kaan Yüksel - bilal.yuksel@ogr.sakarya.edu.tr
 * @since 26.04.2025
 * <p>
 * UzayAraci sınıfı, uzay araçlarının çıkış, varış, yolcular ve yolculuk süreci bilgilerini tutar.
 * Araçların hareketleri, kalan saatleri ve varış tarihleri bu sınıfta yönetilir.
 * </p>
 */package simulasyon;

import java.util.ArrayList;
import java.util.List;

public class UzayAraci {
    private String ad;
    private String cikis;
    private String varis;
    private Zaman cikisTarihi;
    private int mesafe;
    private int kalanSaat;
    private String durum; // "Bekliyor", "Yolda", "Vardı", "IMHA"
    private List<Kisi> yolcular;

    public UzayAraci(String ad, String cikis, String varis, Zaman cikisTarihi, int mesafe) {
        this.ad = ad;
        this.cikis = cikis;
        this.varis = varis;
        this.cikisTarihi = cikisTarihi;
        this.mesafe = mesafe;
        this.kalanSaat = mesafe;
        this.durum = "Bekliyor";
        this.yolcular = new ArrayList<>();
    }

    public void yolcuEkle(Kisi kisi) {
        yolcular.add(kisi);
    }

    public void kontrolEtVeBaslat(Zaman gezegenZamani) {
        if (durum.equals("Bekliyor") && gezegenZamani.ayniMi(cikisTarihi)) {
            durum = "Yolda";
        }
    }

    public void saatIlerle(int hedefGezegenGunSaat) {
        if (durum.equals("Yolda")) {
            kalanSaat--;
            for (Kisi k : yolcular) {
                if (k.isHayatta()) {
                    k.saatIlerle();
                }
            }

            boolean hepsiOlmus = yolcular.stream().noneMatch(Kisi::isHayatta);
            if (hepsiOlmus) {
                durum = "IMHA";
            } else if (kalanSaat <= 0) {
                durum = "Vardı";
            }
        } else if (durum.equals("Bekliyor")) {
            for (Kisi k : yolcular) {
                if (k.isHayatta()) {
                    k.saatIlerle();
                }
            }
        }
    }

    public boolean yolculukBittiMi() {
        return durum.equals("Vardı") || durum.equals("IMHA");
    }

    public String getDurum() {
        return durum;
    }

    public String getAd() {
        return ad;
    }

    public String getCikis() {
        return cikis;
    }

    public String getVaris() {
        return varis;
    }

    public int getKalanSaat() {
        return kalanSaat;
    }

    public List<Kisi> getYolcular() {
        return yolcular;
    }

    public Zaman getCikisTarihi() {
        return cikisTarihi;
    }

    // 🔥 Doğru varış tarihi hesaplaması
    public String hedefeVarisTarihi(Gezegen cikisGezegen, Gezegen varisGezegen) {
        // 1) Çıkış gezegeninde çıkış tarihine kadar geçen süre (gün)
        int farkGun = (cikisTarihi.getZamanYili() - cikisGezegen.getBaslangicZamani().getZamanYili()) * 365
                    + (cikisTarihi.getZamanAyi() - cikisGezegen.getBaslangicZamani().getZamanAyi()) * 30
                    + (cikisTarihi.getZamanGunu() - cikisGezegen.getBaslangicZamani().getZamanGunu());

        int beklemeSaati = farkGun * cikisGezegen.getGunSaat();

        // 2) Toplam geçen saat
        int toplamSaat = beklemeSaati + mesafe;

        // 3) Varış gezegenine göre kaç gün eder
        int toplamGun = toplamSaat / varisGezegen.getGunSaat();

        // 4) Varış gezegeninin başlangıç tarihinden itibaren ilerle
        Zaman varisTarihi = new Zaman(
            varisGezegen.getBaslangicZamani().getZamanGunu(),
            varisGezegen.getBaslangicZamani().getZamanAyi(),
            varisGezegen.getBaslangicZamani().getZamanYili()
        );

        for (int i = 0; i < toplamGun; i++) {
            varisTarihi.ileriSaatEkle(varisGezegen.getGunSaat(), varisGezegen.getGunSaat());
        }

        return varisTarihi.tarihYazdir();
    }
}
