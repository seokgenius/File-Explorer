import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class SearchTableModel implements TableModel{
	private String[] headers = { "이름", "수정한 날짜", "유형", "크기" };
	
	private ArrayList<String> name;
	private ArrayList<String> date;
	private ArrayList<String> type;
	private ArrayList<String> size;
	SearchTableModel(ArrayList<String> x,ArrayList<String> y,ArrayList<String> z,
			ArrayList<String> w){
		this.name = x;
		this.date = y;
		this.type = z;
		this.size = w;
		
		
		
	}

	
	
	public void addTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		return Object.class;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.headers.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		return this.headers[columnIndex];
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.name.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
		if (columnIndex == 0)
			return name.get(rowIndex);
		if (columnIndex == 1)
			return date.get(rowIndex);
		if (columnIndex == 2) 
			return type.get(rowIndex);
		
		if (columnIndex == 3)	
			return size.get(rowIndex);
		return "오류";
		
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
	}

}
