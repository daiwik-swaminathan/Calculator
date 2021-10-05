import java.awt.*;import java.awt.event.*;import javax.swing.*;import java.util.*;
public class Calculator implements ActionListener{
	JTextField console;
	String calcText = "0";
	String equation = "";
	JButton ac;
	boolean isInt;
	boolean veryFirst = true;
	boolean firstSpot = true;
	int firstSymbolIndex;
	boolean first = true;
	String holder;
	int numCount;
	double num1, num2;
	JButton[] buttons = new JButton[16];
	String symbol = "";
	public static void main(String[] args){
		new Calculator();
	}
	public Calculator(){
		JFrame frame = new JFrame("Calculator");
		frame.setBounds(0, 0, 400, 422);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setLayout(null);
		frame.setFocusable(true);
		console = new JTextField();
		console.setFont(new Font("Helvetica", Font.BOLD, 50));
		console.setText(calcText);
		console.setBounds(0, 5, 400, 50);
		frame.add(console);
		JPanel panel = new JPanel();
		frame.getContentPane().setBackground(panel.getBackground());
		panel.setBounds(0, 55, 400, 291);
		panel.setLayout(new GridLayout(4,0));
		ac = new JButton("C");
		ac.setBounds(0, 341, 400, 59);
		ac.setFont(new Font("Helvetica", Font.BOLD, 50));
		int count = 1;
		for(int i=0; i<16; i++){
			buttons[i] = new JButton();
			buttons[i].setFont(new Font("Helvetica", Font.BOLD, 50));
			if(i==0) buttons[i].setText("+");
			else if(i==4) buttons[i].setText("-");
			else if(i==8) buttons[i].setText("x");
			else if(i==12) buttons[i].setText("/");
			else if(i==13) buttons[i].setText("0");
			else if(i==14) buttons[i].setText(".");
			else if(i==15) buttons[i].setText("=");
			else {
				buttons[i].setText(""+count);
				count++;
			}
			buttons[i].addActionListener(this);
			panel.add(buttons[i]);
		}
		ac.addActionListener(this);
		frame.add(ac);
		frame.add(panel);
		frame.setVisible(true);
	}
	public boolean check(){
		int temp = 0;
		for(int i=0; i<equation.length(); i++){
		if(i!=0){
		if(equation.charAt(i)=='+' || equation.charAt(i)=='-' || equation.charAt(i)=='x' || equation.charAt(i)=='/') {
		temp++;
		if(firstSpot) {
			firstSymbolIndex = i;
			firstSpot = false;
			symbol = ""+equation.charAt(i);
					  }
			}
			}
		}
		if(temp>1) return true;
		return false;
	}
	public boolean checkEquals(){
		int temp = 0;
		for(int i=0; i<equation.length(); i++){
		if(equation.charAt(i)=='+' || equation.charAt(i)=='-' || equation.charAt(i)=='x' || equation.charAt(i)=='/') {
		temp++;
		if(firstSpot) {
			firstSymbolIndex = i;
			firstSpot = false;
			symbol = ""+equation.charAt(i);
				 }
			}
		}
		if(temp>0) return true;
		return false;
	}
	public void checkInt(){
		int toCheck = (int)num1;
		if(toCheck==num1) isInt = true;
		else isInt = false;
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="." && veryFirst) return;
		if(e.getActionCommand()=="+" && veryFirst) return;
		if(e.getActionCommand()=="-" && veryFirst) return;
		if(e.getActionCommand()=="x" && veryFirst) return;
		if(e.getActionCommand()=="/" && veryFirst) return;
		veryFirst = false;
		if(e.getActionCommand()=="." && equation.charAt(equation.length()-1)=='.') return;
		if(e.getActionCommand()=="+" && equation.charAt(equation.length()-1)=='+') return;
		if(e.getActionCommand()=="-" && equation.charAt(equation.length()-1)=='-') return;
		if(e.getActionCommand()=="x" && equation.charAt(equation.length()-1)=='x') return;
		if(e.getActionCommand()=="/" && equation.charAt(equation.length()-1)=='/') return;
		if(e.getSource()==ac){
			equation = "";
			calcText = "0";
			console.setText(calcText);
			first = true;
			veryFirst = true;
			return;
		}
		if(e.getSource()==buttons[15]) {
			if(checkEquals()==true){
			num1 = Double.parseDouble(equation.substring(0, firstSymbolIndex));
			num2 = Double.parseDouble(equation.substring(firstSymbolIndex+1));
			if(symbol.equals("+")) num1 = num1+num2;
			else if(symbol.equals("-")) num1 = num1-num2;
			else if(symbol.equals("x")) num1 = num1*num2;
			else if(symbol.equals("/")) num1 = num1/num2;
			checkInt();
			if(isInt)calcText = ""+(int)num1;
			else calcText = ""+num1;
			console.setText(calcText);
			console.setCaretPosition(0);
			first = true;
			firstSpot = true;
			return;
		}
		}
		else{
		equation += e.getActionCommand();
		if(check()==true){
			holder = ""+equation.charAt(equation.length()-1);
			equation = equation.substring(0, equation.length()-1);
			num1 = Double.parseDouble(equation.substring(0, firstSymbolIndex));
			num2 = Double.parseDouble(equation.substring(firstSymbolIndex+1));
			if(symbol.equals("+")) num1 = num1+num2;
			else if(symbol.equals("-")) num1 = num1-num2;
			else if(symbol.equals("x")) num1 = num1*num2;
			else if(symbol.equals("/")) num1 = num1/num2;
			checkInt();
			if(isInt)calcText = ""+(int)num1;
			else calcText = ""+num1;
			console.setText(calcText);
			console.setCaretPosition(0);
			equation = calcText+holder;
			first = true;
			firstSpot = true;
			return;
		}
		if(first && e.getActionCommand().equals(".")==false){
			calcText = e.getActionCommand();
			first = false;
			console.setText(calcText);
		}
		else {
			if(e.getActionCommand().equals("+")==false && e.getActionCommand().equals("-")==false && e.getActionCommand().equals("x")==false && e.getActionCommand().equals("/")==false)calcText += e.getActionCommand();
			else first = true;
			if(e.getActionCommand().equals(".")) first = false;
			console.setText(calcText);
		}
		}
	}
}