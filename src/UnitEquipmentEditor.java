import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.GridLayout;
import javax.swing.JTabbedPane;
import javax.swing.JSeparator;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class UnitEquipmentEditor extends JFrame {

	private JPanel contentPane;
	private JToolBar toolBar;
	private JTable table;
	private JLabel label;
	private JComboBox<String> factionsComboBox;
	private JLabel label_1;
	private JComboBox<String> unitsComboBox;
	private JButton btnPrecompile;
	private JSeparator separator;
	private JButton button_1;
	private JButton btnAddFaction;
	private JButton button_3;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JButton btnSave;
	private JButton btnPaste;
	private GraphView graphView = new GraphView();
	private HashMap<String, Faction> factions = new HashMap<>();
	private Faction currentFaction = new Faction("");

	/*
	 * Launch the application.
	 * 
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { UnitEquipmentEditor frame = new
	 * UnitEquipmentEditor(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */
	/**
	 * Create the frame.
	 */

	private PersonalEquipmentModel model;
	private JButton btnPastenew;
	private JButton btnNewButton;
	private JButton btnGraph;

	private ArrayList<String> browseFolder(String path, boolean isDir) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
		}
		ArrayList<String> result = new ArrayList<String>();
		for (String name : file.list()) {
			if (new File(path + name).isDirectory() == isDir) {
				result.add(name);
			}
		}
		return result;
	}

	private void updateFactionAndUnitLists() {
		browseFolder("equip_patterns\\", true).forEach(x -> {
			if (!factions.containsKey(x)) {
				System.out.println("Adding faction " + x);
				Faction newFaction = new Faction(x);
				String filePath = "equip_patterns\\"+newFaction.getName()+"\\";
				for (String unitFileName: browseFolder(filePath,false)) {
					newFaction.getUnits().put(unitFileName, new PersonalEquipmentModel(filePath+unitFileName));
				}
				factions.put(x, newFaction);
				if (currentFaction == null) {
					currentFaction = newFaction;
				}
				this.factionsComboBox.addItem(x);
			}
		});
		currentFaction.updateUnits();
	}
	
	private void updateUnitsListOnly() {
		this.unitsComboBox.removeAllItems();
		System.out.println(currentFaction.getName());
		System.out.println(currentFaction.getUnits().size());
		for (String unitName: currentFaction.getUnits().keySet()) {
			System.out.println("Unit " + unitName + " " + currentFaction.getUnits().get(unitName));
			unitsComboBox.addItem(unitName);
		}
	}

	public UnitEquipmentEditor(PersonalEquipmentModel itemModel) {
		this.model = itemModel;
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		toolBar = new JToolBar();
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.insets = new Insets(0, 0, 5, 0);
		gbc_toolBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 0;
		contentPane.add(toolBar, gbc_toolBar);

		label = new JLabel("\u0424\u0440\u0430\u043A\u0446\u0438\u044F");
		toolBar.add(label);

		factionsComboBox = new JComboBox<String>();
		toolBar.add(factionsComboBox);
		factionsComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				currentFaction = factions.get(factionsComboBox.getSelectedItem().toString());
				updateUnitsListOnly();
			}
		});

		btnAddFaction = new JButton("+");
		btnAddFaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createNewFaction();
			}
		});
		toolBar.add(btnAddFaction);
		
		btnGraph = new JButton("\u0413\u0440\u0430\u0444");
		btnGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graphView.setVisible(true);
				graphView.repopulateGraph(currentFaction);
			}
		});
		toolBar.add(btnGraph);

		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_1);

		label_1 = new JLabel("\u0428\u0430\u0431\u043B\u043E\u043D");
		toolBar.add(label_1);

		unitsComboBox = new JComboBox<String>();
		toolBar.add(unitsComboBox);
		unitsComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> comboBox = (JComboBox) e.getSource();
				if (comboBox.getSelectedItem() != null) {
					String path = "equip_patterns\\" + currentFaction.getName() + "\\"
							+ comboBox.getSelectedItem().toString();
					System.out.println(path);
					if (new File(path).exists()) {
						itemModel.reloadFromFile(path);
						itemModel.setCurrentFaction(currentFaction);
						itemModel.fireTableDataChanged();
					}
				}
			}

		});

		button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createNewPattern();
			}
		});
		button_3.setToolTipText("\u0421\u043E\u0437\u0434\u0430\u0442\u044C \u0448\u0430\u0431\u043B\u043E\u043D");
		button_3.setIcon(new ImageIcon(
				UnitEquipmentEditor.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")));
		toolBar.add(button_3);

		btnSave = new JButton("");
		btnSave.setEnabled(false);
		btnSave.setDisabledIcon(new ImageIcon(
				UnitEquipmentEditor.class.getResource("/com/sun/java/swing/plaf/windows/icons/HardDrive.gif")));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveUnit();
			}

		});
		btnSave.setIcon(new ImageIcon(
				UnitEquipmentEditor.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		btnSave.setToolTipText("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		toolBar.add(btnSave);

		btnPaste = new JButton("");
		btnPaste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itemModel.setCurrentFaction(currentFaction);
				itemModel.reloadFromClipboard();
			}
		});
		btnPaste.setIcon(new ImageIcon(
				UnitEquipmentEditor.class.getResource("/com/sun/javafx/scene/web/skin/Paste_16x16_JFX.png")));
		btnPaste.setToolTipText(
				"\u0412\u0441\u0442\u0430\u0432\u0438\u0442\u044C \u0438\u0437 \u0431\u0443\u0444\u0435\u0440\u0430");
		toolBar.add(btnPaste);

		btnPastenew = new JButton("+");
		btnPastenew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemModel.setCurrentFaction(currentFaction);
				createNewPattern();
				itemModel.reloadFromClipboard();
			}

		});
		btnPastenew.setIcon(new ImageIcon(
				UnitEquipmentEditor.class.getResource("/com/sun/javafx/scene/web/skin/Paste_16x16_JFX.png")));
		btnPastenew.setToolTipText(
				"\u0412\u0441\u0442\u0430\u0432\u0438\u0442\u044C \u0432 \u043D\u043E\u0432\u044B\u0439 \u0448\u0430\u0431\u043B\u043E\u043D");
		toolBar.add(btnPastenew);

		separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_2);

		btnPrecompile = new JButton("\u041F\u0440\u0435\u043A\u043E\u043C\u043F\u0438\u043B\u0438\u0440\u043E\u0432\u0430\u0442\u044C");
		btnPrecompile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveAllUnits(true);
			}
		});
		toolBar.add(btnPrecompile);

		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator);

		button_1 = new JButton("+");
		button_1.setToolTipText("\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u0441\u0442\u0440\u043E\u043A\u0443");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArmaEquip a = new ArmaEquip();
				a.faction = currentFaction;
				itemModel.addItem(a);
				itemModel.fireTableDataChanged();
			}
		});
		toolBar.add(button_1);
		
		btnNewButton = new JButton("-");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itemModel.deleteItem(table.convertRowIndexToModel(table.getSelectedRow()));
			}
		});
		btnNewButton.setToolTipText("\u0423\u0434\u0430\u043B\u0438\u0442\u044C \u0441\u0442\u0440\u043E\u043A\u0443");
		toolBar.add(btnNewButton);

		table = new JTable() {
			public void changeSelection(final int row, final int column, boolean toggle, boolean extend)
            {
                super.changeSelection(row, column, toggle, extend);
                if(column>0 && column <4) {
                	table.editCellAt(row, column);
                	table.transferFocus();
                }
            }
		};
		table.setAutoCreateRowSorter(true);

		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 1;
		contentPane.add(new JScrollPane(table), gbc_table);
		// contentPane.add(table, gbc_table);
		this.updateFactionAndUnitLists();
	}

	protected void createNewFaction() {
		String name = JOptionPane.showInputDialog("¬ведите название новой фракции:");
		if (name != null && name.length() > 0) {
			File f = new File(
					"equip_patterns\\" + name);
			if (!f.exists()) {
				f.mkdir();
				this.updateFactionAndUnitLists();
				this.factionsComboBox.setSelectedItem(name);
			} else {
				errorMessage("‘ракци€ с таким названием уже существует!");
			}
		}
		
	}

	private void errorMessage(String string) {
		// TODO Auto-generated method stub
		System.out.println(string);
	}

	public JTable getTable() {
		return table;
	}
	
	private void createNewPattern(String name) {
		// TODO Auto-generated method stub
		if (name != null && name.length() > 0) {
			String extension = "";
			if (!name.toLowerCase().endsWith(".sqf"))
				extension = ".sqf";
			File f = new File(
					"equip_patterns\\" + factionsComboBox.getSelectedItem().toString() + "\\" + name + extension);
			try {
				f.createNewFile();
				this.updateUnitsListOnly();
				this.unitsComboBox.setSelectedItem(name + extension);
			} catch (IOException e) {
				// TODO unable to create file
				e.printStackTrace();
			}
		}
	}

	private void createNewPattern() {
		// TODO Auto-generated method stub
		String name = JOptionPane.showInputDialog("¬ведите название нового шаблона:");
		this.createNewPattern(name);
	}

	public void saveUnit() {
		// TODO Auto-generated method stub
		if (unitsComboBox.getSelectedItem() == null || factionsComboBox.getSelectedItem() == null) {
			return;
		}
		String path = "equip_patterns\\" + factionsComboBox.getSelectedItem().toString() + "\\"
				+ unitsComboBox.getSelectedItem().toString();
		try {
			EquipmentWriter writer = new EquipmentWriter(this.model.getAllItems(), path, false);
			writer.write();
			this.model.setGotChangesAndUpdate(false);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveAllUnits(boolean precompile) {
		// TODO Auto-generated method stub
		if (unitsComboBox.getItemCount()<1 || factionsComboBox.getSelectedItem() == null) {
			return;
		}
		for (int i=0; i<unitsComboBox.getItemCount();i++) {
			String path = "equip_patterns\\" + factionsComboBox.getSelectedItem().toString() + "\\"
					+ unitsComboBox.getItemAt(i).toString();
			try {
				this.model.reloadFromFile(path);
				this.model.setCurrentFaction(currentFaction);
				EquipmentWriter writer = new EquipmentWriter(this.model.getAllItems(), path, precompile);
				writer.write();
				this.model.setGotChangesAndUpdate(false);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			EquipmentWriter.finalize(factionsComboBox.getSelectedItem().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class MyComboBoxRenderer extends JComboBox implements TableCellRenderer {
		public MyComboBoxRenderer(String[] strings) {
			super(strings);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				super.setBackground(table.getSelectionBackground());
			} else {
				setForeground(table.getForeground());
				setBackground(table.getBackground());
			}
			if (!value.toString().equals("-")) {
				setSelectedItem(value);
			} else {
				setSelectedItem("ANY");
			}
			// System.out.println(column + " " + row + " " + value.toString());
			return this;
		}

		public void setValue(Object value) {
			setSelectedItem(value);
			System.out.println(value.toString());
		}
	}

	class MyComboBoxEditor extends DefaultCellEditor {
		public MyComboBoxEditor(String[] s) {
			super(new JComboBox(s));
		}
	}

	public void updateColumnEditors() {
		TableColumn col = table.getColumnModel().getColumn(1);
		col.setCellEditor(new MyComboBoxEditor(AddableType.stringValues()));
		col.setCellRenderer(new MyComboBoxRenderer(AddableType.stringValues()));

		TableColumn col1 = table.getColumnModel().getColumn(4);
		col1.setCellEditor(new MyComboBoxEditor(Destination.stringValues()));
		col1.setCellRenderer(new MyComboBoxRenderer(Destination.stringValues()));
		
/*		TableColumn col2 = table.getColumnModel().getColumn(2);
		col2.setCellEditor(new AutocompleteEditor());*/
	}

	public JButton getBtnSave() {
		return btnSave;
	}

}
