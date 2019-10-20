const baseCurd = {
  handleView:function(row) {
    this.dialogFormVisible = true;
    getObj(row.id).then(response => {
      this.form = response.data;
      this.dialogFormVisible = true;
      this.dialogStatus = 'query';
    });
    this.dialogFormVisible = true;
    this.dialogStatus = 'query';
  }
}
export default baseCurd
