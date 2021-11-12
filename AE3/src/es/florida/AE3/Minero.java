package es.florida.AE3;

public class Minero implements Runnable {
	
	int bolsa;
    int tiempoExtraccion = 1000;
    Mina mina;
    
    public Minero(Mina mina) {
        this.mina = mina;
        bolsa = 0;
    }
    
    public void setBolsa(int bolsa) {
        this.bolsa = bolsa;
    }

    public int getBolsa() {
        return bolsa;
    }

	@Override
	public void run() {
		
	}
    
    

}
