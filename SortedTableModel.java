import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class SortedTableModel implements TableModel{
	SortedData[] sort;
	String[] headers = {"이름", "수정한 날짜", "유형", "크기"};
	
	SortedTableModel(SortedData[] x){
		this.sort = x;
		
	}
	

	
	@Override
	public void addTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		// TODO Auto-generated method stub
		return Object.class;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.headers.length;
	}

	@Override
	public String getColumnName(int arg0) {
		// TODO Auto-generated method stub
		return this.headers[arg0];
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return sort.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		
		if (col == 0)
			return sort[row].name;
		if (col == 1)
			return sort[row].date;
		if (col == 2) 
			return sort[row].type;
		
		if (col == 3)	
			return sort[row].size;
		
		
		
		return "오류";
		
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}

