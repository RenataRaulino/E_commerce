package br.com.residencia.ecommerce.dto.imgBB;



public class ImgBBDTO {
	private Data data;

	@Override
	public String toString() {
		return "ImgBBDTO [data=" + data + "]";
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
	
}
