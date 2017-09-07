/*    */ import java.util.List;
/*    */ import javax.swing.event.TableModelListener;
/*    */ import javax.swing.table.TableModel;
/*    */ 
/*    */ class HongTable
/*    */   implements TableModel
/*    */ {
/*  8 */   private String[] headers = { "이름", "수정한 날짜", "유형", "크기" };
/*    */   private KimWrappedFile currentDir;
/*    */   private List<KimWrappedFile> children;
/*    */ 
/*    */   HongTable(KimWrappedFile currentDir)
/*    */   {
/* 14 */     setCurrentDir(currentDir);
/*    */   }
/*    */ 
/*    */   void setCurrentDir(KimWrappedFile currentDir) {
/* 18 */     this.currentDir = currentDir;
/* 19 */     this.children = this.currentDir.getChildren();
/*    */   }
/*    */ 
/*    */   KimWrappedFile getCurrentDir() {
/* 23 */     return this.currentDir;
/*    */   }
/*    */ 
/*    */   public void addTableModelListener(TableModelListener l)
/*    */   {
/*    */   }
/*    */ 
/*    */   public Class<?> getColumnClass(int columnIndex) {
/* 31 */     return Object.class;
/*    */   }
/*    */ 
/*    */   public int getColumnCount()
/*    */   {
/* 36 */     return this.headers.length;
/*    */   }
/*    */ 
/*    */   public String getColumnName(int column)
/*    */   {
/* 41 */     return this.headers[column];
/*    */   }
/*    */ 
/*    */   public int getRowCount()
/*    */   {
/* 46 */     return this.children.size();
/*    */   }
/*    */ 
/*    */   public Object getValueAt(int rowIndex, int columnIndex)
/*    */   {
/* 51 */     KimWrappedFile fileWrapper = (KimWrappedFile)this.children.get(rowIndex);
/*    */ 
/* 53 */     if (columnIndex == 0)
/* 54 */       return fileWrapper.getName();
/* 55 */     if (columnIndex == 1)
/* 56 */       return fileWrapper.getDate();
/* 57 */     if (columnIndex == 2) {
/* 58 */       if (fileWrapper.isDirectory()) {
/* 59 */         return "파일 폴더";
/*    */       }
/* 61 */       String fileName = fileWrapper.getName();
/* 62 */       int dotIndex = fileName.lastIndexOf('.');
/* 63 */       if (dotIndex > -1) {
/* 64 */         String extension = fileName.substring(dotIndex + 1);
/* 65 */         return extension.toUpperCase() + " 파일";
/*    */       }
/*    */ 
/* 68 */       return "파일";
/*    */     }
/*    */ 
/* 71 */     if (!fileWrapper.isDirectory()) {
/* 72 */       return String.format("%,d", new Object[] { Long.valueOf((long)Math.ceil(fileWrapper.getSize() / 1024.0D)) }) + "KB";
/*    */     }
/* 74 */     return "";
/*    */   }
/*    */ 
/*    */   public boolean isCellEditable(int rowIndex, int columnIndex)
/*    */   {
/* 80 */     return false;
/*    */   }
/*    */ 
/*    */   public void removeTableModelListener(TableModelListener l)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void setValueAt(Object aValue, int rowIndex, int columnIndex)
/*    */   {
				
				
/*    */   }
/*    */ }

/* Location:           C:\Users\dajs1\Desktop\2016-1\자바\Explorer\
 * Qualified Name:     FileSystemTableModel
 * JD-Core Version:    0.6.0
 */