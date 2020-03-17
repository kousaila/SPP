package tp34;


import java.util.Random;
import java.util.concurrent.Exchanger;

public class exo21 extends Thread{
	public String name; 
	public String label; 
	@SuppressWarnings("rawtypes")
	public Exchanger exchanger; 
	
	//CONSTRUCTOR
	public exo21 (String nom, String texte, @SuppressWarnings("rawtypes") Exchanger exchangeur){
		this.name=nom;
		this.label=texte; 
		this.exchanger=exchangeur;
	}
	
	
@SuppressWarnings("unchecked")
public void run(){
	
	for(int i=0; i<3; i++){
		System.out.println("Iteration : " +i+" "+ this.name +" "+this.label);
		System.out.println("Iteration : " +i+" "+ this.name +" going to sleep.");
		Random r = new Random(); 
		int sleep = r.nextInt(5000); 
		try {
			Thread.sleep((long) sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		System.out.println("Iteration : " +i+" "+ this.name +" ready to exchange");
		try {
			this.label= (String) (this.exchanger).exchange(label);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Iteration : " +i+" "+ this.name +" exchange completed");
	
	}
}
	
	

	public static void main(String[] args) {
		Exchanger<String> exchange = new Exchanger<String>();
		
		Thread alice= new exo21("Alice", "Ping", exchange);
		
		Thread bob=new exo21("Bob", "Pong", exchange); 
		
		alice.start();
		
		bob.start();

	}

}
