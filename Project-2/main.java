import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JSpinner.ListEditor;

import com.sun.tools.javac.Main;

public class project_2 {
	
	public static boolean liste_dolu = true;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		ArrayList<Integer> list = new ArrayList<Integer>();
		
		System.out.println("\n[1] Sayı Listesi Oluştur\n[2] Frekans Hesapla\n[3] Frekansa Göre Sil\n[4] Yeni Eleman Ekle\n[5] Çıkış");
		
		
		boolean liste_olustu = false, frekans_hesaplandi = false;
		while (true) {
			int islem;
			if (liste_dolu == false) islem = 4;
			else {
				System.out.print("\nYapmak istediğiniz işlemi giriniz: ");
				islem = input.nextInt();
			}
			
			if (islem == 1) {
				if (liste_olustu == false) SayiListesiOlustur(list);
				else {
					System.out.print("\nZaten sayi listesi oluşturuldu!");
					continue;
				}
				liste_olustu = true;
			}
			
			else if (islem == 2) {
				if (liste_olustu == true) FrekansHesapla(list);
				else {
					System.out.print("\nÖncelikle bir liste oluşturunuz!");
					continue;
				}
				frekans_hesaplandi = true;
			}
			
			else if (islem == 3) {
				if (liste_olustu == false) System.out.print("\nÖncelikle bir liste oluşturunuz!");
				else if (frekans_hesaplandi == true) {
					FrekansaGoreSil(list);
					frekans_hesaplandi = false;
				}
				else System.out.print("\nÖncelikle Frekansı hesaplayınız!");
			}
			
			else if (islem == 4) {
				if (liste_olustu == true) {
					YeniElemanEkle(list);
					frekans_hesaplandi = false;
					//liste_dolu = true;
				}
				else System.out.print("\nÖncelikle bir liste oluşturunuz!");
			}
			
			else if (islem == 5) {
				System.out.print("\n\nProgram Kapatıldı...");
				break;
			}
			
			else System.out.print("Hatali giriş yaptınız! Lütfen tekrar deneyin.");
		}
	}
	
	
	public static void SayiListesiOlustur(ArrayList<Integer> list) {
		for (int i = 0; i < 30; i++) {
			int sayi = (int) (Math.random() * 10);
			if (sayi == 0) i--;
			else list.add(sayi);
		}
		System.out.println();
		for (Integer integer : list) {
			System.out.print(integer + " ");
		}
		System.out.println("\nListedeki eleman sayısı: "+ list.size());
	}
	
	
	public static void FrekansHesapla(ArrayList<Integer> list) {		
		ArrayList<Integer> miktar_listesi = new ArrayList<Integer>();
		
		for (int i = 1; i < 10; i++) {			
			int sayac = 0;
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j) == i) {
					sayac++;
				}
			}
			miktar_listesi.add(sayac);
		}
		
		System.out.println("\n\nListedeki Sayıların Frekansları:");
		
		for (int i = 0; i < 9; i++) {
			int enbuyuk = 0;
			Boolean kontrol = false;
			int enbuyuk_indis = 0;
			
			for (int j = 0; j < 9; j++) {
				if (miktar_listesi.get(j) > enbuyuk) {
					enbuyuk = miktar_listesi.get(j);
					enbuyuk_indis = j;
					kontrol = true;
				}
			}
			
			if (kontrol) {
				//System.out.println((enbuyuk_indis+1) + " sayısı "+  enbuyuk + " kez tekrar ediyor.");
				System.out.println((enbuyuk_indis+1) + " sayısının frekansı: " + enbuyuk);
				miktar_listesi.remove(enbuyuk_indis);
				miktar_listesi.add(enbuyuk_indis, -1);
			}
		}
		
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == 0) {
				list.remove(i);
			}
		}
	}
	
	
	public static void FrekansaGoreSil(ArrayList<Integer> list) {
		Scanner input = new Scanner(System.in);
		System.out.print("\nKaç kez tekrar eden veri silinsin: ");
		int tekrar = input.nextInt();
		
		ArrayList<Integer> miktarlistesi = new ArrayList<Integer>();
		
		for (int i = 1; i < 10; i++) {			
			int sayac = 0;
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j) == i) { 
					sayac++;
				}
			}
			miktarlistesi.add(sayac);
		}
		
		ArrayList<Integer> silinecek = new ArrayList<Integer>();
		
		int a = 0;
		for (int i = 0; i < 9; i++) {
			if (tekrar == miktarlistesi.get(i)) {
				silinecek.add(i+1);
				a++;
			}
		}

		if (a == 0) {
			System.out.println("\nGirdiğiniz frekansta sayı bulunmamaktadır!");
			return;
		}
		
		for (int i = 0; i < a; i++) {
			for (int j = 0; j < list.size(); j++) {
				
				if (list.get(j) == silinecek.get(i)) {
					list.remove(j);
					j--;
				}
			}
		}
		
		
		System.out.print("\n" + tekrar + " kez tekrar eden sayıların hepsi silindi.\nSilinen sayılar: ");
		for (int i = 0; i < a; i++) System.out.print(silinecek.get(i) + " ");
		
		System.out.println("\n\nSayı listesinin yeni hali:");
		int sayac = 0;
		for (Integer integer : list) {
			System.out.print(integer +" ");
			sayac++;
		}
		if (sayac == 0) {
			System.out.print("\n\nListede Hiç Eleman Kalmadı! \nYalnızca listeye ekleme yapabilirsiniz.");
			liste_dolu = false;
		}
		else System.out.println("\nListedeki eleman sayısı: "+ list.size());
	}
	

	public static void YeniElemanEkle(ArrayList<Integer> list) {
		Scanner input = new Scanner(System.in);
		System.out.print("\n\nListeye yeni elemandan kaç tane eklensin: ");
		int miktar = input.nextInt();
		
		if (miktar <= 0) {
			System.out.print("\n\n! Hiç eleman eklenmedi !");
			return;
		}
		
		boolean kontrol2 = false;
		
		for (int i = 1; i < 10; i++) {
			boolean kontrol = true;
			
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j) == i) {
					kontrol = false;
					break;
				}
			}
			if (kontrol == true) { // Eğer i sayısı listede yoksa:
				System.out.println("\nEklenen sayı: "+ i);
				for (int k = 0; k < miktar; k++) list.add(i);
				kontrol2 = true;
				break;
			}
		}
		if (kontrol2 == false) {
			System.out.println("\n! Bu listede bütün sayılar var. \nHerhangi bir ekleme yapılamadı!");
		}
		else {
			System.out.println("\nSayı listesinin yeni hali:");
			for (Integer integer : list) {
				System.out.print(integer +" ");
			}
			System.out.println("\nListedeki eleman sayısı: "+ list.size());
			liste_dolu = true;
		}
	}
	
}
