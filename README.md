# PDP - Uzay Simulasyonu

Bu proje, **Programlama Dilleri Prensipleri** dersi kapsaminda gelistirilmistir.  
Amac; farkli gezegenler, uzay araclari ve yolcular arasinda gecerli bir uzay yolculugu simulasyonunu gerceklestirmektir.

---

##  Klasor Yapisi

UzaySimulasyonu/ ├── src/ → Java kaynak kodlari ├── dist/ → Calistirilabilir .jar dosyasi ├── Araclar.txt ├── Gezegenler.txt ├── Kisiler.txt ├── Rapor.pdf → Odev raporu


## Calistirma

```bash
cd UzaySimulasyonu
java -jar dist/simulasyon.jar

Gerekli veriler ayni klasorde bulunan .txt dosyalarindan alinmaktadir.
Simulasyon her saat ilerleyerek ekrani temizler ve guncel durumu yazdirir.

---

## Girdi Dosyalarinin Formati

Araclar.txt

UzayAraciAdi#CikisGezegeni#VarisGezegeni#CikisTarihi#MesafeSaat

Örnek:
A1#X#Y#15.06.2023#365

Gezegenler.txt

GezegenAdi#BaslangicTarihi#GunSaat

Örnek:
X#01.05.2023#14

Kisiler.txt

Isim#Yas#KalanOmur#BulunduguUzayAraci

Örnek: 
Ahmet#25#8500#A1

Kodun Genel Isleyisi
Program basladiginda gezegenler kendi baslangic tarihleri ve saat yapilariyla baslatilir.

Simulasyon her saat ilerlediginde:

Gezegenlerin tarihi artar

Uzay araclari cikis tarihine geldiklerinde yola cikar

Bekleme + yolculuk suresi toplanir

Yolcularin omru her saatte azalir

Eger bir arac icindeki tum kisiler olumlu ise, arac IMHA edilir.

Arac hedef gezegene vardiginda:

Hayatta kalanlar varis gezegenine nufus olarak eklenir

Varis tarihi hedef gezegenin zaman dilimine gore yazdirilir

Tum araclar varis yaptiginda simulasyon sona erer ve son durum ekrana yazilir.

Gelistirici
Bilal Kaan Yuksel
kaan12358@gmail.com











