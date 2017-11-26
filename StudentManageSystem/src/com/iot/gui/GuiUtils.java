package com.iot.gui;

import java.awt.Font;

import javax.swing.UIManager;

public class GuiUtils {
	
	// ��������, ���Զ�һ��ҳ��ȫ���������壬����Ҫÿһ���ؼ�������������
	public static void setUIFont()
	{
		Font f = new Font("Serif",Font.PLAIN,28);
		String   names[]={ "Label", "CheckBox", "PopupMenu","MenuItem", "CheckBoxMenuItem",
				"JRadioButtonMenuItem","ComboBox", "Button", "Tree", "ScrollPane",
				"TabbedPane", "EditorPane", "TitledBorder", "Menu", "TextArea",
				"OptionPane", "MenuBar", "ToolBar", "ToggleButton", "ToolTip",
				"ProgressBar", "TableHeader", "Panel", "List", "ColorChooser",
				"PasswordField","TextField", "Table", "Label", "Viewport",
				"RadioButtonMenuItem","RadioButton", "DesktopPane", "InternalFrame"
		}; 
		for (String item : names) {
			 UIManager.put(item+ ".font",f); 
		}
	}
}
