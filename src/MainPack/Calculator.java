package MainPack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigInteger;
import java.util.ArrayList;


public class Calculator extends JFrame implements ActionListener, MouseListener {
    JButton menuButton;
    JLabel titleLabel, equationLabel, answerLabel;
    JButton hexButton, decButton, octButton, binButton;
    JButton placeHolderButton, wordButton, msButton, mButton;
    JButton lshButton, rshButton, orButton, xOrButton, notButton, andButton;					// row 0
    JButton secondButton, modButton, clearEButton, clearButton, backButton, divButton;			// row 1
    JButton numAButton, numBButton, num7Button, num8Button, num9Button, multButton;				// row 2
    JButton numCButton, numDButton, num4Button, num5Button, num6Button, minusButton;			// row 3
    JButton numEButton, numFButton, num1Button, num2Button, num3Button, pluseButton;			// row 4
    JButton openParButton, closeParButton, signButton, num0Button, periodButton, equalButton;	// row 5
    JPanel mainPanel;
    GridBagConstraints c;

    final int HEXADECIMAL = 16;
    final int DECIMAL = 10;
    final int OCTAL = 8;
    final int BINARY = 2;

    final int BYTE_SIZE = 8;
    final int WORD_SIZE = 16;
    final int DWORD_SIZE = 32;
    final int QWORD_SIZE = 64;

    int activeDataSize = QWORD_SIZE;

    ArrayList<String> displayEquation = new ArrayList<String>();
    ArrayList<ArrayList<String>> equationList = new ArrayList<ArrayList<String>>();
    int activeEquation = 0;
    int activeBase = DECIMAL;
    int numOpenPar = 0;
    String currentValue = "0";
    boolean beginNewNumber = true;
    boolean closeParenthesis = false;
    boolean afterEqualButtonPressed = false;
    boolean numberInserted = false;
    boolean secondButtonToggle = false;

    public Calculator() {

        equationList.add(new ArrayList<String>());

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(70, 70, 70));

        menuButton = new JButton("menu");
        titleLabel = new JLabel("Programmer");

        equationLabel = new JLabel("", SwingConstants.RIGHT);
        answerLabel = new JLabel("0", SwingConstants.RIGHT);

        hexButton = new JButton("HEX  0");
        decButton = new JButton("DEC  0");
        octButton = new JButton("OCT  0");
        binButton = new JButton("BIN   0");

        placeHolderButton = new JButton("Place holder");
        wordButton = new JButton("QWORD");
        msButton = new JButton("MS");
        mButton = new JButton("M");

        lshButton = new JButton("shr");
        rshButton = new JButton("shl");
        orButton = new JButton("or");
        xOrButton = new JButton("xor");
        notButton = new JButton("not");
        andButton = new JButton("and");

        secondButton = new JButton("2nd");
        modButton = new JButton("Mod");
        clearEButton = new JButton("CE");
        clearButton = new JButton("C");
        backButton = new JButton("\u232b");
        divButton = new JButton("\u00f7");

        numAButton = new JButton("A");
        numBButton = new JButton("B");
        num7Button = new JButton("7");
        num8Button = new JButton("8");
        num9Button = new JButton("9");
        multButton = new JButton("x");

        numCButton = new JButton("C");
        numDButton = new JButton("D");
        num4Button = new JButton("4");
        num5Button = new JButton("5");
        num6Button = new JButton("6");
        minusButton = new JButton("-");

        numEButton = new JButton("E");
        numFButton = new JButton("F");
        num1Button = new JButton("1");
        num2Button = new JButton("2");
        num3Button = new JButton("3");
        pluseButton = new JButton("+");

        openParButton = new JButton("(");
        closeParButton = new JButton(")");
        signButton = new JButton("+/-");
        num0Button = new JButton("0");
        periodButton = new JButton(".");
        equalButton = new JButton("=");


        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        c.gridy = 0;
        c.gridx = 0;
        c.weightx = 1;
        c.weighty = .3;
        mainPanel.add(menuButton, c);
        menuButton.setFocusPainted(false);
        menuButton.setContentAreaFilled(false);
        menuButton.setBorderPainted(false);
        menuButton.setOpaque(false);
        menuButton.setForeground(Color.white);
        menuButton.setBackground(Color.gray);
        c.gridwidth = 6;
        c.gridx = 1;
        mainPanel.add(titleLabel, c);
        titleLabel.setForeground(Color.white);

        c.gridy = 1;
        c.insets = new Insets(0, 0, 0, 10);
        mainPanel.add(equationLabel, c);
        equationLabel.setForeground(Color.white);

        c.gridy = 2;
        c.gridx = 0;
        c.weighty = 1;
        c.gridwidth = 7;
        mainPanel.add(answerLabel, c);
        answerLabel.setForeground(Color.white);

        c.gridy = 3;
        c.ipady = 0;
        c.weighty = .2;
        c.insets = new Insets(0, 0, 0, 0);
        mainPanel.add(hexButton, c);
        hexButton.setFocusPainted(false);
        hexButton.setContentAreaFilled(false);
        hexButton.setBorderPainted(false);
        hexButton.setOpaque(false);
        hexButton.setForeground(Color.white);
        hexButton.setBackground(Color.gray);
        hexButton.setHorizontalAlignment(SwingConstants.LEFT);

        c.gridy = 4;
        mainPanel.add(decButton, c);
        decButton.setFocusPainted(false);
        decButton.setContentAreaFilled(false);
        decButton.setBorderPainted(false);
        decButton.setOpaque(false);
        decButton.setForeground(Color.white);
        decButton.setBackground(Color.gray);
        decButton.setHorizontalAlignment(SwingConstants.LEFT);

        c.gridy = 5;
        mainPanel.add(octButton, c);
        octButton.setFocusPainted(false);
        octButton.setContentAreaFilled(false);
        octButton.setBorderPainted(false);
        octButton.setOpaque(false);
        octButton.setForeground(Color.white);
        octButton.setBackground(Color.gray);
        octButton.setHorizontalAlignment(SwingConstants.LEFT);

        c.gridy = 6;
        mainPanel.add(binButton, c);
        binButton.setFocusPainted(false);
        binButton.setContentAreaFilled(false);
        binButton.setBorderPainted(false);
        binButton.setOpaque(false);
        binButton.setForeground(Color.white);
        binButton.setBackground(Color.gray);
        binButton.setHorizontalAlignment(SwingConstants.LEFT);

        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(1, 1, 1, 1);
        c.gridy = 7;
        c.gridwidth = 2;
        mainPanel.add(placeHolderButton, c);
        setDefaultButtonStyle(placeHolderButton);
        c.gridx = 2;
        mainPanel.add(wordButton, c);
        setDefaultButtonStyle(wordButton);
        c.gridwidth = 1;
        c.gridx = 4;
        mainPanel.add(msButton, c);
        setDefaultButtonStyle(msButton);
        c.gridx = 5;
        mainPanel.add(mButton, c);
        setDefaultButtonStyle(mButton);

        c.gridy = 8;
        c.weighty = .4;
        c.gridwidth = 1;
        c.gridx = 0;
        mainPanel.add(lshButton, c);
        setDefaultButtonStyle(lshButton);
        c.gridx = 1;
        mainPanel.add(rshButton, c);
        setDefaultButtonStyle(rshButton);
        c.gridx = 2;
        mainPanel.add(orButton, c);
        setDefaultButtonStyle(orButton);
        c.gridx = 3;
        mainPanel.add(xOrButton, c);
        setDefaultButtonStyle(xOrButton);
        c.gridx = 4;
        mainPanel.add(notButton, c);
        setDefaultButtonStyle(notButton);
        c.gridx = 5;
        mainPanel.add(andButton, c);
        setDefaultButtonStyle(andButton);

        c.gridy = 9;
        c.gridx = 0;
        mainPanel.add(secondButton, c);
        setDefaultButtonStyle(secondButton);
        c.gridx = 1;
        mainPanel.add(modButton, c);
        setDefaultButtonStyle(modButton);
        c.gridx = 2;
        mainPanel.add(clearEButton, c);
        setDefaultButtonStyle(clearEButton);
        c.gridx = 3;
        mainPanel.add(clearButton, c);
        setDefaultButtonStyle(clearButton);
        c.gridx = 4;
        mainPanel.add(backButton, c);
        setDefaultButtonStyle(backButton);
        c.gridx = 5;
        mainPanel.add(divButton, c);
        setDefaultButtonStyle(divButton);

        c.gridy = 10;
        c.gridx = 0;
        mainPanel.add(numAButton, c);
        setNumberButtonStyle(numAButton);
        numAButton.setForeground(new Color(45, 45, 45));
        c.gridx = 1;
        mainPanel.add(numBButton, c);
        setNumberButtonStyle(numBButton);
        numBButton.setForeground(new Color(45, 45, 45));
        c.gridx = 2;
        mainPanel.add(num7Button, c);
        setNumberButtonStyle(num7Button);
        c.gridx = 3;
        mainPanel.add(num8Button, c);
        setNumberButtonStyle(num8Button);
        c.gridx = 4;
        mainPanel.add(num9Button, c);
        setNumberButtonStyle(num9Button);
        c.gridx = 5;
        mainPanel.add(multButton, c);
        setDefaultButtonStyle(multButton);

        c.gridy = 11;
        c.gridx = 0;
        mainPanel.add(numCButton, c);
        setNumberButtonStyle(numCButton);
        numCButton.setForeground(new Color(45, 45, 45));
        c.gridx = 1;
        mainPanel.add(numDButton, c);
        setNumberButtonStyle(numDButton);
        numDButton.setForeground(new Color(45, 45, 45));
        c.gridx = 2;
        mainPanel.add(num4Button, c);
        setNumberButtonStyle(num4Button);
        c.gridx = 3;
        mainPanel.add(num5Button, c);
        setNumberButtonStyle(num5Button);
        c.gridx = 4;
        mainPanel.add(num6Button, c);
        setNumberButtonStyle(num6Button);
        c.gridx = 5;
        mainPanel.add(minusButton, c);
        setDefaultButtonStyle(minusButton);

        c.gridy = 12;
        c.gridx = 0;
        mainPanel.add(numEButton, c);
        setNumberButtonStyle(numEButton);
        numEButton.setForeground(new Color(45, 45, 45));
        c.gridx = 1;
        mainPanel.add(numFButton, c);
        setNumberButtonStyle(numFButton);
        numFButton.setForeground(new Color(45, 45, 45));
        c.gridx = 2;
        mainPanel.add(num1Button, c);
        setNumberButtonStyle(num1Button);
        c.gridx = 3;
        mainPanel.add(num2Button, c);
        setNumberButtonStyle(num2Button);
        c.gridx = 4;
        mainPanel.add(num3Button, c);
        setNumberButtonStyle(num3Button);
        c.gridx = 5;
        mainPanel.add(pluseButton, c);
        setDefaultButtonStyle(pluseButton);

        c.gridy = 13;
        c.gridx = 0;
        mainPanel.add(openParButton, c);
        setDefaultButtonStyle(openParButton);
        c.gridx = 1;
        mainPanel.add(closeParButton, c);
        setDefaultButtonStyle(closeParButton);
        c.gridx = 2;
        mainPanel.add(signButton, c);
        setDefaultButtonStyle(signButton);
        c.gridx = 3;
        mainPanel.add(num0Button, c);
        setNumberButtonStyle(num0Button);
        c.gridx = 4;
        mainPanel.add(periodButton, c);
        setDefaultButtonStyle(periodButton);
        c.gridx = 5;
        mainPanel.add(equalButton, c);
        setDefaultButtonStyle(equalButton);

        add(mainPanel, BorderLayout.CENTER);

        hexButton.addActionListener(this);
        decButton.addActionListener(this);
        octButton.addActionListener(this);
        binButton.addActionListener(this);

        num0Button.addActionListener(this);
        num1Button.addActionListener(this);
        num2Button.addActionListener(this);
        num3Button.addActionListener(this);
        num4Button.addActionListener(this);
        num5Button.addActionListener(this);
        num6Button.addActionListener(this);
        num7Button.addActionListener(this);
        num8Button.addActionListener(this);
        num9Button.addActionListener(this);
        numAButton.addActionListener(this);
        numBButton.addActionListener(this);
        numCButton.addActionListener(this);
        numDButton.addActionListener(this);
        numEButton.addActionListener(this);
        numFButton.addActionListener(this);

        pluseButton.addActionListener(this);
        minusButton.addActionListener(this);
        multButton.addActionListener(this);
        divButton.addActionListener(this);
        modButton.addActionListener(this);
        openParButton.addActionListener(this);
        closeParButton.addActionListener(this);
        equalButton.addActionListener(this);
        backButton.addActionListener(this);
        clearButton.addActionListener(this);
        clearEButton.addActionListener(this);
        signButton.addActionListener(this);
        wordButton.addActionListener(this);
        secondButton.addActionListener(this);

        menuButton.addMouseListener(this);
        hexButton.addMouseListener(this);
        decButton.addMouseListener(this);
        octButton.addMouseListener(this);
        binButton.addMouseListener(this);
    }

    public void setDefaultButtonStyle(JButton button){

        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(true);
        button.setOpaque(true);
        button.setForeground(Color.white);
        button.setBackground(new Color(45, 45, 45));
    }

    public void setNumberButtonStyle(JButton button) {
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(true);
        button.setOpaque(true);
        button.setForeground(Color.white);
        button.setBackground(new Color(15, 15, 15));
    }

    public void mouseClicked (MouseEvent e) {
    }

    public void mousePressed (MouseEvent e)  {
    }

    public void mouseEntered (MouseEvent e){
        if(e.getSource() == hexButton) {
            hexButton.setOpaque(true);
        }

        if(e.getSource() == decButton) {
            decButton.setOpaque(true);
        }

        if(e.getSource() == octButton){
            octButton.setOpaque(true);
        }

        if(e.getSource() == binButton){
            binButton.setOpaque(true);
        }

        if(e.getSource() == menuButton){
            menuButton.setOpaque(true);
        }
    }

    public void mouseExited (MouseEvent e){
        if(e.getSource() == hexButton) {
            hexButton.setOpaque(false);
        }

        if(e.getSource() == decButton) {
            decButton.setOpaque(false);
        }

        if(e.getSource() == octButton){
            octButton.setOpaque(false);
        }

        if(e.getSource() == binButton){
            binButton.setOpaque(false);
        }

        if(e.getSource() == menuButton){
            menuButton.setOpaque(false);
        }
    }

    public void mouseReleased (MouseEvent e){
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == num0Button) {
            updateNumber("0");
        }

        if(e.getSource() == num1Button) {
            updateNumber("1");
        }

        if(activeBase >= OCTAL) {
            if(e.getSource() == num2Button){
                updateNumber("2");
            }

            if(e.getSource() == num3Button){
                updateNumber("3");
            }

            if(e.getSource() == num4Button){
                updateNumber("4");
            }

            if(e.getSource() == num5Button){
                updateNumber("5");
            }

            if(e.getSource() == num6Button){
                updateNumber("6");
            }

            if(e.getSource() == num7Button){
                updateNumber("7");
            }
        }

        if(activeBase >= DECIMAL){
            if(e.getSource() == num8Button){
                updateNumber("8");
            }

            if(e.getSource() == num9Button){
                updateNumber("9");
            }
        }

        if(activeBase >= HEXADECIMAL){
            if(e.getSource() == numAButton){
                updateNumber("A");
            }

            if(e.getSource() == numBButton){
                updateNumber("B");
            }

            if(e.getSource() == numCButton) {
                updateNumber("C");
            }

            if(e.getSource() == numDButton){
                updateNumber("D");
            }

            if(e.getSource() == numEButton){
                updateNumber("E");
            }

            if(e.getSource() == numFButton){

                updateNumber("F");
            }
        }

        if(e.getSource() == pluseButton && (!beginNewNumber || afterEqualButtonPressed)){

            performOperation("+");
        }

        if(e.getSource() == minusButton && (!beginNewNumber || afterEqualButtonPressed)){
            performOperation("-");
        }

        if(e.getSource() == multButton && (!beginNewNumber || afterEqualButtonPressed)){
            higherOrderOperation();
            performOperation("x");
        }

        if(e.getSource() == divButton && (!beginNewNumber || afterEqualButtonPressed)){
            higherOrderOperation();
            performOperation("\u00f7");
        }

        if(e.getSource() == modButton && (!beginNewNumber || afterEqualButtonPressed)){
            higherOrderOperation();
            performOperation("Mod");
        }

        if(e.getSource() == openParButton){
            equationList.get(activeEquation).add("(");
            higherOrderOperation();

            displayEquation.add("(");
            equationLabel.setText(equationLabel.getText() + "(");
            numOpenPar++;
        }

        if(e.getSource() == closeParButton && numOpenPar > 0 && !beginNewNumber){
            if(numberInserted) {
                displayEquation.add(currentValue);
                equationLabel.setText(equationLabel.getText() + currentValue);
                equationList.get(activeEquation).add(baseConversion(currentValue, activeBase, DECIMAL));

                numberInserted = false;
            }

            displayEquation.add(")");
            equationLabel.setText(equationLabel.getText() + ")");

            closeParenthesis = true;
            resolveEquation();

            numOpenPar--;
        }

        if(e.getSource() == equalButton && !beginNewNumber) {
            equationList.get(activeEquation).add(baseConversion(currentValue, activeBase, DECIMAL));
            while(activeEquation > 0 || equationList.get(activeEquation).size() > 1){
                closeParenthesis = true;
                resolveEquation();
            }
            equationLabel.setText("");
            displayEquation.clear();
            equationList.clear();
            equationList.add(new ArrayList<String>());
            beginNewNumber = true;
            afterEqualButtonPressed = true;
        }

        if(e.getSource() == backButton && !beginNewNumber) {
            String temp = currentValue.substring(0, currentValue.length() - 1);
            if(temp.length() == 0){
                temp = "0";
            }

            beginNewNumber = true;
            updateNumber(temp);

            if(currentValue == "0")
                beginNewNumber = true;
        }

        if(e.getSource() == clearEButton && !beginNewNumber){
            beginNewNumber = true;
            updateNumber("0");

            beginNewNumber = true;
        }

        if(e.getSource() == clearButton){
            beginNewNumber = true;
            updateNumber("0");

            equationLabel.setText("");
            displayEquation.clear();
            equationList.clear();
            equationList.add(new ArrayList<String>());
            beginNewNumber = true;
            activeEquation = 0;
            numOpenPar = 0;
            closeParenthesis = false;
            afterEqualButtonPressed = false;
            numberInserted = false;
        }

        if(e.getSource() == signButton && !beginNewNumber){
            long temp = Long.parseLong(baseConversion(currentValue, activeBase, DECIMAL));
            temp = 0 - temp;
            beginNewNumber = true;
            updateNumber(baseConversion("" + temp, DECIMAL, activeBase));
        }

        if(e.getSource() == wordButton){
            if(activeDataSize == QWORD_SIZE){
                activeDataSize = DWORD_SIZE;
                wordButton.setText("DWORD");
            }
            else if(activeDataSize == DWORD_SIZE){
                activeDataSize = WORD_SIZE;
                wordButton.setText("WORD");
            }
            else if(activeDataSize == WORD_SIZE){
                activeDataSize = BYTE_SIZE;
                wordButton.setText("BYTE");
            }
            else{
                activeDataSize = QWORD_SIZE;
                wordButton.setText("    ");
            }

            currentValue = dataSizeOverflow(currentValue);
            displayValue(currentValue);
        }

        if(e.getSource() == hexButton) {
            updateBase(HEXADECIMAL);
            numFButton.setForeground(Color.white);
            numEButton.setForeground(Color.white);
            numDButton.setForeground(Color.white);
            numCButton.setForeground(Color.white);
            numBButton.setForeground(Color.white);
            numAButton.setForeground(Color.white);
            num9Button.setForeground(Color.white);
            num8Button.setForeground(Color.white);
            num7Button.setForeground(Color.white);
            num6Button.setForeground(Color.white);
            num5Button.setForeground(Color.white);
            num4Button.setForeground(Color.white);
            num3Button.setForeground(Color.white);
            num2Button.setForeground(Color.white);
        }

        if(e.getSource() == decButton){
            updateBase(DECIMAL);
            numFButton.setForeground(new Color(45, 45, 45));
            numEButton.setForeground(new Color(45, 45, 45));
            numDButton.setForeground(new Color(45, 45, 45));
            numCButton.setForeground(new Color(45, 45, 45));
            numBButton.setForeground(new Color(45, 45, 45));
            numAButton.setForeground(new Color(45, 45, 45));
            num9Button.setForeground(Color.white);
            num8Button.setForeground(Color.white);
            num7Button.setForeground(Color.white);
            num6Button.setForeground(Color.white);
            num5Button.setForeground(Color.white);
            num4Button.setForeground(Color.white);
            num3Button.setForeground(Color.white);
            num2Button.setForeground(Color.white);
        }

        if(e.getSource() == octButton){
            updateBase(OCTAL);
            numFButton.setForeground(new Color(45, 45, 45));
            numEButton.setForeground(new Color(45, 45, 45));
            numDButton.setForeground(new Color(45, 45, 45));
            numCButton.setForeground(new Color(45, 45, 45));
            numBButton.setForeground(new Color(45, 45, 45));
            numAButton.setForeground(new Color(45, 45, 45));
            num9Button.setForeground(new Color(45, 45, 45));
            num8Button.setForeground(new Color(45, 45, 45));
            num7Button.setForeground(Color.white);
            num6Button.setForeground(Color.white);
            num5Button.setForeground(Color.white);
            num4Button.setForeground(Color.white);
            num3Button.setForeground(Color.white);
            num2Button.setForeground(Color.white);
        }

        if(e.getSource() == binButton){
            updateBase(BINARY);
            numFButton.setForeground(new Color(45, 45, 45));
            numEButton.setForeground(new Color(45, 45, 45));
            numDButton.setForeground(new Color(45, 45, 45));
            numCButton.setForeground(new Color(45, 45, 45));
            numBButton.setForeground(new Color(45, 45, 45));
            numAButton.setForeground(new Color(45, 45, 45));
            num9Button.setForeground(new Color(45, 45, 45));
            num8Button.setForeground(new Color(45, 45, 45));
            num7Button.setForeground(new Color(45, 45, 45));
            num6Button.setForeground(new Color(45, 45, 45));
            num5Button.setForeground(new Color(45, 45, 45));
            num4Button.setForeground(new Color(45, 45, 45));
            num3Button.setForeground(new Color(45, 45, 45));
            num2Button.setForeground(new Color(45, 45, 45));
        }

        if(e.getSource() == secondButton){
            if(!secondButtonToggle){
                secondButtonToggle = true;
                lshButton.setText("RoL");
                rshButton.setText("RoR");
            }
            else{
                secondButtonToggle = false;
                lshButton.setText("Lsh");
                rshButton.setText("Rsh");
            }
        }
    }

    public void updateNumber(String num)
    {
        String tempValue;
        if(beginNewNumber){
            beginNewNumber = false;
            tempValue = num;
        }
        else{
            tempValue = currentValue + num;
        }

        if(isWithinDataSize(tempValue)){
            currentValue = tempValue;

            displayValue(currentValue);

            numberInserted = true;
        }
    }

    public void performOperation(String op){
        beginNewNumber = true;
        afterEqualButtonPressed = false;
        if(displayEquation.size() == 0 || (displayEquation.size() > 0 && displayEquation.get(displayEquation.size() - 1) != ")")){
            equationLabel.setText(equationLabel.getText() + currentValue);
            displayEquation.add(currentValue);
            equationList.get(activeEquation).add(baseConversion(currentValue, activeBase, DECIMAL));
        }
        if((op == "x" || op == "\u00f7") && displayEquation.size() > 0 && displayEquation.get(displayEquation.size() - 1) == ")"){
            equationList.get(activeEquation - 1).remove(equationList.get(activeEquation - 1).size() - 1);
            equationList.get(activeEquation).add(currentValue);
        }
        equationLabel.setText(equationLabel.getText() + ' ' + op + ' ');
        displayEquation.add(' ' + op + ' ');
        resolveEquation();
        equationList.get(activeEquation).add(op);
    }

    public void higherOrderOperation(){
        equationList.add(new ArrayList<String>());
        activeEquation++;
    }

    public void updateBase(int newBase){
        equationLabel.setText("");
        for(int i = 0; i < displayEquation.size(); i++){
            try{
                displayEquation.set(i, baseConversion(displayEquation.get(i), activeBase, newBase));
            }catch(Exception e) {
            }
            equationLabel.setText(equationLabel.getText() + displayEquation.get(i));
        }

        currentValue = baseConversion(currentValue, activeBase, newBase);
        activeBase = newBase;
        answerLabel.setText(formatForDisplay(currentValue, activeBase));
    }
    public String baseConversion(String value, int fromBase, int toBase){
        if(fromBase == toBase){
            return value;
        }
        return Long.toString(Long.parseLong(value, fromBase), toBase).toUpperCase();
    }

    public void resolveEquation() {
        int i = 0;
        while(i < equationList.get(activeEquation).size()){
            Long result;

            if(equationList.get(activeEquation).get(i) == "x"){
                result = Long.parseLong(equationList.get(activeEquation).get(i-1)) * Long.parseLong(equationList.get(activeEquation).get(i+1));
            }
            else if(equationList.get(activeEquation).get(i) == "\u00f7") {
                result = Long.parseLong(equationList.get(activeEquation).get(i-1)) / Long.parseLong(equationList.get(activeEquation).get(i+1));
            }
            else if(equationList.get(activeEquation).get(i) == "Mod"){
                result = Long.parseLong(equationList.get(activeEquation).get(i-1)) % Long.parseLong(equationList.get(activeEquation).get(i+1));
            }
            else if(equationList.get(activeEquation).get(i) == "+"){
                result = Long.parseLong(equationList.get(activeEquation).get(i-1)) + Long.parseLong(equationList.get(activeEquation).get(i+1));
            }
            else if(equationList.get(activeEquation).get(i) == "-") {
                result = Long.parseLong(equationList.get(activeEquation).get(i-1)) - Long.parseLong(equationList.get(activeEquation).get(i+1));
            }
            else {
                i++;
                continue;
            }

            equationList.get(activeEquation).remove(i+1);
            equationList.get(activeEquation).remove(i);
            equationList.get(activeEquation).set(i - 1, "" + result);

            i--;

            if(closeParenthesis && activeEquation > 0 && equationList.get(activeEquation - 1).size() > 0 && equationList.get(activeEquation - 1).get(equationList.get(activeEquation - 1).size() - 1) == "("){
                equationList.get(activeEquation - 1).remove(equationList.get(activeEquation - 1).size() - 1);
                closeParenthesis = false;
            }

            if(activeEquation > 0 && (equationList.get(activeEquation - 1).size() == 0 || equationList.get(activeEquation - 1).get(equationList.get(activeEquation - 1).size() - 1) != "(")){
                equationList.remove(activeEquation);
                activeEquation--;
                equationList.get(activeEquation).add("" + result);
            }
        }

        if(closeParenthesis && activeEquation > 0 && equationList.get(activeEquation).size() == 1 && equationList.get(activeEquation - 1).size() > 0 && equationList.get(activeEquation - 1).get(equationList.get(activeEquation - 1).size() - 1) == "("){
            String temp = equationList.get(activeEquation).get(0);
            equationList.remove(activeEquation);
            activeEquation--;
            equationList.get(activeEquation).remove(equationList.get(activeEquation).size()-1);
            equationList.get(activeEquation).add(temp);
        }

        currentValue = dataSizeOverflow(baseConversion(equationList.get(activeEquation).get(equationList.get(activeEquation).size() - 1), DECIMAL, activeBase));
        displayValue(currentValue);
    }

    public boolean isWithinDataSize(String value){
        BigInteger maxDataSize = BigInteger.valueOf(2).pow(activeDataSize - 1);
        BigInteger num = new BigInteger(value, activeBase);
        return (num.compareTo(maxDataSize) == -1 && num.compareTo(maxDataSize.multiply(BigInteger.valueOf(-1))) > -1);
    }
    public String dataSizeOverflow(String value){
        if(isWithinDataSize(value))
            return value;

        BigInteger maxDataSize = BigInteger.valueOf(2).pow(activeDataSize - 1);
        BigInteger num = new BigInteger(value, activeBase);

        num = num.mod(maxDataSize.multiply(BigInteger.valueOf(2)));

        if(num.compareTo(maxDataSize) != -1){
            num = num.subtract(maxDataSize.multiply(BigInteger.valueOf(2)));
        }
        else if(num.compareTo(maxDataSize.multiply(BigInteger.valueOf(-1))) == -1){
            num = num.add(maxDataSize.multiply(BigInteger.valueOf(2)));
        }

        return num.toString(activeBase);
    }

    public void displayValue(String value){
        answerLabel.setText(formatForDisplay(value, activeBase));
        hexButton.setText("HEX   " + formatForDisplay(value, HEXADECIMAL));
        decButton.setText("DEC   " + formatForDisplay(value, DECIMAL));
        octButton.setText("OCT   " + formatForDisplay(value, OCTAL));
        binButton.setText("BIN    " + formatForDisplay(value, BINARY));
    }

    public String formatForDisplay(String value, int toBase){
        value = baseConversion(value, activeBase, toBase);
        String negate = "";
        if(Long.parseLong(value, toBase) < 0){
            value = value.substring(1);
            negate = "-";
        }

        if(toBase == DECIMAL){
            int commaOffset = value.length() % 3;
            for(int i = value.length() - 1; i > 0; i--){
                if(i % 3 == commaOffset)
                    value = value.substring(0,i) + "," + value.substring(i);
            }

            value = negate + value;
        }

        else if(toBase == HEXADECIMAL || toBase == BINARY){
            if(negate == "-"){
                BigInteger unsignedLong = new BigInteger("0");
                unsignedLong = unsignedLong.add(BigInteger.valueOf(2).pow(activeDataSize));
                unsignedLong = unsignedLong.subtract(new BigInteger(value, toBase));
                value = unsignedLong.toString(toBase);
            }

            int spaceOffset = value.length() % 4;
            for(int i = value.length() - 1; i > 0; i--){
                if(i % 4 == spaceOffset)
                    value = value.substring(0, i) + " " + value.substring(i);
            }

            value = value.toUpperCase();
        }

        else if(toBase == OCTAL){
            if(negate == "-"){
                BigInteger unsignedLong = new BigInteger("0");
                unsignedLong = unsignedLong.add(BigInteger.valueOf(2).pow(activeDataSize));
                unsignedLong = unsignedLong.subtract(new BigInteger(value, toBase));
                value = unsignedLong.toString(toBase);
            }

            int spaceOffset = value.length() % 3;
            for(int i = value.length() - 1; i > 0; i--){
                if(i % 3 == spaceOffset)
                    value = value.substring(0,i) + " " + value.substring(i);
            }
        }

        return value;
    }


}

