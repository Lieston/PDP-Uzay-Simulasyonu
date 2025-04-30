/**
 * 
 * @author Bilal Kaan Yüksel - bilal.yuksel@ogr.sakarya.edu.tr
 * @since 26.04.2025
 * <p>
 * DosyaOkuma sınıfı, Gezegenler.txt, Araclar.txt ve Kisiler.txt dosyalarını okuyarak
 * gezegenler, uzay araçları ve yolcuların bilgilerinin simülasyona yüklenmesini sağlar.
 * </p>
 */package simulasyon;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

public class DosyaOkuma {

    public static Map<String, Gezegen> gezegenleriOku(String dosyaYolu) {
        Map<String, Gezegen> gezegenler = new LinkedHashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dosyaYolu))) {
            String satir;
            while ((satir = br.readLine()) != null) {
                String[] parca = satir.split("#");
                String ad = parca[0];
                int gunSaat = Integer.parseInt(parca[1]);
                String[] tarih = parca[2].split("\\.");
                Zaman zaman = new Zaman(Integer.parseInt(tarih[0]), Integer.parseInt(tarih[1]), Integer.parseInt(tarih[2]));
                Gezegen g = new Gezegen(ad, gunSaat, zaman);
                gezegenler.put(ad, g);
            }
        } catch (Exception e) {
            System.out.println("Gezegen okuma hatası: " + e.getMessage());
        }
        return gezegenler;
    }

    public static List<UzayAraci> araclariOku(String dosyaYolu) {
        List<UzayAraci> araclar = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dosyaYolu))) {
            String satir;
            while ((satir = br.readLine()) != null) {
                String[] parca = satir.split("#");
                String ad = parca[0];
                String cikis = parca[1];
                String varis = parca[2];
                String[] tarih = parca[3].split("\\.");
                Zaman cikisZamani = new Zaman(Integer.parseInt(tarih[0]), Integer.parseInt(tarih[1]), Integer.parseInt(tarih[2]));
                int mesafe = Integer.parseInt(parca[4]);
                araclar.add(new UzayAraci(ad, cikis, varis, cikisZamani, mesafe));
            }
        } catch (Exception e) {
            System.out.println("Araç okuma hatası: " + e.getMessage());
        }
        return araclar;
    }

    public static void kisileriOku(String dosyaYolu, List<UzayAraci> araclar) {
        try (BufferedReader br = new BufferedReader(new FileReader(dosyaYolu))) {
            String satir;
            while ((satir = br.readLine()) != null) {
                String[] parca = satir.split("#");
                String isim = parca[0];
                int yas = Integer.parseInt(parca[1]);
                int kalanOmur = Integer.parseInt(parca[2]);
                String aracAdi = parca[3];

                Kisi kisi = new Kisi(isim, yas, kalanOmur, aracAdi);
                for (UzayAraci arac : araclar) {
                    if (arac.getAd().equals(aracAdi)) {
                        arac.yolcuEkle(kisi);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Kişi okuma hatası: " + e.getMessage());
        }
    }
}
