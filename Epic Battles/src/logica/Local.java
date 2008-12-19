package logica;

public class Local extends Thread implements Oponente {

	public Local() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Local(Runnable target) {
		super(target);
		// TODO Auto-generated constructor stub
	}

	public Local(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public Local(ThreadGroup group, Runnable target) {
		super(group, target);
		// TODO Auto-generated constructor stub
	}

	public Local(ThreadGroup group, String name) {
		super(group, name);
		// TODO Auto-generated constructor stub
	}

	public Local(Runnable target, String name) {
		super(target, name);
		// TODO Auto-generated constructor stub
	}

	public Local(ThreadGroup group, Runnable target, String name) {
		super(group, target, name);
		// TODO Auto-generated constructor stub
	}

	public Local(ThreadGroup group, Runnable target, String name, long stackSize) {
		super(group, target, name, stackSize);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void decidir() {
		// TODO Auto-generated method stub

	}

}
