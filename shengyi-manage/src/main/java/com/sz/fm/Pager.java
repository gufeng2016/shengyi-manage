package com.sz.fm;


public class Pager {

    private int current;  // 当前页码
    private int size;     // 每页记录
    private int record;   // 总记录数
    
    public Pager() {
        this(1, 10, 0);
    }
    
    public Pager(int size, int record) {
        this(1, size, record);
    }
    
    public Pager(int current, int size, int record) {
        this.current = current;
        this.size = size;
        this.record = record;
    }
    
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public int getRecord() {
        return record;
    }
    public void setRecord(int record) {
        this.record = record;
    }
    public int getCurrent() {
        return current;
    }
    public void setCurrent(int current) {
        this.current = current;
    }
    public int getPageCount() {
        if(record == 0)
            return 1;
        return record / size + (record % size == 0 ? 0 : 1);
    }
    public boolean getBefore(){
        if(current <= 1)
            return false;
        return true;
    }
    public boolean getNext(){
        if(getPageCount() <= 1 || getPageCount() <= current)
            return false;
        return true;
    }
}
