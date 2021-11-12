package es.florida.AE3;

public class Ventilador {
	
	int numero = 1;
    Mina mina;
    
    public Ventilador(Mina stockMina) {
        this.mina = stockMina;
    }
    
    public void encenderVentilador() throws InterruptedException {
        while (true) {
            if (0<mina.getStock()){
                synchronized (this) {
                    while (numero == 1) {
                        Thread.sleep(3000);
                        System.out.println("---------------------------");
                        System.out.println("Ventilador encendido");
                        System.out.println("---------------------------");
                        numero = 2;
                        notify();
                    }
                }
            } else {
                break;
            }
        }
    }
    
    public void apagarVentilador() throws InterruptedException {
        while (true) {
            if (0<mina.getStock()) {
                synchronized (this) {
                    while (numero == 2) {

                        Thread.sleep(2000);
                        System.out.println("---------------------------");
                        System.out.println("Ventilador apagado");
                        System.out.println("---------------------------");
                        numero = 1;
                        notify();
                    }
                }
            } else {
                break;
            }
        }
    }
}
