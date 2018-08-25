package com.revature.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class BankFileWriter {
 	public BankFileWriter() {
		super();
	}
 	public void writeFile(ArrayList<String> t, String path) {
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
 		try (FileWriter fw = new FileWriter(file, false);
 			BufferedWriter bw = new BufferedWriter(fw)){		
			for (String str: t) {
				bw.write(str + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
