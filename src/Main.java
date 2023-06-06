import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Telefono telefono1= new Telefono("4588754","223","549");
		Telefono telefono2= new Telefono("4888754","223","549");
		
		List<Telefono> telefonos= new ArrayList<>();
		telefonos.add(telefono1);
		telefonos.add(telefono2);
		
		Domicilio domicilio1= new Domicilio("Avenida Siempre Viva", 123);
		
		Cliente cliente1= new Cliente("Pepe", "Luis", 45, domicilio1, telefonos);
	
		String path="C:\\Users\\Guille\\Documents\\Proyectos de Eclipse\\Serializacion y Json\\file\\miArchivo.txt";
		
	    File myFile= new File(path);
		  
		try {
			if (!myFile.exists()) {
				System.out.println(" El archivo no existe. Creando uno...");
				myFile.createNewFile();
				System.out.println("Archivo: " + myFile.getName() + " creado con exito!\n" );
			}
				
			    // Escribimos el archivo serializado
				FileOutputStream fileOutputStream= new FileOutputStream(myFile);
				
				ObjectOutputStream objOutputStream = new ObjectOutputStream(fileOutputStream);
				
				objOutputStream.writeObject(cliente1);
				
				objOutputStream.close();
				
				// Escribimos en el JACKSON
				File file = new File("file\\miArchivo.json");
				
				ObjectMapper mapper = new ObjectMapper();
				
				// Cargamos los datos del Objeto en el JSON
				mapper.writeValue(file, cliente1);
				
				// Lectura del archivo serializado
				FileInputStream fileInputStream =new FileInputStream(myFile);
				
				ObjectInputStream objInputStream = new ObjectInputStream(fileInputStream);
				
				// Se podria castear
				Object aux = objInputStream.readObject();
				
				System.out.println("Archivo con ObjectInputStream:\n"+aux.toString()+"\n");
				
				objInputStream.close();
				
				// Lectura con JACKSON
				file=new File("file\\miArchivo.json");
				
				Cliente cliente = mapper.readValue(file, Cliente.class);
				
				System.out.println("Archivo con JACKSON:\n" + cliente.toString());		
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
