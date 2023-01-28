
public class project1 {

	public static void main(String[] args) {
		for (int p = 1; p <= 10; p++) {
			if (SayiAsalMi(p) == true) {
				System.out.println("p = " + p + " sayisi ile asal oluştu");
				int muk_sayi = MukemmelSayiHesapla(p);
				System.out.println("Elde Edilen Mükemmel Sayi: " + muk_sayi);
				BolenleriGoster(muk_sayi);
			}
		}
	}

	static boolean SayiAsalMi(int P) {
		int sayi = UsHesapla(P)-1;
		if (sayi == 0 || sayi == 1) return false;
		for (int i = 2; i < sayi; i++) if (sayi % i == 0) return false;
		return true;
	}

	static int MukemmelSayiHesapla(int P) {
		int sayi1 = UsHesapla((P-1));
		int sayi2 = UsHesapla(P)-1;
		return (sayi1 * sayi2);
	}

	static void BolenleriGoster(int sayi) {
		int toplam = 0;
		System.out.print("Bölenleri: ");
		for (int i = 1; i < sayi; i++) {
			if (sayi % i == 0) {
				toplam += i;
				System.out.print(i + " ");
			}
		}
		System.out.println("\nBölenleri toplamı:\t" + toplam + "\nSayının Kendisi:\t" + sayi + "\n\n");
	}

	
	static int UsHesapla(int P) {
		// 2'nin, gönderilen P sayısı kadar üssünü alıyor.
		int sayi = 1;
		for (int i = 0; i < P; i++)	sayi *= 2;
		return sayi;
	}
}
