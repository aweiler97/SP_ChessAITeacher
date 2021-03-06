package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TutorialHelperPanel extends JPanel {

	private int width;
	private int height;
	private JLabel helpImage = new JLabel(new ImageIcon());
	private JTextPane textArea = new JTextPane();
	private final JButton prev = new JButton("Back");
	private final JButton next = new JButton("Next");
	private final JButton finish = new JButton("Finish");
	private final JButton menu = new JButton("Main Menu");

	public TutorialHelperPanel(TutorialTeacherPanel teacherPanel, MainFrame mainFrame) {
		Dimension size = getPreferredSize();
		size.width = 185;
		size.height = 650;
		this.setPreferredSize(size);
		this.width = (int) size.getWidth();
		this.height = (int) size.getHeight();
		setBackground(Color.WHITE);
		textArea.setEditable(false);

		prev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				teacherPanel.prevStep();
			}
		});

		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				teacherPanel.nextStep();
			}
		});

		finish.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (teacherPanel.hasBoardTutorial()) {
					teacherPanel.startStrategyGame();
				} else {
					mainFrame.startMainMenu();
				}
			}
		});

		menu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.startMainMenu();
			}
		});

		startGuide();
	}

	public void setImage(ImageIcon image) {
		helpImage.setIcon(image);
	}

	public void setTextBox(String text) {
		textArea.setText(text);
	}

	public void removeSequence() {
		removeAll();
		repaint();
	}

	private void startGuide() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		// BoardImage
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 0.5;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(helpImage, gbc);

		// Textarea
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(textArea, gbc);

		// Previous button
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(prev, gbc);

		// Next button
		gbc.gridwidth = 1;
		gbc.gridx = 1;
		gbc.gridy = 2;
		add(next, gbc);

		// Finish button
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(finish, gbc);
	}

	public void startBoardGuide() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		// BoardImage
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(helpImage, gbc);

		// Textarea
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(textArea, gbc);

		// Previous button
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(menu, gbc);
	}

}
