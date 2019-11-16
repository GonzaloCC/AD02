package AD02;

import java.util.ArrayList;
import java.util.Scanner;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
	static File arquivo = new File("data.json");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
        Compania compania=new Compania();

        try {

            //Creamos un fluxo de entrada para o arquivo
            FileReader fluxoDatos;
            fluxoDatos = new FileReader(arquivo);

            //Creamos o bufer de entrada
            BufferedReader buferEntrada = new BufferedReader(fluxoDatos);

            //Imos lendo linea a linea
            StringBuilder jsonBuilder = new StringBuilder();
            String linea;
            while ((linea=buferEntrada.readLine()) != null) {
                jsonBuilder.append(linea).append("\n");
            }

            //Temos que cerrar sempre o ficheiro
            buferEntrada.close();

            //Construimos o String con todalas lineas lidas
            String json = jsonBuilder.toString();

            //Pasamos o json a clase ca cal se corresponde
            Gson gson = new Gson();
            compania = gson.fromJson(json, Compania.class);
            
			

        } catch (FileNotFoundException e) {
            System.out.println("Non se encontra o arquivo");
        } catch (IOException e) {
            System.out.println("Erro de entrada saida");
        }
        
 
		Scanner entrada= new Scanner(System.in);
		int eleccion;
		
		
		
		do {
		System.out.println("1-Engadir unha tenda.\r\n" + 
				"2-Eliminar unha tenda (eliminanse tódolos productos e empregados desta).\r\n" + 
				"3-Engadir un producto a tenda.\r\n" + 
				"4-Eliminiar un producto a tenda.\r\n" + 
				"5-Engadir un empregado a tenda.\r\n" + 
				"6-Eliminar un emprega a tenda.\r\n" + 
				"7-Engadir un cliente.\r\n" + 
				"8-Eliminar un cliente.\r\n" + 
				"9-Crear unha copia de seguriadade dos datos\r\n" + 
				"10-Ler os titulares do periodico El País\r\n" + 
				"11-Sair do programa.");
		
		eleccion= entrada.nextInt();
		String salto= entrada.nextLine();
		switch(eleccion) {
		case 1:
			String nome;
			String cidade;
			System.out.println("Nome da tenda");
			nome= entrada.nextLine();
			System.out.println("Cidade da tenda");
			cidade= entrada.nextLine();
			Tenda tenda= new Tenda(nome, cidade);
			compania.tendas.add(tenda);	
			serializar(compania);
			break;
		case 2:
			if(compania.tendas.size()>0) {
				System.out.println("Que tenda desexa eliminar?");
				for(int i=0;i<compania.tendas.size();i++) {
					System.out.println(i+": "+compania.tendas.get(i).getNome());
				}
				int eliminar= entrada.nextInt();
				String salto2=entrada.nextLine();
				if(eliminar<compania.tendas.size()) {
					compania.tendas.remove(eliminar);
				}else {
					System.out.println("Numero incorrecto");
				}
				serializar(compania);
			}else {
				System.out.println("Non hai creada ningunha tenda");
			}			
			break;
		case 3:
			String identificador;
			String descripcion;
			double prezo;
			int cantidade;
			System.out.println("Identificador do producto");
			identificador=entrada.nextLine();
			System.out.println("Descripcion do producto");
			descripcion=entrada.nextLine();
			System.out.println("Prezo do producto");
			prezo=entrada.nextDouble();
			System.out.println("Cantidade do producto");
			cantidade=entrada.nextInt();
			Producto producto=new Producto(identificador,descripcion,prezo,cantidade);
			if(compania.tendas.size()>0) {
			System.out.println("A que tenda quere engadir o producto");
				for (int i=0; i<compania.tendas.size();i++) {			
					System.out.println(i+"-"+compania.getTendas().get(i).getNome());
				}
				int numTenda=entrada.nextInt();
				String salto2=entrada.nextLine();
				if(numTenda<compania.tendas.size()) {
					compania.getTendas().get(numTenda).productos.add(producto);
				}else {
					System.out.println("Numero incorrecto");
				}
				serializar(compania);
			}else {
				System.out.println("Non hai ningunha tenda creada a que agregar o producto");
			}

			break;
		case 4:
			if(compania.tendas.size()>0) {
				System.out.println("Elixe unha tenda:");
				for(int i=0;i<compania.tendas.size();i++) {
					System.out.println(i+": "+compania.tendas.get(i).getNome());				
				}
				int tendaElexida= entrada.nextInt();
				String salto3=entrada.nextLine();
				System.out.println("Elixe un producto:");
				if(compania.tendas.get(tendaElexida).productos.size()>0) {
					for(int i=0;i<compania.tendas.size();i++) {
						System.out.println(i+": "+compania.tendas.get(i).productos.get(i).getDescripcion()); 
					}
					int eliminar= entrada.nextInt();
					String salto4=entrada.nextLine();
					if(eliminar<=compania.tendas.get(tendaElexida).productos.size()) {
						compania.tendas.get(tendaElexida).productos.remove(eliminar);
					}else {
						System.out.println("Numero incorrecto");
					}
					serializar(compania);
				}else {
					System.out.println("Non hai ningun producto");
				}
			}else {
				System.out.println("Non hai creada ningunha tenda");
			}
			break;
		case 5:
			String nomeEmpregado;
			String apelidosEmpregado;
			System.out.println("Nome do empregado");
			nomeEmpregado=entrada.nextLine();
			System.out.println("Apelidos do empregado");
			apelidosEmpregado=entrada.nextLine();
			Empregado empregado=new Empregado(nomeEmpregado,apelidosEmpregado);
			if(compania.tendas.size()>0) {
				System.out.println("A que tenda quere engadir o empregado");
				for (int i=0; i<compania.tendas.size();i++) {			
					System.out.println(i+"-"+compania.getTendas().get(i).getNome());
				}
				int numTenda2=entrada.nextInt();
				String salto3=entrada.nextLine();
				if(numTenda2<compania.tendas.size()) {
					compania.getTendas().get(numTenda2).empregados.add(empregado);
				}else {
					System.out.println("numero incorrecto");
				}
				serializar(compania);
			}else {
				System.out.println("Non hai ningunha tenda creada a que agregar o empregado");
			}

			break;
		case 6:
			if(compania.tendas.size()>0) {
				System.out.println("Elixe unha tenda:");
				for(int i=0;i<compania.tendas.size();i++) {
					System.out.println(i+": "+compania.tendas.get(i).getNome());				
				}
				int tendaElexida= entrada.nextInt();
				String salto4=entrada.nextLine();
				if(compania.tendas.get(tendaElexida).empregados.size()>0) {
					for(int i=0;i<compania.tendas.size();i++) {
						System.out.println(i+": "+compania.tendas.get(i).empregados.get(i).getNome()); 
					}
					int eliminar= entrada.nextInt();
					if(eliminar<=compania.tendas.get(tendaElexida).empregados.size()) {
						compania.tendas.get(tendaElexida).empregados.remove(eliminar);
					}else {
						System.out.println("Numero incorrecto");
					}
					serializar(compania);
				}else {
					System.out.println("Non hai ningï¿½n producto");
				}
			}else {
				System.out.println("Non hai creada ningunha tenda");
			}
			break;

		case 7:
			String nomeCliente;
			String apelidosCliente;
			String email;
			System.out.println("Nome do cliente");
			nomeCliente=entrada.nextLine();
			System.out.println("Apelido do cliente");
			apelidosCliente=entrada.nextLine();
			System.out.println("E-mail do cliente");
			email=entrada.nextLine();
			Cliente cliente=new Cliente(nomeCliente,apelidosCliente,email);
			compania.clientes.add(cliente);
			serializar(compania);
			break;
		case 8:
			if(compania.clientes.size()>0) {
				System.out.println("Que cliente desexa eliminar?");
				for(int i=0;i<compania.clientes.size();i++) {
					System.out.println(i+": "+compania.clientes.get(i).getNome());
				}
				int eliminar= entrada.nextInt();
				String salto5=entrada.nextLine();
				if(eliminar<compania.clientes.size()) {
					compania.clientes.remove(eliminar);
				}
				serializar(compania);
			}else {
				System.out.println("Non hai clientes que eliminar");
			}
			break;
		case 9:
			System.out.println("Se procede a crear copia de seguridad");
			String nomeArquivo=arquivo.getName().substring(0,arquivo.getName().length()-5);
			nomeArquivo=nomeArquivo+"_backup.json";
	        File arquivoCopia = new File(nomeArquivo);

	        try{
	            //Creamos o fluxo de saida
	        	FileInputStream fluxoEntrada= new FileInputStream(arquivo);
	            FileOutputStream fluxoSaida = new FileOutputStream(arquivoCopia);
	            
	            
	            int datoByte;
	            while ((datoByte=fluxoEntrada.read())!= -1) {
	            	fluxoSaida.write(datoByte);
	            }


	            //Cerramos o arquivo
	            fluxoEntrada.close();
	            fluxoSaida.close();
	        }
	        catch(IOException e){
	            System.out.println("Non se pode escribir no arquivo");
	        }
	        if(arquivoCopia.exists()) {
	        	System.out.println("Copia realizada con exito");
	        }else {
	        	System.out.println("Ocorreu algun erro e non se realizou a copia");
	        }

			break;
		case 10:
			 XMLReader procesadorXML = null;
			 
			 try {

		            //Creamos un parseador de texto e engadimoslle a nosa clase que vai parsear o texto
		            procesadorXML = XMLReaderFactory.createXMLReader();
		            TitularesXML titularesXML = new TitularesXML();
		            procesadorXML.setContentHandler(titularesXML);

		            //Indicamos o texto donde estan gardadas as persoas
		            InputSource arquivo = new InputSource("http://ep00.epimg.net/rss/elpais/portada.xml");
		            procesadorXML.parse(arquivo);

		            //Imprimimos os datos lidos no XML
		            ArrayList<Titular> titulares = titularesXML.getTitulares();
		            
		            for(int i=0;i<titulares.size();i++){
		                Titular titularAux = titulares.get(i);
		                System.out.println("Titular "+(i+1)+": " + titularAux.getTexto() );
		            }

		        } catch (SAXException e) {
		            System.out.println("Ocurriu un erro ao ler o arquivo XML");
		        } catch (IOException e) {
		            System.out.println("Ocurriu un erro ao ler o arquivo XML");
		        }
			break;
		case 11:
			System.exit(0);
			break;
		default:
			break;
		}
		}while(eleccion!=11);
		
	}
	
	static void serializar(Compania compania) {
		 //Pasamos a nosa clase a JSON utilizando a libreria GSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(compania);

        //Vamos comezar declarando o ficheiro
        

        try {
            //Creamos o fluxo de saida
            FileWriter fluxoDatos = new FileWriter(arquivo);
            BufferedWriter buferSaida = new BufferedWriter(fluxoDatos);

            buferSaida.write(json);

            //Cerramos o arquivo
            buferSaida.close();
        } catch (IOException e) {

        }
	}
	
		

}
