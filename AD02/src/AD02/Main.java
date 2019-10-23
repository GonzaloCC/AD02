package AD02;

import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Compañia compañia= new Compañia();
		deserializar(compañia);
		Scanner entrada= new Scanner(System.in);
		int eleccion;
		
		
		
		
		System.out.println("1-Engadir unha tenda.\r\n" + 
				"2-Eliminar unha tenda (elimínanse tódolos productos e empragados desta).\r\n" + 
				"3-Engadir un producto a tenda.\r\n" + 
				"4-Eliminiar un producto a tenda.\r\n" + 
				"5-Engadir un empregado a tenda.\r\n" + 
				"6-Eliminar un emprega a tenda.\r\n" + 
				"7-Engadir un cliente.\r\n" + 
				"8-Eliminar un cliente.\r\n" + 
				"9-Crear unha copia de seguriadade dos datos\r\n" + 
				"10-Ler os titulares do periódico El País\r\n" + 
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
			compañia.tendas.add(tenda);	
			serializar(compañia);
			break;
		case 2:
			System.out.println("Nome da tenda");
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
			break;
		case 4:
			System.out.println("Identificador do producto");
			break;
		case 5:
			String nomeEmpregado;
			String apelidosEmpregado;
			System.out.println("Nome do empregado");
			nomeEmpregado=entrada.nextLine();
			System.out.println("Apelidos do empregado");
			apelidosEmpregado=entrada.nextLine();
			break;
		case 6:
			System.out.println("Nome do empregado");
			break;
		case 7:
			String nomeCliente;
			String apelidosCliente;
			String email;
			System.out.println("Nome do cliente");
			System.out.println("Apelido do cliente");
			System.out.println("E-mail do cliente");
			break;
		case 8:
			System.out.println("Nome do cliente");
			break;
		case 9:
			System.out.println("Se procede a crear copia de seguridad");
			break;
		case 10:
			break;
		case 11:
			System.exit(0);
			break;
		default:
			break;
		}
		
	}
	
	static void serializar(Compañia compañia) {
		 //Pasamos a nosa clase a JSON utilizando a libreria GSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(compañia);

        //Vamos comezar declarando o ficheiro
        File arquivo = new File("data.json");

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
	
	
	static void deserializar(Compañia compañia) {
		//Vamos comezar declarando o ficheiro
        File arquivo = new File("data.json");

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
            compañia = gson.fromJson(json, Compañia.class);
            
            //Comprobamos que se leron ben os datos
            
            System.out.println("tendas:");
            for(int i=0;i<compañia.getTendas().size();i++){
                Tenda tendaAux = compañia.getTendas().get(i);
                System.out.println(tendaAux.getNome() + " " + tendaAux.getCidade());
            }
			

        } catch (FileNotFoundException e) {
            System.out.println("Non se encontra o arquivo");
        } catch (IOException e) {
            System.out.println("Erro de entrada saída");
        }
    }
	
	
	
		

}
