package listeners;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class ButtonListeners implements MouseListener {
	private JButton button;
	public ButtonListeners(final JButton button) {
		this.button =  button;
	}

	@Override
	public void mouseClicked(final MouseEvent event) {
		
	}

	@Override
	public void mouseEntered(final MouseEvent event) {
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseExited(final MouseEvent event) {
		
	}

	@Override
	public void mousePressed(final MouseEvent event) {
		
	}

	@Override
	public void mouseReleased(final MouseEvent event) {
		
	}
}
