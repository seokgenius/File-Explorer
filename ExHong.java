import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.basic.BasicBorders;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ExHong extends JFrame implements TreeSelectionListener{
	
	private KimWrappedFile root;
	private JTree tree;
	private JTable table;
	private KimTree treemodel;
	private HongTable tablemodel;
	
	private SearchTableModel tablemodel2;
	private SortedTableModel tablemodel3;
	
	private JSplitPane sp1;
	private JSplitPane sp2;
	private JScrollPane sc1;
	private JScrollPane sc2;
	private JScrollPane sc3;
	private JTextArea ta;
	private JTabbedPane tp;
	
	
	private JMenuBar menubar;
	private JMenu menu1;
	private JMenu menu2;
	private JTextField search;
	private JLabel searchlb;
	private int actionCount=0;
	JMenuItem item1 = new JMenuItem("open");
	JMenuItem item2 = new JMenuItem("save");
	
	JMenuItem item3 = new JMenuItem("닫기");
	
	
	ExHong(KimWrappedFile root){
		this.root = root;
		
		setSize(1000,800);
		setTitle("Explorer");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		treemodel = new KimTree(root);
		tablemodel = new HongTable(root);
		tree= new JTree(treemodel);
		tree.addTreeSelectionListener(this);
		table = new JTable(tablemodel);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = ExHong.this.table.getSelectedRow();
					String fileName = ExHong.this.table.getValueAt(row, 0).toString();
					String path = ExHong.this.tablemodel.getCurrentDir().getPath();
					File file = new File(path, fileName);
					String text = ExHong.this.openFile(file);
					if (text != null) {
						ExHong.this.tp.setTitleAt(0, file.getAbsolutePath());
						ExHong.this.ta.setText(text);
					}
				}
			}
		});
		
		
		sp1 = new JSplitPane(1);
		sp2 = new JSplitPane(0);
		ta = new JTextArea();
		tp = new JTabbedPane(1);
		
		sc1 = new JScrollPane(tree);
		sc2 = new JScrollPane(table);
		sc3 = new JScrollPane(tp);
		tp.addTab("", ta);
		
		
		menu1 = new JMenu("file");
		
		
		
		
		menu1.add(item1);
		menu1.add(item2);
		menu1.addSeparator();
		
		
		
		menu1.addSeparator();
		menu1.add(item3);
		
		search = new JTextField(10);
		searchlb = new JLabel("search : ");
		menubar = new JMenuBar();
		
		
		
		
		JButton bt = new JButton("정렬");
		bt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ExHong.this.actionCount ++;
				
				SortedData[] datatmp = new SortedData[table.getRowCount()];
				
			
				
				for(int i=0; i< datatmp.length; i++)
					datatmp[i] = new SortedData();
				
				for(int i=0; i < datatmp.length; i++){
					
					datatmp[i].name = ((String)table.getValueAt(i,0));
					datatmp[i].date = ((String)table.getValueAt(i,1));
					datatmp[i].type = ((String)table.getValueAt(i,2));
					datatmp[i].size = ((String)table.getValueAt(i,3));
					
					
					
					
				
				}
				
				class Compa implements Comparator{

					@Override
					public int compare(Object arg0, Object arg1) {
						// TODO Auto-generated method stub
						if((ExHong.this.actionCount %2) ==1)
							return (((SortedData)arg0).name).compareTo(((SortedData)arg1).name);
						else
							return (((SortedData)arg1).name).compareTo(((SortedData)arg0).name);
					}
					
				}
				Arrays.sort(datatmp, new Compa());
				
				
				tablemodel3 = new SortedTableModel(datatmp);
				table.setModel(tablemodel3);
				table.updateUI();
				
				
			}
			
		});
		menubar.add(menu1);
		
		
		menubar.add(searchlb);
		menubar.add(search);
		
		menubar.add(bt);
		
		
		search.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String tmp1 = search.getText();
				String nameTmp;
				String dateTmp;
				String typeTmp;
				String sizeTmp;
				ArrayList<String> name = new ArrayList();
				ArrayList<String> date = new ArrayList();
				ArrayList<String> type = new ArrayList();
				ArrayList<String> size = new ArrayList();
				
				for(int i=0; i < tablemodel.getRowCount(); i++){
					nameTmp = ((String)tablemodel.getValueAt(i,0));
					dateTmp = ((String)tablemodel.getValueAt(i,1));
					typeTmp = ((String)tablemodel.getValueAt(i,2));
					sizeTmp = ((String)tablemodel.getValueAt(i,3));
					if(nameTmp.contains(tmp1)){
						name.add(nameTmp);
						date.add(dateTmp);
						type.add(typeTmp);
						size.add(sizeTmp);
					}
				
				}
				
				tablemodel2 = new SearchTableModel(name,date,type,size);
				table.setModel(tablemodel2);
				table.updateUI();
			}
				
			
			
		});
		
		setJMenuBar(menubar);
		
		setContentPane(sp1);
		sp1.setLeftComponent(sc1);
		sp1.setRightComponent(sp2);
		sp2.setTopComponent(sc2);
		sp2.setBottomComponent(sc3);
		
		
		
		
		setVisible(true);
	}
	private String openFile(File file) {
		StringBuffer text = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String line = null;
			
			while ((line = br.readLine()) != null) {
				text.append(line + '\n');
			}
			
			br.close();
			
			if(menu1.getMenuComponentCount() >=10){
				menu1.remove(3);
				JMenuItem history = new JMenuItem(file.getName());
				menu1.add(history,menu1.getMenuComponentCount()-2);
				history.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						String text = ExHong.this.historyOpen(file);
						if (text != null) {
							ExHong.this.tp.setTitleAt(0, file.getAbsolutePath());
							ExHong.this.ta.setText(text);
						}
						
					}
					
				});
			}
			else{
				
				JMenuItem history2 = new JMenuItem(file.getName());
				menu1.add(history2,menu1.getMenuComponentCount()-2);
				history2.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String text = ExHong.this.historyOpen(file);
						if (text != null) {
							ExHong.this.tp.setTitleAt(0, file.getAbsolutePath());
							ExHong.this.ta.setText(text);
						}
					}
					
				});
			}
			
			
			
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "텍스트 파일이 아닙니다.", "Message", 0);
			return null;
		}
		
		return text.toString();
	}
	 private String historyOpen(File file) {
		 StringBuffer text = new StringBuffer();
		 try {
			 BufferedReader br = new BufferedReader(new FileReader(file));
			 
			 String line = null;
			 
			 while ((line = br.readLine()) != null) {
				 text.append(line + '\n');
			 }
			 
			 br.close();
		 } catch (IOException e) {
			 JOptionPane.showMessageDialog(this, "텍스트 파일이 아닙니다.", "Message", 0);
			 return null;
		 }
		 
		 return text.toString();
	 }

	
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		Object o = ExHong.this.tree.getLastSelectedPathComponent();
		if ((o instanceof KimWrappedFile)) {
			KimWrappedFile currentDir = (KimWrappedFile)o;
			this.tablemodel.setCurrentDir(currentDir);
			this.table.setModel(tablemodel);
			this.table.updateUI();
		}
		
	}
	
	
	public static void main(String[] args) {
		new ExHong(new KimWrappedFile("C:/"));

	}

}
class SortedData {
	public String name;
	public String date;
	public String type;
	public String size;
	
}
