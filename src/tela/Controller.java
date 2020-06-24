package tela;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Controller extends PlainDocument{
	
	@Override
	public void insertString(int offs,String str,javax.swing.text.AttributeSet attr) throws BadLocationException{
		super.insertString(offs, str.replaceAll("[^0-9]", ""), attr);
		}
	
	public void replace(int offs,String str,javax.swing.text.AttributeSet attr) throws BadLocationException{
			super.insertString(offs, str.replaceAll("[^0-9]", ""), attr);
		}
	
	

}
