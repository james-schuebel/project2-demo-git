import java.util.ArrayList;

class Stmt extends BaseToken {
  int typeNumber;
  boolean isLoop, hasSemi;
  ArrayList<Fielddecl> fielddecls;
  ArrayList<Stmt> stmts;
  ArrayList funclist;
  ArrayList<Expr> args;
  Expr expr;
  Stmt stmt, elsestmt;
  Name name;
  String id;
  String unaryOp;
  
  public Stmt(Expr e, Stmt st, Stmt elsest) { // PROD 1 - if
    expr = e;
    stmt = st;
    elsestmt = elsest;
    typeNumber = 0;
  }
  public Stmt(String id, ArrayList<Expr> ag, boolean func) { // PROD 8 - id(args)
    this.id = id;
    args = ag;
    typeNumber = 1;
  }
  public Stmt(ArrayList<Fielddecl> fs, ArrayList<Stmt> sts, boolean semi) { // PROD 12 - block
    fielddecls = fs;
    stmts = sts;
    hasSemi = semi;
    typeNumber = 2;
  }
  public Stmt(Expr e, Stmt st) { // PROD 2 - while
    expr = e;
    stmt = st;
    typeNumber = 3;
  }
  public Stmt(Name n, Expr e) { // PROD 3 - assign
    name = n;
    expr = e;
    typeNumber = 4;
  }
  public Stmt(String func, ArrayList lst) { // PROD 4,5,6 - read, print, printline
    id = func;
    funclist = lst;
    typeNumber = ( func == "read" ? 5 : ( func == "print" ? 6 : 7 ) );
  }
  public Stmt(Name n, String unary) { // PROD 10,11 - INC and DEC
    name = n;
    unaryOp = unary;
    typeNumber = 8;
  }
  public Stmt(Expr e) { // PROD 9 - return expr
    expr = e;
    typeNumber = 9;
  }
  public Stmt(String id) { // PROD 7 - id()
    this.id = id;
    typeNumber = 10;
  }
  public Stmt() { // PROD 8 - return 
    typeNumber = 11;
  }

  public String toString(int depth) {
    switch (this.typeNumber) {
      case 0:
        return getTabs(depth) +
          "if (" + expr.toString() + ")\n" +
          ( stmt.typeNumber == 2 ? stmt.toString(depth) : getTabs(depth) +"{\n" + stmt.toString(depth+1) + "\n"+ getTabs(depth) + "}" ) +
          ( elsestmt != null ? "\n" + getTabs(depth) + "else\n" + ( elsestmt.typeNumber == 2 ? elsestmt.toString(depth) : getTabs(depth) +"{\n" + elsestmt.toString(depth+1) + "\n"+ getTabs(depth) + "}") : "" );    
      case 1:
        String list = "";
        for (Expr e: args) {
          list += e.toString() + ", ";
        } list = list.substring(0, list.length() > 0 ? list.length() - 2 : 0);
        return getTabs(depth) + id + "(" + list + ");";
      case 2:
        String result = "";
        for (Fielddecl f: fielddecls) {
          result += f.toString(depth+1) + "\n";
        }
        for (Stmt st: stmts) {
          result += st.toString(depth+1) + "\n";
        }
        return getTabs(depth) + "{\n" + result + getTabs(depth) + "}";
      case 3:
        return getTabs(depth) +
          "while (" + expr.toString() + ")\n" + stmt.toString(stmt.typeNumber == 2 ? depth : depth+1) + "\n";
      case 4:
        return getTabs(depth) +
          name.toString() + " = " + expr.toString() + ";"; 
      case 5:
        list = "";
        for (Name n: (ArrayList<Name>)funclist) {
          list += n.toString() + ", ";
        } list = list.substring(0, list.length() > 0 ? list.length() - 2 : 0);
        return getTabs(depth) + id + "(" + list + ");";
      case 6:
        list = "";
        for (Expr e: (ArrayList<Expr>)funclist) {
          list += e.toString() + ", ";
        } list = list.substring(0, list.length() > 0 ? list.length() - 2 : 0);
        return getTabs(depth) + id + "(" + list + ");";
      case 7:
        list = "";
        for (Expr e: (ArrayList<Expr>)funclist) {
          list += e.toString() + ", ";
        } list = list.substring(0, list.length() > 0 ? list.length() - 2 : 0);
        return getTabs(depth) + id + "(" + list + ");";
      case 8:
        return getTabs(depth) + name.toString() + unaryOp + ";";
      case 9:
        return getTabs(depth) + "return " + expr.toString() + ";"; 
      case 10:
        return getTabs(depth) + id + "();";
      case 11:
        return getTabs(depth) + "return;";
      default:
        return "";
    }
  }

  public boolean isReturn() {
    return this.typeNumber == 9 | this.typeNumber == 11;
  }
}
