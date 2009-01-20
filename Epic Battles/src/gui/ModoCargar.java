package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Vector;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import logica.Local;
import logica.Partida;
import logica.Red;
import logica.Tablero;
import red.GestorSockets;
import basedatos.GestorBaseDatos;

import sun.audio.*;

public class ModoCargar extends JPanel implements ActionListener, ListSelectionListener
{
	private static final long serialVersionUID = 467462451714083887L;
	private Principal parent = null;
	private boolean red = false;
	private Vector<Long> ordenPartidas = null;
	private Vector<String> movimientos = null;
	private String ip = null;
	private GestorSockets gestorSockets = null;
	private JPanel lateral = null;
	private JPanel botonera = null;
	private JPanel grid = null;
	private MiBoton bBorrar = null;
	private MiBoton bCargar = null;
	private MiBoton bVolver = null;
	private JList partidas = null;
	private Tablero tablero = null;
	
	public ModoCargar(Principal parent, boolean red)
	{
		super();
		this.parent = parent;
		this.red = red;
		initialize();
		getBCargar().addActionListener(this);
		getBBorrar().addActionListener(this);
		getBVolver().addActionListener(this);
		getPartidas().addListSelectionListener(this);
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		this.setLayout(new BorderLayout());
		this.add(tablero.getEscenario(), BorderLayout.CENTER);
		this.add(getLateral(), BorderLayout.EAST);
	}
	
	@Override
	public String toString()
	{
		return "Pantalla de carga";
	}

	/**
	 * This method initializes lateral	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getLateral()
	{
		if (lateral == null)
		{
			lateral = new JPanel();
			lateral.setOpaque(false);
			lateral.setLayout(new BorderLayout());
			lateral.add(getPartidas(), BorderLayout.CENTER);
			lateral.add(getBotonera(), BorderLayout.SOUTH);
		}
		return lateral;
	}

	/**
	 * This method initializes botonera	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getBotonera()
	{
		if (botonera == null)
		{
			botonera = new JPanel();
			botonera.setOpaque(false);
			botonera.setLayout(new FlowLayout());
			botonera.add(getGrid(), null);
		}
		return botonera;
	}

	/**
	 * This method initializes grid	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getGrid()
	{
		if (grid == null)
		{
			grid = new JPanel();
			grid.setOpaque(false);
			grid.setLayout(new GridLayout(3, 0, 10, 10));
			grid.add(getBCargar(), null);
			grid.add(getBBorrar(), null);
			grid.add(getBVolver(), null);
		}
		return grid;
	}

	/**
	 * This method initializes bBorrar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private MiBoton getBBorrar()
	{
		if (bBorrar == null)
		{
			bBorrar = new MiBoton("Borrar partida");
			bBorrar.setEnabled(false);
		}
		return bBorrar;
	}

	/**
	 * This method initializes bCargar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private MiBoton getBCargar()
	{
		if (bCargar == null)
		{
			bCargar = new MiBoton("Cargar partida");
			bCargar.setEnabled(false);
		}
		return bCargar;
	}

	/**
	 * This method initializes bVolver	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private MiBoton getBVolver()
	{
		if (bVolver == null)
		{
			bVolver = new MiBoton("Volver");
		}
		return bVolver;
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{	
		if (ae.getSource().equals(getBCargar()))
		{
			if (red)
			{
				JDialog jd = new JDialog(parent, "Realizando la conexión", true)
				{
					private static final long serialVersionUID = -6891043435460362290L;
					
				};
				jd.setVisible(true);
				
				this.gestorSockets = new GestorSockets();
			}
			
			parent.loadRootPanel(new ModoJuego(parent, red, new Partida(parent, tablero, (red ? new Red(gestorSockets) : new Local()))));
		}
		else if (ae.getSource().equals(getBBorrar()))
		{
			int index = getPartidas().getSelectedIndex();
			DefaultListModel model = (DefaultListModel) getPartidas().getModel();
			
			try
			{
				AudioPlayer.player.start(new AudioStream(new FileInputStream("sonidos/erased.wav")));
				GestorBaseDatos.borrarPartida(ordenPartidas.get(index));
			}
			catch (SQLException sqle)
			{
				sqle.printStackTrace();
			}
			catch (FileNotFoundException fnfe)
			{ }
			catch (IOException ioe)
			{ }
			
			model.remove(index);
		}
		else if (ae.getSource().equals(getBVolver()))
		{
			if (red)
			{
				parent.loadRootPanel(new ModoJuegoRed(parent));
			}
			else
			{
				parent.loadRootPanel(new ModoJuegoLocal(parent));
			}
		}
	}
	
	/**
	 * This method initializes partidas	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getPartidas()
	{
		if (partidas == null)
		{
			DefaultListModel model = new DefaultListModel();
			partidas = new JList(model);
			
			Hashtable<Long,String> tablaPartidas = null;
			try
			{
				ordenPartidas = new Vector<Long>();
				tablaPartidas = GestorBaseDatos.leerPartidas(red);
				
				for (Entry<Long, String> hs: tablaPartidas.entrySet())
				{
					model.addElement(hs.getValue());
					ordenPartidas.add(hs.getKey());
				}
			}
			catch (SQLException sqle)
			{
				model.addElement(sqle.getMessage());
				parent.setHelp(sqle.getMessage());
			}
		}
		return partidas;
	}

	@Override
	public void valueChanged(ListSelectionEvent lse)
	{
		try
		{
			movimientos = GestorBaseDatos.leerPartida(ordenPartidas.get(getPartidas().getSelectedIndex()));
			this.ip = movimientos.get(0);
			this.movimientos.remove(0);
			this.tablero = new Tablero(movimientos);
			
			this.getBCargar().setEnabled(true);
			this.getBBorrar().setEnabled(true);
		}
		catch (SQLException sqle)
		{
			parent.setHelp(sqle.getMessage());
		}
		catch (Exception e)
		{
			parent.setHelp(e.getMessage());
		}
		
		this.getBCargar().setEnabled(true);
		this.getBBorrar().setEnabled(true);
	}
}
