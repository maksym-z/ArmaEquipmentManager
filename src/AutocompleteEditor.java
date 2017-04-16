import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultCellEditor;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AutocompleteEditor extends DefaultCellEditor {

	private HashMap<String, ArrayList<String>> suggestionSets = new HashMap<>();

	public AutocompleteEditor() {
		super(new JTextField());
		this.suggestionSets.put("WEAPON", new ArrayList<String>());
		this.suggestionSets.get("WEAPON").add("rhs_weap_ak74m");
		this.suggestionSets.get("WEAPON").add("rhs_30Rnd_545x39_AK");
		this.suggestionSets.get("WEAPON").add("rhs_30Rnd_545x39_AK_no_tracers");
		this.suggestionSets.get("WEAPON").add("rhs_30Rnd_545x39_7N10_AK");
		this.suggestionSets.get("WEAPON").add("rhs_30Rnd_545x39_7N22_AK");
		this.suggestionSets.get("WEAPON").add("hlc_45Rnd_545x39_t_rpk");
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		JTextField field = (JTextField) super.getTableCellEditorComponent(table, value, isSelected, row, column);
/*		JList autocompletePanel = new JList();
		table.add(autocompletePanel);
		autocompletePanel.setLocation(field.getLocation().x, field.getLocation().y-field.getHeight());
		autocompletePanel.setSize(field.getWidth(), 50);
		autocompletePanel.setBackground(Color.RED);
		autocompletePanel.setVisible(true);
		field.getDocument().addDocumentListener(new DocumentListener(){
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				updateProposals();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				updateProposals();
				System.out.println(field.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				updateProposals();
			}});

		if (column == 2) {
			List<String> suggestions = this.getSuggestions(column, field.getText(), table.getValueAt(row, column - 1));
		}
*/
		return field;
	}

	protected void updateProposals() {
		// TODO Auto-generated method stub
		
	}

	private List<String> getSuggestions(int column, Object value, Object value2) {
		// TODO Auto-generated method stub
		System.out.println(column + " " + value.toString() + " " + value2.toString());
		System.out.println(this.suggestionSets.get(value2));
		if (this.suggestionSets.get(value2.toString().toUpperCase())!=null) {
			return this.suggestionSets.get(value2.toString().toUpperCase()).stream().filter(x -> x.startsWith(value.toString())).collect(Collectors.toList());
		} else {
			return new ArrayList<String>();
		}
	}
}