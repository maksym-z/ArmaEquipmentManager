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
import java.util.HashSet;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

public class EquipmentEditor extends JFrame {

	private JPanel contentPane;
	private JToolBar toolBar;
	private JTable table;
	private JLabel label;
	JComboBox<String> factionsComboBox;
	private JLabel label_1;
	JComboBox<String> unitsComboBox;
	private JButton btnPrecompile;
	private JSeparator separator;
	private JButton btnAddLine;
	private JButton btnAddFaction;
	private JButton button_3;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JButton btnSave;
	private JButton btnPaste;

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

	private UnitModel model;
	private JButton btnPastenew;
	private JButton btnDeleteLine;
	private JButton btnGraph;
	private Controller controller;
	private JButton btnLsn;
	private JToolBar listenerToolbar;
	private JCheckBox chckbxNewCheckBox;
	
	private HashMap<JCheckBox,AddableType> checkBoxToTypeMap = new HashMap<>();
	private JButton btnSaveall;

	public void updateFactionList() {
		factionsComboBox.removeAllItems();
		for (String factionName : controller.getFactions().keySet()) {
			factionsComboBox.addItem(factionName);
		}
		factionsComboBox.repaint();
	}

	public void updateUnitList(Faction faction) {
		unitsComboBox.removeAllItems();
		for (String unitName : faction.getUnits().keySet()) {
			unitsComboBox.addItem(unitName);
		}
	}

	public EquipmentEditor(Controller controller) { //UnitModel unitModel, 
		this.controller = controller;
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
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
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
				JComboBox cb = (JComboBox)event.getSource();
				System.out.println(cb.getSelectedItem().toString());
				controller.setCurrentFaction(cb.getSelectedItem().toString());
				factionsComboBox.setSelectedItem(cb.getSelectedItem());
			}
		});

		btnAddFaction = new JButton("+");
		btnAddFaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.createNewFaction();
			}
		});
		toolBar.add(btnAddFaction);

		btnGraph = new JButton("\u0413\u0440\u0430\u0444");
		btnGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.showGraphView();
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
					controller.setSelectedUnit(comboBox.getSelectedItem().toString());
					System.out.println(comboBox.getSelectedItem());
//					itemModel.fireTableDataChanged();
				}
			}
		});

		button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createNewUnit();
			}
		});
		button_3.setToolTipText("\u0421\u043E\u0437\u0434\u0430\u0442\u044C \u0448\u0430\u0431\u043B\u043E\u043D");
		button_3.setIcon(
				new ImageIcon(EquipmentEditor.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")));
		toolBar.add(button_3);

		btnSave = new JButton("");
		btnSave.setEnabled(false);
		btnSave.setDisabledIcon(new ImageIcon(
				EquipmentEditor.class.getResource("/com/sun/java/swing/plaf/windows/icons/HardDrive.gif")));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.saveUnit(controller.getSelectedUnit());
			}

		});
		btnSave.setIcon(new ImageIcon(
				EquipmentEditor.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		btnSave.setToolTipText("\u0421\u043E\u0445\u0440\u0430\u043D\u0438\u0442\u044C");
		toolBar.add(btnSave);

		btnPaste = new JButton("");
		btnPaste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.pasteFromClipboard();
			}
		});
		
		btnSaveall = new JButton("");
		btnSaveall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveAll(controller.getCurrentFaction());
			}
		});
		btnSaveall.setToolTipText("Save all");
		btnSaveall.setVerticalAlignment(SwingConstants.BOTTOM);
		btnSaveall.setIcon(new ImageIcon(EquipmentEditor.class.getResource("/com/sun/java/swing/plaf/windows/icons/UpFolder.gif")));
		toolBar.add(btnSaveall);
		btnPaste.setIcon(
				new ImageIcon(EquipmentEditor.class.getResource("/com/sun/javafx/scene/web/skin/Paste_16x16_JFX.png")));
		btnPaste.setToolTipText(
				"\u0412\u0441\u0442\u0430\u0432\u0438\u0442\u044C \u0438\u0437 \u0431\u0443\u0444\u0435\u0440\u0430");
		toolBar.add(btnPaste);

		btnPastenew = new JButton("+");
		btnPastenew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createNewUnit();
				controller.pasteFromClipboard();
			}

		});
		btnPastenew.setIcon(
				new ImageIcon(EquipmentEditor.class.getResource("/com/sun/javafx/scene/web/skin/Paste_16x16_JFX.png")));
		btnPastenew.setToolTipText(
				"\u0412\u0441\u0442\u0430\u0432\u0438\u0442\u044C \u0432 \u043D\u043E\u0432\u044B\u0439 \u0448\u0430\u0431\u043B\u043E\u043D");
		toolBar.add(btnPastenew);
		
		btnLsn = new JButton("");
		btnLsn.setIcon(new ImageIcon(EquipmentEditor.class.getResource("/com/sun/javafx/webkit/prism/resources/mediaMuteDisabled.png")));
		toolBar.add(btnLsn);
		btnLsn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.toggleListeningMode();
				listenerToolbar.setVisible(controller.getListeningMode());
			}
		});

		separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_2);

		btnPrecompile = new JButton(
				"\u041F\u0440\u0435\u043A\u043E\u043C\u043F\u0438\u043B\u0438\u0440\u043E\u0432\u0430\u0442\u044C");
		btnPrecompile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.saveAllUnits(true);
			}
		});
		toolBar.add(btnPrecompile);

		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator);

		btnAddLine = new JButton("+");
		btnAddLine.setToolTipText(
				"\u0414\u043E\u0431\u0430\u0432\u0438\u0442\u044C \u0441\u0442\u0440\u043E\u043A\u0443");
		btnAddLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArmaEquip a = new ArmaEquip();
				controller.getSelectedUnit().addItem(a);
				model.fireTableDataChanged();
			}
		});
		toolBar.add(btnAddLine);

		btnDeleteLine = new JButton("-");
		btnDeleteLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.deleteItem(table.convertRowIndexToModel(table.getSelectedRow()));
			}
		});
		btnDeleteLine.setToolTipText("\u0423\u0434\u0430\u043B\u0438\u0442\u044C \u0441\u0442\u0440\u043E\u043A\u0443");
		toolBar.add(btnDeleteLine);
		
		listenerToolbar = new JToolBar();
		listenerToolbar.setVisible(false);
		GridBagConstraints gbc_listenerToolbar = new GridBagConstraints();
		gbc_listenerToolbar.insets = new Insets(0, 0, 5, 0);
		gbc_listenerToolbar.gridx = 0;
		gbc_listenerToolbar.gridy = 1;
		contentPane.add(listenerToolbar, gbc_listenerToolbar);
		
		for (AddableType addableType: AddableType.values()) {
			JCheckBox newCheckBox = new JCheckBox(addableType.toString().toLowerCase().substring(0,Math.min(5,addableType.toString().length())));
			checkBoxToTypeMap.put(newCheckBox, addableType);
			newCheckBox.setToolTipText(addableType.toString());
			listenerToolbar.add(newCheckBox);
			newCheckBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JCheckBox box = (JCheckBox)arg0.getSource();
					if (box.isSelected()) {
						controller.addSomethingToListenFor(checkBoxToTypeMap.get(box));
					} else {
						controller.removeSomethingToListenFor(checkBoxToTypeMap.get(box));
					}
				}
			});
		};

		table = new JTable() {
			public void changeSelection(final int row, final int column, boolean toggle, boolean extend) {
				super.changeSelection(row, column, toggle, extend);
				if (column > 0 && column < 4) {
					table.editCellAt(row, column);
					table.transferFocus();
				}
			}
		};
		table.setAutoCreateRowSorter(true);

		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 2;
		contentPane.add(new JScrollPane(table), gbc_table);
		// contentPane.add(table, gbc_table);
//		this.updateFactionAndUnitLists();
	}

	public JTable getTable() {
		return table;
	}

	/**
	 * Opens a dialog to create a new unit with the name specified by user
	 */
	private void createNewUnit() {
		// TODO Auto-generated method stub
		String name = JOptionPane.showInputDialog("Введите название нового шаблона:");
		controller.createUnit(name);
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

		/*
		 * TableColumn col2 = table.getColumnModel().getColumn(2);
		 * col2.setCellEditor(new AutocompleteEditor());
		 */
	}

	public JButton getBtnSave() {
		return btnSave;
	}
	
	public void displayUnit(Unit unit) {
		if (model == null) {
			model = new UnitModel(unit, controller);
		} else {
			model.setCurrentUnit(unit);
		}
	}

	public UnitModel getModel() {
		return model;
	}

	public void setModel(UnitModel model) {
		this.model = model;
		getTable().setModel(model);
		System.out.println("Model set, row count: " + model.getRowCount());
		updateColumnEditors();
	}

	public Controller getController() {
		return controller;
	}


}
