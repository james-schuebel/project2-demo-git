class Program extends BaseToken {
  private Expr expr;
  //Constructor
  public Program(Expr e) {
    expr = e;
  }

  public String toString(int t) {
    return "Program:\n" + expr.toString(t+1) + "\n";
  }
}
