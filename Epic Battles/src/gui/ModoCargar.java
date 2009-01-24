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
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import basedatos.GestorBaseDatos;

public class ModoCargar extends JPanel implements ActionListener, ListSelectionListener
{
	private static final long serialVersionUID = 467462451714083887L;
	private MiBoton bBorrar = null;
	private MiBoton bCargar = null;
	private JPanel botonera = null;
	private MiBoton bVolver = null;
	private GestorSockets gestorSockets = null;
	private JPanel grid = null;
	@SuppressWarnings("unused")
	private String ip = null;
	private JPanel lateral = null;
	private Vector<String> movimientos = null;
	private Vector<Long> ordenPartidas = null;
	private Principal parent = null;
	private JList partidas = null;
	private boolean red = false;
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
			this.removeAll();
			this.add(getLateral(), BorderLayout.EAST);
			getBBorrar().setEnabled(false);
			getBCargar().setEnabled(false);
			repaint();
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
	
	private MiBoton getBBorrar()
	{
		if (bBorrar == null)
		{
			bBorrar = new MiBoton("Borrar partida");
			bBorrar.setEnabled(false);
		}
		return bBorrar;
	}
	
	private MiBoton getBCargar()
	{
		if (bCargar == null)
		{
			bCargar = new MiBoton("Cargar partida");
			bCargar.setEnabled(false);
		}
		return bCargar;
	}
	
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
	
	private MiBoton getBVolver()
	{
		if (bVolver == null)
		{
			bVolver = new MiBoton("Volver");
		}
		return bVolver;
	}
	
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
	
	private JList getPartidas()
	{
		if (partidas == null)
		{
			Hashtable<Long,String> tablaPartidas = null;
			DefaultListModel model = new DefaultListModel();
			
			partidas = new JList(model);
			ordenPartidas = new Vector<Long>();
			
			try
			{
				
				tablaPartidas = GestorBaseDatos.leerPartidas(red);
				for (Entry<Long, String> e: tablaPartidas.entrySet())
				{
					model.addElement(e.getValue());
					ordenPartidas.add(e.getKey());
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
	
	private void initialize()
	{
		this.setLayout(new BorderLayout());
		this.add(getLateral(), BorderLayout.EAST);
	}
	
	@Override
	public String toString()
	{
		return "Pantalla de carga";
	}

	@Override
	public void valueChanged(ListSelectionEvent lse)
	{
		try
		{
			movimientos = GestorBaseDatos.leerPartida(ordenPartidas.get(getPartidas().getSelectedIndex()));
			this.ip = movimientos.get(0);
			this.movimientos.remove(0);
			this.tablero = new Tablero(movimientos, red);
			
			this.removeAll();
			this.add(getLateral(), BorderLayout.EAST);
			this.add(tablero.getEscenario(), BorderLayout.CENTER);
			validate();
			
			this.getBCargar().setEnabled(true);
			this.getBBorrar().setEnabled(true);
		}
		catch (SQLException sqle)
		{
			parent.setHelp(sqle.getMessage());
			return;
		}
		catch (Exception e)
		{
			parent.setHelp(e.getMessage());
		}
	}
}
