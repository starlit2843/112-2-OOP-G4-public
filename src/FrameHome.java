import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.util.ArrayList;

public class FrameHome extends JFrame {
    private JLabel user = new JLabel("Hello! " + User.getInstance().getName());
    private JPanel pWestBnGroup;
    private JButton bt_Rent,bt_Cart,bt_Map,bt_How,bt_LogOut,bt_Record,bt_PlaceHolder;
    private ArrayList<JButton> btList = new ArrayList<JButton>();

    private int control = 0;
    private JPanel pMain = new JPanel(new BorderLayout());
    
    public FrameHome() {
        cButton();
        cComponent();
        
        user.setFont(new Font("TimesRoman", Font.BOLD, 30));
        getContentPane().add(user,BorderLayout.NORTH);
        getContentPane().add(pWestBnGroup,BorderLayout.WEST);
        getContentPane().add(pMain,BorderLayout.CENTER);
        
        setTitle("Login");
        setSize(1000, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cLayOut();
        
    }

    private void cComponent() {
        pWestBnGroup = new JPanel();
        pWestBnGroup.setLayout(new GridLayout(7,1));
        for (JButton bt:btList) {
            bt.setPreferredSize(new Dimension(125,50));
            pWestBnGroup.add(bt);
        }
    }

    private void cButton() {
        bt_Rent = new JButton("Rent");
        bt_Rent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                
                    control = 0;
                    cLayOut();
                
            }
        });

        bt_Cart = new JButton("Cart");
        bt_Cart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                
                    control = 1;
                    cLayOut();
                
            }
        });

        bt_Record = new JButton("Record");
        bt_Record.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                
                    control = 2;
                    cLayOut();
                
            }
        });

        bt_Map = new JButton("Map");
        bt_Map.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                
                    control = 3;
                    cLayOut();
                
            }
        });

        bt_How = new JButton("How");
        bt_How.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                
                    control = 4;
                    cLayOut();
                
            }
        });

        bt_LogOut = new JButton("Log out");
        bt_LogOut.setBackground(new Color(250,210,210));
        
        bt_LogOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	bt_LogOut.setBackground(new Color(243,132,132));
                int reply = JOptionPane.showConfirmDialog(pMain,"Log out?","Logging out",JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    new FrameLogin();
                    setVisible(false);
                } else {
                	bt_LogOut.setBackground(new Color(250, 210, 210));
                    return;
                }
                
            }
        });
        
        bt_PlaceHolder = new JButton("");
        bt_PlaceHolder.setEnabled(false);

        btList.add(bt_Rent);btList.add(bt_Cart);btList.add(bt_Record);btList.add(bt_Map);btList.add(bt_How);
        btList.add(bt_PlaceHolder);
        btList.add(bt_LogOut);
    }

    private void cLayOut() {
        
        pMain.removeAll();
        
        btResetAll();
        switch (control) {
            case 0:
                JPanel panelRent = new PanelRent();
                pMain = panelRent;
                
                bt_Rent.setEnabled(false);
                
                break;
            case 1:
                JPanel panelCart = new PanelCart();
                pMain = panelCart;

                bt_Cart.setEnabled(false);

                break;
            case 2:
                JPanel panelRecord = new PanelRecord();
                pMain = panelRecord;

                bt_Record.setEnabled(false);

                break;
            case 3:
                JPanel panelMap = new PanelMap();
                pMain = panelMap;

                bt_Map.setEnabled(false);

                break;
            case 4:
                JPanel panelHow = new PanelHow();
                pMain = panelHow;

                bt_How.setEnabled(false);

                break;
        }
        
        pMain.revalidate();
        pMain.repaint();
        getContentPane().add(pMain,BorderLayout.CENTER);  
        
        setVisible(true);
    }

    private void btResetAll() {
        for (JButton bt:btList) {
            bt.setEnabled(true);
            bt_PlaceHolder.setEnabled(false);
        }
    }
}