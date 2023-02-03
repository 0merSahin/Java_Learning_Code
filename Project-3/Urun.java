
public class Urun {
	
	private String Isim;
	private Double KategoriIndex;
	private String Agirlik;
	private Double Fiyat;
	private Double Miktar;
	
	
	public Urun(String isim, Double kategoriIndex, String agirlik, Double fiyat, Double miktar) {
		this.Isim = isim;
		this.KategoriIndex = kategoriIndex;
		this.Agirlik = agirlik;
		this.Fiyat = fiyat;
		this.Miktar = miktar;
	}
	
	public Urun() {
		this.Isim = null;
		this.KategoriIndex = null;
		this.Agirlik = null;
		this.Fiyat = null;
		this.Miktar = null;
	}
	
	
	@Override
    public String toString() {
		return this.Isim + "\t" + this.KategoriIndex + "\t" + this.Agirlik + "\t" + this.Fiyat + "\t" + this.Miktar;
	}
	
	
	public String getIsim() {
		return Isim;
	}
	public void setIsim(String isim) {
		Isim = isim;
	}
	
	public Double getKategoriIndex() {
		return KategoriIndex;
	}
	public void setKategoriIndex(Double kategoriIndex) {
		KategoriIndex = kategoriIndex;
	}
	
	public String getAgirlik() {
		return Agirlik;
	}
	public void setAgirlik(String agirlik) {
		Agirlik = agirlik;
	}
	
	public Double getFiyat() {
		return Fiyat;
	}
	public void setFiyat(Double fiyat) {
		Fiyat = fiyat;
	}
	
	public Double getMiktar() {
		return Miktar;
	}
	public void setMiktar(Double miktar) {
		Miktar = miktar;
	}
	
	
}
