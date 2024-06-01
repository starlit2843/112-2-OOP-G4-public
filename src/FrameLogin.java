import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class FrameLogin extends JFrame {
    private JTextField username_input = new JTextField(20);
    private JPasswordField password_input = new JPasswordField(20);
    private JPanel pUser,pPass,pInput,pButton,pPlaceHolder;
    private JButton bt_SignUp,bt_LogIn;
    private JCheckBox cb_pVisible;
    
    public FrameLogin() {   
        setTitle("Login");
        setSize(400, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cLayOut();
    }  
    
    private void cComponent() {
        pUser = new JPanel(new FlowLayout(FlowLayout.CENTER,3,0));
        pUser.add(new JLabel("Username: "));
        pUser.add(username_input);

        pPass = new JPanel(new FlowLayout(FlowLayout.CENTER,4,0));
        pPass.add(new JLabel("Password: "));
        pPass.add(password_input);
        
        pButton = new JPanel(new GridLayout(1,2,10,6));
        pButton.add(bt_SignUp);
        pButton.add(bt_LogIn);
        
        pButton.setPreferredSize(new Dimension(200,20));
        
        pPlaceHolder = new JPanel();
        
        pInput = new JPanel();
        pInput.add(pUser);
        pInput.add(pPass);
        pInput.add(pButton);
        pInput.add(cb_pVisible);
        
    }
    
    private void cButton() {
        bt_SignUp = new JButton("Sign up");
        bt_SignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String username = username_input.getText();
                String password = new String(password_input.getPassword());
                
                try {
                    
                    User.getInstance().signUp(username,password);
                    JOptionPane.showMessageDialog(null, "Sign up Successfully, Press Log in again to login!", "Success",JOptionPane.INFORMATION_MESSAGE);

                } catch(ShortPasswordError e) {
					JOptionPane.showMessageDialog(pPlaceHolder, e,
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				} catch (AlreadyExistError e) {
					JOptionPane.showMessageDialog(pPlaceHolder, e,
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
            }
        });
        
        bt_LogIn = new JButton("Log in");
        bt_LogIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String username = username_input.getText();
                String password = new String(password_input.getPassword());
                
                User.getInstance().setName(username);
                try {
                    User.getInstance().Login(username, password);
                    new FrameHome();

                } catch (WrongDataError e) {
                    JOptionPane.showMessageDialog(pPlaceHolder, e,
							"Error", JOptionPane.ERROR_MESSAGE);
					return;
                }
                
                setVisible(false);
            }
        });
    }

    private void cCheckBox() {
        cb_pVisible = new JCheckBox("Show password");
        password_input.setEchoChar('*');

        cb_pVisible.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (cb_pVisible.isSelected()) {
                    password_input.setEchoChar((char)0);
                } else {
                    password_input.setEchoChar('*');
                }
            }
        });

    }

    private void cLayOut() {
        cButton();
        cCheckBox();
        cComponent();
        
        setLayout(new BorderLayout(30,80));
        getContentPane().add(pPlaceHolder,BorderLayout.NORTH);
        getContentPane().add(pInput,BorderLayout.CENTER);

        setVisible(true);
    }
}