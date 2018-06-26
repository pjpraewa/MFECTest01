package model;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Consumer {
	
	private String useDate;
	private String phoneNo;
	private String promotion;
	private String startTime;
	private String endTime;
	
	private int useTime;
	private double expense;
	
	public Consumer(String useDate, String s, String e, String no, String p) {
		this.useDate = useDate;
		this.startTime = s;
		this.endTime= e;
		this.phoneNo = no;
		this.promotion = p;
	}
	
	public int caluseTime(String d,String s, String en) {
		SimpleDateFormat dateDisplayFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
		try {
			Date start = dateDisplayFormat.parse(d+" "+s);
			Date end = dateDisplayFormat.parse(d+" "+en);
			double milliTime = end.getTime() - start.getTime();
			NumberFormat nf = NumberFormat.getNumberInstance();
	        nf.setMaximumFractionDigits(0);
	        nf.setRoundingMode(RoundingMode.HALF_UP);
			int useMinute = Integer.parseInt(nf.format(milliTime/(1000*60)));
			return useMinute;
		

//			System.out.println(date.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0; 
	}

	public double getExpense() {
		if (promotion.equals("P1")||promotion.equals("p1")) {
			expense=(useTime-1)+3;
		}
		return expense;
	}

	public void setExpense(double expense) {
		this.expense = expense;
	}

	public int getUseTime() {
		return useTime;
	}

	public void setUseTime(int useTime) {
		this.useTime = useTime;
	}

	public String getUseDate() {
		return useDate;
	}

	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	


}
