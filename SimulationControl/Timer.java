package SimulationControl;

public class Timer {

	private int timeout_ms;
	private long start_time, end_time, duration;
	private String message = null;
	
	public Timer(int ms) {
		this.timeout_ms = ms;
	}
	
	public void start() {
		this.start_time = System.nanoTime();
	}
	
	public void end() {
		this.end_time = System.nanoTime();
		this.duration = (this.end_time - this.start_time) / 1000000;
		this.show_message(this.duration);
		this.sleep(Math.max((this.timeout_ms - (int)duration), 0));
	}
	
	public void set_message(String _message) {
		this.message = _message;
	}
	
	private void show_message(long duration) {
		if (this.message != null) {
			System.out.printf("%s - %dms\n", this.message, duration);
		}
	}
	
	private void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch(InterruptedException ex) {
			
		}
	}
	
}
