package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class FileManagement {
	private String data;

	public String readDataFile(String fd) throws FileNotFoundException,IOException {
		//String data;
		try (BufferedReader reader = new BufferedReader(new FileReader(fd))) {
			// Access the data from the file
			// Create a new StringBuilder
			StringBuilder string = new StringBuilder();

			// Read line-by-line
			String line = reader.readLine();
			while (line != null) {
				string.append(line + "\n");
				line = reader.readLine();
			}
			data = string.toString() + "\n";
		} catch (Exception er) {
			// Since there was an error, you probably want to notify the user
			// For that error. So return the error.
			data = er.getMessage();
		}
//		   System.out.println(data);
		return data;
	}
	
	public void writeDataFile(String fd, ArrayList<Consumer> lst) throws IOException, JSONException {
		FileWriter fileWriter = new FileWriter(fd);
		for(int i = 0 ; i<lst.size();i++) {
			// Create a new JSONObject
			if (i == 0) {
				fileWriter.write("[");
			}
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("phoneno", lst.get(i).getPhoneNo());
            jsonObject.put("usedate",lst.get(i).getUseDate());
            jsonObject.put("usetime", lst.get(i).getUseTime());
            jsonObject.put("expense", String.format ("%.2f", lst.get(i).getExpense()));
            fileWriter.write(jsonObject.toString());
            if(i<lst.size()-1) {
            	fileWriter.write(",");
            }else {
            	fileWriter.write("]");
            }
           
            
		}
		fileWriter.flush();
		fileWriter.close();
	}

}
