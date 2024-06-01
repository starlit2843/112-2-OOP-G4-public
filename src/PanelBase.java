
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public abstract class PanelBase extends JPanel {
	protected JPanel pSouthBnGroup;
	protected JButton bt_First, bt_Second;
	// add
	protected JLabel imageLabel_First, imageLabel_Second;
	protected int control = 0;
	protected JPanel pMain = new JPanel(new BorderLayout());

	public PanelBase() {
		cButton();
		// add
		cImageIcon();
		cComponent();
		cLayOut();
	}

	private void cComponent() {
		pSouthBnGroup = new JPanel();
		pSouthBnGroup.add(bt_First);
		pSouthBnGroup.add(bt_Second);

		bt_First.setEnabled(false);
	}

	private void cButton() {
		bt_First = new JButton(getFirstBtName());
		bt_First.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				control = 0;
				bt_First.setEnabled(false);
				bt_Second.setEnabled(true);
				cLayOut();
			}
		});

		bt_Second = new JButton(getSecondBtName());
		bt_Second.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				control = 1;
				bt_First.setEnabled(true);
				bt_Second.setEnabled(false);
				cLayOut();
			}
		});
	}

	private void cImageIcon() {
		// 建立 ImageIcon
		ImageIcon imageIcon_First = new ImageIcon(getFirstIconName());
		// 獲取圖片並調整其大小以適應按鈕
		Image image_First = imageIcon_First.getImage();
		Image scaledImage_First = image_First.getScaledInstance(getIconLength(), 380, Image.SCALE_SMOOTH);

		// 將調整大小後的圖片設定為按鈕的圖示=
		ImageIcon scaledIcon_First = new ImageIcon(scaledImage_First);
		
		imageLabel_First = new JLabel(scaledIcon_First);

		// 建立 ImageIcon
		ImageIcon imageIcon_Second = new ImageIcon(getSecondIconName());
		// 獲取圖片並調整其大小以適應按鈕
		Image image_Second = imageIcon_Second.getImage();
		Image scaledImage_Second = image_Second.getScaledInstance(getIconLength(), 380, Image.SCALE_SMOOTH);

		// 將調整大小後的圖片設定為按鈕的圖示=
		ImageIcon scaledIcon_Second = new ImageIcon(scaledImage_Second);

		imageLabel_Second = new JLabel(scaledIcon_Second);
	}

	protected abstract String getFirstBtName();

	protected abstract String getSecondBtName();

	protected abstract String getFirstIconName();

	protected abstract String getSecondIconName();

	protected abstract int getIconLength();

	protected abstract void updateLayout();

	public void cLayOut() {
		pMain.removeAll();

		updateLayout();

		pMain.revalidate();
		pMain.repaint();

		add(pMain, BorderLayout.CENTER);
		add(pSouthBnGroup, BorderLayout.SOUTH);

		// 讓Buttons換行
		pSouthBnGroup.setPreferredSize(new Dimension(600, 50));
	}
}