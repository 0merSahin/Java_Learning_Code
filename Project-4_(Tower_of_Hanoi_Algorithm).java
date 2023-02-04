package project;

import java.util.Scanner;

public class project {
	
	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		System.out.print("Disk adedini giriniz: ");
		int n = input.nextInt();
		
		System.out.println(n + " halkadan oluşan Hanoi probleminin çözüm adımları:\n");
		Hanoi_Kulesi(n, 'A', 'C', 'B');
		System.out.println("\nTüm aşamalar tamamlanmıştır.");
    }
	
	
    static void Hanoi_Kulesi(int n, char ana_kule, char hedef_kule, char yardimci_kule) {
        if (n == 0) {
            return;
        }
        
        Hanoi_Kulesi(n - 1, ana_kule, yardimci_kule, hedef_kule);
        System.out.println(n + " numaralı disk taşındı: " + ana_kule + " --> " + hedef_kule);
        Hanoi_Kulesi(n - 1, yardimci_kule, hedef_kule, ana_kule);
    }
    
}
