package br.com.residencia.ecommerce.dto.imgBB;

public class Image {
	private String filename;

	@Override
	public String toString() {
		return "Image [filename=" + filename + "]";
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
}
