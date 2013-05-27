package engine.input;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class JInputTest {

	private void identifyInputDevices() {
		Controller[] controllers = ControllerEnvironment
				.getDefaultEnvironment().getControllers();

		for (Controller controller : controllers) {

			Component[] components = controller.getComponents();
			for (Component component : components) {
				// ...
				// http://www.java-gaming.org/index.php?topic=16866.0
			}

		}

	}

	public JInputTest() {
		identifyInputDevices();
	}

	public static void main(String[] args) {
		new JInputTest();
	}

}
