/**
 * 
 * @author Bilal Kaan Yüksel - bilal.yuksel@ogr.sakarya.edu.tr
 * @since 26.04.2025
 * <p>
 * Simulasyon sınıfı, saatlik döngü içerisinde gezegenlerin ve uzay araçlarının zamanını ilerletir.
 * Araçların hareketini, yolcuların yaşamını ve simülasyonun genel akışını kontrol eder.
 * </p>
 */package simulasyon;

import java.util.List;
import java.util.Map;

public class Simulasyon {
    private Map<String, Gezegen> gezegenler;
    private List<UzayAraci> araclar;
    private int toplamSaat;

    public Simulasyon(Map<String, Gezegen> gezegenler, List<UzayAraci> araclar) {
        this.gezegenler = gezegenler;
        this.araclar = araclar;
        this.toplamSaat = 0;
    }

    public void baslat() {
        boolean tumAraclarBittiMi = false;

        while (!tumAraclarBittiMi) {
            toplamSaat++;

            ekranTemizle();

            // Gezegen zamanı ilerlet
            for (Gezegen gezegen : gezegenler.values()) {
                gezegen.saatIlerle();
            }

            // Uzay araçlarını ilerlet
            for (UzayAraci arac : araclar) {
                Gezegen cikisGezegen = gezegenler.get(arac.getCikis());
                Gezegen varisGezegen = gezegenler.get(arac.getVaris());

                arac.kontrolEtVeBaslat(cikisGezegen.getZaman());
                arac.saatIlerle(varisGezegen.getGunSaat());
            }

            // Ekrana yaz
            gezegenDurumuYazdir();
            uzayAraciDurumuYazdir();

            tumAraclarBittiMi = araclar.stream().allMatch(UzayAraci::yolculukBittiMi);
        }

        // Simülasyon bitince hayatta kalanlar varış gezegenine eklenir
        for (UzayAraci arac : araclar) {
            if (arac.getDurum().equals("Vardı")) {
                Gezegen varisGezegen = gezegenler.get(arac.getVaris());
                for (Kisi kisi : arac.getYolcular()) {
                    if (kisi.isHayatta()) {
                        varisGezegen.nufusArttir(1);
                    }
                }
            }
        }

        ekranTemizle();
        gezegenDurumuYazdir();
        uzayAraciDurumuYazdir();

        System.out.println("\n--- SİMÜLASYON TAMAMLANDI ---");
        System.out.println("TOPLAM GEÇEN SAAT: " + toplamSaat);
    }

    private void gezegenDurumuYazdir() {
        System.out.println("Gezegenler:\n");

        // Başta boşluk ver
        System.out.print("\t");
        for (Gezegen g : gezegenler.values()) {
            System.out.printf("--- %s ---\t", g.getAd());
        }
        System.out.println();

        System.out.print("Tarih\t");
        for (Gezegen g : gezegenler.values()) {
            System.out.printf("%s\t", g.getZaman().tarihYazdir());
        }
        System.out.println();

        System.out.print("Nüfus\t");
        for (Gezegen g : gezegenler.values()) {
            System.out.printf("%d\t\t", g.getNufus());
        }
        System.out.println();
    }



    private void uzayAraciDurumuYazdir() {
        System.out.println("Uzay Araçları:\n");
        System.out.println("Araç Adı\tDurum\tÇıkış\tVarış\tHedefe Kalan\tVarış Tarihi");
        for (UzayAraci a : araclar) {
            String aracAdi = a.getAd();
            String durum = a.getDurum();
            String cikis = a.getCikis();
            String varis = a.getVaris();

            String kalanSaat = switch (durum) {
                case "Vardı" -> "0";
                case "IMHA" -> "--";
                default -> String.valueOf(a.getKalanSaat());
            };

            String varisTarihi;
            if (durum.equals("IMHA")) {
                varisTarihi = "--";
            } else if (durum.equals("Vardı")) {
                // Burada doğru çağrı yapılıyor: çıkış ve varış gezegeni veriliyor
                varisTarihi = a.hedefeVarisTarihi(
                    gezegenler.get(cikis),
                    gezegenler.get(varis)
                );
            } else {
                varisTarihi = "-";
            }

            System.out.printf("%s\t%s\t%s\t%s\t%s\t\t%s\n",
                    aracAdi, durum, cikis, varis, kalanSaat, varisTarihi);
        }
        System.out.println();
    }

    private void ekranTemizle() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
