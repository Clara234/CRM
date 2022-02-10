package com.crm.auxiliares;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CreaCarpetaInformes {

	public static void main(String[] args) throws IOException {

		String fileName = "C:\\Users\\dam\\documents\\Informes_hipotecario";

		Path path = Paths.get(fileName);

		if (!Files.exists(path)) {
			Files.createDirectory(path);
			System.out.println("New Directory created !   " + fileName);
		} else {

			System.out.println("Directory already exists");
		}
	}

}
