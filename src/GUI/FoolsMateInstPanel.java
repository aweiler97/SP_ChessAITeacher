package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class FoolsMateInstPanel extends JPanel implements TutorialTeacherPanel {

	private int width;
	private int height;
	private int index;
	private TutorialHelperPanel helperPanel;
	private JLabel boardImage = new JLabel(new ImageIcon());
	private TutorialBlock[] blocks;

	public FoolsMateInstPanel(MainFrame mainFrame) {
		Dimension size = getPreferredSize();
		size.width = 740;
		size.height = 570;
		this.setPreferredSize(size);
		this.width = (int) size.getWidth();
		this.height = (int) size.getHeight();
		setBackground(Color.WHITE);

		helperPanel = new TutorialHelperPanel(this, mainFrame);

		ImageIcon blockImage1 = null;
		ImageIcon blockImage2 = null;
		ImageIcon blockImage3 = null;
		ImageIcon blockImage4 = null;
		ImageIcon blockImage5 = null;
		ImageIcon blockImage6 = null;

		try {
			blockImage1 = new ImageIcon(ImageIO.read(ResourceLoader.load("images/foolsmate/foolsmatefinished.PNG")));
			blockImage2 = new ImageIcon(ImageIO.read(ResourceLoader.load("images/foolsmate/foolsmatestarter.PNG")));
			blockImage3 = new ImageIcon(ImageIO.read(ResourceLoader.load("images/foolsmate/foolsmateblackmove1.PNG")));
			blockImage4 = new ImageIcon(ImageIO.read(ResourceLoader.load("images/foolsmate/foolsmatewhitemove2.PNG")));
			blockImage5 = new ImageIcon(ImageIO.read(ResourceLoader.load("images/foolsmate/foolsmateblackmove2.PNG")));
			blockImage6 = new ImageIcon(ImageIO.read(ResourceLoader.load("images/foolsmate/foolsmatefinished2.PNG")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		String blockText1 = "Fool's Mate is a strategy that black player can use to punish beginner chess players by achieving checkmate in only two moves. " +
				"As you can see, the white king is in check by the black queen at h5.";
		String blockText2 = "This is heavily dependent on the white player misplaying their first two moves. " +
				"Their pawn at f7 must first move to either f6 or f5.";
		String blockText3 = "After white has moved the previously mentioned pawn, move your own pawn at e2 to e3. " +
				"This will allow the queen at d1 to move ";
		String blockText4 = "Now white must move their pawn at g7 to g5.";
		String blockText5 = "Finally, move your queen from d1 to h5.";
		String blockText6 = "Your opponent's king at e8 is now in checkmate from your queen at h5.";

		blocks = new TutorialBlock[6];
		blocks[0] = new TutorialBlock(blockImage1, blockText1);
		blocks[1] = new TutorialBlock(blockImage2, blockText2);
		blocks[2] = new TutorialBlock(blockImage3, blockText3);
		blocks[3] = new TutorialBlock(blockImage4, blockText4);
		blocks[4] = new TutorialBlock(blockImage5, blockText5);
		blocks[5] = new TutorialBlock(blockImage6, blockText6);

		setLayout(new BorderLayout());

		add(boardImage, BorderLayout.WEST);
		add(helperPanel, BorderLayout.EAST);

		startTutorial();
	}

	private void startTutorial() {
		index = 0;
		boardImage.setIcon(blocks[index].getImage());
		helperPanel.setTextBox(blocks[index].getText());
	}

	public void prevStep() {
		index--;
		if (index >= 0) {
			boardImage.setIcon(blocks[index].getImage());
			helperPanel.setTextBox(blocks[index].getText());
		} else {
			index++;
			return;
		}
	}

	public void nextStep() {
		index++;
		if (index <= blocks.length - 1) {
			boardImage.setIcon(blocks[index].getImage());
			helperPanel.setTextBox(blocks[index].getText());
		} else {
			index--;
			return;
		}
	}

	public void startStrategyGame() {
		JPanel gamePanel = new JPanel();
		FoolsMateBoardPanel boardPanel = new FoolsMateBoardPanel(this);
		ColumnLabelPanel columnLabelPanel = new ColumnLabelPanel();
		RowLabelPanel rowLabelPanel = new RowLabelPanel();

		gamePanel.setLayout(new BorderLayout());
		gamePanel.add(columnLabelPanel, BorderLayout.PAGE_START);
		gamePanel.add(rowLabelPanel, BorderLayout.LINE_START);
		gamePanel.add(boardPanel, BorderLayout.CENTER);

		remove(boardImage);
		add(gamePanel, BorderLayout.WEST);
		repaint();
		helperPanel.removeSequence();
		helperPanel.startBoardGuide();
	}

	@Override
	public boolean hasBoardTutorial() {
		return true;
	}

	public void printInstructions1() {
		ImageIcon inst1Image = null;
		try {
			inst1Image = new ImageIcon(ImageIO.read(ResourceLoader.load("images/foolsmate/foolsmateboardmove1.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		String inst1Text = "Your opponent seems to be falling into the fool's mate trap. Move your pawn and introduce them to the strategy.";
		TutorialBlock inst1 = new TutorialBlock(inst1Image, inst1Text);

		helperPanel.setImage(inst1.getImage());
		helperPanel.setTextBox(inst1.getText());
	}

	public void printInstructions2() {
		ImageIcon inst2Image = null;
		try {
			inst2Image = new ImageIcon(ImageIO.read(ResourceLoader.load("images/foolsmate/foolsmateboardmove2.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		String inst2Text = "Finally, move your queen.";
		TutorialBlock inst2 = new TutorialBlock(inst2Image, inst2Text);

		helperPanel.setImage(inst2.getImage());
		helperPanel.setTextBox(inst2.getText());
	}

	public void printfinalInstructions() {
		ImageIcon inst3Image = null;
		try {
			inst3Image = new ImageIcon(ImageIO.read(ResourceLoader.load("images/foolsmate/white.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		String inst3Text = "Your opponent is now in checkmate. Good job, you've learned how to use fool's mate. " +
				"Press the main menu button to leave";
		TutorialBlock inst3 = new TutorialBlock(inst3Image, inst3Text);

		helperPanel.setImage(inst3.getImage());
		helperPanel.setTextBox(inst3.getText());
	}

}
