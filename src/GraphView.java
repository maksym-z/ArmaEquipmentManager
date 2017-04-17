import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGraphModel.mxGeometryChange;
import com.mxgraph.model.mxICell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.handler.mxConnectionHandler;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.view.mxGraph;

public class GraphView extends JFrame {

	private mxGraph graph;

	private JPanel contentPane;
	private Controller controller;
	private Faction currentFaction;
	private HashMap<String, Object> vertices;
	private int rowCount = 0;

	/**
	 * Create the frame.
	 * 
	 * @param controller
	 */
	public GraphView(Controller controller) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 603, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		this.controller = controller;
		repopulateGraph();
	}

	public void repopulateGraph() {
		graph = new mxGraph();
		vertices = new HashMap<>();
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().removeAll();
		getContentPane().add(graphComponent);
		graphComponent.getGraphControl().addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {
				Object cell = graphComponent.getCellAt(e.getX(), e.getY());
				if (cell != null && graph.getModel().isVertex(cell)) {
					controller.setSelectedUnit(graph.getLabel(cell));
					System.out.println("cell=" + graph.getLabel(cell));
				}
			}
		});

		graphComponent.getConnectionHandler().addListener(mxEvent.CONNECT, new mxIEventListener() {
			public void invoke(Object sender, mxEventObject evt) {
				if (evt.getProperty("cell") instanceof mxCell) {
					mxCell edge = (mxCell) evt.getProperty("cell");
						mxICell target = edge.getTarget();
						mxICell source = edge.getSource();
						ArmaEquip item = new ArmaEquip();
						String classname = graph.getLabel(target);
						item.classnames.add(classname);
						item.addableType = AddableType.KIT;
						item.qty = 1;
						controller.getCurrentFaction().getUnit(graph.getLabel(source)).addItem(item);
						controller.setSelectedUnit(graph.getLabel(source));
					System.out.println("edge=" + evt.getProperty("cell"));
				}
				
			}
		});
	}

	public void refreshGraph(Faction faction) {
		// graph = new mxGraph();
		if (currentFaction == null || (faction != currentFaction)) {
			repopulateGraph();
		}
		Object parent = graph.getDefaultParent();
		graph.getModel().beginUpdate();
		try {
			for (String unitName : faction.getUnits().keySet()) {
				addUnit(unitName);
			}
			for (String unitName : vertices.keySet()) {
				System.out.println("Checking edges from " + unitName);
				System.out.println(faction.getUnit(unitName).getChildren().size());
				for (Unit v : faction.getUnit(unitName).getChildren()) {
					if (v != null) {
						System.out.println("Checking edges to " + v.getName());
						if (graph.getEdges(vertices.get(unitName), vertices.get(v.getName())).length == 0) {
							graph.insertEdge(parent, null, "", vertices.get(unitName), vertices.get(v.getName()));
							System.out.println("Adding edge " + unitName + " " + v.getName());
						}
					} else {
						System.out.println("Wrong child of " + unitName);
					}
				}
			}
			// graph.insertEdge(parent, null, "Edge", v1, v2);
		} finally {
			graph.getModel().endUpdate();
		}
	}

	public void addUnit(String unitName) {
		int column = 100;
		Faction faction = controller.getCurrentFaction();
		Object parent = graph.getDefaultParent();
		if (!vertices.containsKey(unitName)) {
			faction.getUnit(unitName).updateChildren();
			faction.getUnit(unitName).updateParentInfo();
			if (faction.getUnit(unitName).getParents().size() > 0
					&& faction.getUnit(unitName).getChildren().size() > 0) {
				if (faction.getUnit(unitName).getParents().size() > faction.getUnit(unitName).getChildren()
						.size()) {
					column = 300;
				} else {
					column = 150;
				}
			} else if (faction.getUnit(unitName).getParents().size() > 0) {
				column = 450; // TODO: THIS IS A PROTOTYPE
			} else if (faction.getUnit(unitName).getChildren().size() > 0) {
				column = 10;
			}
			rowCount++;
			vertices.put(unitName, graph.insertVertex(parent, null, unitName, column, rowCount * 20, 80, 30));
		}
	}

}
