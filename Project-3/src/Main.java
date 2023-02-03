import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main {

	static boolean liste_olustu = false;
	
	public static void main(String[] args) {
		
		ArrayList<Urun> urunList = new ArrayList<>();
		
		Scanner input = new Scanner(System.in);
		
		String doysa_adi = "UrunListesi.xlsx";
		
		while (true) {
			System.out.println("\n\n[1] Liste Oluştur / Liste yazdır\n[2] Yeni Kategori Liste Olustur\n[3] Birim Fiyat Güncelle\n[4] Stok Güncelle\n[5] Ürün sil\n[6] Çıkış");
			System.out.print("İşlem numarasi giriniz: ");
			int islem = input.nextInt();
			
			
			if (islem == 1) {
				ListeOlustur(doysa_adi, urunList);
				liste_olustu = true;
			}
			
			else if (islem == 2) {
				if (liste_olustu == false) System.out.println("\n--> Öncelikle bir liste oluşturun!");
				else KategoriGoster(urunList);
			}
			
			else if (islem == 3) {
				if (liste_olustu == false) System.out.println("\n--> Öncelikle bir liste oluşturun!");
				else {
					System.out.print("Kaçıncı indexteki ürünlere zam/indirim yapılsın: ");
					int index = input.nextInt();
					if (index < 1 || index > 9) {
						System.out.println("\n--> İndex 1 ile 9 arasında olmalıdır!");
						return;
					}
					System.out.print("% kaç zam/indirim yapılsın: ");
					int miktar = input.nextInt();
					if (miktar <= 0) {
						System.out.println("Sıfırdan küçük bir değer giremezsiniz");
						return;
					}
					BirimFiyatGuncelle(index, miktar, urunList); // 1. kategori indexinde bulunan en ucuz/pahalı ürüne %10 zam/indirim yapıyor.
				}
			}
			
			else if (islem == 4) {
				if (liste_olustu == true) {
					// Stok Güncelleme:
					System.out.print("Ürün adını giriniz: ");
					String urunAdi = input.next();
					System.out.print("Satış mı yapacaksınız [Evet/Hayır]: ");
					String secim = input.next();
					System.out.print("Miktarı giriniz: ");
					int miktar = input.nextInt();
					
					if (secim.contains("evet") || secim.contains("Evet")) StokGuncelle(urunAdi, miktar, true, urunList);
					else if (secim.contains("hayır") || secim.contains("Hayır")) StokGuncelle(urunAdi, miktar, false, urunList);
					else System.out.println("\n--> Hatalı giriş yaptınız!");
				}
				else System.out.println("\n--> Öncelikle bir liste oluşturunuz!");
			}
			
			else if (islem == 5) {
				if (liste_olustu == true) {
					System.out.print("Kaçıncı indexteki ürün(ler) silinsin: ");
					int index = input.nextInt();
					if (index < 1 || index > 9) {
						System.out.println("\n--> İndex 1 ile 9 arasında olmalıdır!");
						return;
					}
					UrunSil(index, urunList);
				}
				else System.out.println("\n--> Öncelikle bir liste oluşturunuz!");
			}
			
			else if (islem == 6) {
				System.out.println("\nProgram Kapatıldı...");
				break;
			}
			else System.out.println("\nHatali bir giriş yaptınız!");
		}
	}
	
	
	static void ListeOlustur(String dosya_adi, ArrayList<Urun> urunList) {
		System.out.println("\nListe oluşturuldu:\n");
				
		if (liste_olustu == true) {
			while (true) {
				if (urunList.size() < 51) {
					urunList.add(new Urun());
				}
				else if (urunList.size() == 51) break;
			}
		}
				
		ArrayList<Urun> yeni_liste = new ArrayList<>();
		
		File file = new File(dosya_adi);
		try {

			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);
			Iterator<Row> iter = sheet.iterator();
			int sayac = 0;
			while (iter.hasNext()) {
				
				String isim = null;
				Double kategori_index = null; // !
				String birim_agirligi = null;
				Double fiyat = null; // !
				Double miktar = null; // !
				
				
				Row row = iter.next(); // her bir satırı alıyor.
				Iterator<Cell> cellIterator = row.cellIterator();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next(); // Alınan satırdaki her bir hücreyi alıyor.
					sayac++;
					if (sayac > 5) {
						
						if (sayac % 5 == 1)	isim = cell.toString();
						
						else if (sayac % 5 == 2) {
							String str = cell.toString();
							kategori_index = Double.parseDouble(str);
						}
						
						else if (sayac % 5 == 3) birim_agirligi = cell.toString();
						
						else if (sayac % 5 == 4) {
							String str = cell.toString();
							fiyat = Double.parseDouble(str);
						}
						
						else if (sayac % 5 == 0) {
							String str = cell.toString();
							miktar = Double.parseDouble(str);
						}
					}
				}
				
				if (liste_olustu == true) yeni_liste.add(new Urun(isim, kategori_index, birim_agirligi, fiyat, miktar));
				else urunList.add(new Urun(isim, kategori_index, birim_agirligi, fiyat, miktar));
			}
			
			if (liste_olustu == true) {
				for (int i = 1; i < yeni_liste.size(); i++) {
					urunList.get(i).setIsim(yeni_liste.get(i).getIsim());
					urunList.get(i).setKategoriIndex(yeni_liste.get(i).getKategoriIndex());
					urunList.get(i).setAgirlik(yeni_liste.get(i).getAgirlik());
					urunList.get(i).setFiyat(yeni_liste.get(i).getFiyat());
					urunList.get(i).setMiktar(yeni_liste.get(i).getMiktar());
				}
			}
			
			for (int i = 1; i < urunList.size(); i++) {
				System.out.println(urunList.get(i));
			}
			
			
		}
		catch (IOException e) {
			System.err.println("Bir hata oluştu!");
			e.printStackTrace();
		}
	}
	
	
	
	static void KategoriGoster(ArrayList<Urun> urunList) {
		
		ArrayList<Kategori> kategori_list = new ArrayList<>();
		System.out.println("\n\nHangi kategori index'inde kaç tane ürün olduğu gösteriliyor:");
		
		int a = 1;
		int[] dizi = new int[10];
		
		for (int j = 1; j < 10; j++, a++) {
			dizi[a] = 0;
			for (int i = 1; i < urunList.size(); i++) {
				if (j == urunList.get(i).getKategoriIndex()) {
					dizi[a]++;
				}
			}
			kategori_list.add(new Kategori(a, dizi[a]));
		}
		
		System.out.println("\nIndex   Adet");
		for (int i = 0; i < kategori_list.size(); i++) {
			System.out.println("  " + kategori_list.get(i).getIndex() + "      " + kategori_list.get(i).getAdet());
		}
	}
	
	
	
	static void BirimFiyatGuncelle(int index, int x, ArrayList<Urun> urunList) {
		// index 1 azalt !
		System.out.println("\n\nZam ve İndirim uygulandı:");
		
		Double enkucuk_fiyat = Double.MAX_VALUE;
		int enkucuk_index = 0;
		for (int i = 1; i < urunList.size(); i++) {
			if (urunList.get(i).getKategoriIndex() == index) {
				if (urunList.get(i).getFiyat() < enkucuk_fiyat) {
					enkucuk_fiyat = urunList.get(i).getFiyat();
					enkucuk_index = i;
				}
			}
		}
		String isim1 = urunList.get(enkucuk_index).getIsim();
		Double fiyat1 = urunList.get(enkucuk_index).getFiyat();
		Double zam = (fiyat1 * x) / 100;
		Double fiyat12 = fiyat1 + zam;
		urunList.get(enkucuk_index).setFiyat(fiyat12);
		
		
		Double enbuyuk_fiyat = Double.MIN_VALUE;
		int enbuyuk_index = 0;
		for (int i = 1; i < urunList.size(); i++) {
			if (urunList.get(i).getKategoriIndex() == index) {
				if (urunList.get(i).getFiyat() > enbuyuk_fiyat) {
					enbuyuk_fiyat = urunList.get(i).getFiyat();
					enbuyuk_index = i;
				}
			}
		}
		
		String isim2 = urunList.get(enbuyuk_index).getIsim();
		Double fiyat2 = urunList.get(enbuyuk_index).getFiyat();
		Double indirim = (fiyat2 * x) / 100;
		Double fiyat21 = fiyat2 - indirim;
		urunList.get(enbuyuk_index).setFiyat(fiyat2);
		
		
		System.out.println("\n" + index + ". Kategori indexinde bulunan '" + isim1 + "' isimli ürüne %" + x + " zam yapıldı: " + fiyat1 + " --> " + fiyat12);
		System.out.println(index + ". Kategori indexinde bulunan '" + isim2 + "' isimli ürüne %" + x + " indirim yapıldı: " + fiyat2 + " --> " + fiyat21);
	}
	
	
	
	static void StokGuncelle(String urunAdi, int adet, boolean SatisMi, ArrayList<Urun> urunList) {
		System.out.println("\nStok Güncellendi: ");
		
		boolean kontrol = false;
		for (int i = 1; i < urunList.size(); i++) {
			String str = urunList.get(i).getIsim();
			
			if (str.contains(urunAdi)) {
				kontrol = true;
				if (SatisMi == true) { // Satiş yapılacak:
					Double onceki_miktar = urunList.get(i).getMiktar();
					Double yeni_miktar = urunList.get(i).getMiktar();
					if (adet > yeni_miktar) {
						System.out.println("\n--> Girdiğiniz miktarda stok bulunmamakta!");
						return;
					}
					yeni_miktar -= adet;
					urunList.get(i).setMiktar(yeni_miktar);
					System.out.println(urunList.get(i).getIsim() + " isimli ürünün stoğu güncellendi: " + onceki_miktar + " --> " + yeni_miktar);
				}
				
				else { // Alış yapılacak:
					Double onceki_miktar = urunList.get(i).getMiktar();
					Double yeni_miktar = urunList.get(i).getMiktar();
					yeni_miktar += adet;
					urunList.get(i).setMiktar(yeni_miktar);
					System.out.println(urunList.get(i).getIsim() + " isimli ürünün stoğu güncellendi: " + onceki_miktar + " --> " + yeni_miktar);
				}
			}
		}
		if (kontrol == false) {
			System.out.println("\n--> Girdiğiniz bilgilerde hata var!\n--> İşlem yapılamadı");
			return;
		}
	}
	
	
	
	static void UrunSil(int kategori_index, ArrayList<Urun> urunList) {
		System.out.println("\n--> İndisi " + kategori_index + " olup, miktarı en az olan ürünler silindi: ");
		int miktar = 0;
		boolean kontrol = false;
		
		while (true) {
			for (int i = 1; i < urunList.size(); i++) {
				if (urunList.get(i).getKategoriIndex() == kategori_index) {
					if (urunList.get(i).getMiktar() == miktar) {
						System.out.println("Silinen ürün:  "+ urunList.get(i));
						urunList.remove(i);
						i--;
						kontrol = true;
					}
				}
			}
			if (kontrol == true) break;
			else if (miktar > 999) break;
			else miktar++;
		}
	}
}
