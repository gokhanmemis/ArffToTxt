import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArffToTxt {

	public static void main(String[] args) {
		
		File folder = new File(args[0]);
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile() && file.getName().contains("arff")) {
		        System.out.println(file.getName());
		        
		        try {
					BufferedReader reader = new BufferedReader(new FileReader(file));
					BufferedWriter writer = new BufferedWriter(
							new FileWriter(file.getAbsolutePath().replaceAll("arff", "txt")));

					String lineToRemove = "@data";
					String currentLine;
					
					boolean startWrite = false;
					
					while((currentLine = reader.readLine()) != null) {
					    // trim newline when comparing with lineToRemove
					    String trimmedLine = currentLine.trim();
					    if(trimmedLine.equals(lineToRemove) || trimmedLine.equals("@DATA")){
					    	startWrite = true;
					    	continue;
					    }
					    if(!startWrite){
					    	continue;
					    }
					    if(currentLine.contains(",")){
					    	writer.write(currentLine + System.getProperty("line.separator"));
					    }
					    else{
					    	writer.write(currentLine.replaceAll(" ", ",") + System.getProperty("line.separator"));
					    }
					}
					writer.close(); 
					reader.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		}
	}
	
}
