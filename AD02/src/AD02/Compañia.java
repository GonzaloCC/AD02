package AD02;

import java.util.ArrayList;

public class Compañia {
	ArrayList<Tenda> tendas;
	ArrayList<Cliente> clientes;
	
	public Compañia( ) {
		tendas = new ArrayList<Tenda>();
		clientes = new ArrayList <Cliente>();
		}
	public ArrayList<Tenda> getTendas() {
		return tendas;
	}
	public void setTendas(ArrayList<Tenda> tendas) {
		this.tendas = tendas;
	}
	public ArrayList<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}
	
    //Metodo que pasa a JSON
    public String toJSON(){
        String json = new String();
        json = json + "{ ";
        json = json + "\"tendas\" : \"" + this.tendas + "\",";
        json = json + "\"clientes\" : " + this.clientes + " }";
        return json;
    }
	

}
