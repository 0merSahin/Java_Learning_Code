
public class Kategori {

	private int Index;
	private int Adet;
	
	
	public Kategori(int index, int adet) {
		this.Index = index;
		this.Adet = adet;
	}
	
	
	public String toString() {
		return this.Index + " " + this.Adet;
	}
	
	public int getIndex() {
		return Index;
	}
	public void setIndex(int index) {
		Index = index;
	}
	public int getAdet() {
		return Adet;
	}
	public void setAdet(int adet) {
		Adet = adet;
	}
	
	
	
}
