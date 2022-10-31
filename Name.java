class Name extends BaseToken {
  String id;
  Expr index;
  public Name(String id) {
    this.id = id;
    this.index = null;
  }
  public Name(String id, Expr e) {
    this.id = id;
    this.index = e;
  }
  public String toString() {
    return id + ( hasIndex() ? "[" + index.toString() + "]" : "" );
  }

  private boolean hasIndex() {
    return index != null;
  }
}
