package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JTextArea;

import org.json.JSONException;

import model.Consumer;
import model.FileManagement;
import view.ResultView;

public class ControlSystem {
	
	private FileManagement fm ;
	private String filedata;
	private ArrayList<Consumer> lstConsumers;
	private ArrayList<String> result;

	
	public void StartApplication() {
		ResultView window = new ResultView(this);
		fm = new FileManagement();
		lstConsumers = new ArrayList<Consumer>();

	}
	
	public void Analysis() {
		CreateCustomer(filedata);
		System.out.println(lstConsumers.size());
		for(int i = 0 ; i<lstConsumers.size();i++) {
//			lstConsumers.get(i).caluseTime(lstConsumers.get(i).getUseDate(),lstConsumers.get(i).getStartTime(), lstConsumers.get(i).getEndTime());
			System.out.println(lstConsumers.get(i).getUseTime()+" "+lstConsumers.get(i).getExpense());
		}
	}
	
	public String getDataModel(String fd) throws FileNotFoundException, IOException {
		filedata = fm.readDataFile(fd);
		System.out.println(filedata);
		return filedata;	
	}
	
	public void CreateCustomer(String data) {
		StringTokenizer st1 = new StringTokenizer(data, "\n");
		while(st1.hasMoreTokens()) {
			StringTokenizer st2 = new StringTokenizer(st1.nextToken(), "|");
			String[] arrData = new String[5];
			int i = 0;
			while(st2.hasMoreTokens()) {
				arrData[i] = st2.nextToken();
				i = i+1;
			}
			// Create Consumer
			Consumer c = new Consumer(arrData[0],arrData[1],arrData[2],arrData[3],arrData[4]);
			c.setUseTime(c.caluseTime(arrData[0],arrData[1],arrData[2]));
			lstConsumers.add(c);
		}
	}

	public void getSaveFileName(String fn) throws IOException, JSONException {
		// TODO Auto-generated method stub
		fm.writeDataFile(fn, lstConsumers);
		
	}
	
	public ArrayList<String> getResult(){
		result = new ArrayList<String>();
		String header = "เบอร์โทรศัพท์      \t  วันที่      \t เวลาที่ใช้  (นาที)\t ค่าใช้บริการ  (บาท)\n";
		String l = "--------------------------------------------------------------------------------------------------------------------\n";
		result.add(l);
		result.add(header);
		result.add(l);
		
		for(int i = 0 ; i < lstConsumers.size(); i++) {
			String detail = lstConsumers.get(i).getPhoneNo()+" \t"+lstConsumers.get(i).getUseDate()+"  \t "+
						lstConsumers.get(i).getUseTime()+"   \t"+String.format("%.2f", lstConsumers.get(i).getExpense())+"\n";
			result.add(detail);
		}

		return result;
		
	}
	
	public void setResult(JTextArea textArea) {
		result =  getResult();
		String r = "";
		for(int i = 0; i<result.size();i++) {
			r += result.get(i);
		}
		textArea.setText(r);
	}



}
