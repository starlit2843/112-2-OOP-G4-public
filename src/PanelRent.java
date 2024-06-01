import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.ArrayList;

public class PanelRent extends JPanel {
	private JPanel pSouthBnGroup, pNumberMonitor;
	private JButton bt_p1, bt_p2, bt_p3, bt_p4, bt_p5, bt_p6, bt_PlaceHolder1, bt_PlaceHolder2, bt_Add, bt_Minus,
			bt_PlaceHolder3, bt_Submit;

	private JLabel num = new JLabel("0");
	private ArrayList<JButton> btList = new ArrayList<JButton>();

	// add
	private ImageIcon scaledIcon_Item1, scaledIcon_Item2, scaledIcon_Item3, scaledIcon_Item4, scaledIcon_Set1,
			scaledIcon_Set2;

	private int control = 1;
	private int q0, q1, q2, q3, q4, q5, q6 = 0;
	private JPanel pMain = new JPanel(new BorderLayout());

	public PanelRent() {
		cButton();
		cComponent();
		// add
		createImageIcon();
		cLayOut();
	}

	private void cComponent() {
		pSouthBnGroup = new JPanel();
		pSouthBnGroup.setLayout(new FlowLayout());

		for (JButton bt : btList) {
			pSouthBnGroup.add(bt);
		}

		pNumberMonitor = new JPanel();
		pNumberMonitor.setLayout(new FlowLayout());

		pNumberMonitor.add(bt_Minus);
		num.setPreferredSize(new Dimension(50, 10));
		pNumberMonitor.add(num);
		pNumberMonitor.add(bt_Add);
		bt_PlaceHolder3.setPreferredSize(new Dimension(200, 0));
		pNumberMonitor.add(bt_PlaceHolder3);
		pNumberMonitor.add(bt_Submit);

	}

	private void cButton() {
		// BtGroup 1
		// Adjust the names of products
		bt_p1 = new JButton("Cup");
		bt_p1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				control = 1;
				resetQ0();
				cLayOut();

			}
		});

		bt_p2 = new JButton("Bowl");
		bt_p2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				control = 2;
				resetQ0();
				cLayOut();

			}
		});

		bt_p3 = new JButton("Spoon");
		bt_p3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				control = 3;
				resetQ0();
				cLayOut();

			}
		});

		bt_p4 = new JButton("Chopsticks");
		bt_p4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				control = 4;
				resetQ0();
				cLayOut();

			}
		});

		bt_p5 = new JButton("Bowl & Spoon");
		bt_p5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				control = 5;
				resetQ0();
				cLayOut();

			}
		});

		bt_p6 = new JButton("Bowl & Spoon & Chopsticks");
		bt_p6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				control = 6;
				resetQ0();
				cLayOut();

			}
		});

		bt_PlaceHolder1 = new JButton("single:");
		bt_PlaceHolder2 = new JButton("sets:");
		bt_PlaceHolder1.setEnabled(false);
		bt_PlaceHolder2.setEnabled(false);

		btList.add(bt_PlaceHolder1);
		btList.add(bt_p1);
		btList.add(bt_p2);
		btList.add(bt_p3);
		btList.add(bt_p4);
		btList.add(bt_PlaceHolder2);
		btList.add(bt_p5);
		btList.add(bt_p6);

		// BtGroup 2
		bt_Minus = new JButton("-");
		bt_Minus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (q0 > 0) {
					q0--;
				}
				num.setText(Integer.toString(q0));
				cLayOut();
			}
		});

		bt_Add = new JButton("+");
		bt_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				q0++;
				num.setText(Integer.toString(q0));
				cLayOut();
			}
		});

		bt_PlaceHolder3 = new JButton("");
		bt_PlaceHolder3.setEnabled(false);
		bt_PlaceHolder3.setPreferredSize(new Dimension(200, 0));

		bt_Submit = new JButton("submit");
		bt_Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (q0 > 0) {
					switch (control) {

					case 1:
						q1 += q0;
						User.getInstance().setq1(q1);
						break;
					case 2:
						q2 += q0;
						User.getInstance().setq2(q2);
						break;
					case 3:
						q3 += q0;
						User.getInstance().setq3(q3);
						break;
					case 4:
						q4 += q0;
						User.getInstance().setq4(q4);
						break;
					case 5:
						q5 += q0;
						User.getInstance().setq5(q5);
						break;
					case 6:
						q6 += q0;
						User.getInstance().setq6(q6);
						break;
					}
				}
				resetQ0();
			}
		});
	}

	private void cLayOut() {

		pMain.removeAll();

		// pMain.add(new JTextArea(Integer.toString(control)));

		btResetAll();
		switch (control) {
		case 1:
			bt_p1.setEnabled(false);
			// add picture
			pMain.add(new JLabel(scaledIcon_Item1));
			break;
		case 2:
			bt_p2.setEnabled(false);
			// add picture
			pMain.add(new JLabel(scaledIcon_Item2));
			break;
		case 3:
			bt_p3.setEnabled(false);
			// add picture
			pMain.add(new JLabel(scaledIcon_Item3));
			break;
		case 4:
			bt_p4.setEnabled(false);
			// add picture
			pMain.add(new JLabel(scaledIcon_Item4));
			break;
		case 5:
			bt_p5.setEnabled(false);
			// add picture
			pMain.add(new JLabel(scaledIcon_Set1));
			break;
		case 6:
			bt_p6.setEnabled(false);
			// add picture
			pMain.add(new JLabel(scaledIcon_Set2));
			break;
		default:

			break;
		}

		pMain.revalidate();
		pMain.repaint();

		add(pSouthBnGroup, BorderLayout.NORTH);
		pMain.setPreferredSize(new Dimension(800, 400));
		add(pMain, BorderLayout.CENTER);
		add(pNumberMonitor, BorderLayout.SOUTH);

		setVisible(true);
	}

	private void resetQ0() {
		q0 = 0;
		num.setText(Integer.toString(q0));
	}

	private void btResetAll() {
		for (JButton bt : btList) {
			bt.setEnabled(true);
		}
		bt_PlaceHolder1.setEnabled(false);
		bt_PlaceHolder2.setEnabled(false);
	}

	private void createImageIcon() {
		// 單品1
		int newWidth_Item1 = 350;
		int newHeight_Item1 = 350;

		// 建立 ImageIcon
		ImageIcon imageIcon_Item1 = new ImageIcon("杯.jpg");
		// 獲取圖片並調整其大小以適應按鈕
		Image image_Item1 = imageIcon_Item1.getImage();
		Image scaledImage_Item1 = image_Item1.getScaledInstance(newWidth_Item1, newHeight_Item1, Image.SCALE_SMOOTH);

		// 將調整大小後的圖片設定為按鈕的圖示=
		scaledIcon_Item1 = new ImageIcon(scaledImage_Item1);

		// 單品2
		int newWidth_Item2 = 350;
		int newHeight_Item2 = 350;

		// 建立 ImageIcon
		ImageIcon imageIcon_Item2 = new ImageIcon("碗.jpg");
		// 獲取圖片並調整其大小以適應按鈕
		Image image_Item2 = imageIcon_Item2.getImage();
		Image scaledImage_Item2 = image_Item2.getScaledInstance(newWidth_Item2, newHeight_Item2, Image.SCALE_SMOOTH);

		// 將調整大小後的圖片設定為按鈕的圖示=
		scaledIcon_Item2 = new ImageIcon(scaledImage_Item2);

		// 單品3
		int newWidth_Item3 = 350;
		int newHeight_Item3 = 350;

		// 建立 ImageIcon
		ImageIcon imageIcon_Item3 = new ImageIcon("湯匙.jpg");
		// 獲取圖片並調整其大小以適應按鈕
		Image image_Item3 = imageIcon_Item3.getImage();
		Image scaledImage_Item3 = image_Item3.getScaledInstance(newWidth_Item3, newHeight_Item3, Image.SCALE_SMOOTH);

		// 將調整大小後的圖片設定為按鈕的圖示=
		scaledIcon_Item3 = new ImageIcon(scaledImage_Item3);

		// 單品4
		int newWidth_Item4 = 350;
		int newHeight_Item4 = 350;

		// 建立 ImageIcon
		ImageIcon imageIcon_Item4 = new ImageIcon("筷.jpg");
		// 獲取圖片並調整其大小以適應按鈕
		Image image_Item4 = imageIcon_Item4.getImage();
		Image scaledImage_Item4 = image_Item4.getScaledInstance(newWidth_Item4, newHeight_Item4, Image.SCALE_SMOOTH);

		// 將調整大小後的圖片設定為按鈕的圖示=
		scaledIcon_Item4 = new ImageIcon(scaledImage_Item4);

		// 組合1
		int newWidth_Set1 = 350;
		int newHeight_Set1 = 350;

		// 建立 ImageIcon
		ImageIcon imageIcon_Set1 = new ImageIcon("筷勺.jpg");
		// 獲取圖片並調整其大小以適應按鈕
		Image image_Set1 = imageIcon_Set1.getImage();
		Image scaledImage_Set1 = image_Set1.getScaledInstance(newWidth_Set1, newHeight_Set1, Image.SCALE_SMOOTH);

		// 將調整大小後的圖片設定為按鈕的圖示=
		scaledIcon_Set1 = new ImageIcon(scaledImage_Set1);

		// 組合2
		int newWidth_Set2 = 350;
		int newHeight_Set2 = 350;

		// 建立 ImageIcon
		ImageIcon imageIcon_Set2 = new ImageIcon("碗筷勺.jpg");
		// 獲取圖片並調整其大小以適應按鈕
		Image image_Set2 = imageIcon_Set2.getImage();
		Image scaledImage_Set2 = image_Set2.getScaledInstance(newWidth_Set2, newHeight_Set2, Image.SCALE_SMOOTH);

		// 將調整大小後的圖片設定為按鈕的圖示=
		scaledIcon_Set2 = new ImageIcon(scaledImage_Set2);

	}

	protected int getQ0() {
		return q0;
	}
}