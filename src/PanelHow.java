import javax.swing.*;
import java.awt.*;

public class PanelHow extends PanelBase{
	
    protected String getFirstBtName() {
        return "Rent";
    }

    protected String getSecondBtName() {
        return "Return";
    }

    protected String getFirstIconName() {
    	return "租借步驟.png";
    }
    
    protected String getSecondIconName() {
    	return "歸還步驟.png";
    }
    
    protected int getIconLength() {
    	return 585;
    }
    protected void updateLayout() {
        switch (control) {
            case 0: 
        		pMain.add(imageLabel_First);
                break;
            case 1:
            	pMain.add(imageLabel_Second);
                break;
        }
        //pMain.add(new JTextArea(Integer.toString(control)));
    }
    
 
}