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
    
    private void extraerRecursos(String nombre) {
        
        if (mina.stock > 0) {
            mina.stock--;
            setBolsa(bolsa + 1);
            System.out.println(nombre + " ha extraido 1 recurso de la mina");
            System.out.println("Quedan " + mina.stock + " recursos en la mina");
        } else {
            System.out.println(nombre + " ha salido con las manos vacias");
            System.out.println("No quedan suficientes recursos");
        }
        System.out.println("-------------------------------------------------");
    }

	@Override
	public synchronized void run() {
		  try {
	            Thread.sleep(tiempoExtraccion);
	            String nombre = Thread.currentThread().getName();
	            extraerRecursos(nombre);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	}
}
