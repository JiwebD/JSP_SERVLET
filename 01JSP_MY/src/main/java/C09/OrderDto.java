package C09;

import java.time.LocalDate;

public class OrderDto {

	private String CATEGORY;
	private Integer SUM;
	private Double AVG;
	private LocalDate ORDER_DATE;
	
	
	//생성자
	public OrderDto() {}

	
	
	public OrderDto(String cATEGORY, Integer sUM, LocalDate oRDER_DATE, Double aVG) {
		super();
		CATEGORY = cATEGORY;
		SUM = sUM;
		ORDER_DATE = oRDER_DATE;
		AVG = aVG;
	}



	//getter,setter
	public String getCATEGORY() {
		return CATEGORY;
	}
	public void setCATEGORY(String cATEGORY) {
		CATEGORY = cATEGORY;
	}
	public Integer getSUM() {
		return SUM;
	}
	public void setSUM(Integer sUM) {
		SUM = sUM;
	}
	public LocalDate getORDER_DATE() {
		return ORDER_DATE;
	}
	public void setORDER_DATE(LocalDate oRDER_DATE) {
		ORDER_DATE = oRDER_DATE;
	}
	public Double getAVG() {
		return AVG;
	}
	
	
	
	public void setAVG(Double aVG) {
		AVG = aVG;
	}
	
	
	//toString
	@Override
	public String toString() {
		return "OrderDto [CATEGORY=" + CATEGORY + ", SUM=" + SUM + "]";
	}




	



	
}
