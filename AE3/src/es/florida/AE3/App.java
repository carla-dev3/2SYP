package es.florida.AE3;

public class App {

	public static void main(String[] args) {
		
		int hilos = 10;
        Mina mina = new Mina();
        Minero minero = new Minero(mina);
        Thread hilo;

        System.out.println("Se ha detectado en la mina una cantidad de recursos de "+ mina.getStock());


        while (mina.getStock() > 0) {
            for (int i = 0; i < hilos; i++) {
                hilo = new Thread(minero);
                hilo.setName("Minero " + (i + 1));
                hilo.start();
            }
        }

        System.out.println("La bolsa esta llena y la mina vacia, buen trabajo");
        System.out.println("Cantidad de la bolsa -> " + minero.getBolsa());
        System.exit(0);

	}

}
