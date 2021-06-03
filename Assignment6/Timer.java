public class Timer {

	   private long startTime = System.currentTimeMillis();

	   private long elapsedTime = 0;


	   public void start() { 
		   startTime = System.currentTimeMillis(); 
	   }

	   public long since() { 
		   elapsedTime = System.currentTimeMillis() - startTime;
		   return elapsedTime;
	   }

	   public long report() {
		   return elapsedTime;
	   }

	   public void report(String s) {
		   System.out.println(s + elapsedTime);
	   }

}