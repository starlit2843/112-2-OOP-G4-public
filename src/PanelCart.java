import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import javax.swing.*;
import java.io.*;

public class PanelCart extends JPanel {
	private JPanel pSouthBnGroup, pMonitor;
	private JButton bt_submit, bt_PlaceHolder, bt_clearAll;
	private JRadioButton rbt_1, rbt_2;
	private ButtonGroup rbtGroup;
	private JTextArea p1, p2, p3, p4, p5, p6;
	private JScrollPane scrollPane;
	private int q1 = 0;
	private int q2 = 0;
	private int q3 = 0;
	private int q4 = 0;
	private int q5 = 0;
	private int q6 = 0;
	private JPanel pMain = new JPanel(new BorderLayout());

	// add
	private JPanel product1Panel, product2Panel, product3Panel, product4Panel, product5Panel, product6Panel;
	private JLabel picLabel_Item1, picLabel_Item2, picLabel_Item3, picLabel_Item4, picLabel_Set1, picLabel_Set2;
	private JLabel quantityLabel_Item1, quantityLabel_Item2, quantityLabel_Item3, quantityLabel_Item4,
			quantityLabel_Set1, quantityLabel_Set2;
	private int product1_Quantity, product2_Quantity, product3_Quantity, product4_Quantity, product5_Quantity,
			product6_Quantity;
	private int totalPayment;
	private JLabel total;
	private JPanel productsPanel;
	private JButton btnItem1_Cancel, btnItem2_Cancel, btnItem3_Cancel, btnItem4_Cancel, btnSet1_Cancel, btnSet2_Cancel;

	public PanelCart() {
		cButton();
		// add
		cComponent();

		setCart();

		setOrder();
		// updateCart();
		productVisible();

		cLayOut();
	}

	private void cComponent() {
		/*
		 * p1 = new JTextArea(); p2 = new JTextArea(); p3 = new JTextArea(); p4 = new
		 * JTextArea(); p5 = new JTextArea(); p6 = new JTextArea();
		 * p1.setPreferredSize(new Dimension(700,40)); p2.setPreferredSize(new
		 * Dimension(700,40)); p3.setPreferredSize(new Dimension(700,40));
		 * p4.setPreferredSize(new Dimension(700,40)); p5.setPreferredSize(new
		 * Dimension(700,40)); p6.setPreferredSize(new Dimension(700,40));
		 */
		if(User.getInstance().getq0() > 0) {
			q1 += User.getInstance().getq1();
			q2 += User.getInstance().getq2();
			q3 += User.getInstance().getq3();
			q4 += User.getInstance().getq4();
			q5 += User.getInstance().getq5();
			q6 += User.getInstance().getq6();
		} else {
			q1 = User.getInstance().getq1();
			q2 = User.getInstance().getq2();
			q3 = User.getInstance().getq3();
			q4 = User.getInstance().getq4();
			q5 = User.getInstance().getq5();
			q6 = User.getInstance().getq6();
		}
		product1_Quantity = q1;
		product2_Quantity = q2;
		product3_Quantity = q3;
		product4_Quantity = q4;
		product5_Quantity = q5;
		product6_Quantity = q6;

		/*
		 * p1.setText(Integer.toString(q1)); p2.setText(Integer.toString(q2));
		 * p3.setText(Integer.toString(q3)); p4.setText(Integer.toString(q4));
		 * p5.setText(Integer.toString(q5)); p6.setText(Integer.toString(q6));
		 * 
		 * pMonitor = new JPanel(new GridLayout(7,1,30,30)); pMonitor.add(p1);
		 * pMonitor.add(p2); pMonitor.add(p3); pMonitor.add(p4); pMonitor.add(p5);
		 * pMonitor.add(p6);
		 */

		pSouthBnGroup = new JPanel();
		pSouthBnGroup.setLayout(new FlowLayout());

		total = new JLabel("總金額 : $ " + totalPayment);
		total.setFont(new Font("TimesRoman", Font.PLAIN, 15));

		pSouthBnGroup.add(rbt_1);
		pSouthBnGroup.add(rbt_2);
		bt_PlaceHolder.setEnabled(false);
		bt_PlaceHolder.setPreferredSize(new Dimension(200, 0));

		// add
		pSouthBnGroup.add(total);

		pSouthBnGroup.add(bt_PlaceHolder);
		pSouthBnGroup.add(bt_submit);
		// pSouthBnGroup.add(bt_clearAll);
	}

	private void cButton() {
		bt_submit = new JButton("Submit");
		bt_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				/*
				 * //add q1 = product1_Quantity; q2 = product2_Quantity; q3 = product3_Quantity;
				 * q4 = product4_Quantity; q5 = product5_Quantity; q6 = product6_Quantity;
				 */

				if (rbt_1.isSelected()) {
					User.getInstance().setPlace("商院中庭（山下）");
				} else if (rbt_2.isSelected()) {
					User.getInstance().setPlace("安九食堂（山上）");
				}
				int reply = JOptionPane.showConfirmDialog(null, "Submit?", "Submitting...", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					User.getInstance().submit(User.getInstance().getq1(), User.getInstance().getq2(),
							User.getInstance().getq3(), User.getInstance().getq4(), User.getInstance().getq5(),
							User.getInstance().getq6(), totalPayment);
					/*
					 * p1.setText("0"); p2.setText("0"); p3.setText("0"); p4.setText("0");
					 * p5.setText("0"); p6.setText("0");
					 */

					User.getInstance().resetAll();
					JOptionPane.showMessageDialog(null, "Success!", "Success", JOptionPane.INFORMATION_MESSAGE);
					System.out.println(User.getInstance().info());
					
					cLayOut();
					//清空購物車
					productsPanel.setVisible(false);
				} else {
					return;
				}
			}
		});

		/*
		 * bt_clearAll = new JButton("Clear cart"); bt_clearAll.addActionListener(new
		 * ActionListener() { public void actionPerformed(ActionEvent event) { q1 = 0;
		 * q2 = 0; q3 = 0; q4 = 0; q5 = 0; q6 = 0; User.getInstance().resetAll();
		 * p1.setText(Integer.toString(q1)); p2.setText(Integer.toString(q2));
		 * p3.setText(Integer.toString(q3)); p4.setText(Integer.toString(q4));
		 * p5.setText(Integer.toString(q5)); p6.setText(Integer.toString(q6));
		 * cLayOut(); } });
		 */
		rbt_1 = new JRadioButton("商院中庭（山下）");
		rbt_1.setSelected(true);
		rbt_2 = new JRadioButton("安九食堂（山上）");
		rbtGroup = new ButtonGroup();
		rbtGroup.add(rbt_1);
		rbtGroup.add(rbt_2);
		bt_PlaceHolder = new JButton();
	}

	public void cLayOut() {
		pMain.removeAll();

		pMonitor = new JPanel();
		pMonitor.setPreferredSize(new Dimension(700, 300));
		productsPanel = new JPanel(new GridLayout(6, 1));
		productsPanel.setPreferredSize(new Dimension(700, 300));

		if (q1 != 0) {
			productsPanel.add(product1Panel);
		}
		if (q2 != 0) {
			productsPanel.add(product2Panel);
		}
		if (q3 != 0) {
			productsPanel.add(product3Panel);
		}
		if (q4 != 0) {
			productsPanel.add(product4Panel);
		}
		if (q5 != 0) {
			productsPanel.add(product5Panel);
		}
		if (q6 != 0) {
			productsPanel.add(product6Panel);
		}

		scrollPane = new JScrollPane(productsPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(700, 300));
		pMonitor.add(scrollPane);

		pMain.add(pMonitor);

		pMain.revalidate();
		pMain.repaint();

		add(pMain, BorderLayout.CENTER);
		add(pSouthBnGroup, BorderLayout.SOUTH);
	}

	private void setOrder() {
		totalPayment = 0;
		// product1_Quantity += q1;
		quantityLabel_Item1.setText(Integer.toString(q1));

		// product2_Quantity += q2;
		quantityLabel_Item2.setText(Integer.toString(q2));

		// product3_Quantity += q3;
		quantityLabel_Item3.setText(Integer.toString(q3));

		// product4_Quantity += q4;
		quantityLabel_Item4.setText(Integer.toString(q4));

		// product5_Quantity += q5;
		quantityLabel_Set1.setText(Integer.toString(q5));

		// product6_Quantity += q6;
		quantityLabel_Set2.setText(Integer.toString(q6));

		totalPayment += 4 * q1 + 9 * q2 + 3 * q3 + 3 * q4 + 5 * q5 + 10 * q6;
		total.setText("總金額 : $ " + totalPayment);
	}

	private void setCart() {
		try {
			picLabel_Item1 = new JLabel();
			// 單品1
			// 單品1
			int newWidth_Item1 = 50;
			int newHeight_Item1 = 50;
			// 建立 ImageIcon
			ImageIcon imageIcon_Item1 = new ImageIcon("杯.jpg");
			// 獲取圖片並調整其大小以適應按鈕
			Image image_Item1 = imageIcon_Item1.getImage();
			Image scaledImage_Item1 = image_Item1.getScaledInstance(newWidth_Item1, newHeight_Item1,
					Image.SCALE_SMOOTH);
			// 將調整大小後的圖片設定為按鈕的圖示=
			ImageIcon scaledIcon_Item1 = new ImageIcon(scaledImage_Item1);
			picLabel_Item1.setIcon(scaledIcon_Item1);
			// 創建商品名稱
			JLabel nameLabel_Item1 = new JLabel("環保杯");
			// 創建商品價格
			JLabel priceLabel_Item1 = new JLabel("$ 4");
			// 創建訂單數量
			quantityLabel_Item1 = new JLabel(Integer.toString(product1_Quantity));
			// 設定框線
			Border border = BorderFactory.createLineBorder(Color.BLACK);
			quantityLabel_Item1.setBorder(border);
			// 設定按鍵與訂單數加減
			JButton btnItem1_add = new JButton("+");
			btnItem1_add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					int quantity_Item1 = Integer.parseInt(quantityLabel_Item1.getText());
					quantity_Item1 += 1;
					product1_Quantity += 1;
					quantityLabel_Item1.setText(Integer.toString(quantity_Item1));
					totalPayment += 4;
					total.setText("總金額 : $ " + totalPayment);
					// data同步
					User.getInstance().setq1(1);
				}
			});
			JButton btnItem1_minus = new JButton("-");
			btnItem1_minus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					int quantity_Item1 = Integer.parseInt(quantityLabel_Item1.getText());
					if (quantity_Item1 - 1 >= 0) {
						quantity_Item1 -= 1;
						product1_Quantity -= 1;
						// data同步
						User.getInstance().setq1(-1);
					}
					quantityLabel_Item1.setText(Integer.toString(quantity_Item1));
					if (totalPayment - 4 >= 0) {
						totalPayment -= 4;
						total.setText("總金額 : $ " + totalPayment);
					}
					if (quantity_Item1 == 0) {
						productsPanel.remove(product1Panel);
						productsPanel.revalidate();
						productsPanel.repaint();
					}
				}
			});
			// 設定取消按鍵
			btnItem1_Cancel = new JButton("取消");
			JPanel btnItem1_Cancel_Middle = new JPanel(new GridLayout(3, 3));
			btnItem1_Cancel_Middle.add(new JPanel());
			btnItem1_Cancel_Middle.add(new JPanel());
			btnItem1_Cancel_Middle.add(new JPanel());
			btnItem1_Cancel_Middle.add(new JPanel());
			btnItem1_Cancel_Middle.add(btnItem1_Cancel);
			btnItem1_Cancel_Middle.add(new JPanel());
			btnItem1_Cancel_Middle.add(new JPanel());
			btnItem1_Cancel_Middle.add(new JPanel());
			btnItem1_Cancel_Middle.add(new JPanel());
			// 創個小分Panel讓數量按鍵在同一列
			JPanel quantityPanel_Product1 = new JPanel(new GridLayout(1, 3));
			JPanel quantityBorderPanel_Product1 = new JPanel(new GridLayout(3, 1));
			quantityBorderPanel_Product1.add(new JPanel());
			quantityBorderPanel_Product1.add(quantityPanel_Product1);
			quantityBorderPanel_Product1.add(new JPanel());
			quantityPanel_Product1.add(btnItem1_minus);
			quantityPanel_Product1.add(quantityLabel_Item1);
			quantityPanel_Product1.add(btnItem1_add);
			// 創個分Panel放商品資訊
			product1Panel = new JPanel(new GridLayout(1, 7));
			product1Panel.add(picLabel_Item1);
			product1Panel.add(nameLabel_Item1);
			product1Panel.add(priceLabel_Item1);
			product1Panel.add(quantityBorderPanel_Product1);
			product1Panel.add(btnItem1_Cancel_Middle);
			picLabel_Item2 = new JLabel();
			// 單品2
			// 單品2
			int newWidth_Item2 = 50;
			int newHeight_Item2 = 50;
			// 建立 ImageIcon
			ImageIcon imageIcon_Item2 = new ImageIcon("碗.jpg");
			// 獲取圖片並調整其大小以適應按鈕
			Image image_Item2 = imageIcon_Item2.getImage();
			Image scaledImage_Item2 = image_Item2.getScaledInstance(newWidth_Item2, newHeight_Item2,
					Image.SCALE_SMOOTH);
			// 將調整大小後的圖片設定為按鈕的圖示=
			ImageIcon scaledIcon_Item2 = new ImageIcon(scaledImage_Item2);
			picLabel_Item2.setIcon(scaledIcon_Item2);
			JLabel nameLabel_Item2 = new JLabel("環保碗");
			JLabel priceLabel_Item2 = new JLabel("$ 9");
			quantityLabel_Item2 = new JLabel(Integer.toString(product2_Quantity));
			// 設定框線
			quantityLabel_Item2.setBorder(border);
			JButton btnItem2_add = new JButton("+");
			btnItem2_add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					int quantity_Item2 = Integer.parseInt(quantityLabel_Item2.getText());
					quantity_Item2 += 1;
					product2_Quantity += 1;
					quantityLabel_Item2.setText(Integer.toString(quantity_Item2));
					totalPayment += 9;
					total.setText("總金額 : $ " + totalPayment);
					// data同步
					User.getInstance().setq2(1);
				}
			});
			JButton btnItem2_minus = new JButton("-");
			btnItem2_minus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					int quantity_Item2 = Integer.parseInt(quantityLabel_Item2.getText());
					if (quantity_Item2 - 1 >= 0) {
						quantity_Item2 -= 1;
						product2_Quantity -= 1;
						// data同步
						User.getInstance().setq2(-1);
					}
					quantityLabel_Item2.setText(Integer.toString(quantity_Item2));
					if (totalPayment - 9 >= 0) {
						totalPayment -= 9;
						total.setText("總金額 : $ " + totalPayment);
					}
					if (quantity_Item2 == 0) {
						productsPanel.remove(product2Panel);
						productsPanel.revalidate();
						productsPanel.repaint();
					}
				}
			});
			// 設定取消按鍵
			btnItem2_Cancel = new JButton("取消");
			JPanel btnItem2_Cancel_Middle = new JPanel(new GridLayout(3, 3));
			btnItem2_Cancel_Middle.add(new JPanel());
			btnItem2_Cancel_Middle.add(new JPanel());
			btnItem2_Cancel_Middle.add(new JPanel());
			btnItem2_Cancel_Middle.add(new JPanel());
			btnItem2_Cancel_Middle.add(btnItem2_Cancel);
			btnItem2_Cancel_Middle.add(new JPanel());
			btnItem2_Cancel_Middle.add(new JPanel());
			btnItem2_Cancel_Middle.add(new JPanel());
			btnItem2_Cancel_Middle.add(new JPanel());
			// 創個小分Panel讓數量按鍵在同一列
			JPanel quantityPanel_Product2 = new JPanel(new GridLayout(1, 3));
			JPanel quantityBorderPanel_Product2 = new JPanel(new GridLayout(3, 1));
			quantityBorderPanel_Product2.add(new JPanel());
			quantityBorderPanel_Product2.add(quantityPanel_Product2);
			quantityBorderPanel_Product2.add(new JPanel());
			quantityPanel_Product2.add(btnItem2_minus);
			quantityPanel_Product2.add(quantityLabel_Item2);
			quantityPanel_Product2.add(btnItem2_add);
			// 創個分Panel放商品資訊
			product2Panel = new JPanel(new GridLayout(1, 7));
			product2Panel.add(picLabel_Item2);
			product2Panel.add(nameLabel_Item2);
			product2Panel.add(priceLabel_Item2);
			product2Panel.add(quantityBorderPanel_Product2);
			product2Panel.add(btnItem2_Cancel_Middle);
			picLabel_Item3 = new JLabel();
			// 單品3
			int newWidth_Item3 = 50;
			int newHeight_Item3 = 50;
			// 建立 ImageIcon
			ImageIcon imageIcon_Item3 = new ImageIcon("湯匙.jpg");
			// 獲取圖片並調整其大小以適應按鈕
			Image image_Item3 = imageIcon_Item3.getImage();
			Image scaledImage_Item3 = image_Item3.getScaledInstance(newWidth_Item3, newHeight_Item3,
					Image.SCALE_SMOOTH);
			// 將調整大小後的圖片設定為按鈕的圖示=
			ImageIcon scaledIcon_Item3 = new ImageIcon(scaledImage_Item3);
			picLabel_Item3.setIcon(scaledIcon_Item3);
			JLabel nameLabel_Item3 = new JLabel("湯匙");
			JLabel priceLabel_Item3 = new JLabel("$ 3");
			quantityLabel_Item3 = new JLabel(Integer.toString(product3_Quantity));
			// 設定框線
			quantityLabel_Item3.setBorder(border);
			JButton btnItem3_add = new JButton("+");
			btnItem3_add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					int quantity_Item3 = Integer.parseInt(quantityLabel_Item3.getText());
					quantity_Item3 += 1;
					product3_Quantity += 1;
					quantityLabel_Item3.setText(Integer.toString(quantity_Item3));
					totalPayment += 3;
					total.setText("總金額 : $ " + totalPayment);
					// data同步
					User.getInstance().setq3(1);
				}
			});
			JButton btnItem3_minus = new JButton("-");
			btnItem3_minus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					int quantity_Item3 = Integer.parseInt(quantityLabel_Item3.getText());
					if (quantity_Item3 - 1 >= 0) {
						quantity_Item3 -= 1;
						product3_Quantity -= 1;
						// data同步
						User.getInstance().setq3(-1);
					}
					quantityLabel_Item3.setText(Integer.toString(quantity_Item3));
					if (totalPayment - 3 >= 0) {
						totalPayment -= 3;
						total.setText("總金額 : $ " + totalPayment);
					}
					if (quantity_Item3 == 0) {
						productsPanel.remove(product3Panel);
						productsPanel.revalidate();
						productsPanel.repaint();
					}
				}
			});
			// 設定取消按鍵
			btnItem3_Cancel = new JButton("取消");
			JPanel btnItem3_Cancel_Middle = new JPanel(new GridLayout(3, 3));
			btnItem3_Cancel_Middle.add(new JPanel());
			btnItem3_Cancel_Middle.add(new JPanel());
			btnItem3_Cancel_Middle.add(new JPanel());
			btnItem3_Cancel_Middle.add(new JPanel());
			btnItem3_Cancel_Middle.add(btnItem3_Cancel);
			btnItem3_Cancel_Middle.add(new JPanel());
			btnItem3_Cancel_Middle.add(new JPanel());
			btnItem3_Cancel_Middle.add(new JPanel());
			btnItem3_Cancel_Middle.add(new JPanel());
			// 創個小分Panel讓數量按鍵在同一列
			JPanel quantityPanel_Product3 = new JPanel(new GridLayout(1, 3));
			JPanel quantityBorderPanel_Product3 = new JPanel(new GridLayout(3, 1));
			quantityBorderPanel_Product3.add(new JPanel());
			quantityBorderPanel_Product3.add(quantityPanel_Product3);
			quantityBorderPanel_Product3.add(new JPanel());
			quantityPanel_Product3.add(btnItem3_minus);
			quantityPanel_Product3.add(quantityLabel_Item3);
			quantityPanel_Product3.add(btnItem3_add);
			// 創個分Panel放商品資訊
			product3Panel = new JPanel(new GridLayout(1, 7));
			product3Panel.add(picLabel_Item3);
			product3Panel.add(nameLabel_Item3);
			product3Panel.add(priceLabel_Item3);
			product3Panel.add(quantityBorderPanel_Product3);
			product3Panel.add(btnItem3_Cancel_Middle);
			picLabel_Item4 = new JLabel();
			// 單品4
			int newWidth_Item4 = 50;
			int newHeight_Item4 = 50;
			// 建立 ImageIcon
			ImageIcon imageIcon_Item4 = new ImageIcon("筷.jpg");
			// 獲取圖片並調整其大小以適應按鈕
			Image image_Item4 = imageIcon_Item4.getImage();
			Image scaledImage_Item4 = image_Item4.getScaledInstance(newWidth_Item4, newHeight_Item4,
					Image.SCALE_SMOOTH);
			// 將調整大小後的圖片設定為按鈕的圖示=
			ImageIcon scaledIcon_Item4 = new ImageIcon(scaledImage_Item4);
			picLabel_Item4.setIcon(scaledIcon_Item4);
			JLabel nameLabel_Item4 = new JLabel("筷子");
			JLabel priceLabel_Item4 = new JLabel("$ 3");
			quantityLabel_Item4 = new JLabel(Integer.toString(product4_Quantity));
			// 設定框線
			quantityLabel_Item4.setBorder(border);
			JButton btnItem4_add = new JButton("+");
			btnItem4_add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					int quantity_Item4 = Integer.parseInt(quantityLabel_Item4.getText());
					quantity_Item4 += 1;
					product4_Quantity += 1;
					quantityLabel_Item4.setText(Integer.toString(quantity_Item4));
					totalPayment += 3;
					total.setText("總金額 : $ " + totalPayment);
					// data同步
					User.getInstance().setq4(1);
				}
			});
			JButton btnItem4_minus = new JButton("-");
			btnItem4_minus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					int quantity_Item4 = Integer.parseInt(quantityLabel_Item4.getText());
					if (quantity_Item4 - 1 >= 0) {
						quantity_Item4 -= 1;
						product4_Quantity -= 1;
						// data同步
						User.getInstance().setq4(-1);
					}
					quantityLabel_Item4.setText(Integer.toString(quantity_Item4));
					if (totalPayment - 3 >= 0) {
						totalPayment -= 3;
						total.setText("總金額 : $ " + totalPayment);
					}
					if (quantity_Item4 == 0) {
						productsPanel.remove(product4Panel);
						productsPanel.revalidate();
						productsPanel.repaint();
					}
				}
			});
			// 設定取消按鍵
			btnItem4_Cancel = new JButton("取消");
			JPanel btnItem4_Cancel_Middle = new JPanel(new GridLayout(3, 3));
			btnItem4_Cancel_Middle.add(new JPanel());
			btnItem4_Cancel_Middle.add(new JPanel());
			btnItem4_Cancel_Middle.add(new JPanel());
			btnItem4_Cancel_Middle.add(new JPanel());
			btnItem4_Cancel_Middle.add(btnItem4_Cancel);
			btnItem4_Cancel_Middle.add(new JPanel());
			btnItem4_Cancel_Middle.add(new JPanel());
			btnItem4_Cancel_Middle.add(new JPanel());
			btnItem4_Cancel_Middle.add(new JPanel());
			// 創個小分Panel讓數量按鍵在同一列
			JPanel quantityPanel_Product4 = new JPanel(new GridLayout(1, 3));
			JPanel quantityBorderPanel_Product4 = new JPanel(new GridLayout(3, 1));
			quantityBorderPanel_Product4.add(new JPanel());
			quantityBorderPanel_Product4.add(quantityPanel_Product4);
			quantityBorderPanel_Product4.add(new JPanel());
			quantityPanel_Product4.add(btnItem4_minus);
			quantityPanel_Product4.add(quantityLabel_Item4);
			quantityPanel_Product4.add(btnItem4_add);
			// 創個分Panel放商品資訊
			product4Panel = new JPanel(new GridLayout(1, 7));
			product4Panel.add(picLabel_Item4);
			product4Panel.add(nameLabel_Item4);
			product4Panel.add(priceLabel_Item4);
			product4Panel.add(quantityBorderPanel_Product4);
			product4Panel.add(btnItem4_Cancel_Middle);
			picLabel_Set1 = new JLabel();
			// 組合1
			int newWidth_Set1 = 50;
			int newHeight_Set1 = 50;
			// 建立 ImageIcon
			ImageIcon imageIcon_Set1 = new ImageIcon("筷勺.jpg");
			// 獲取圖片並調整其大小以適應按鈕
			Image image_Set1 = imageIcon_Set1.getImage();
			Image scaledImage_Set1 = image_Set1.getScaledInstance(newWidth_Set1, newHeight_Set1, Image.SCALE_SMOOTH);
			// 將調整大小後的圖片設定為按鈕的圖示=
			ImageIcon scaledIcon_Set1 = new ImageIcon(scaledImage_Set1);
			picLabel_Set1.setIcon(scaledIcon_Set1);
			JLabel nameLabel_Set1 = new JLabel("筷勺兩件組");
			JLabel priceLabel_Set1 = new JLabel("$ 5");
			quantityLabel_Set1 = new JLabel(Integer.toString(product5_Quantity));
			// 設定框線
			quantityLabel_Set1.setBorder(border);
			JButton btnSet1_add = new JButton("+");
			btnSet1_add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					int quantity_Set1 = Integer.parseInt(quantityLabel_Set1.getText());
					quantity_Set1 += 1;
					product5_Quantity += 1;
					quantityLabel_Set1.setText(Integer.toString(quantity_Set1));
					totalPayment += 5;
					total.setText("總金額 : $ " + totalPayment);
					// data同步
					User.getInstance().setq5(1);
				}
			});
			JButton btnSet1_minus = new JButton("-");
			btnSet1_minus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					int quantity_Set1 = Integer.parseInt(quantityLabel_Set1.getText());
					if (quantity_Set1 - 1 >= 0) {
						quantity_Set1 -= 1;
						product5_Quantity -= 1;
						// data同步
						User.getInstance().setq5(-1);
					}
					quantityLabel_Set1.setText(Integer.toString(quantity_Set1));
					if (totalPayment - 5 >= 0) {
						totalPayment -= 5;
						total.setText("總金額 : $ " + totalPayment);
					}
					if (quantity_Set1 == 0) {
						productsPanel.remove(product5Panel);
						productsPanel.revalidate();
						productsPanel.repaint();
					}
				}
			});
			// 設定取消按鍵
			btnSet1_Cancel = new JButton("取消");
			JPanel btnSet1_Cancel_Middle = new JPanel(new GridLayout(3, 3));
			btnSet1_Cancel_Middle.add(new JPanel());
			btnSet1_Cancel_Middle.add(new JPanel());
			btnSet1_Cancel_Middle.add(new JPanel());
			btnSet1_Cancel_Middle.add(new JPanel());
			btnSet1_Cancel_Middle.add(btnSet1_Cancel);
			btnSet1_Cancel_Middle.add(new JPanel());
			btnSet1_Cancel_Middle.add(new JPanel());
			btnSet1_Cancel_Middle.add(new JPanel());
			btnSet1_Cancel_Middle.add(new JPanel());
			JPanel quantityPanel_Product5 = new JPanel(new GridLayout(1, 3));
			JPanel quantityBorderPanel_Product5 = new JPanel(new GridLayout(3, 1));
			quantityBorderPanel_Product5.add(new JPanel());
			quantityBorderPanel_Product5.add(quantityPanel_Product5);
			quantityBorderPanel_Product5.add(new JPanel());
			quantityPanel_Product5.add(btnSet1_minus);
			quantityPanel_Product5.add(quantityLabel_Set1);
			quantityPanel_Product5.add(btnSet1_add);
			product5Panel = new JPanel(new GridLayout(1, 7));
			product5Panel.add(picLabel_Set1);
			product5Panel.add(nameLabel_Set1);
			product5Panel.add(priceLabel_Set1);
			product5Panel.add(quantityBorderPanel_Product5);
			product5Panel.add(btnSet1_Cancel_Middle);
			picLabel_Set2 = new JLabel();
			// 組合2
			int newWidth_Set2 = 50;
			int newHeight_Set2 = 38;
			// 建立 ImageIcon
			ImageIcon imageIcon_Set2 = new ImageIcon("碗筷勺.jpg");
			// 獲取圖片並調整其大小以適應按鈕
			Image image_Set2 = imageIcon_Set2.getImage();
			Image scaledImage_Set2 = image_Set2.getScaledInstance(newWidth_Set2, newHeight_Set2, Image.SCALE_SMOOTH);
			// 將調整大小後的圖片設定為按鈕的圖示=
			ImageIcon scaledIcon_Set2 = new ImageIcon(scaledImage_Set2);
			picLabel_Set2.setIcon(scaledIcon_Set2);
			JLabel nameLabel_Set2 = new JLabel("碗筷勺三件組");
			JLabel priceLabel_Set2 = new JLabel("$ 10");
			quantityLabel_Set2 = new JLabel(Integer.toString(product6_Quantity));
			// 設定框線
			quantityLabel_Set2.setBorder(border);
			JButton btnSet2_add = new JButton("+");
			btnSet2_add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					int quantity_Set2 = Integer.parseInt(quantityLabel_Set2.getText());
					quantity_Set2 += 1;
					product6_Quantity += 1;
					quantityLabel_Set2.setText(Integer.toString(quantity_Set2));
					totalPayment += 10;
					total.setText("總金額 : $ " + totalPayment);
					// data同步
					User.getInstance().setq6(1);
				}
			});
			JButton btnSet2_minus = new JButton("-");
			btnSet2_minus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					int quantity_Set2 = Integer.parseInt(quantityLabel_Set2.getText());
					if (quantity_Set2 - 1 >= 0) {
						quantity_Set2 -= 1;
						product6_Quantity -= 1;
						// data同步
						User.getInstance().setq6(-1);
					}
					quantityLabel_Set2.setText(Integer.toString(quantity_Set2));
					if (totalPayment - 10 >= 0) {
						totalPayment -= 10;
						total.setText("總金額 : $ " + totalPayment);
					}
					if (quantity_Set2 == 0) {
						productsPanel.remove(product6Panel);
						productsPanel.revalidate();
						productsPanel.repaint();
					}
				}
			});
			// 設定取消按鍵
			btnSet2_Cancel = new JButton("取消");
			JPanel btnSet2_Cancel_Middle = new JPanel(new GridLayout(3, 3));
			btnSet2_Cancel_Middle.add(new JPanel());
			btnSet2_Cancel_Middle.add(new JPanel());
			btnSet2_Cancel_Middle.add(new JPanel());
			btnSet2_Cancel_Middle.add(new JPanel());
			btnSet2_Cancel_Middle.add(btnSet2_Cancel);
			btnSet2_Cancel_Middle.add(new JPanel());
			btnSet2_Cancel_Middle.add(new JPanel());
			btnSet2_Cancel_Middle.add(new JPanel());
			btnSet2_Cancel_Middle.add(new JPanel());
			JPanel quantityPanel_Product6 = new JPanel(new GridLayout(1, 3));
			JPanel quantityBorderPanel_Product6 = new JPanel(new GridLayout(3, 1));
			quantityBorderPanel_Product6.add(new JPanel());
			quantityBorderPanel_Product6.add(quantityPanel_Product6);
			quantityBorderPanel_Product6.add(new JPanel());
			quantityPanel_Product6.add(btnSet2_minus);
			quantityPanel_Product6.add(quantityLabel_Set2);
			quantityPanel_Product6.add(btnSet2_add);
			product6Panel = new JPanel(new GridLayout(1, 7));
			product6Panel.add(picLabel_Set2);
			product6Panel.add(nameLabel_Set2);
			product6Panel.add(priceLabel_Set2);
			product6Panel.add(quantityBorderPanel_Product6);
			product6Panel.add(btnSet2_Cancel_Middle);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void productVisible() {
		btnItem1_Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				productsPanel.remove(product1Panel);
				productsPanel.revalidate();
				productsPanel.repaint();
				int quantity_Item1 = Integer.parseInt(quantityLabel_Item1.getText());
				totalPayment -= 4 * quantity_Item1;
				total.setText("總金額 : $ " + totalPayment);
				// 讓data歸零，不然取消後重新加入數量會出錯
				if ((User.getInstance().getq1() - q1) >= 0) {
					User.getInstance().setq1(-q1);
				}
				product1_Quantity = 0;
				quantityLabel_Item1.setText("0");
			}
		});
		btnItem2_Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				productsPanel.remove(product2Panel);
				productsPanel.revalidate();
				productsPanel.repaint();
				int quantity_Item2 = Integer.parseInt(quantityLabel_Item2.getText());
				totalPayment -= 9 * quantity_Item2;
				total.setText("總金額 : $ " + totalPayment);
				// 讓data歸零，不然取消後重新加入數量會出錯
				if ((User.getInstance().getq2() - q2) >= 0) {
					User.getInstance().setq2(-q2);
				}
				product2_Quantity = 0;
				quantityLabel_Item2.setText("0");
			}
		});
		btnItem3_Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				productsPanel.remove(product3Panel);
				productsPanel.revalidate();
				productsPanel.repaint();
				int quantity_Item3 = Integer.parseInt(quantityLabel_Item3.getText());
				totalPayment -= 3 * quantity_Item3;
				total.setText("總金額 : $ " + totalPayment);
				// 讓data歸零，不然取消後重新加入數量會出錯
				if ((User.getInstance().getq3() - q3) >= 0) {
					User.getInstance().setq3(-q3);
				}
				User.getInstance().setq3(-q3);
				product3_Quantity = 0;
				quantityLabel_Item3.setText("0");
			}
		});
		btnItem4_Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				productsPanel.remove(product4Panel);
				productsPanel.revalidate();
				productsPanel.repaint();
				int quantity_Item4 = Integer.parseInt(quantityLabel_Item4.getText());
				totalPayment -= 3 * quantity_Item4;
				total.setText("總金額 : $ " + totalPayment);
				// 讓data歸零，不然取消後重新加入數量會出錯
				if ((User.getInstance().getq4() - q4) >= 0) {
					User.getInstance().setq4(-q4);
				}
				product4_Quantity = 0;
				quantityLabel_Item4.setText("0");
			}
		});
		btnSet1_Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				productsPanel.remove(product5Panel);
				productsPanel.revalidate();
				productsPanel.repaint();
				int quantity_Set1 = Integer.parseInt(quantityLabel_Set1.getText());
				totalPayment -= 5 * quantity_Set1;
				total.setText("總金額 : $ " + totalPayment);
				// 讓data歸零，不然取消後重新加入數量會出錯
				if ((User.getInstance().getq5() - q5) >= 0) {
					User.getInstance().setq5(-q5);
				}
				product5_Quantity = 0;
				quantityLabel_Set1.setText("0");
			}
		});
		btnSet2_Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				productsPanel.remove(product6Panel);
				productsPanel.revalidate();
				productsPanel.repaint();
				int quantity_Set2 = Integer.parseInt(quantityLabel_Set2.getText());
				totalPayment -= 10 * quantity_Set2;
				total.setText("總金額 : $ " + totalPayment);
				// 讓data歸零，不然取消後重新加入數量會出錯
				if ((User.getInstance().getq6() - q6) >= 0) {
					User.getInstance().setq6(-q6);
				}
				product6_Quantity = 0;
				quantityLabel_Set2.setText("0");
			}
		});
	}

}
