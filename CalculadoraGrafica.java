import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class CalculadoraGrafica implements ActionListener
{
	Calculadora c = new Calculadora(); //Objeto de la clase Calculadora
	JTextField tfexp,tfx,tfy,tfz; //Campos de texto para las expresiones y los valores de las variables
	JLabel tfres;
	public CalculadoraGrafica()
	{
		//En el constructor se crean las componentes gráficas
		JFrame f = new JFrame(); //Objeto JFrame que contendrá todas las componentes gráficas
		f.setTitle("Calculadora");
		f.setMinimumSize(new Dimension(280, 450));
		f.setLocation(50,50);
		f.getContentPane().setLayout(null); //El JFrame estará dividido en tres paneles
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		
		JPanel panel1 = new JPanel(); panel1.setLayout(null); panel1.setBounds(0,  0,280,200);
		JPanel panel2 = new JPanel(); panel2.setLayout(null); panel2.setBounds(0,205,280,130);
		JPanel panel3 = new JPanel(); panel3.setLayout(null); panel3.setBounds(0,330,280,120);
		
		JLabel l1 = new JLabel("Introducir expresion",JLabel.CENTER);
     //Etiqueta  
		l1.setBounds(5, 0, 270, 30);
		l1.setFont(new Font(Font.SANS_SERIF, Font.BOLD,14));
		l1.setForeground(Color.black);
		panel1.add(l1);

		tfexp = new JTextField(); //Campo de texto para introducir la expresion
        tfexp.setBounds(17, 35, 205, 30);
		panel1.add(tfexp);

		JButton b = new JButton("C");
			b.addActionListener(this); b.setBounds(222, 30, 40, 40);
			 panel1.add(b);

		JPanel panel11 = new JPanel();
		panel11.setLayout(new GridLayout(3,6));
		
		//Botones para introducir la expresion

		b = new JButton("x"); b.addActionListener(this); panel11.add(b); 
		b = new JButton("y"); b.addActionListener(this); panel11.add(b);
		b = new JButton("z"); b.addActionListener(this); panel11.add(b);
		b = new JButton("1"); b.addActionListener(this); panel11.add(b);
		b = new JButton("2"); b.addActionListener(this); panel11.add(b);
		b = new JButton("3"); b.addActionListener(this); panel11.add(b);
		b = new JButton("+"); b.addActionListener(this); panel11.add(b);
		b = new JButton("*"); b.addActionListener(this); panel11.add(b);
		b = new JButton("-"); b.addActionListener(this); panel11.add(b);
		b = new JButton("4"); b.addActionListener(this); panel11.add(b);
		b = new JButton("5"); b.addActionListener(this); panel11.add(b);
		b = new JButton("6"); b.addActionListener(this); panel11.add(b);
		b = new JButton("("); b.addActionListener(this); panel11.add(b);
		b = new JButton(")"); b.addActionListener(this); panel11.add(b);
		b = new JButton("0"); b.addActionListener(this); panel11.add(b);
		b = new JButton("7"); b.addActionListener(this); panel11.add(b);
		b = new JButton("8"); b.addActionListener(this); panel11.add(b);
		b = new JButton("9"); b.addActionListener(this); panel11.add(b);

		panel11.setBounds(15,70,250,120);
		panel1.add(panel11);
		f.getContentPane().add(panel1);

		JLabel l2 = new JLabel(" Dar valores a las variables", JLabel.CENTER);
	//Etiqueta
		l2.setBounds(5, 0, 280, 30);
		l2.setFont(new Font(Font.SANS_SERIF, Font.BOLD,14));
		l2.setForeground(Color.black);
		l2.setBackground(Color.yellow);
		panel2.add(l2);

		JPanel panel21=new JPanel();
		panel21.setLayout(new GridLayout(3,2));

		panel21.add(new JLabel("x = ",JLabel.RIGHT)); tfx = new JTextField();
		panel21.add(tfx);
		panel21.add(new JLabel("y = ",JLabel.RIGHT)); tfy = new JTextField();
		panel21.add(tfy);
		panel21.add(new JLabel("z = ",JLabel.RIGHT)); tfz = new JTextField();
		panel21.add(tfz);
		panel21.setBounds(40,40,150,80);

		panel2.add(panel21);
		f.getContentPane().add(panel2);

		JLabel l3 = new JLabel(" Resultados", JLabel.CENTER); //Etiqueta
		l3.setFont(new Font(Font.SANS_SERIF, Font.BOLD,14));
		l3.setBounds(5, 0, 280, 30);
		l3.setForeground(Color.black);
		panel3.add(l3);
	//Botones para realizar las operaciones sobre la expresion
		tfres = new JLabel("...", JLabel.CENTER); tfres.setBounds(10, 30, 270, 30);
		panel3.add(tfres);

		b = new JButton("Sustituir");
			b.addActionListener(this); b.setBounds(5,65,87,30);   panel3.add(b);
        b = new JButton("Calcular");
			b.addActionListener(this); b.setBounds(97,65,87,30);  panel3.add(b);
		b = new JButton("Borrar");
			b.addActionListener(this); b.setBounds(189,65,87,30); panel3.add(b);

		
		f.getContentPane().add(panel3);
		f.setVisible(true);

		tfexp.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e){
                if (e.getKeyCode() == KeyEvent.VK_ENTER)   calcular();
        }});
	}

	public void actionPerformed(ActionEvent ev)
	{
		String aux = ((JButton)ev.getSource()).getText();

		if      (aux.equals("Sustituir")) sustituir();
		else if (aux.equals("Calcular"))  calcular();
		else if (aux.equals("Borrar")) 	  reset();
		else if (aux.equals("C"))
		{
			String aux2 = tfexp.getText();
			if (!aux2.equals("")) tfexp.setText(aux2.substring(0,aux2.length()-1));
		}
		else
		{
			String aux2 = tfexp.getText();
			tfexp.setText(aux2+aux);
			/********* WORK UNDER PROGRESS *********
			 tfexp.requestFocusInWindow();
			 int end = tfexp.getText().length();
			 tfexp.setCaretPosition(end);
			 ********* WORK UNDER PROGRESS *********/
		}
	}

	private void calcular()
	{
		String s      = tfexp.getText();
		if (!s.equals(""))
		{
			try
			{
				Expresion exp = parser(s);
				Valoracion v  = new Valoracion();

				String sx = tfx.getText();
				if (!sx.equals("")) v.add("x", Integer.parseInt(sx));

				String sy = tfy.getText();
				if (!sy.equals("")) v.add("y", Integer.parseInt(sy));

				String sz = tfz.getText();
				if (!sz.equals("")) v.add("z", Integer.parseInt(sz));

				c = new Calculadora(exp,v);
				try
				{
					int res = c.calcular();
					tfres.setText(Integer.toString(res));
				}
				catch (ArithmeticException e)
				{
					tfres.setText("Error: variable no declarada");
					tfres.requestFocus(); //Para si lo hacemos pulsando enter
					JOptionPane.showMessageDialog(null, "¡ERROR! \n" + e.getMessage(), "Error en variable", JOptionPane.ERROR_MESSAGE);
				}
			}
			catch (NumberFormatException e)
			{
				tfres.setText("Error en la expresion");
				tfres.requestFocus(); //Para si lo hacemos pulsando enter
				JOptionPane.showMessageDialog(null, "¡ERROR! \n No se ha podido entender la expresion.", "Error en expresion", JOptionPane.ERROR_MESSAGE);
			}
			catch (Exception e)
			{
				tfres.setText("Error en la expresion");
				tfres.requestFocus(); //Para si lo hacemos pulsando enter
				JOptionPane.showMessageDialog(null, "¡ERROR!\n Error desconocido en la expresion", "Error en expresion", JOptionPane.ERROR_MESSAGE);
			}
		}
		else tfres.setText("No hay expresion");;
	}

	private void sustituir()
	{
        String s      = tfexp.getText();
		if (!s.equals(""))
		{
			try
			{
				Expresion exp = parser(s);

				String sx = tfx.getText();
				if (!sx.equals("")) exp.sustituir("x", Integer.parseInt(sx));

				String sy = tfy.getText();
				if (!sy.equals("")) exp.sustituir("y", Integer.parseInt(sy));

				String sz = tfz.getText();
				if (!sz.equals("")) exp.sustituir("z", Integer.parseInt(sz));

				tfexp.setText(exp.toString());
				tfres.setText("Variables declaradas sustituidas");
			}
			catch (NumberFormatException e)
			{
				tfres.setText("Error en la expresion");
				tfres.requestFocus(); //Para si lo hacemos pulsando enter
				JOptionPane.showMessageDialog(null, "¡ERROR! \n No se ha podido entender la expresion.", "Error en expresion", JOptionPane.ERROR_MESSAGE);
			}
			catch (Exception e)
			{
				tfres.setText("Error en la expresion");
				tfres.requestFocus(); //Para si lo hacemos pulsando enter
				JOptionPane.showMessageDialog(null, "¡ERROR!\n Error desconocido en la expresion", "Error en expresion", JOptionPane.ERROR_MESSAGE);
			}
		}
		else JOptionPane.showMessageDialog(null, "No se ha encontrado ninguna expresion.", "Falta expresion", JOptionPane.INFORMATION_MESSAGE);
    }

	private void reset()
	{
    	tfexp.setText("");
    	tfx.setText("");
    	tfy.setText("");
    	tfz.setText("");
    	tfres.setText("...");
    }

	private static String getExp1(String s)
	{
		int i=1,na=1,nc=0;
		while (na!=nc)
		{
			if (s.charAt(i)=='(') na++;
			else if (s.charAt(i)==')') nc++;
			i++;
		}
		return s.substring(0,i);
	}

	private static Expresion parser(String s)
	{
		if (s.charAt(0)=='(')
		{
			String s1=getExp1(s);
			if (s1.length()==s.length())
			{
				return parser(s.substring(1,s.length()-1));
			}
			char op = s.charAt(s1.length());
			String s2 = s.substring(s1.length()+1, s.length());

			if (op=='+') return new Suma (parser(s1),parser(s2));
			else return new Producto (parser(s1),parser(s2));	
		}
		else
		{
			if (s.charAt(0) =='-')
			{
				if (s.charAt(1)=='(')
				{
					s=s.substring(2, s.length()-1);
					return new Opuesto(parser(s));
				}
				else
				{	
					s=s.substring(1, s.length());
					return new Opuesto(parser(s));
				}
			}
			else
			{
				int i1=s.indexOf("+");
				int i2=s.indexOf("*");
				if (i1!=-1)
				{
					String s1=s.substring(0,i1);
					String s2=s.substring(i1+1, s.length());
					return new Suma(parser(s1),parser(s2));
				}
				else if (i2!=-1)
				{
					String s1=s.substring(0,i2);
					String s2=s.substring(i2+1, s.length());
					return new Producto(parser(s1),parser(s2));
				}
				else if (s.equals("x")||s.equals("y")||s.equals("z"))
				{
					return new ExpAtomica(s);
				}
				else return new ExpAtomica(Integer.parseInt(s));
			}
		}
	}
}