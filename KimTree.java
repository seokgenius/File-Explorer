/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.swing.event.TreeModelListener;
/*    */ import javax.swing.tree.TreeModel;
/*    */ import javax.swing.tree.TreePath;
/*    */ 
/*    */ class KimTree
/*    */   implements TreeModel
/*    */ {
/*    */   private KimWrappedFile root;
/*    */   private List<TreeModelListener> listeners;
/*    */ 
/*    */   KimTree(KimWrappedFile root)
/*    */   {
/* 14 */     this.root = root;
/* 15 */     this.listeners = new ArrayList();
/*    */   }
/*    */ 
/*    */   public void addTreeModelListener(TreeModelListener l)
/*    */   {
/* 20 */     if ((l != null) && (!this.listeners.contains(l)))
/* 21 */       this.listeners.add(l);
/*    */   }
/*    */ 
/*    */   public Object getChild(Object parent, int index)
/*    */   {
/* 26 */     Object child = null;
/*    */ 
/* 28 */     if ((parent instanceof KimWrappedFile)) {
/* 29 */       List children = ((KimWrappedFile)parent).getSubdirectory();
/*    */ 
/* 31 */       if ((children != null) && 
/* 32 */         (index < children.size()))
/* 33 */         child = children.get(index);
/*    */     }
/* 35 */     return child;
/*    */   }
/*    */ 
/*    */   public int getChildCount(Object parent)
/*    */   {
/* 40 */     int count = 0;
/*    */ 
/* 42 */     if ((parent instanceof KimWrappedFile)) {
/* 43 */       count = ((KimWrappedFile)parent).getSubdirectoryCount();
/*    */     }
/* 45 */     return count;
/*    */   }
/*    */ 
/*    */   public int getIndexOfChild(Object parent, Object child)
/*    */   {
/* 50 */     int index = -1;
/*    */ 
/* 52 */     if ((parent instanceof KimWrappedFile)) {
/* 53 */       List children = ((KimWrappedFile)parent).getSubdirectory();
/*    */ 
/* 55 */       if (children != null) {
/* 56 */         index = children.indexOf(child);
/*    */       }
/*    */     }
/* 59 */     return index;
/*    */   }
/*    */ 
/*    */   public Object getRoot()
/*    */   {
/* 64 */     return this.root;
/*    */   }
/*    */ 
/*    */   public boolean isLeaf(Object node)
/*    */   {
/* 70 */     return !((KimWrappedFile)node).isDirectory();
/*    */   }
/*    */ 
/*    */   public void removeTreeModelListener(TreeModelListener l)
/*    */   {
/* 77 */     this.listeners.remove(l);
/*    */   }
/*    */ 
/*    */   public void valueForPathChanged(TreePath path, Object newValue)
/*    */   {
/*    */   }
/*    */ }

/* Location:           C:\Users\dajs1\Desktop\2016-1\자바\Explorer\
 * Qualified Name:     FileSystemTreeModel
 * JD-Core Version:    0.6.0
 */