/*    */ import java.io.File;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ 
/*    */ public class KimWrappedFile
/*    */ {
/*    */   private File file;
/*    */   private List<KimWrappedFile> children;
/*    */   private List<KimWrappedFile> subdir;
/*    */ 
/*    */   KimWrappedFile(String fileName)
/*    */   {
/* 14 */     this(new File(fileName));
/*    */   }
/*    */ 
/*    */   private KimWrappedFile(File file) {
/* 18 */     this.file = file;
/*    */   }
/*    */ 
/*    */   boolean isDirectory() {
/* 22 */     return this.file.isDirectory();
/*    */   }
/*    */ 
/*    */   List<KimWrappedFile> getChildren() {
/* 26 */     if ((isDirectory()) && (this.children == null)) {
/* 27 */       this.children = new ArrayList();
/* 28 */       String[] list = this.file.list();
/* 29 */       if (list == null) {
/* 30 */         return this.children;
/*    */       }
/* 32 */       ArrayList onlyFiles = new ArrayList();
/*    */ 
/* 34 */       for (int i = 0; i < list.length; i++) {
/* 35 */         File aChild = new File(this.file, list[i]);
/* 36 */         KimWrappedFile aChildWrapper = new KimWrappedFile(aChild);
/* 37 */         if (aChildWrapper.isDirectory())
/* 38 */           this.children.add(aChildWrapper);
/*    */         else {
/* 40 */           onlyFiles.add(aChildWrapper);
/*    */         }
/*    */       }
/* 43 */       this.children.addAll(onlyFiles);
/*    */     }
/* 45 */     return this.children;
/*    */   }
/*    */ 
/*    */   List<KimWrappedFile> getSubdirectory() {
/* 49 */     if ((isDirectory()) && (this.subdir == null)) {
/* 50 */       this.subdir = new ArrayList();
/*    */ 
/* 52 */       int c = getChildCount();
/* 53 */       for (int i = 0; i < c; i++) {
/* 54 */         KimWrappedFile child = (KimWrappedFile)this.children.get(i);
/* 55 */         if (child.isDirectory()) {
/* 56 */           this.subdir.add(child);
/*    */         }
/*    */       }
/*    */     }
/* 60 */     return this.subdir;
/*    */   }
/*    */ 
/*    */   private int getChildCount() {
/* 64 */     if (this.children == null) {
/* 65 */       getChildren();
/*    */     }
/* 67 */     return this.children.size();
/*    */   }
/*    */ 
/*    */   int getSubdirectoryCount() {
/* 71 */     if (this.subdir == null) {
/* 72 */       getSubdirectory();
/*    */     }
/* 74 */     return this.subdir.size();
/*    */   }
/*    */ 
/*    */   String getName() {
/* 78 */     return this.file.getName();
/*    */   }
/*    */ 
/*    */   long getSize() {
/* 82 */     return this.file.length();
/*    */   }
/*    */ 
/*    */   String getDate() {
/* 86 */     Date d = new Date(this.file.lastModified());
/* 87 */     SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd a h:mm");
/* 88 */     return f.format(d);
/*    */   }
/*    */ 
/*    */   String getPath() {
/* 92 */     return this.file.getAbsolutePath();
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 96 */     return getName();
/*    */   }
/*    */ }

/* Location:           C:\Users\dajs1\Desktop\2016-1\자바\Explorer\
 * Qualified Name:     WrappedFile
 * JD-Core Version:    0.6.0
 */