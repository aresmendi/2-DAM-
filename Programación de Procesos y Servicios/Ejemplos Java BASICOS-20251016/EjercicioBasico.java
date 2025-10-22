package unidad2;

class HiloBasico extends Thread {
	@Override
	public void run() {
		System.out.println("Soy el hilo creado que está empezando.");
		try {
			System.out.println("Voy a esperar 3 segundos.");
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println("Hilo creado interrumpido.");
			return;
		}
		System.out.println("Soy el hilo creado que va a acabar.");
	}
}

public class Ejercicio {
	public static void main(String args[]) {
		HiloBasico hilo = new HiloBasico();
		hilo.start();
	}	
}