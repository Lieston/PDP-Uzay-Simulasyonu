package simulasyon;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Senin Adın - Senin Mailin
 * @since 26.04.2025
 * <p>
 * Main sınıfı, dosyaları okuyarak simülasyonu başlatır.
 * </p>
 */

public class Main {

    public static void main(String[] args) {
        // Dosya yolları (Eclipse'te proje köküne atılan dosyalar)
        String gezegenlerDosyasi = "Gezegenler.txt";
        String araclarDosyasi = "Araclar.txt";
        String kisilerDosyasi = "Kisiler.txt";

        // Dosyalardan verileri oku
        Map<String, Gezegen> gezegenler = DosyaOkuma.gezegenleriOku(gezegenlerDosyasi);
        List<UzayAraci> araclar = DosyaOkuma.araclariOku(araclarDosyasi);
        DosyaOkuma.kisileriOku(kisilerDosyasi, araclar);

        // Simülasyonu başlat
        Simulasyon simulasyon = new Simulasyon(gezegenler, araclar);
        simulasyon.baslat();
    }
}
